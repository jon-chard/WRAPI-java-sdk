package org.radioplayer.wrapi.sdk;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class UnitTestConfigurator {
	
	@Value( "${pemfile.path}")
	private static String PEM_FILE_WITH_PATH;

	
	/*
	 * Returns the pem file with path and the key to the calling tests
	 */
	static Stream<Arguments> getParams() {
        
        return Stream.of(
                Arguments.of(getFileAndPath(), getKeyFromFileAndPath())
        );
    }
	
	
	/*
	 * Returns the PEM file key from the filename
	 */
	private static final String getKeyFromFileAndPath() {
		
		String filepath = PEM_FILE_WITH_PATH;
		String[] units = filepath.split("\\/");
		String filename = units[units.length-1];
		
		String[] filenameUnits = filename.split("\\.");
		String pemKey = filenameUnits[0];
		
		return pemKey;
	}
	
	private static String getFileAndPath() {
		return PEM_FILE_WITH_PATH;
	}

}
