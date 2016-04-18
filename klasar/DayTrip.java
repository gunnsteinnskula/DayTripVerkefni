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
	 * DayTrip geymir allar uppl�singar um s�rstaka dagsfer�.
	 * startDate og endDate spanna �a� t�mabil sem fer�in er � gangi.
	 * Ekki er endilega fari� � fer�ir � �llum d�gum �essa t�mabils en �a� er teki� frekar fram � Trip.
	 * 
	 * rating er einkunn fer�arinnar fr� 0 upp � 5
	 * ratecount heldur utan  um hversu margir hafa gefi� fer�inni einkunn
	 * 
	 * @param name er nafn fer�arinnar
	 * @param length er hversu marga daga fer�in er
	 * @param type er hva�a tegund af fer� �etta er, t.d. sightseeing e�a hiking
	 * @param agency er nafn fer�askrifstofunnar sem rekur �essa fer�
	 * @param price er ver� � mann � fer�ina � �slenskum kr�num
	 * @param location er landhluti sem �essi fer� er �
	 * @param size er hversu margir komast � fer�ina
	 * @param extraInfo er aukauppl�singar um fer�ina
	 * @param startDate er hven�r �rs byrja� er a� fara � �essa fer�
	 * @param endDate er hven�r �rs h�tt er a� fara � �essa fer�
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
	 * @return lengd fer�ar
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * 
	 * @return tegund fer�arinnar
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * @return ver� fer�arinnar
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @return landhluti fer�ar
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * 
	 * @return st�r� fer�ar
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 
	 * @return einkunn fer�arinnar � bilinu 0 upp � 5
	 */
	public double getRating() {
		return rating;
	}
	
	/**
	 * 
	 * @return nafn fer�arinnar
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return aukauppl�singar um fer�ina
	 */
	public String getExtraInfo() {
		return extraInfo;
	}
	
	/**
	 * 
	 * @return nafn fer�askrifstofunnar sem heldur utan um �essa fer�
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
	 * reiknar �t n�ja einkunn fyrir �essa fer� og breytir
	 * rating � samr�mi vi� �a�
	 * @param newRating einkunn sem var a� berast
	 */
	public void rate(int newRating) {
		double a = rating*rateCounter + newRating;
		rating = a/(++rateCounter);
	}
	
	/**
	 * 
	 * @return byrjunardagsetning � strengjaforminu 'yyyy-mm-dd'
	 */
	public String getStartDate() {
		return formatter.format(dates[0]);
	}
	
	/**
	 * 
	 * @return lokadagsetningin � strengjaforminu 'yyyy-mm-dd'
	 */
	public String getEndDate() {
		return formatter.format(dates[1]);
	}
	
	/**
	 * B�tir inn trip fyrir �essa Daytrip � trips listann
	 * @param newTrip er fer�in sem � a� b�ta vi�
	 */
	public void addTrip(Trip newTrip) {
		trips.add(newTrip);
	}
	
}
