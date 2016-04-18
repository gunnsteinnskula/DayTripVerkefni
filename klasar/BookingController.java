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
	 * Smi�ur- bookingNumber er n�llstillt
	 * 
	 * �essi klasi me�h�ndlar b�kunaruppl�singar � fer�um. Hann talar vi� gagnagrunninn og kallar � pop-up glugga �egar a� �arf.
	 */
	public BookingController() {
		bookingNumber = 0;
		connection = new DatabaseConnection();
		bookingView = new Booking();
	}
	
	/**
	 * Eftir a� �kve�in fer� hefur veri� valin eru uppl�singar um b�karann fundnar og b�kunin sett inn � gagnagrunn.
	 * B�kunin er ekki framkv�md ef ekki er pl�ss fyrir h�pinn � fer�inni.
	 * @param trip er fer�in sem � a� b�ka �.
	 * @return b�kunarn�mer �essa t�rista � �essa fer�.
	 */
	public int book(Trip trip) {
		if(trip==null) throw new IllegalArgumentException("Vantar trip");
		Date[] tripDates = trip.getDate();
		int tripID = connection.getTripID(trip.getDayTrip(), tripDates[0]);
		Tourist tourist = getTourist();
		if(trip.getbook()+groupSize > trip.getSize()) {
			System.out.println("Fer�in er full.");
			return -1;
		}
		
		while(!connection.book(tripID, tourist.getEmail(), bookingNumber, groupSize))
			System.out.println("Booking failed!");
		trip.addBooked(groupSize);
		System.out.println(bookingNumber);
		
		return bookingNumber++;
	}
	
	/**
	 * Opnar pop-up glugga til �ess a� s� sem b�kar geti sett inn uppl�singar um b�kunina. 
	 * Nau�synlegar uppl�singar eru email og groupSize ef �essi t�risti er n� �egar inni � gagnagrunni
	 * Ef �essi t�risti hefur ekki b�ka� ��ur �� �arf a� gefa meiri uppl�singar.
	 * 
	 * Athuga� er s�rstaklega a� netfang innihaldi att-merki�. 
	 * 
	 * @return Tourist staki sem inniheldur alla uppl�singar um �ennan t�rista.
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
			System.out.println("Ekki l�glegt netfang, kv gunnsteinn");
			return null;
		}
	}
	
	public static void main(String[] args) {
		BookingController b = new BookingController();
		b.getTourist();
	}
}
