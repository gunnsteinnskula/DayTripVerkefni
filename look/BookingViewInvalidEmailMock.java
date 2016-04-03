package look;

public class BookingViewInvalidEmailMock implements BookingView {

	/*
	 * A sample version of BookingView with an invalid email
	 * for testing purposes. 
	 */
	
	@Override
	public String getInputName() {
		return "Halla";
	}

	@Override
	public String getInputEmail() {
		return "hbrhi.is";
	}

	@Override
	public int getInputAge() {
		return 21;
	}

	@Override
	public String getInputCountry() {
		return "Iceland";
	}

	@Override
	public int getInputGroupSize() {
		return 5;
	}

	@Override
	public void setVisible(boolean t) {

	}

}
