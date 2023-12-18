
public class Faculty extends Person {

	//fields
	enum FacultyType{
		Professor,Adjunct;
	}
	private FacultyType title;
	private String titleString;
	private String department;
	boolean hasDepartment;
	
	public Faculty(String lastName, String firstName, String Email, String Password, int BannerID,String title, String department) throws Exception {
		super(lastName, firstName, Email, Password, BannerID);
		this.title = FacultyType.valueOf(title);
		this.titleString = title;
		this.department = department;
	}
	
	
	//constructor for missing banner ID 
	public Faculty(String lastName, String firstName, String Email, String Password,String title, String department) throws Exception {
		super(lastName, firstName, Email, Password);
		this.title = FacultyType.valueOf(title);
		this.department = department;
	}
	
	//getters and setters

	public FacultyType getTitle() {
		return title;
	}

	public void setTitle(FacultyType title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getTitleString() {
		return this.titleString;
	}
	
	//checks to see if object has a banner ID. 
	public void declaredDepartment() {
		
	}
	
		
	

	//toString 
	@Override
	public String toString() {
		return "Faculty [title=" + title + ", department=" + department + ", lastName=" + lastName + ", firstName="
				+ firstName + ", Email=" + Email + ", Password=" + Password + ", BannerID=" + BannerID + "]";
	}
	
	

	
	
	
}
