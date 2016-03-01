package klasar;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class DayTrip {
	private int length;
	private String name;
	private String type;
	private TravelAgency agency;
	private int price;
	private String location;
	private int size;
	private double rating;
	private String extraInfo;
	private List<Trip> trips;
	private Date[] dates;
	private int rateCounter;
	
	public DayTrip(String name, int length, String type, TravelAgency agency,
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
	
	public TravelAgency getTravelAgency() {
		return agency;
	}
	
	public int getTotalBookings() {
		return 0;
	}
	
	public void rate(int newRating) {
		double a = rating*rateCounter + newRating;
		rating = a/(++rateCounter);
	}
	
	public Date getStartDate() {
		return dates[0];
	}
	
	public Date getEndDate() {
		return dates[1];
	}
	
	public void addTrip(Trip newTrip) {
		trips.add(newTrip);
	}
	
}
