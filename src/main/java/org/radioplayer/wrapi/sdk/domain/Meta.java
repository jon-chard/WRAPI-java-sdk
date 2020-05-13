package org.radioplayer.wrapi.sdk.domain;

public class Meta {
	
	private boolean paginated;
	private boolean nesting;
	private Integer count;
	private String dataType;
	private boolean fromCache;
	private Long cacheExpiresAt;
	private int pageSize;
	
	private int country;
	private Long rpuid;
	
	private boolean firstPage;
	private boolean lastPage;
	private int totalPages;
	private int pageNumber;
	
	public boolean isPaginated() {
		return paginated;
	}
	
	public void setPaginated(boolean paginated) {
		this.paginated = paginated;
	}
	
	public boolean isNesting() {
		return nesting;
	}
	
	public void setNesting(boolean nesting) {
		this.nesting = nesting;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isFromCache() {
		return fromCache;
	}

	public void setFromCache(boolean fromCache) {
		this.fromCache = fromCache;
	}

	public Long getCacheExpiresAt() {
		return cacheExpiresAt;
	}

	public void setCacheExpiresAt(Long cacheExpiresAt) {
		this.cacheExpiresAt = cacheExpiresAt;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public Long getRpuid() {
		return rpuid;
	}

	public void setRpuid(Long rpuid) {
		this.rpuid = rpuid;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

}
