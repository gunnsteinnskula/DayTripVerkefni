package klasar;

import java.util.ArrayList;
import java.util.List;

public class TravelAgency {
	private String name;
	private double rating;
	private List<DayTrip> dayTrips;
	
	/**
	 * Þessi klasi heldur utan um ferðaskrifstofu og ferðirnar sem sú stofa heldur utan um.
	 * 
	 * rating er meðaltal einkunna allra ferða skrifstofunnar
	 * daytrips eru allar ferðir sem þessi ferðaskrifstofa sér um
	 * 
	 * @param name nafn ferðaskrifstofunnar
	 */
	public TravelAgency(String name) {
		this.name = name;
		
		rating = 0;
		dayTrips = new ArrayList<DayTrip>();
	}
	
	/**
	 * 
	 * @return skilar DayTrip[] með öllum ferðum sem þessi stofa sér um
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
	 * @return skilar einkunn ferðaskrifstofunnar
	 *
	 */
	public double getRating() {
		return rating;
	}
}
