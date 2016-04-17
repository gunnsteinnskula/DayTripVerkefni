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
	
	public static String getTripByName(String name) {
		return "A daytrip.";
	}
	
	public int getLength() {
		return length;
	}
	
	public String getType() {
		return type;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getSize() {
		return size;
	}
	
	public double getRating() {
		return rating;
	}
	
	public String getName() {
		return name;
	}
	
	public String getExtraInfo() {
		return extraInfo;
	}
	
	public String getTravelAgency() {
		return agency;
	}
	
	public int getTotalBookings() {
		return 0;
	}
	
	public void rate(int newRating) {
		double a = rating*rateCounter + newRating;
		rating = a/(++rateCounter);
	}
	
	public String getStartDate() {
		return formatter.format(dates[0]);
	}
	
	public String getEndDate() {
		return formatter.format(dates[1]);
	}
	
	public void addTrip(Trip newTrip) {
		trips.add(newTrip);
	}
	
}
