package klasar;

public class Tourist {
	private String name;
	private String email;
	private String country;
	private int age;
	
	/**
	 * Tourist geymir uppl�singar um fer�amenn sem hafa b�ka� fer�ir.
	 * 
	 * @param name nafn t�ristans
	 * @param email netfang t�ristans, ver�ur a� innihalda att-merki�
	 * @param country heimaland t�ristans
	 * @param age aldur t�ristans
	 */
	public Tourist(String name, String email, String country, int age){
		this.name = name;
		this.email = email;
		this.country = country;
		this.age = age;
	}
	
	/**
	 * 
	 * @return skilar nafni t�ristans
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return skilar netfangi t�ristans
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * 
	 * @return skilar heimalandi t�ristans
	 */
	public String getCountry(){
		return country;
	}
	
	/**
	 * 
	 * @return skilar aldri t�ristans
	 */
	public int getAge(){
		return age;
	}
}
