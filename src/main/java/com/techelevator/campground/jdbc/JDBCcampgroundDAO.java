package com.techelevator.campground.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Campground;
import com.techelevator.CampgroundDAO;


public class JDBCcampgroundDAO implements CampgroundDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCcampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void displaycampgroundsByParkId(Long userChoice) {

		List<Campground> allCampGrounds = new ArrayList<Campground>() ;
		String sqlQueryForCampgrounds = "SELECT * FROM campground WHERE park_id = "+ userChoice;
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQueryForCampgrounds);
		
		while(results.next()) {
			allCampGrounds.add(mapRowSetCampground(results));
		}
		
		if (userChoice == 1L) {
			System.out.println("Acadia National Park Campgrounds: ");
		}
		
		if (userChoice == 2L) {
			System.out.println("Arches National Park Campgrounds: ");
		}
		
		else if (userChoice == 3L) {
			System.out.println("Cuyahoga Valley National Park Campgrounds: ");
		}
		
		for(Campground each : allCampGrounds ) {
			
			System.out.print(each.getCampgroundId() + "\t" + each.getCampgroundName()+ "\t\t" + intToMonth(each.getOpenDate())
			+ "\t" +intToMonth(each.getClosingDate()) + "\t" + each.getDailyFee()+"\n");
		}
	}
	
	private String intToMonth(String month) {
		String theMonth ="";
			switch (month) {
				case "01" :theMonth ="January";
				break;
				case "02" :theMonth ="February";
				break;
				case "03" :theMonth ="March";
				break;
				case "04" :theMonth ="April";
				break;
				case "05" :theMonth ="May";
				break;
				case "06" :theMonth ="June";
				break;
				case "07" :theMonth ="July";
				break;
				case "08" :theMonth ="August";
				break;
				case "09" :theMonth ="September";
				break;
				case "10" :theMonth ="October";
				break;
				case "11" :theMonth ="November";
				break;
				case "12" :theMonth ="December";
				break;
			}   
		return theMonth;
	}
	
	private Campground mapRowSetCampground(SqlRowSet results) {
		Campground thecampground = new Campground();
		thecampground.setCampgroundId(results.getLong("campground_id"));
		thecampground.setParkId(results.getLong("park_id"));
		thecampground.setCampgroundName(results.getString("name"));
		thecampground.setOpenDate(results.getString("open_from_mm"));
		thecampground.setClosingDate(results.getString("open_to_mm"));
		thecampground.setDailyFee(results.getBigDecimal("daily_fee"));
		return thecampground;
	}

}
