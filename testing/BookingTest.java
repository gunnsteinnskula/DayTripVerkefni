package testing;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import klasar.BookingController;
import klasar.Tourist;
import klasar.Trip;
import look.BookingViewInvalidEmailMock;
import look.BookingViewMock;

public class BookingTest {
	
	private BookingController contr;
	Calendar cal;
	Date date1;
	Date date2;
	Trip trip;
	Trip trip2;
	BookingViewMock mock1;
	BookingViewInvalidEmailMock mock2;
	
	@Before
	public void setUp() {
		mock1 = new BookingViewMock();
		mock2 = new BookingViewInvalidEmailMock();
		contr = new BookingController(mock1);
		cal = Calendar.getInstance();
		Calendar.getInstance();
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		date1 = cal.getTime();
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		date2 = cal.getTime();
		trip = new Trip("Geysir", date1, date2, 100, 20);
		trip2 = new Trip("Gullfoss", date1, date2, 100, 20);
		
	}
	
	@After
	public void tearDown() {
		contr = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalBook() {
		contr.book(null);
	}
	
	@Test
	public void testReturnBookingNumber() {
		assertEquals("Valid", 0, contr.book(trip));
		assertEquals("Valid", 1, contr.book(trip2));
	}
	
	@Test
	public void testValidEmailTourist() {
		contr = new BookingController(mock2);
		assertNull(contr.getTourist());
	}
	
	@Test
	public void testNonexistentTourist() {
		contr = new BookingController(mock1);
		Tourist tour = contr.getTourist();
		assertEquals("Valid", "Halla", tour.getName());
	}
}
