package klasar;

public class Tourist {
	private String name;
	private String email;
	private String country;
	private int age;
	
	/**
	 * Tourist geymir upplısingar um ferğamenn sem hafa bókağ ferğir.
	 * 
	 * @param name nafn túristans
	 * @param email netfang túristans, verğur ağ innihalda att-merkiğ
	 * @param country heimaland túristans
	 * @param age aldur túristans
	 */
	public Tourist(String name, String email, String country, int age){
		this.name = name;
		this.email = email;
		this.country = country;
		this.age = age;
	}
	
	/**
	 * 
	 * @return skilar nafni túristans
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return skilar netfangi túristans
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * 
	 * @return skilar heimalandi túristans
	 */
	public String getCountry(){
		return country;
	}
	
	/**
	 * 
	 * @return skilar aldri túristans
	 */
	public int getAge(){
		return age;
	}
}
