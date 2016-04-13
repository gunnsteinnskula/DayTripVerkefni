package klasar;

import java.util.Date;

import java.util.List;

import look.SearchView;


public class SearchController {
    private DatabaseConnection connection;
    private SearchView searchView;
    String print;


    public void search(Date date1, Date date2, String name, String type,
                       int size, int price, int length, String location) {

        List<DayTrip> daytrips = connection.search(date1, date2, name, type, size, price,
                length, location);
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
        }
    }


    public void oneDayTrip(DayTrip daytrip){

        searchView.setVisible(true);
        String getName = daytrip.getName();
        int getPrice = daytrip.getPrice();
        int getLength = daytrip.getLength();
        String getType = daytrip.getType();
        String getLocation = daytrip.getLocation();
        Date startDate = daytrip.getStartDate();
        Date endDate = daytrip.getEndDate();
        int getSize = daytrip.getSize();

        List<Trip> trips = connection.getTrips(daytrip);
        List<Festival> festivals = connection.searchFestival(startDate, endDate);


        print = "";

        print += getName + "/n" + "Lengd ferðar: " + getLength + "Sem hægt er að fara í frá " + startDate +
                " til " + endDate + ". /n" + "Tegund ferðar: " + getType + "/n Staðsetning ferðar: " +
                getLocation + "/n Ferðin kostar " + getPrice + "." + "og tekur " + getSize + "manns /n";


        for (Trip trip : trips) {

            Date[] dates = trip.getDate();
            Date startTripDate = dates[0];
            Date endTripDate = dates[1];

            print += "/n" + getName + "Frá " + startTripDate + "til " + endTripDate;
        }

        if(festivals.size() < 0) {
            print += "/n Hátíðir á þessum tíma:";

            for (Festival festival : festivals) {
                String festivalName = festival.getName();
                String festivalType = festival.getType();
                Date startFestivalDate = festival.getStartDate();
                Date endFestivalDate = festival.getEndDate();
                String festivalLocation = festival.getLocation();

                print += "/n" + festivalName + " er hátíð haldin " + startFestivalDate + " til " + endFestivalDate +
                        "./n Staðsetning: " + festivalLocation + " /n Tegund hátíðar: " + festivalType;
            }
        }
        //Commit

        else{ print += "/n Því miður eru engar hátíðir á þessum tíma.";}

        System.out.println(print);
    }


    public void manyDayTrips(List<DayTrip> daytrips) {
        searchView.setVisible(true);
        for (DayTrip daytrip : daytrips) {
            String getName = daytrip.getName();
            int getPrice = daytrip.getPrice();
            int getLength = daytrip.getLength();
            String getType = daytrip.getType();
            String getLocation = daytrip.getLocation();
            Date startDate = daytrip.getStartDate();
            Date endDate = daytrip.getEndDate();
            int getSize = daytrip.getSize();

            print += getName + " " + "frá " + startDate + "til " + endDate + "og kostar " + getPrice + "." + "/n";
        }
        System.out.println(print);
    }
}
