package klasar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    DateFormat formatter;

    /**
     * Smidur: Tengist vid gagnagrunninn og upphafsstillir oll gildi.
     * Allar dagsetningar i strengjaforminu eru a forminu "yyyy-MM-dd".
     */
    public SearchController() {
        connection = new DatabaseConnection();
        print = "";
        date1 = null;
        date2 = null;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * @param startdate1 er fyrri dagsetningin sem t�ristinn setur inn
     * @param enddate2 er seinni dagsetningin sem t�ristinn setur inn
     * @param name er nafn fer�arinnar
     * @param type er tegund fer�arinnar
     * @param size er st�r� h�psins sem b�ka� er fyrir. Einungis fer�ir sem henta �eim
     * fj�lda koma upp
     * @param price er ver�bil sem t�risti velur og leitar eftir
     * @param length er lengd fer�arinnar � d�gum
     * @param location er landshluti sem leita� er �
     * @return lista af fer�um sem � bo�i eru. Fer�in ver�ur a� vera � bo�i
     * fyrir date1 og eftir date2
     */
    public List<DayTrip> search(String startdate1, String enddate2, String name, String type,
                                int size, int price, int length, String location) {

        try {
            this.date1 = formatter.parse(startdate1);
            this.date2 = formatter.parse(enddate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(startdate1);

        System.out.println(date1);
        System.out.println(date2);
        List<DayTrip> daytrips = connection.search(date1, date2, name, type, size, price,
                length, location);


        if(daytrips.size() == 0){
            System.out.println("Því miður fundust engar ferðir.");
            allFestivals(date1, date2);
        }

        if(daytrips.size() == 1){
            System.out.println("Ferðin sem fannst var: ");
            oneDayTrip(daytrips.get(0));
        }

        else{
            System.out.println("Ferðirnar sem fundust voru: ");
            manyDayTrips(daytrips);
            allFestivals(date1, date2);
        }

        return daytrips;
    }

    /**
     *
     * @param daytrip er tegund ferdar sem verid er ad skoda
     * @return skilar lista af ferdum af tiltekinni tegund sem er i bodi. Ferdin verdur
     * ad vera i bodi fyrir date1 og eftir date2
     */
    public List<Trip> oneDayTrip(DayTrip daytrip){

        //searchView.setVisible(true);
        String getName = daytrip.getName();
        int getPrice = daytrip.getPrice();
        int getLength = daytrip.getLength();
        String getType = daytrip.getType();
        String getLocation = daytrip.getLocation();
        String startDate = daytrip.getStartDate();
        String endDate = daytrip.getEndDate();
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
        return trips;
    }

    /**
     * @param daytrips er tegund ferdar sem er verid ad skoda.
     */
    public void manyDayTrips(List<DayTrip> daytrips) {

        //searchView.setVisible(true);
        for (DayTrip daytrip : daytrips) {
            String getName = daytrip.getName();
            int getPrice = daytrip.getPrice();
            int getLength = daytrip.getLength();
            String getType = daytrip.getType();
            String getLocation = daytrip.getLocation();
            String startDate = daytrip.getStartDate();
            String endDate = daytrip.getEndDate();
            int getSize = daytrip.getSize();

            print += getName + " " + " frá " + startDate + " til " + endDate + " og kostar " + getPrice + "." + "\n";
        }

        System.out.println(print);
    }

    /**
     *
     * @param date1 er fyrri dagsetningin sem turistinn setur inn
     * @param date2 er seinni dagsetningin sem turistinn setur inn
     * @return lista af festivals sem byrja og enda innan timarammans sem turistinn setur inn
     */
    public List<Festival> allFestivals(Date date1, Date date2){
        List<Festival> festivals = connection.searchFestival(date1, date2);
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
        return festivals;
    }

    public static void main(String[] args) {
        SearchController s = new SearchController();
        String date1 = "2016-06-20";
        String date2 = "2016-09-01";
        List<DayTrip> dt = s.search(date1,date2,"Golden Circle",null,0,0,0,null);
        System.out.println(dt.get(0).getStartDate());
    }
}
