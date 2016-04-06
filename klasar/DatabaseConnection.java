package klasar;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	private String getTripsQuery;
	private PreparedStatement pstatement;
	private Connection conn;
	private ResultSet rs; 
	private ResultSet tresult;
	private String currentDir;
	DateFormat formatter;
	
	public DatabaseConnection() {
		currentDir = System.getProperty("user.dir");
		formatter = new SimpleDateFormat("yyyy-mm-dd");
	
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
			addTouristQuery = "INSERT INTO tourists values(?,?,?,?)";
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
	
	public int getTripID(String daytrip, Date tripDates) {
		int id = 0;
		try {
			getTripIDQuery = "SELECT id FROM dayTrips WHERE name='"+daytrip+"' AND startDate='"+new java.sql.Date(tripDates.getTime())+"'";
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
	
	public List<Trip> getTrips(DayTrip daytrip) {
		int id = 0;
		List<Trip> trips = new ArrayList<Trip>();
		getTripsQuery = "SELECT * from trips where dayTrip = " + id;
		try {
			pstatement = conn.prepareStatement("SELECT id FROM dayTrips WHERE name = '" + daytrip.getName() + "'");
			rs = pstatement.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
			pstatement = conn.prepareStatement(getTripsQuery);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				Date startDate = formatter.parse(rs.getString("startdate"));
				Date endDate = formatter.parse(rs.getString("enddate"));
				int maxSize = rs.getInt("maxsize");
				int booked = rs.getInt("bookings");
				trips.add(new Trip(daytrip.getName(), startDate, endDate, maxSize, booked));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trips;
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
				searchTripQuery += " WHERE";
				if(date1 != null) searchTripQuery += " startDate <= '" + new java.sql.Date(date1.getTime()) + "' AND";
				if(date2 != null) searchTripQuery += " endDate >= '" + new java.sql.Date(date2.getTime()) + "' AND";
				if(name != null) searchTripQuery += " name = '" + name + "' AND";
				if(type != null) searchTripQuery += " type = '" + type + "' AND";
				if(size != 0) searchTripQuery += " size = " + size + " AND";
				if(price != 0) searchTripQuery += " price <= " + price + " AND";
				if(length != 0) searchTripQuery += " length = " + length + " AND";
				if(location != null) searchTripQuery += " location = '" + location + "' AND";
				searchTripQuery = searchTripQuery.substring(0, searchTripQuery.lastIndexOf(" ")) + ";";
				System.out.println(searchTripQuery);
		}
		List<DayTrip> daytrips = new ArrayList<DayTrip>();
		try {
			pstatement = conn.prepareStatement(searchTripQuery);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				System.out.println("Fann DayTrip.");
				name = rs.getString("name");
				price = rs.getInt("price");
				length = rs.getInt("length");
				type = rs.getString("type");
				location = rs.getString("location");
				Date startDate = formatter.parse(rs.getString("startDate"));
				Date endDate = formatter.parse(rs.getString("endDate"));
				size = rs.getInt("size");
				String extraInfo = rs.getString("extraInfo");
				int tID = rs.getInt("travelAgency");
				pstatement = conn.prepareStatement("SELECT name FROM travelAgencies WHERE id =" + tID);
				tresult = pstatement.executeQuery();
				String travelAgency = tresult.getString("name");
				daytrips.add(new DayTrip(name, length, type, travelAgency, price, location, size, extraInfo, startDate, endDate));
			}
			rs.close();
			pstatement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return daytrips;
	}
	
	public List<Festival> searchFestival(Date date1, Date date2) {
		List<Festival> festivals = new ArrayList<Festival>();
		try {
			searchFestivalQuery = "SELECT * FROM festivals WHERE startDate <='"+ new java.sql.Date(date1.getTime()) +"' AND endDate >='"+ new java.sql.Date(date2.getTime())+"';";
			pstatement = conn.prepareStatement(searchFestivalQuery);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				System.out.println("Fann festival.");
				int length = rs.getInt("length");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Date startDate = formatter.parse(rs.getString("startDate"));
				Date endDate = formatter.parse(rs.getString("endDate"));
				String location = rs.getString("location");
				int price = rs.getInt("price");
				festivals.add(new Festival(length, name, type, startDate, endDate, location, price));
			}
			rs.close();
			pstatement.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return festivals;
	}
		
	public static void main(String[] args) {
		DatabaseConnection connect = new DatabaseConnection();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		Date date1 = cal.getTime();
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date2 = cal.getTime();
		java.sql.Date dat = new java.sql.Date(date2.getTime());
		System.out.println(dat);
		List<Festival> found = connect.searchFestival(date1,date2);
		System.out.println(found.get(0).getName());
		List<DayTrip> founddt = connect.search(null, null, "Geysir", null, 0, 0, 0, null);
		System.out.println(founddt.get(0).getTravelAgency());
	}
}
