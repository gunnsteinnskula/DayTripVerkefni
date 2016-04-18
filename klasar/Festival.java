/**
 * Created by Gunnsteinn on 3/8/16.
 */
package klasar;

import java.util.Date;

public class Festival{
    private int length;
    private String name;
    private String type;
    private Date[] dates;
    private String location;
    private int price;
    
    
    /**
     * Festival eru allar h�t��ir sem eru � gangi � tilteknu bili dagsetninga.
     * @param length er lengd h�t��arinnar
     * @param name er nafn h�t��arinnar
     * @param type er tegund h�t��ar, t.d. fj�lskylduh�t��, �tih�t�� o.s.frv.
     * @param startDate er byrjunardagsetning h�t��ar
     * @param endDate er lokadagsetning h�t��ar
     * @param location er sta�setning h�t��ar
     * @param price er ver� inn � h�t��ina
     */
    public Festival(int length, String name, String type, Date startDate,
                    Date endDate, String location, int price){

        this.length = length;
        this.name = name;
        this.type = type;
        this.location = location;
        this.price = price;

        dates = new Date[2];
        dates[0] = startDate;
        dates[1] = endDate;
    }
    
    /**
     * 
     * @return lengd h�t��arinnar
     */
    public int getLength(){
        return length;
    }
    
    /**
     * 
     * @return nafn h�t��arinnar
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return tegund h�t��arinnar
     */
    public String getType(){
        return type;
    }

    /**
     * 
     * @return byrjunardagsetningu h�t��arinnar
     */
    public Date getStartDate(){
        return dates[0];
    }

    /**
     * 
     * @return lokadagsetningu h�t��arinnar
     */
    public Date getEndDate(){
        return dates[1];
    }

    /**
     * 
     * @return sta�setningu h�t��arinnar
     */
    public String getLocation(){
        return location;
    }

    /**
     * 
     * @return ver�i h�t��arinnar
     */
    public int getPrice(){
        return price;

    }
}
