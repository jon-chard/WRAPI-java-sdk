package org.radioplayer.wrapi.sdk.domain;


public class Recommendation {
	
	private Long rpuid;
	private String name;
	private String description;
	private String country;
	private MultiMedia[] multimedia;
	private LiveStreams[] liveStreams;
	private Bearer[] bearers;
	private SocialId[] socialIds;
	private String[] epgLanguages;
	private String alphanumericKey; 
	private Location location;
	private OnDemandStreams[] onDemandStreams;
	private Series series;
	private String href;
	private PhoneticInput[] phoneticInputs;
	private PhoneticOutput[] phoneticOutputs;
	private String type;
	private String[] factors;
	private Song song;
	private Show show;
	private String id;
	
	private InnerData[] innerdata;
	
	private Meta meta;

	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String[] getFactors() {
		return factors;
	}
	
	public void setFactors(String[] factors) {
		this.factors = factors;
	}

	public Long getRpuid() {
		return rpuid;
	}

	public void setRpuid(Long rpuid) {
		this.rpuid = rpuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public MultiMedia[] getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(MultiMedia[] multimedia) {
		this.multimedia = multimedia;
	}

	public LiveStreams[] getLiveStreams() {
		return liveStreams;
	}

	public void setLiveStreams(LiveStreams[] liveStreams) {
		this.liveStreams = liveStreams;
	}

	public Bearer[] getBearers() {
		return bearers;
	}

	public void setBearers(Bearer[] bearers) {
		this.bearers = bearers;
	}

	public SocialId[] getSocialIds() {
		return socialIds;
	}

	public void setSocialIds(SocialId[] socialIds) {
		this.socialIds = socialIds;
	}

	public String[] getEpgLanguages() {
		return epgLanguages;
	}

	public void setEpgLanguages(String[] epgLanguages) {
		this.epgLanguages = epgLanguages;
	}

	public String getAlphanumericKey() {
		return alphanumericKey;
	}

	public void setAlphanumericKey(String alphanumericKey) {
		this.alphanumericKey = alphanumericKey;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public OnDemandStreams[] getOnDemandStreams() {
		return onDemandStreams;
	}

	public void setOnDemandStreams(OnDemandStreams[] onDemandStreams) {
		this.onDemandStreams = onDemandStreams;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public PhoneticInput[] getPhoneticInputs() {
		return phoneticInputs;
	}

	public void setPhoneticInputs(PhoneticInput[] phoneticInputs) {
		this.phoneticInputs = phoneticInputs;
	}

	public PhoneticOutput[] getPhoneticOutputs() {
		return phoneticOutputs;
	}

	public void setPhoneticOutputs(PhoneticOutput[] phoneticOutputs) {
		this.phoneticOutputs = phoneticOutputs;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InnerData[] getInnerdata() {
		return innerdata;
	}

	public void setInnerdata(InnerData[] innerdata) {
		this.innerdata = innerdata;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
}
