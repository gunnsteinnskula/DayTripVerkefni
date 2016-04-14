package klasar;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

import look.SearchView;


public class SearchController {
    private DatabaseConnection connection;
    private SearchView searchView;
    String print;
    Date date1;
    Date date2;

    public SearchController() {
    	connection = new DatabaseConnection();
    	print = "";
    	date1 = null;
    	date2 = null;
    }

    public List<DayTrip> search(Date date1, Date date2, String name, String type,
                       int size, int price, int length, String location) {

    	this.date1 = date1;
    	this.date2 = date2;
        List<DayTrip> daytrips = connection.search(date1, date2, name, type, size, price,
                length, location);

        List<Festival> festivals = connection.searchFestival(date1, date2);

        if(daytrips.size() == 0){
            System.out.println("Því miður fundust engar ferðir.");
        }

        if(daytrips.size() == 1){
            System.out.println("Ferðin sem fannst var: ");
            oneDayTrip(daytrips.get(0));
        }

        else{
            System.out.println("Ferðirnar sem fundust voru: ");
            manyDayTrips(daytrips);
            allFestivals(festivals);
        }
        
        return daytrips;
    }

    public void oneDayTrip(DayTrip daytrip){

        //searchView.setVisible(true);
        String getName = daytrip.getName();
        int getPrice = daytrip.getPrice();
        int getLength = daytrip.getLength();
        String getType = daytrip.getType();
        String getLocation = daytrip.getLocation();
        Date startDate = daytrip.getStartDate();
        Date endDate = daytrip.getEndDate();
        int getSize = daytrip.getSize();

        List<Trip> trips = connection.getTrips(daytrip);

        System.out.println(startDate);
        System.out.println(endDate);

        print = "";

        print += getName + "\n" + "Lengd ferðar: " + getLength + "Sem hægt er að fara í frá " + startDate +
                " til " + endDate + ". \n" + "Tegund ferðar: " + getType + "\n Staðsetning ferðar: " +
                getLocation + "\n Ferðin kostar " + getPrice + "." + "og tekur " + getSize + "manns \n";


        for (Trip trip : trips) {
            Date[] dates = trip.getDate();
            Date startTripDate = dates[0];
            Date endTripDate = dates[1];

            print += "\n" + getName + "Frá " + startTripDate + "til " + endTripDate;
        }


        System.out.println(print);
    }


    public void manyDayTrips(List<DayTrip> daytrips) {


        //searchView.setVisible(true);
        for (DayTrip daytrip : daytrips) {
            String getName = daytrip.getName();
            int getPrice = daytrip.getPrice();
            int getLength = daytrip.getLength();
            String getType = daytrip.getType();
            String getLocation = daytrip.getLocation();
            Date startDate = daytrip.getStartDate();
            Date endDate = daytrip.getEndDate();
            int getSize = daytrip.getSize();

            print += getName + " " + " frá " + startDate + " til " + endDate + " og kostar " + getPrice + "." + "\n";
        }


        System.out.println(print);
    }

    public void allFestivals(List<Festival> festivals){
        if(festivals.size() > 0) {
            print += "\n Hátíðir á þessum tíma:";

            for (Festival festival : festivals) {
                String festivalName = festival.getName();
                String festivalType = festival.getType();
                Date startFestivalDate = festival.getStartDate();
                Date endFestivalDate = festival.getEndDate();
                String festivalLocation = festival.getLocation();

                print += "\n" + festivalName + " er hátíð haldin " + startFestivalDate + " til " + endFestivalDate +
                        ".\n Staðsetning: " + festivalLocation + " \n Tegund hátíðar: " + festivalType;
            }
        }

        else{ print += "\n Því miður eru engar hátíðir á þessum tíma.";}
    }

    public static void main(String[] args) {
    	SearchController s = new SearchController();
    	
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date1 = cal.getTime();
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date2 = cal.getTime();
		System.out.println(date1);
		System.out.println(date2);
    	List<DayTrip> dt = s.search(date1,date2,"Golden Circle",null,0,0,0,null);
    	System.out.println(dt.size());
    }
}
