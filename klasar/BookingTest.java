package klasar;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookingTest {
	
	private Tourist tourist1, tourist2;
	
	@Before
	public void setUp() {
		tourist1 = new Tourist("Lisa", "lisa@gmail.com", "Denmark", 30);
		tourist2 = new Tourist("Brandon1", "brandon2.se", "Sweden4", 110);
	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	public void testName() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmail() {
		
	}
	
	@Test
	public void testAge() {
		
	}
	
	@Test
	public void testCountry() {
		
	}
	
	@Test public void testGroupSize() {
		
	}
	

}
