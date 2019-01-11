package com.techelevator.campground.jdbc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Site;
import com.techelevator.SiteDAO;

public class JDBCsiteDAO implements SiteDAO{
	private JdbcTemplate jdbcTemplate;

	public JDBCsiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void getAvailableSites(Long campgroundId, LocalDate startDate, LocalDate endDate){
		List<Site> openSites = new ArrayList<Site>();
		String sqlQueryOpenSites = "SELECT * FROM site s WHERE campground_id = ? AND s.site_id NOT IN " +
								   "(SELECT s.site_id FROM site s JOIN reservation r ON r.site_id = s.site_id " +
								   "WHERE ? BETWEEN from_date AND to_date " +
				                   "OR ? BETWEEN from_date AND to_date) " + 
								   "ORDER BY s.site_id LIMIT 5"; 
	   
	   	SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQueryOpenSites, campgroundId, startDate, endDate);
		while(results.next()) {
			openSites.add(mapRowSetSites(results));
		}
		System.out.println("Site Id. \t Max Occup. \t Accessible? \t Max RV Length \t Utility \t Cost");
		for (Site e : openSites) {
			System.out.print(e.getSiteNumber() + " \t\t" + e.getMaxOccupancy() + " \t\t" + e.isAccessible()
				+ " \t\t" + e.getMaxRvLength() + " \t\t" + e.isUtilities() + " \t\t" + getCostOfSite(campgroundId) + "\n");
		}
	}
	

	@Override
	public Site chooseSite() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BigDecimal getCostOfSite(Long campgroundId) {
		String sqlCostQuery = "SELECT daily_fee FROM campground WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCostQuery, campgroundId);
		BigDecimal siteCost = BigDecimal.ZERO;
		while (results.next()) {
			siteCost = results.getBigDecimal("daily_fee");
		}
		return siteCost;
	};
	
	private Site mapRowSetSites(SqlRowSet results) {
		Site theSite = new Site();
		theSite.setSiteId(results.getLong("site_id"));
		theSite.setCampgroundId(results.getLong("campground_id"));
		theSite.setSiteNumber(results.getLong("site_number"));
		theSite.setMaxOccupancy(results.getLong("max_occupancy"));
		theSite.setAccessible(results.getBoolean("accessible"));
		theSite.setMaxRvLength(results.getLong("max_rv_length"));
		theSite.setUtilities(results.getBoolean("utilities"));
		return theSite;
	}

}
