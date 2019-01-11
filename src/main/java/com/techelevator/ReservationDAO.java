package com.techelevator;

import java.time.LocalDate;

public interface ReservationDAO {

	public void makeReservation(Long siteId, String name, LocalDate startDate, LocalDate endDate);
	public void displayReservationsNextThirtyDays(String choice);
}
