package klasar;
import java.sql.*;


public class DatabaseConnection {
	
	private String sqlqueries;
	private String TABLENAME;
	
	public DatabaseConnection() {
		
	}

	
	public boolean book(int tripID, String touristEmail, int bookingnumber, int groupSize) {
		return false;
	}
	
	public boolean findTourist(String email) {
		return false;
	}
	
	public boolean addTourist(String name, String email, String country, int age) {
		return false;
	}
	
	public int getTripID(DayTrip dayTrip, Date startDate) {
		return null;
	}
	
	public Tourist getTourist(String email) {
		return null;
	}
	
	public List<DayTrip> search(Date date1, Date date2, String name, String type, int size, int price, int length, String location) {
		return null;
	}
	
	public List<Festival> searchFestival(Date date1, Date date2) {
		return null;
	}
	
}
