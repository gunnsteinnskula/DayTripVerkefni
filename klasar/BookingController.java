package klasar;

import java.util.Date;

import look.Booking;

public class BookingController {
	private int bookingNumber;
	private DatabaseConnection connection;
	private Booking bookingView;
	private int groupSize;
	
	public BookingController() {
		bookingNumber = 0;
		connection = new DatabaseConnection();
		bookingView = new Booking();
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
		bookingView.setVisible(true);
		String touristEmail = bookingView.getInputEmail();
		String touristName = bookingView.getInputName();
		String touristCountry = bookingView.getInputCountry();
		int touristAge = bookingView.getInputAge();
		groupSize = bookingView.getInputGroupSize();
		Tourist tourist = new Tourist(touristName, touristEmail, touristCountry, touristAge);
		if(connection.findTourist(touristEmail)) return connection.getTourist(touristEmail);
		else {
			while(!connection.addTourist(touristName, touristEmail, touristCountry, touristAge))
				System.out.println("Tourist not added");
			return tourist;			
		}
	}
	
	public static void main(String[] args) {
		BookingController booking = new BookingController();
		booking.getTourist();
	}
	
}
