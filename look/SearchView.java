package look;

/**
 * Created by Gunnsteinn on 4/6/16.
 */

import java.util.Date;

public interface SearchView {

    //Date1
    public Date getStartdate();

    public Date getEndDate();

    public String getInputName();

    public String getType();

    public int getSize();

    public int getPrice ();

    public int getLength();

    public String getInputLocation();

    public void setVisible(boolean t);

}
