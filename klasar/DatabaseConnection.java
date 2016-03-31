package klasar;
import java.sql.*;
import java.util.Date;


public class DatabaseConnection {
	
	private String addTouristQuery;
	private String getTripIDQuery;
	private String findTouristQuery;
	private String getTouristQuery;
	private String addBookingQuery;
	private String searchTripQuery;
	private String searchFestivalQuery;
	private PreparedStatement pstatement;
	private Connection conn;
	private ResultSet rs; 
	private String currentDir;
	
	public DatabaseConnection() {
		currentDir = System.getProperty("user.dir");
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+currentDir+"\\src\\daytrips.db");
			System.out.println("Tenging er komin á");
		} catch (Exception e) {
			System.out.println("Ekki náðist tenging við gagnagrunninn");
			System.exit(0);
		}	
	}
	
	public boolean book(int tripID, String touristEmail, int bookingnumber, int groupSize) {
		try {
			addBookingQuery = "INSERT INTO bookings values(?,?,?)";
			pstatement = conn.prepareStatement(addBookingQuery);
			pstatement.setString(1, touristEmail);
			pstatement.setInt(2, tripID);
			pstatement.setInt(3, bookingnumber);
			pstatement.executeUpdate();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean findTourist(String email) {
		Boolean found = false;
		try {
			findTouristQuery = "SELECT * FROM tourists WHERE email='"+email+"'";
			pstatement = conn.prepareStatement(findTouristQuery);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				found = true;
			}
			rs.close();
			pstatement.close();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Fann ekki túristann");
			found = false;
		}
		return found;
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
	
	public int getTripID(DayTrip daytrip, Date tripDates) {
		int id = 0;
		try {
			getTripIDQuery = "SELECT id FROM dayTrips WHERE name='"+daytrip.getName()+"' AND startDate="+tripDates;
			pstatement = conn.prepareStatement(getTripIDQuery);
			rs = pstatement.executeQuery();
			while(rs.next()){
				id = rs.getInt("id");
				System.out.println(id);
			}
			rs.close();
			pstatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Virkaði ekki");
			System.exit(0);
		}
		return id;
	}
	
	public Tourist getTourist(String email) {
		String name = null;
		String email1 = null;
		String country = null;
		int age = 0;
		try{
			getTouristQuery = "SELECT * FROM tourists WHERE email='"+email+"'";
			pstatement = conn.prepareStatement(getTouristQuery);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				email1 = rs.getString("email");
				country = rs.getString("country");
				age = rs.getInt("age");
			}
			rs.close();
			pstatement.close();
			Tourist tourist = new Tourist(name, email1, country, age);
			return tourist;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Gat ekki náð í túristann");
			return null;
		}
	}
	
	public List<DayTrip> search(Date date1, Date date2, String name, String type, int size, int price, int length, String location) {
		try {
			
		} catch(Exception e) {
			
		}
		return null;
	}
	
	public List<Festival> searchFestival(Date date1, Date date2) {

		try {
			searchFestivalQuery = "SELECT * FROM festivals WHERE startDate="+date1+" AND endDate="+date2;
			pstatement = conn.prepareStatement(searchFestivalQuery);
			rs = pstatement.executeQuery();
			//Eftir að klára hér
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return null;
	}
	

	
}
