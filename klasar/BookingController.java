package klasar;

import java.util.Date;

public class BookingController {
	private int bookingNumber;
	private DatabaseConnection connection;
	private BookingView bookingView;
	private int groupSize;
	
	public BookingController() {
		bookingNumber = 0;
		connection = new DatabaseConnection();
		bookingView = new BookingView();
	}
	
	public int book(Trip trip) {
		Date[] tripDates = trip.getDate();
		int tripID = connection.getTripID(trip.getDayTrip(), tripDates[0]);
		Tourist tourist = getTourist();
		while(!connection.book(tripID, tourist.getEmail(), bookingNumber, groupSize))
			System.out.println("Booking failed!");
		return bookingNumber++;
	}
	
	public Tourist getTourist() {
		bookingView.show();
		String touristEmail = bookingView.getEmail();
		String touristName = bookingView.getName();
		String touristCountry = bookingView.getCountry();
		int touristAge = bookingView.getAge();
		groupSize = bookingView.getGroupSize();
		Tourist tourist = new Tourist(touristName, touristEmail, touristCountry, touristAge);
		if(connection.findTourist(touristEmail)) return connection.getTourist(touristEmail);
		else {
			while(!connection.addTourist(touristName, touristEmail, touristCountry, touristAge))
				System.out.println("Tourist not added");
			return tourist;			
		}
	}
	
}
