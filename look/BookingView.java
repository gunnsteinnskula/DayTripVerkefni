package look;

public interface BookingView {
	/*
	 * Mock object for a UI object called BookingView.
	 * This object collects information from the user to complete booking.
	 */
	
	//Gets name of user
	public String getInputName();
	
	//Gets user's email
	public String getInputEmail();
	
	//Gets user's age 
	public int getInputAge();
	
	//Gets user's country
	public String getInputCountry();
	
	//Gets user's intended group size on a particular trip
	public int getInputGroupSize();
	
	//Assuming this object is a UI object we need a function to make that object
	//visible for the user.
	public void setVisible(boolean t);
}
