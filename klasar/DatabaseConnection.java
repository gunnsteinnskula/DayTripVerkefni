package klasar;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
	private ResultSet tresult;
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
		searchTripQuery = "SELECT * FROM dayTrips";
		if(date1 != null || date2 != null || name != null || type != null || size != 0 || price != 0 || length != 0 || location != null) {
				searchTripQuery += "WHERE";
				if(date1 != null) searchTripQuery += "startDate =" + new java.sql.Date(date1.getTime()) + "AND";
				if(date2 != null) searchTripQuery += "endDate =" + new java.sql.Date(date2.getTime()) + "AND";
				if(name != null) searchTripQuery += "name = '" + name + "' AND";
				if(type != null) searchTripQuery += "type = '" + type + "' AND";
				if(size != 0) searchTripQuery += "size = " + size + "AND";
				if(price != 0) searchTripQuery += "price = " + price + "AND";
				if(length != 0) searchTripQuery += "length = " + length + "AND";
				if(location != null) searchTripQuery += "location = '" + location + "' AND";
				searchTripQuery = searchTripQuery.substring(0, searchTripQuery.lastIndexOf(" "));
		}
		List<DayTrip> daytrips = new ArrayList<DayTrip>();
		try {
			pstatement = conn.prepareStatement(searchTripQuery);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
				price = rs.getInt("price");
				length = rs.getInt("length");
				type = rs.getString("type");
				location = rs.getString("location");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				size = rs.getInt("size");
				String extraInfo = rs.getString("extraInfo");
				int tID = rs.getInt("travelAgency");
				pstatement = conn.prepareStatement("SELECT name FROM travelAgencies WHERE id =" + tID);
				tresult = pstatement.executeQuery();
				String travelAgency = tresult.getString("id");
				daytrips.add(new DayTrip(name, length, type, travelAgency, price, location, size, extraInfo, startDate, endDate));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return daytrips;
	}
	
	public List<Festival> searchFestival(Date date1, Date date2) {
		List<Festival> festivals = new ArrayList<Festival>();
		try {
			searchFestivalQuery = "SELECT * FROM festivals WHERE startDate="+date1+" AND endDate="+date2;
			pstatement = conn.prepareStatement(searchFestivalQuery);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				int length = rs.getInt("length");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				String location = rs.getString("location");
				int price = rs.getInt("price");
				festivals.add(new Festival(length, name, type, startDate, endDate, location, price));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return festivals;
	}
		
}
