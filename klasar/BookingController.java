package klasar;

import java.util.Date;

import look.BookingView;

public class BookingController {
	private int bookingNumber;
	private DatabaseConnection connection;
	private BookingView bookingView;
	private int groupSize;
	
	public BookingController(BookingView mockobject) {
		bookingNumber = 0;
		connection = new DatabaseConnection();
		bookingView = mockobject;
	}
	
	public int book(Trip trip) {
		if(trip==null) throw new IllegalArgumentException("Vantar trip");
		Date[] tripDates = trip.getDate();
		int tripID = connection.getTripID(trip.getDayTrip(), tripDates[0]);
		Tourist tourist = getTourist();
		while(!connection.book(tripID, tourist.getEmail(), bookingNumber, groupSize))
			System.out.println("Booking failed!");
		System.out.println(bookingNumber);
		return bookingNumber++;
	}
	
	public Tourist getTourist() {
		bookingView.setVisible(true);
		String touristEmail = bookingView.getInputEmail();
		String touristName = bookingView.getInputName();
		String touristCountry = bookingView.getInputCountry();
		int touristAge = bookingView.getInputAge();
		groupSize = bookingView.getInputGroupSize();
		if(touristEmail.contains("@")) {
			Tourist tourist = new Tourist(touristName, touristEmail, touristCountry, touristAge);
			if(connection.findTourist(touristEmail)) return connection.getTourist(touristEmail);
			else {
				while(!connection.addTourist(touristName, touristEmail, touristCountry, touristAge))
					System.out.println("Tourist not added");
				return tourist;			
			}
		}
		else {
			System.out.println("Ekki löglegt veffang, kv gunnsteinn");
			return null;
		}
	}
	
	
}
