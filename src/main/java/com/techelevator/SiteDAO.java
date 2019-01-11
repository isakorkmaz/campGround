package com.techelevator;

import java.time.LocalDate;


public interface SiteDAO {
	
	public Site chooseSite();
	public void getAvailableSites(Long campgroundId, LocalDate startDate, LocalDate endDate);
}
