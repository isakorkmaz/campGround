package com.techelevator;

import java.time.LocalDate;

public class Park {
	
	private Long parkId;
	private String parkName;
	private String parkLocation;
	private LocalDate establishDate;
	private Long parkSize;
	private Long parkVisitors;
	
	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public void setParkLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}

	public void setEstablishDate(LocalDate establishDate) {
		this.establishDate = establishDate;
	}

	public void setParkSize(Long parkSize) {
		this.parkSize = parkSize;
	}

	public void setParkVisitors(Long parkVisitors) {
		this.parkVisitors = parkVisitors;
	}

	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}

	private String parkDescription;
	
	
	public Long getParkId() {
		return parkId;
	}
	
	public String getParkName() {
		return parkName;
	}
	
	public String getParkLocation() {
		return parkLocation;
	}
	
	public LocalDate getEstablishDate() {
		return establishDate;
	}
	
	public Long getParkSize() {
		return parkSize;
	}
	
	public Long getParkVisitors() {
		return parkVisitors;
	}
	
	public String getParkDescription() {
		return parkDescription;
	}
	
	
}
