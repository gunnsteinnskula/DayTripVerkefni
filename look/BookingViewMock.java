package look;

public class BookingViewMock implements BookingView {

	/*
	 * A sample version of BookingView for testing purposes.
	 */
	
	@Override
	public String getInputName() {
		return "Halla";
	}

	@Override
	public String getInputEmail() {
		return "hbr@hi.is";
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
