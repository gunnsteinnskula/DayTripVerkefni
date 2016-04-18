package klasar;

import java.util.Date;

public class Trip {
	private String dayTrip;
	private Date[] dates;
	private int MAX_SIZE;
	private int booked;
	
	/**
	 * Trip eru öll instance á DayTrip á mismunandi dagsetningum.
	 * T.d. er DayTrip Northern Lights Tour farin á ýmsum dagsetningum yfir árið
	 * og hver sú ferð er skráð sem Trip.
	 * 
	 * @param dayTrip er yfirferð þessarar trip
	 * @param startDate er hvenær er lagt af stað
	 * @param endDate er hvenær er komið til baka
	 * @param maxSize er hversu margir komast í þessa ferð
	 * @param booked er hversu margir hafa bókað sig í þessa ferð
	 */
	public Trip(String dayTrip, Date startDate, Date endDate, int maxSize, int booked){
		this.dayTrip = dayTrip;
		this.MAX_SIZE = maxSize;
		this.booked = booked;
		dates = new Date[2];
		dates[0] = startDate;
		dates[1] = endDate;
		
	}
	
	/**
	 * 
	 * @return nafn þessarar ferðar
	 */
	public String getDayTrip(){
		return dayTrip;
	}
	
	/**
	 * 
	 * @return Date[2] vigur með upphafs og lokadagsetningum þessarar ferðar
	 */
	public Date[] getDate(){
		return dates;
	}
	
	/**
	 * 
	 * @return mesti fjöldi fólks í þessa ferð
	 */
	public int getSize(){
		return MAX_SIZE;
	}
	
	/**
	 * 
	 * @return hversu margir hafa verið bókaðir í þessa ferð
	 */
	public int getbook(){
		return booked;
	}
	
	/**
	 * Bætir við booked þegar bókað er í þessa ferð
	 * @param size fjöldi sem er verið að bæta í ferðina
	 */
	public void addBooked(int size) {
		booked += size;
	}
	
}
