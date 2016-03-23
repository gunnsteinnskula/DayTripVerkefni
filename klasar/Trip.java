package klasar;

import java.util.Date;

public class Trip {
	private DayTrip dayTrip;
	private Date[] dates;
	private int MAX_SIZE;
	private int booked;
	/*private List<Integer> tourist;
	private List<Integer> bookingNumbers;
	private List<Integer> groupSize;
	*/
	
	public Trip(DayTrip dayTrip, Date startDate, Date endDate, int maxSize, int booked){
		this.dayTrip = dayTrip;
		this.MAX_SIZE = maxSize;
		this.booked = booked;
		dates = new Date[2];
		dates[0] = startDate;
		dates[1] = endDate;
		
	}
	
	public DayTrip getDayTrip(){
		return dayTrip;
	}
	
	public Date[] getDate(){
		return dates;
	}
	
	public int getSize(){
		return MAX_SIZE;
	}
	
	public int getbook(){
		return booked;
	}
	
	/*public int[] gettourist(){
		return tourist;
	}
	
	public int[] getbookingNumbers(){
		return bookingNumbers;
	}
	
	public int[] getgroupSize(){
		return groupSize;
	}*/
	
}
