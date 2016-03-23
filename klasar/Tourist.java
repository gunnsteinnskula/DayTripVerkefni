package klasar;

public class Tourist {
	private String name;
	private String email;
	private String country;
	private int[] bookingNumbers;
	private int age;
	
	public Tourist(String name, String email, String country, int age){
		this.name = name;
		this.email = email;
		this.country = country;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getCountry(){
		return country;
	}
	
	public int[] getBookingNumbers(){
		return bookingNumbers;
	}
	
	public int getAge(){
		return age;
	}
}
