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
     * Festival eru allar hátíðir sem eru í gangi á tilteknu bili dagsetninga.
     * @param length er lengd hátíðarinnar
     * @param name er nafn hátíðarinnar
     * @param type er tegund hátíðar, t.d. fjölskylduhátíð, útihátíð o.s.frv.
     * @param startDate er byrjunardagsetning hátíðar
     * @param endDate er lokadagsetning hátíðar
     * @param location er staðsetning hátíðar
     * @param price er verð inn á hátíðina
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
     * @return lengd hátíðarinnar
     */
    public int getLength(){
        return length;
    }
    
    /**
     * 
     * @return nafn hátíðarinnar
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return tegund hátíðarinnar
     */
    public String getType(){
        return type;
    }

    /**
     * 
     * @return byrjunardagsetningu hátíðarinnar
     */
    public Date getStartDate(){
        return dates[0];
    }

    /**
     * 
     * @return lokadagsetningu hátíðarinnar
     */
    public Date getEndDate(){
        return dates[1];
    }

    /**
     * 
     * @return staðsetningu hátíðarinnar
     */
    public String getLocation(){
        return location;
    }

    /**
     * 
     * @return verði hátíðarinnar
     */
    public int getPrice(){
        return price;

    }
}
