package klasar;

import java.util.Date;

import look.Booking;
import look.BookingView;

public class BookingController {

	private int bookingNumber;
	private DatabaseConnection connection;
	private Booking bookingView;
	private int groupSize;
	
	/**
	 * Smiður- bookingNumber er núllstillt
	 * 
	 * Þessi klasi meðhöndlar bókunarupplýsingar á ferðum. Hann talar við gagnagrunninn og kallar á pop-up glugga þegar að þarf.
	 */
	public BookingController() {
		bookingNumber = 0;
		connection = new DatabaseConnection();
		bookingView = new Booking();
	}
	
	/**
	 * Eftir að ákveðin ferð hefur verið valin eru upplýsingar um bókarann fundnar og bókunin sett inn í gagnagrunn.
	 * Bókunin er ekki framkvæmd ef ekki er pláss fyrir hópinn í ferðinni.
	 * @param trip er ferðin sem á að bóka í.
	 * @return bókunarnúmer þessa túrista á þessa ferð.
	 */
	public int book(Trip trip) {
		if(trip==null) throw new IllegalArgumentException("Vantar trip");
		Date[] tripDates = trip.getDate();
		int tripID = connection.getTripID(trip.getDayTrip(), tripDates[0]);
		Tourist tourist = getTourist();
		if(trip.getbook()+groupSize > trip.getSize()) {
			System.out.println("Ferðin er full.");
			return -1;
		}
		
		while(!connection.book(tripID, tourist.getEmail(), bookingNumber, groupSize))
			System.out.println("Booking failed!");
		trip.addBooked(groupSize);
		System.out.println(bookingNumber);
		
		return bookingNumber++;
	}
	
	/**
	 * Opnar pop-up glugga til þess að sá sem bókar geti sett inn upplýsingar um bókunina. 
	 * Nauðsynlegar upplýsingar eru email og groupSize ef þessi túristi er nú þegar inni í gagnagrunni
	 * Ef þessi túristi hefur ekki bókað áður þá þarf að gefa meiri upplýsingar.
	 * 
	 * Athugað er sérstaklega að netfang innihaldi att-merkið. 
	 * 
	 * @return Tourist staki sem inniheldur alla upplýsingar um þennan túrista.
	 */
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
			System.out.println("Ekki löglegt netfang, kv gunnsteinn");
			return null;
		}
	}
	
	public static void main(String[] args) {
		BookingController b = new BookingController();
		b.getTourist();
	}
}
