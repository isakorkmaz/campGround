package com.techelevator;

public class Site {
	
	private Long siteId;
	private Long campgroundId;
	private Long siteNumber;
	private Long maxOccupancy;
	private boolean accessible;
	private Long maxRvLength;
	private boolean utilities;
	 
	public Long getSiteId() {
		return siteId;
	}
	
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	
	public Long getCampgroundId() {
		return campgroundId;
	}
	
	public void setCampgroundId(Long campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public Long getSiteNumber() {
		return siteNumber;
	}
	
	public void setSiteNumber(Long siteNumber) {
		this.siteNumber = siteNumber;
	}
	
	public Long getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public void setMaxOccupancy(Long maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
	public boolean isAccessible() {
		return accessible;
	}
	
	public void setAccessible(boolean accesible) {
		this.accessible = accesible;
	}
	
	public Long getMaxRvLength() {
		return maxRvLength;
	}
	
	public void setMaxRvLength(Long maxRvLength) {
		this.maxRvLength = maxRvLength;
	}
	
	public boolean isUtilities() {
		return utilities;
	}
	
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}

}
