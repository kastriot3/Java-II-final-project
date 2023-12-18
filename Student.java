/*
 * Class will hold Person attributes for data. 
 */
public class Student extends Person{

	//fields
	public enum StudentType{
		FullTime,PartTime,NoMajor;
	}
	
	private StudentType type;
	private String typeString;
	private String major;
	private float remainingBalance;
	public boolean hasMajor;
	public boolean badData;
	
	//constructor - with all information
	public Student(String lastName,String firstName, String Email, String Password,int BannerID,String type, String major, float remainingBalance) throws Exception {
		super(lastName,firstName, Email, Password,BannerID);
		this.type = StudentType.valueOf(formatMyEnum(type));
		this.typeString = type;
		this.major = major;
		this.remainingBalance = remainingBalance;
		declaredMajor();
	}
	
	//constructor - if Student has no major 
	
	public Student(String lastName,String firstName, String Email, String Password,int BannerID,String type, float remainingBalance) throws Exception {
		super(lastName,firstName,Email,Password,BannerID);
		this.type = StudentType.valueOf(formatMyEnum(type));
		this.remainingBalance = remainingBalance;
		declaredMajor();
	}
	
	public Student(String lastName,String firstName, String Email, String Password,String type, String major, float remainingBalance)throws Exception {
		super(lastName,firstName,Email,Password);
		this.type = StudentType.valueOf(formatMyEnum(type));
		this.remainingBalance = remainingBalance;
		declaredMajor();
		
	}
	
	//constructor - with all information
		public Student(String lastName,String firstName, String Email, String Password,int BannerID,Student.StudentType type, String major, float remainingBalance) throws Exception {
			super(lastName,firstName, Email, Password,BannerID);
			this.type = type;
			this.major = major;
			this.remainingBalance = remainingBalance;
			declaredMajor();
		}
		
		public Student(String lastName, String firstName, String Email, String Password, int highestBannerID, Student.StudentType type, float remainingBalance) throws Exception {
			super(lastName,firstName,Email,Password);
			this.type = type;
			this.remainingBalance = remainingBalance;
		}
		
	
	
	//getters and setters

	

	public Student.StudentType getType() {
		return type;
	}
	public String getTypeInString() {
		return typeString;
	}
	public void setType(StudentType type) {
		this.type = type;
	}
	public float getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(float remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	
	public String getMajor() {
		return this.major;
	}
	
	//method to determine if Student's major is declared. 
	
	public void declaredMajor() throws Exception {
		if(type == Student.StudentType.NoMajor) {
			hasMajor = false;
		} else {
			hasMajor = true;
		}
		
		if(hasMajor == false && major != null) {
			badData = true;
		} else {
			badData = false;
		}
	}
	
	//method to format String to enum when reading in 
	public static String formatMyEnum(String str) {		
		return str.replace(" ", "");
		
	}
	
	
	
	
	
}
