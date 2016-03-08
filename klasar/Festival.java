/**
 * Created by Gunnsteinn on 3/8/16.
 */
package klasar;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Festival{
    private int length;
    private String name;
    private String type;
    private Date[] dates;
    private String location;
    private int price;

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

    public int getLength(){
        return length;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public Date getStartDate(){
        return dates[0];
    }

    public Date getEndDate(){
        return dates[1];
    }

    public String getLocation(){
        return location;
    }

    public int getPrice(){
        return price;

    }
}
