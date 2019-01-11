package com.techelevator.campground.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Reservation;
import com.techelevator.ReservationDAO;

public class JDBCreservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCreservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void makeReservation(Long siteId, String name, LocalDate startDate, LocalDate endDate) {
		String sqlMakeReserve = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) "
								 + "VALUES (?, ?, ?, ?, ?, ?)";
		Long reserveID = getNextReservationId() + 1;
		jdbcTemplate.update(sqlMakeReserve, reserveID, siteId, name, startDate, endDate, LocalDate.now());
		System.out.println("The reservation has been made and the confirmation id is: " + reserveID);
	}
	
	private long getNextReservationId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT max(reservation_id) FROM reservation");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new reservation");
		}
	}
	
	public void displayReservationsNextThirtyDays(String choice) {
		List<Reservation> thirtyDayReservations = new ArrayList<Reservation>();
		String sqlGet30DayReservation = "SELECT s.site_id, from_date, to_date FROM reservation r " + 
										"JOIN site s ON r.site_id = s.site_id " +
										"JOIN campground c ON s.campground_id = c.campground_id WHERE s.campground_id = ? "
										+ "AND (from_date BETWEEN current_date AND (current_date + " + "30) "
										+ "OR (to_date BETWEEN current_date AND (current_date + " + "30)))";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGet30DayReservation, Integer.parseInt(choice));
		while (results.next()) {
			Reservation eachReservation = new Reservation();
			eachReservation.setSiteId(results.getLong("site_id"));
			eachReservation.setFromDate(results.getDate("from_date").toLocalDate());
			eachReservation.setToDate(results.getDate("to_date").toLocalDate());
			thirtyDayReservations.add(eachReservation);
		}
			System.out.println("Site No. \t From Date \t\t To Date");
			for (Reservation e : thirtyDayReservations) {
				System.out.print(e.getSiteId());
				System.out.print("\t\t" + e.getFromDate());
				System.out.print("\t\t" + e.getToDate());
				System.out.println("\n");
			}
	}
}