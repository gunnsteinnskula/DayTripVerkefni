package klasar;

public class Trip {
	private String DayTrip;
	private int[] Date;
	private int MAX_SIZE;
	private int booked;
	private int[] tourist;
	private int[] bookingNumbers;
	private int[] groupSize;
	
	
	public Trip(){
		
	}
	
	public String getDayTrip(){
		return DayTrip;
	}
	
	public int[] getDate(){
		return Date;
	}
	
	public int getSize(){
		return MAX_SIZE;
	}
	
	public int getbook(){
		return booked;
	}
	
	public int[] gettourist(){
		return tourist;
	}
	
	public int[] getbookingNumbers(){
		return bookingNumbers;
	}
	
	public int[] getgroupSize(){
		return groupSize;
	}
	
}
