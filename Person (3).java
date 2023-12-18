import java.util.ArrayList;

public class Person implements KeyFinder, Comparable<Person>{
	
	//fields
	static int nextAvailableBannerId;
	String lastName;
	String firstName;
	String Email;
	String Password;
	int BannerID;
	boolean sameKey;
	
	//constructor
	
	public Person(String lastName,String firstName, String Email, String Password,int BannerID) throws Exception {
		this.lastName = lastName;
		this.firstName = firstName;
		this.Email = Email;
		this.Password = Password;
		this.BannerID = BannerID;
	}
	
	//constructor missing a banner ID
	public Person(String lastName,String firstName, String Email, String Password) throws Exception {
		this.lastName = lastName;
		this.firstName = firstName;
		this.Email = Email;
		this.Password = Password;
	}
	
	//getters and setters 
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public int getBannerID() {
		return BannerID;
	}
	
	public void setBannerID(int bannerID) {
		BannerID = bannerID;
	}
	
	public static int getNextAvailableBannerId() {
		return nextAvailableBannerId;
	}
	

	
	//to String method
	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", firstName=" + firstName + ", Email=" + Email + ", Password="
				+ Password + ", BannerID=" + BannerID + "]";
	}

	
	//interface Methods
	@Override
	public int getKey() {
		
		return BannerID;
	}

	

	@Override
	public boolean sameKey(int k) {
		if(k == BannerID) {
			return true;
		} else {
			return false;
		}
	}

	//comparable method
	@Override
	public int compareTo(Person o) {
		
		//if this last name is greater than other's last name, return -1
		if(getLastName().compareTo(o.getLastName()) < 0) {
			return -1;
		}
		
		//if other's last name is greater than this last name, return 1
		if(getLastName().compareTo(o.getLastName()) > 0) {
			return 1;
		}
		
		//else return 0
		return 0;
		
	}











}
