package com.techelevator;

import java.time.LocalDate;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.jdbc.JDBCcampgroundDAO;
import com.techelevator.campground.jdbc.JDBCparkDAO;
import com.techelevator.campground.jdbc.JDBCreservationDAO;
import com.techelevator.campground.jdbc.JDBCsiteDAO;

import com.techelevator.view.Menu;

public class CampgroundCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_PARKS = "Display All Parks";
	private static final String MAIN_MENU_OPTION_GET_INFO = "View Specific Park Info";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_PARKS, MAIN_MENU_OPTION_GET_INFO,
			MAIN_MENU_OPTION_QUIT };

	private static final String PARK_INFO_MENU_OPTION_VIEW_CAMPGROUNDS = "Display All Campgrounds";
	private static final String PARK_INFO_MENU_OPTION_VIEW_RESERVATIONS = "Search For Reservation";
	private static final String PARK_INFO_MENU_OPTION_RETURN_TO_PARKS = "Return To Previous Screen";
	private static final String[] PARK_INFO_MENU_OPTIONS = { PARK_INFO_MENU_OPTION_VIEW_CAMPGROUNDS,
			PARK_INFO_MENU_OPTION_VIEW_RESERVATIONS, PARK_INFO_MENU_OPTION_RETURN_TO_PARKS };
	
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		this.parkDAO = new JDBCparkDAO(datasource);
		 this.campgroundDAO = new JDBCcampgroundDAO(datasource);
		 this.siteDAO = new JDBCsiteDAO(datasource);
		 this.reservationDAO = new JDBCreservationDAO(datasource);	
	}

	public void run() {
		Menu menu = new Menu(System.in, System.out);
		boolean shouldLoop = true;
		while (shouldLoop) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_PARKS)) {
				System.out.println();
				parkDAO.getAllParks();
			} else if (choice.equals(MAIN_MENU_OPTION_GET_INFO)) {
				System.out.print("\n" + "Type the desired park number for more info: ");
				Scanner sca = new Scanner(System.in);
				Long userChoice = (sca.nextLong());
				parkDAO.displayParkInfo(userChoice);
				campgroundMenu(menu, userChoice);
				sca.close();
			} else if (choice.equals(MAIN_MENU_OPTION_QUIT)) {
				shouldLoop = false;
				
			}
		}
	}

	public void campgroundMenu(Menu menu, Long userChoice) {
		boolean shouldLoop2 = true;
		while (shouldLoop2) {
			String choice = (String) menu.getChoiceFromOptions(PARK_INFO_MENU_OPTIONS);

			if (choice.equals(PARK_INFO_MENU_OPTION_VIEW_CAMPGROUNDS)) {
				campgroundDAO.displaycampgroundsByParkId(userChoice);
			} else if (choice.equals(PARK_INFO_MENU_OPTION_VIEW_RESERVATIONS)) {
				availableReservationMenu();
			} else if (choice.equals(PARK_INFO_MENU_OPTION_RETURN_TO_PARKS)) {
				shouldLoop2 = false;
			}

		}
	}

	public void availableReservationMenu() {
		Scanner sca = new Scanner(System.in);
		System.out.println("\n1) Available Reservations");
		System.out.println("2) Return To Previous Screen");
		System.out.print("\n" +"Please choose an option >>> ");
		boolean shouldLoop3 = true;
		while(shouldLoop3) {
			String choice = sca.nextLine();
			if(choice.equals("1")) {
				System.out.println("1) Search For Available Sites at Desired Park");
				System.out.println("2) See All Reservations for the Next 30 days at Desired Park");
				String choice2 = sca.nextLine();
				if (choice2.equals("1")) {
					System.out.print("\nWhich Campground? (Corresponding Number) >>> ");
					Long campgroundId = Long.parseLong(sca.nextLine());
					System.out.print("What is the Arrival Date (yyyy-MM-dd format) >>> ");
					LocalDate startDate = LocalDate.parse(sca.nextLine());
					System.out.print("What is the Departure Date (yyyy-MM-dd format) >>> \n");
					LocalDate endDate = LocalDate.parse(sca.nextLine());
					siteDAO.getAvailableSites(campgroundId, startDate, endDate);
					reservationChoiceMenu(campgroundId, startDate, endDate);
					sca.close();
				}
				else if (choice2.equals("2")) {
					System.out.println("Enter The Corresponding Number of the Park to Search: ");
					String choice3 = sca.nextLine();
					reservationDAO.displayReservationsNextThirtyDays(choice3);
					sca.close();
				}
			} 

			else if(choice.equals("2")) {
		        shouldLoop3 = false;	  
			  }		  
		}
	}
	
	public void reservationChoiceMenu(Long campgroundId, LocalDate startDate, LocalDate endDate) {
		Scanner sca = new Scanner(System.in);
		boolean shouldLoop4 = true;
		System.out.print("\nWhich site should be reserved? >>> ");
		while (shouldLoop4) {
		Long siteId = Long.parseLong(sca.nextLine());
		if (siteId == 0) {
			shouldLoop4 = false;
		}
		else {
			System.out.print("What name should the reservation be made under? >>> ");
			String name = sca.nextLine();
			reservationDAO.makeReservation(siteId, name, startDate, endDate);
			sca.close();
			System.exit(0);
			}
		}
	}
}