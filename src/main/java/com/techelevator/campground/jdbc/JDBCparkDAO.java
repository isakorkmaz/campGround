package com.techelevator.campground.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Park;
import com.techelevator.ParkDAO;

public class JDBCparkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCparkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void getAllParks(){
		List<Park> allParks = new ArrayList<Park>() ;
		String sqlQueryForParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQueryForParks);
		while(results.next()) {
			allParks.add(mapRowSetPark(results));
		}
		int count = 1;
		for (Park each : allParks) {
			System.out.println(count + ")" + each.getParkName());
			count++;
		}
	}
	
	public void displayParkInfo(Long parkId) {
		String sqlQueryForParks = "SELECT * FROM park WHERE park_id = " + parkId;
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQueryForParks);
		while(results.next()) {
		Park thePark = mapRowSetPark(results);
		System.out.println("\n" + thePark.getParkName() + " National Park");
		System.out.println("Location:\t" + thePark.getParkLocation());
		System.out.println("Established:\t" + thePark.getEstablishDate());
		System.out.println("Area:\t" + thePark.getParkSize());
		System.out.println("Annual Visitors:\t" + thePark.getParkVisitors());
		System.out.println();
		System.out.println(thePark.getParkDescription());
		}
	}
	
	private Park mapRowSetPark(SqlRowSet results) {
		Park thePark = new Park();
		thePark.setParkId(results.getLong(1));
		thePark.setParkName(results.getString(2));
		thePark.setParkLocation(results.getString(3));
		thePark.setEstablishDate(results.getDate(4).toLocalDate());
		thePark.setParkSize(results.getLong(5));
		thePark.setParkVisitors(results.getLong(6));
		thePark.setParkDescription(results.getString(7));
		return thePark;
	}

}
