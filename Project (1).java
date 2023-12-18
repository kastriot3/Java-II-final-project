import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Project {

	static ArrayList<KeyFinder> data = new ArrayList<KeyFinder>();
	static ArrayList<Person> personData = new ArrayList<Person>();
	 
	//A method used to determine whether the first item in the row is a course or a person. What will differentiate them is their first item. If it is a number (CRN), it will be a course. If it is a String, it will be a person
	 public boolean isCourse(String str) {
		 try {
			 int x = Integer.parseInt(str);
			 return true;
		 } catch(NumberFormatException e) {
			 return false;
		 }
		 
	 }
	 
	 //A method used to determine whether the Person is a student or faculty. 
	 public static boolean isStudent(String str) {
		 boolean flag = true;
		 try {
		 if(Student.StudentType.valueOf(formatMyEnum(str)) == Student.StudentType.FullTime || Student.StudentType.valueOf(formatMyEnum(str)) == Student.StudentType.PartTime || Student.StudentType.valueOf(str) == Student.StudentType.NoMajor ) {
			 flag = true;
		 } else {
			 flag = false;
		 }
		 } catch (Exception e) {
			flag = false; 
		 }
		return flag;
	 }
	 
	 
	 public static String formatMyEnum(String str) {
		return str.replace(" ", "");
			
	}

	 //method used to convert the keyfinder arraylist to a sting arraylist which will then be used to either format data or to write to file in the same format as it was written in the document. It will take a keyfinder arraylits as
	 //an argument, and it will convert it to the print data (of type arraylist string).
	public static ArrayList<String> toStringData(ArrayList<KeyFinder> data1) {
		//arraylist used for formatting the data in the file
		ArrayList<String>printdata = new ArrayList<String>(); 
		
		//for each element in arraylist passed in
		for(KeyFinder x : data1) {
			//try 
			try {
				//convert to student object	
				Student s = (Student)x;
				//if student has major 
					if(s.getMajor()!=null) {
						//add this to print data arraylist
						String str = s.getLastName()+" "+s.getFirstName()+", "+ s.getEmail()+", "+s.getPassword()+", "+s.getBannerID()+", "+s.getType()+", "+s.getMajor()+", "+s.getRemainingBalance();
						printdata.add(str);
						//else if student has no major and type is no major
					} else if(s.getMajor()==null && s.getType().equals(Student.StudentType.NoMajor)) {
						String str = s.getLastName()+", "+s.getFirstName()+", "+ s.getEmail()+", "+s.getPassword()+", "+s.getBannerID()+", "+s.getType()+", "+s.getRemainingBalance();
						printdata.add(str);
					}
			 } catch (Exception e1) {
					try {
						//if not student, try faculty
						Faculty fac = (Faculty)x;
						//if faculty has banner ID
						if(fac.getBannerID()!=0) {
							//add this to be written out
							String str = fac.getLastName()+", "+fac.getFirstName()+", "+ fac.getEmail()+", "+fac.getPassword()+", "+fac.getBannerID()+", "+fac.getTitle()+", "+fac.getDepartment();
							printdata.add(str);
							//else if faculty has banner id add this
						} else {
							String str = fac.getLastName()+", "+fac.getFirstName()+", "+ fac.getEmail()+", "+fac.getPassword()+", "+fac.getTitle()+", "+fac.getDepartment();
							printdata.add(str);
						}
					} catch (Exception e2) {
						//if not try course
						try {
							Course c = (Course)x;
							String str = c.getCRN()+" "+c.getCourseName()+" "+c.getCredits();
							printdata.add(str);
						}catch(Exception e3) {
							System.out.println("Bad data");
						}
					}
				}
		 }
		//when done return this arraylist
		return printdata;
	}
	
	//determine highest bannerID for Student
	public static int highestBannerID() {
		int max = 0;
		for(int i=0;i<data.size();i++) {
			if(data.get(i).getKey() > max) {
				max = data.get(i).getKey();
			}
		}
		
		return max+1;
		
	}
	
	//method used to determine if the input is valid
	public static boolean validFindInput(String str) {
		boolean flag = true;
		try {
			Integer.parseInt(str);
		} catch (Exception e){ 
			System.out.println("input invalid");
			flag = false;
		}
		
		return flag;
		
	}
	
	//method used to determine if the object in Arraylist is the same input of this method. returns true or false. then is piped into another method that will return that exact value. 
	
	public static String containsBannerID(String str) {
		String valid = "Error: No such ID exists in the system";
		
		if(validFindInput(str) == true) {
			for(int i = 0; i<data.size();i++) {
				if(data.get(i).getKey()==Integer.parseInt(str)) {
					valid = data.get(i).toString();
				} 
			}
		} else {
			valid = "Error: Invalid data format";
		}
		
		return valid;
		
	}
	
	
	 //method used to convert the keyfinder arraylist to a sting arraylist which will then be used to either format data or to write to file in the same format as it was written in the document. It will take a keyfinder arraylits as
	 //an argument, and it will convert it to the print data (of type arraylist string). THE MAIN DIFFERENCE BETWEEN THIS METHOD AND THE ONE ABOVE IS THAT THIS ONE WILL FORMAT WITHOUT PASSWORD
	public static ArrayList<String> toStringDataStatistics(ArrayList<KeyFinder> data1) {
		//arraylist used for formatting the data in the file
		ArrayList<String>printdata = new ArrayList<String>(); 
		
		//for each element in arraylist passed in
		for(KeyFinder x : data1) {
			//try 
			try {
				//convert to student object	
				Student s = (Student)x;
				//if student has major 
					if(s.getMajor()!=null) {
						//add this to print data arraylist
						String str = s.getLastName()+" "+s.getFirstName()+", "+ s.getEmail()+", "+s.getBannerID()+", "+s.getType()+", "+s.getMajor()+", "+s.getRemainingBalance();
						printdata.add(str);
						//else if student has no major and type is no major
					} else if(s.getMajor()==null && s.getType().equals(Student.StudentType.NoMajor)) {
						String str = s.getLastName()+", "+s.getFirstName()+", "+ s.getEmail()+", "+s.getBannerID()+", "+s.getType()+", "+s.getRemainingBalance();
						printdata.add(str);
					}
			 } catch (Exception e1) {
					try {
						//if not student, try faculty
						Faculty fac = (Faculty)x;
						//if faculty has banner ID
						if(fac.getBannerID()!=0) {
							//add this to be written out
							String str = fac.getLastName()+", "+fac.getFirstName()+", "+ fac.getEmail()+", "+fac.getBannerID()+", "+fac.getTitle()+", "+fac.getDepartment();
							printdata.add(str);
							//else if faculty has banner id add this
						} else {
							String str = fac.getLastName()+", "+fac.getFirstName()+", "+ fac.getEmail()+", "+fac.getTitle()+", "+fac.getDepartment();
							printdata.add(str);
						}
					} catch (Exception e2) {
						//if not try course
						try {
							Course c = (Course)x;
							String str = c.getCRN()+" "+c.getCourseName()+" "+c.getCredits();
							printdata.add(str);
						}catch(Exception e3) {
							System.out.println("Bad data");
						}
					}
				}
		 }
		//when done return this arraylist
		return printdata;
	}
	
	public static void convertToPersonArray(ArrayList<KeyFinder> x) {
		
		for(KeyFinder k : data) {
			
			try {
				//try to convert to person
				Person s = (Person)k;
				personData.add(s);
			
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
			
			
		}
		
	}
	
	//Method is used to convert personData to a String arrayList for better formatting when writing to file. 
	
	public static ArrayList<String> toStringPersonDataStatistics(ArrayList<Person> data1) {
		//arraylist used for formatting the data in the file
		ArrayList<String>printdata = new ArrayList<String>(); 
		
		//for each element in arraylist passed in
		for(Person x : data1) {
			//try 
			try {
				//convert to student object	
				Student s = (Student)x;
				//if student has major 
					if(s.getMajor()!=null) {
						//add this to print data arraylist
						String str = s.getLastName()+" "+s.getFirstName()+", "+ s.getEmail()+", "+s.getBannerID()+", "+s.getType()+", "+s.getMajor()+", "+s.getRemainingBalance();
						printdata.add(str);
						//else if student has no major and type is no major
					} else if(s.getMajor()==null && s.getType().equals(Student.StudentType.NoMajor)) {
						String str = s.getLastName()+", "+s.getFirstName()+", "+ s.getEmail()+", "+s.getBannerID()+", "+s.getType()+", "+s.getRemainingBalance();
						printdata.add(str);
					}
			 } catch (Exception e1) {
					try {
						//if not student, try faculty
						Faculty fac = (Faculty)x;
						//if faculty has banner ID
						if(fac.getBannerID()!=0) {
							//add this to be written out
							String str = fac.getLastName()+", "+fac.getFirstName()+", "+ fac.getEmail()+", "+fac.getBannerID()+", "+fac.getTitle()+", "+fac.getDepartment();
							printdata.add(str);
							//else if faculty has banner id add this
						} else {
							String str = fac.getLastName()+", "+fac.getFirstName()+", "+ fac.getEmail()+", "+fac.getTitle()+", "+fac.getDepartment();
							printdata.add(str);
						}
					} catch (Exception e2) {
						System.out.println(e2.getMessage());
					}
				}
		 }
		//when done return this arraylist
		return printdata;
	}
	

	 
	
	
}
