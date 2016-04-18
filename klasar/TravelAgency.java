package klasar;

import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
	private String name;
	private double rating;
	private List<DayTrip> dayTrips;
	
	/**
	 * �essi klasi heldur utan um fer�askrifstofu og fer�irnar sem s� stofa heldur utan um.
	 * 
	 * rating er me�altal einkunna allra fer�a skrifstofunnar
	 * daytrips eru allar fer�ir sem �essi fer�askrifstofa s�r um
	 * 
	 * @param name nafn fer�askrifstofunnar
	 */
	public TravelAgency(String name) {
		this.name = name;
		
		rating = 0;
		dayTrips = new ArrayList<DayTrip>();
	}
	
	/**
	 * 
	 * @return skilar DayTrip[] me� �llum fer�um sem �essi stofa s�r um
	 */
	public DayTrip[] getDayTrips() {
		return (DayTrip[]) dayTrips.toArray();
	}
	
	/**
	 * 
	 * @return skilar nafni skrifstofunnar
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return skilar einkunn fer�askrifstofunnar
	 *
	 */
	public double getRating() {
		return rating;
	}
}
