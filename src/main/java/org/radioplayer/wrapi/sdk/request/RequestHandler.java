package org.radioplayer.wrapi.sdk.request;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import net.adamcin.httpsig.api.Algorithm;
import net.adamcin.httpsig.api.Authorization;
import net.adamcin.httpsig.api.Challenge;
import net.adamcin.httpsig.api.Constants;
import net.adamcin.httpsig.api.Key;
import net.adamcin.httpsig.api.KeyId;
import net.adamcin.httpsig.api.RequestContent;
import net.adamcin.httpsig.api.Signer;
import net.adamcin.httpsig.bouncycastle.PEMHelper;
import net.adamcin.httpsig.ssh.jce.UserFingerprintKeyId;

public class RequestHandler {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private String apiKey;
	private Signer signer;
	
	public RequestHandler(String pathToPem, String apiKey) throws IOException {
		signer = generateSigner(pathToPem, apiKey);
		this.apiKey = apiKey;
		
	}
	
	private Signer generateSigner(String pathToPem, String apiKey) throws IOException {
		
		File f = new File(pathToPem);
    	Key k = PEMHelper.readKey(f, new char[]{ '\0' });
    	KeyId keyId = new UserFingerprintKeyId(apiKey);

    	Signer signer = new Signer(k, keyId);
    	Challenge challenge = new Challenge("<preemptive>", Constants.DEFAULT_HEADERS, Arrays.asList( new Algorithm[] {Algorithm.RSA_SHA256} ));
    	signer.rotateKeys(challenge);
    	
    	return signer;
	}
	
	
	public HttpResponse signAndSubmitRequest(HttpUriRequest request) throws IOException {
		
		RequestContent.Builder requestContentBuilder = new RequestContent.Builder();   	
    	DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz");
    	requestContentBuilder.addHeader("date", df.format(new Date()));
    	
    	RequestContent requestContent = requestContentBuilder.build();
    	
		Authorization auth = signer.sign(requestContent);

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		
    	HttpClient client = httpClientBuilder.build();
    	
    	String sig = new StringBuilder().append("Signature keyId=")
    			.append(buildKeyString(apiKey))
    			.append(",algorithm=\"rsa-sha256\",signature=\"")
    			.append(auth.getSignature())
    			.append("\"").toString();
    	
    	request.addHeader("date", df.format(requestContent.getDateGMT()));
    	request.addHeader("Authorization", sig);
    	
    	if (logger.isDebugEnabled()) {
    		logger.debug("Authorization: " + sig);
    	}
    	
    	HttpResponse response = null;
    	HttpResponse empty = null;
    	
    	response = client.execute(request);
    	
    	if (logger.isDebugEnabled()) {
    		logger.debug("Status: " + response.getStatusLine());
    	}
    	
    	if (response.getStatusLine().getStatusCode() != 200) {
    		logger.error("The request failed. The remote service provided the following details - Status code " + response.getStatusLine().getStatusCode() + "; Message: " + response.getStatusLine().getReasonPhrase());
    		return empty;
    	}
    	
    	return response;
	}
	
	
	private String buildKeyString(String apiKey) {
		
		StringBuilder keyBuilder = new StringBuilder();
    	keyBuilder.append("\"");
    	keyBuilder.append(apiKey);
    	keyBuilder.append("\"");
    	
    	if (logger.isDebugEnabled()) {
    		logger.debug("apiKey: " + keyBuilder.toString());
    	}
    	
    	return keyBuilder.toString();
	}
	
}
