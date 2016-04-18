package klasar;

import java.util.Date;

public class Trip {
	private String dayTrip;
	private Date[] dates;
	private int MAX_SIZE;
	private int booked;
	
	/**
	 * Trip eru �ll instance � DayTrip � mismunandi dagsetningum.
	 * T.d. er DayTrip Northern Lights Tour farin � �msum dagsetningum yfir �ri�
	 * og hver s� fer� er skr�� sem Trip.
	 * 
	 * @param dayTrip er yfirfer� �essarar trip
	 * @param startDate er hven�r er lagt af sta�
	 * @param endDate er hven�r er komi� til baka
	 * @param maxSize er hversu margir komast � �essa fer�
	 * @param booked er hversu margir hafa b�ka� sig � �essa fer�
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
	 * @return nafn �essarar fer�ar
	 */
	public String getDayTrip(){
		return dayTrip;
	}
	
	/**
	 * 
	 * @return Date[2] vigur me� upphafs og lokadagsetningum �essarar fer�ar
	 */
	public Date[] getDate(){
		return dates;
	}
	
	/**
	 * 
	 * @return mesti fj�ldi f�lks � �essa fer�
	 */
	public int getSize(){
		return MAX_SIZE;
	}
	
	/**
	 * 
	 * @return hversu margir hafa veri� b�ka�ir � �essa fer�
	 */
	public int getbook(){
		return booked;
	}
	
	/**
	 * B�tir vi� booked �egar b�ka� er � �essa fer�
	 * @param size fj�ldi sem er veri� a� b�ta � fer�ina
	 */
	public void addBooked(int size) {
		booked += size;
	}
	
}
