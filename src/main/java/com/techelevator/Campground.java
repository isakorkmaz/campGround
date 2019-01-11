package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Campground {

	private Long campgroundId;
	private Long parkId;
	private String campgroundName;
	private String openDate;
	private String closingDate;
	private BigDecimal dailyFee;
	
	public void setCampgroundId(Long campgroundId) {
		this.campgroundId = campgroundId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	public Long getCampgroundId() {
		return campgroundId;
	}
	
	public Long getParkId() {
		return parkId;
	}
	
	public String getCampgroundName() {
		return campgroundName;
	}
	
	public String getOpenDate() {
		return openDate;
	}
	
	public String getClosingDate() {
		return closingDate;
	}
	
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	
}
