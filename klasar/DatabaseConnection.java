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
	private String findNumberOfBookings;
	private PreparedStatement pstatement;
	private Connection conn;
	private ResultSet rs; 
	private ResultSet tresult;
	private String currentDir;
	DateFormat formatter;
	
	/**
	 * Smiður: Gagnagrunnur fundinn. Á að vera í sömu möppu og verkefnið.
	 * Allar dagsetningar á strengjaformi eru á forminu "yyyy-MM-dd".
	 */
	public DatabaseConnection() {
		currentDir = System.getProperty("user.dir");
		formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+currentDir+"\\src\\daytrips.db");
			System.out.println("Tenging er komin ï¿½");
		} catch (Exception e) {
			System.out.println("Ekki nï¿½ï¿½ist tenging viï¿½ gagnagrunninn");
			System.exit(0);
		}	
	}
	
	/**
	 * Bókun er færð í gagnagrunninn
	 * @param tripID er id á ferðinni sem á að bóka í 
	 * @param touristEmail er email þess sem bókar
	 * @param bookingnumber er sér númer sem á við um þessa bókun
	 * @param groupSize er stærð hópsins sem verið er að bóka fyrir
	 * @return true ef náðist að bóka ferðina, annars false
	 */
	public boolean book(int tripID, String touristEmail, int bookingnumber, int groupSize) {
		int bookings = 0;
		try {
			addBookingQuery = "INSERT INTO bookings values(?,?,?)";
			pstatement = conn.prepareStatement(addBookingQuery);
			pstatement.setString(1, touristEmail);
			pstatement.setInt(2, tripID);
			pstatement.setInt(3, bookingnumber);
			pstatement.executeUpdate();
			findNumberOfBookings = "SELECT bookings FROM trips WHERE id='"+tripID+"'";
			pstatement = conn.prepareStatement(findNumberOfBookings);
			rs = pstatement.executeQuery();
			while(rs.next()) {
				bookings = rs.getInt("bookings");
				bookings = bookings + groupSize;
			}
			String changeBookings = "UPDATE trips SET bookings="+bookings+" WHERE id="+tripID;
			pstatement = conn.prepareStatement(changeBookings);
			rs = pstatement.executeQuery();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * Finnur túrista í gagnagrunninum út frá netfangi
	 * @param email er netfangið sem leitað er að í gagnagrunninum
	 * @return true ef túristinn er nú þegar í gagnagrunninum, annars false
	 */
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
			System.out.println("Fann ekki tï¿½ristann");
			found = false;
		}
		return found;
	}
	
	/**
	 * Bætir túrista við í gagnagrunninn
	 * @param name er nafn þess sem bókar
	 * @param email er netfang þess sem bókar
	 * @param country er land þess sem bókar
	 * @param age er aldur þess sem bókar
	 * @return true ef það tókst að bæta túrista við í gagnagrunninn, annars false
	 */
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
	
	/**
	 * Finnur ferð í gagnagrunninum sem hefur ákveðna byrjunardagsetningu
	 * @param daytrip er nafn ferðarinnar
	 * @param tripDates er byrjunardagsetning ferðarinnar
	 * @return skilar id úr gagnagrunninum fyrir ferðina
	 */
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
			System.out.println("Virkaï¿½i ekki");
			System.exit(0);
		}
		return id;
	}
	
	/**
	 * Finnur öll stök af trip fyrir ákveðið daytrip
	 * @param daytrip er ferðin sem verið er að skoða
	 * @return skilar lista af öllum trip sem fundust
	 */
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
			pstatement.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trips;
	}
	/**
	 * Nær í túrista og upplýsingar um hann úr gagnagrunninum og býr til 
	 * nýtt stak af Tourist
	 * @param email er netfang sem leitað er eftir 
	 * @return skilar stakinu Tourist
	 */
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
			System.out.println("Gat ekki nï¿½ï¿½ ï¿½ tï¿½ristann");
			return null;
		}
	}
	/**
	 * Leitar að ferð í gagnagrunninum út frá tilteknum leitarskilyrðum og býr
	 * til lista af öllum ferðum sem fundust. Ef String eða Date innihalda
	 * NULL og int 0 þá er það túlkað sem svo að engin leitarskilyrði hafi
	 * verið sett á þann parameter.
	 * @param date1 er fyrri dagsetningin sem túristinn setur inn
	 * @param date2 er seinni dagsetningin sem túristinn setur inn
	 * @param name er nafn ferðarinnar
	 * @param type er tegund ferðarinnar
	 * @param size er stærð hópsins sem bókað er fyrir. Einungis ferðir sem henta þeim
	 * fjölda koma upp
	 * @param price er verðbil sem túristi velur og leitar eftir
	 * @param length er lengd ferðarinnar í dögum
	 * @param location er landshluti sem leitað er á
	 * @return lista af ferðum sem í boði eru. Ferðin verður að vera í boði
	 * fyrir date1 og eftir date2
	 */
	public List<DayTrip> search(Date date1, Date date2, String name, String type, int size, int price, int length, String location) {
		searchTripQuery = "SELECT * FROM dayTrips";
		if(date1 != null || date2 != null || name != null || type != null || size != 0 || price != 0 || length != 0 || location != null) {
				searchTripQuery += " WHERE";
				if(date1 != null) searchTripQuery += " startDate <= '" + new java.sql.Date(date1.getTime()) + "' AND";
				if(date2 != null) searchTripQuery += " endDate >= '" + new java.sql.Date(date2.getTime()) + "' AND";
				if(name != null) searchTripQuery += " name = '" + name + "' AND";
				if(type != null) searchTripQuery += " type = '" + type + "' AND";
				if(size != 0) searchTripQuery += " size <= " + size + " AND";
				if(price != 0) searchTripQuery += " price <= " + price + " AND";
				if(length != 0) searchTripQuery += " length <= " + length + " AND";
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
	/**
	 * Leitar að hátíðum í gagnagrunninum sem eru í gangi á tilteknum tíma.
	 * @param date1 er fyrri dagsetningin sem túristinn setur inn
	 * @param date2 er seinni dagsetningin sem túristinn setur inn
	 * @return lista af hátíðum sem eru í gangi. Hátíðin þarf að vera
	 * einhversstaðar á því bili dagsetninga sem hefur verið valið
	 */
	public List<Festival> searchFestival(Date date1, Date date2) {
		List<Festival> festivals = new ArrayList<Festival>();
		try {
			searchFestivalQuery = "SELECT * FROM festivals WHERE startDate >='"+ new java.sql.Date(date1.getTime()) +"' AND endDate <='"+ new java.sql.Date(date2.getTime())+"';";
			System.out.println(searchFestivalQuery);
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
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 26);
		Date date1 = cal.getTime();
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date2 = cal.getTime();
		java.sql.Date dat = new java.sql.Date(date2.getTime());
		System.out.println(dat);
		List<Festival> found = connect.searchFestival(date1,date2);
		System.out.println(found.get(0).getName());
		List<DayTrip> founddt = connect.search(null, null, "Geysir", null, 0, 0, 0, null);
		System.out.println(founddt.get(0).getTravelAgency());
		connect.addTourist("Heiðrún Björk", "heidrunbh@gmail.com", "Iceland", 20);
		System.out.println(connect.book(0, "heidrunbh@gmail.com", 0, 10));
	}
}
