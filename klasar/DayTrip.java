package klasar;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayTrip {
	private int length;
	private String name;
	private String type;
	private String agency;
	private int price;
	private String location;
	private int size;
	private double rating;
	private String extraInfo;
	private List<Trip> trips;
	private Date[] dates;
	private int rateCounter;
	DateFormat formatter;
	
	/**
	 * DayTrip geymir allar upplýsingar um sérstaka dagsferð.
	 * startDate og endDate spanna það tímabil sem ferðin er í gangi.
	 * Ekki er endilega farið í ferðir á öllum dögum þessa tímabils en það er tekið frekar fram í Trip.
	 * 
	 * rating er einkunn ferðarinnar frá 0 upp í 5
	 * ratecount heldur utan  um hversu margir hafa gefið ferðinni einkunn
	 * 
	 * @param name er nafn ferðarinnar
	 * @param length er hversu marga daga ferðin er
	 * @param type er hvaða tegund af ferð þetta er, t.d. sightseeing eða hiking
	 * @param agency er nafn ferðaskrifstofunnar sem rekur þessa ferð
	 * @param price er verð á mann í ferðina í íslenskum krónum
	 * @param location er landhluti sem þessi ferð er á
	 * @param size er hversu margir komast í ferðina
	 * @param extraInfo er aukaupplýsingar um ferðina
	 * @param startDate er hvenær árs byrjað er að fara í þessa ferð
	 * @param endDate er hvenær árs hætt er að fara í þessa ferð
	 */
	public DayTrip(String name, int length, String type, String agency,
			int price, String location, int size, String extraInfo, 
			Date startDate, Date endDate) {
		this.name = name;
		this.length = length;
		this.type = type;
		this.agency = agency;
		this.price = price;
		this.location = location;
		this.size = size;
		this.extraInfo = extraInfo;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		dates = new Date[2];
		dates[0] = startDate;
		dates[1] = endDate;
		
		trips = new ArrayList<Trip>();
		rateCounter = 0;
		rating = 0;
	}
	
	/**
	 * @deprecated
	 * for debugging purposes
	 * @param name
	 * @return
	 */
	public static String getTripByName(String name) {
		return "A daytrip.";
	}
	
	/**
	 * 
	 * @return lengd ferðar
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * 
	 * @return tegund ferðarinnar
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * @return verð ferðarinnar
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @return landhluti ferðar
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * 
	 * @return stærð ferðar
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 
	 * @return einkunn ferðarinnar á bilinu 0 upp í 5
	 */
	public double getRating() {
		return rating;
	}
	
	/**
	 * 
	 * @return nafn ferðarinnar
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return aukaupplýsingar um ferðina
	 */
	public String getExtraInfo() {
		return extraInfo;
	}
	
	/**
	 * 
	 * @return nafn ferðaskrifstofunnar sem heldur utan um þessa ferð
	 */
	public String getTravelAgency() {
		return agency;
	}
	
	/**
	 * @deprecated
	 * Beil
	 * @return 0
	 */
	public int getTotalBookings() {
		return 0;
	}
	
	/**
	 * reiknar út nýja einkunn fyrir þessa ferð og breytir
	 * rating í samræmi við það
	 * @param newRating einkunn sem var að berast
	 */
	public void rate(int newRating) {
		double a = rating*rateCounter + newRating;
		rating = a/(++rateCounter);
	}
	
	/**
	 * 
	 * @return byrjunardagsetning á strengjaforminu 'yyyy-mm-dd'
	 */
	public String getStartDate() {
		return formatter.format(dates[0]);
	}
	
	/**
	 * 
	 * @return lokadagsetningin á strengjaforminu 'yyyy-mm-dd'
	 */
	public String getEndDate() {
		return formatter.format(dates[1]);
	}
	
	/**
	 * Bætir inn trip fyrir þessa Daytrip í trips listann
	 * @param newTrip er ferðin sem á að bæta við
	 */
	public void addTrip(Trip newTrip) {
		trips.add(newTrip);
	}
	
}
