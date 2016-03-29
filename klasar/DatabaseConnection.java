package klasar;
import java.sql.*;
import java.util.Date;


public class DatabaseConnection {
	
	private String addTouristQuery;
	private String TABLENAME;
	private String DB_NAME;
	private Statement statement;
	private PreparedStatement pstatement;
	private Connection conn;
	private final String dir;
	
	public DatabaseConnection() {
		DB_NAME = "daytrips";
		dir = System.getProperty("user.dir");
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dir + "\\src\\daytrips.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean book(int tripID, String touristEmail, int bookingnumber, int groupSize) {
		return false;
	}
	
	public boolean findTourist(String email) {
		return false;
	}
	
	public boolean addTourist(String name, String email, String country, int age) {
		try {
			addTouristQuery = "INSERT INTO Tourist values(?,?,?,?)";
			pstatement = conn.prepareStatement(addTouristQuery);
			pstatement.setString(1, email);
			pstatement.setString(2, name);
			pstatement.setString(3, country);
			pstatement.setInt(4, age);
			pstatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int getTripID(DayTrip dayTrip, Date tripDates) {
		return 0;
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
