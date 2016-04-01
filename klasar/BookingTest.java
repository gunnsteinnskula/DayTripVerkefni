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
		tourist2 = new Tourist("Brandon", "brandon555@gigi.se", "Sweden", 80);
	}
	
	@After
	public void tearDown() {
		tourist1 = null;
		tourist2 = null;
	}

	@Test
	public void testName() {
		assertEquals("Valid","Lisa", tourist1.getName());
		assertEquals("Invalid", "Lisa2", tourist1.getName());
		assertNull("Invalid", tourist2.getName());
	}
	
	@Test
	public void testEmail() {
		assertEquals("Valid", "lisa@gmail.com", tourist1.getEmail());
		assertEquals("Invalid", "brandon555gigi.se", tourist2.getEmail());
	}
	
	@Test
	public void testAge() {
		assertEquals("Valid", 30, tourist1.getAge());
		assertEquals("Invalid", -80, tourist2.getAge());
		assertEquals("Invalid", 110, tourist2.getAge());
	}
	
	@Test
	public void testCountry() {
		assertEquals("Valid", "Sweden", tourist2.getCountry());
		assertEquals("Invalid", "Den5ark", tourist1.getCountry());
	}
}
