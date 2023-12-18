import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddStudent extends JFrame {
	//fields 
		JRadioButton fullTime,partTime,noMajor;
		JButton add;
		JTextField firstName,lastName,password,confirm,majorTbox,remBalTbox;
		JLabel pw,major;
		
		
		
		
		//constructor 
		
		public AddStudent(String title) {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle(title);
			this.setSize(1000,600);
			this.setResizable(false);
			buildPanel();
			this.setVisible(true);
		}
		
		//build panel method 
		public void buildPanel() {
		
			setLayout(new BorderLayout(10,5));
			
			//add Student Label
			JPanel north = new JPanel();
			JLabel addStudent = new JLabel("Add Student: ");
			
			north.add(addStudent);
			
			add(north,BorderLayout.NORTH);
			
			
			//radio buttons - East 
			JPanel east = new JPanel();
			
			JLabel StudentType = new JLabel("Student Type:");
			
			JLabel spacer = new JLabel(" ");
			JLabel spacer2 = new JLabel(" ");
			
			//javadoc, found a way to make radiobuttons vertical through implementation of Box without having to nest panels, with the inner panel containg a gridlayout set. 
			Box verticalBox = Box.createVerticalBox();
					
			fullTime = new JRadioButton("Full Time");
			partTime = new JRadioButton("Part Time");
			noMajor = new JRadioButton("No Major");
			add = new JButton("Add");
			ButtonHandler bh = new ButtonHandler();
			add.addActionListener(bh);
			
			verticalBox.add(StudentType);
			verticalBox.add(spacer2);
			verticalBox.add(fullTime);
			verticalBox.add(partTime);
			verticalBox.add(noMajor);
			
			
			
			
			
			
			east.add(verticalBox);
			
			
			add(east,BorderLayout.EAST);
			
			//button group
			ButtonGroup bg = new ButtonGroup();
			bg.add(fullTime);
			bg.add(partTime);
			bg.add(noMajor);
			bg.add(add);
			
			
			//radio button handler
			ButtonHandler bl = new ButtonHandler();
			fullTime.addActionListener(bl);
			partTime.addActionListener(bl);
			noMajor.addActionListener(bl);
			
			//user input - west
			JPanel west = new JPanel();
			
			Box verticalBox2 = Box.createVerticalBox();
			
			JLabel fName = new JLabel("First Name: ");
			firstName = new JTextField(15);
			
			JLabel lName = new JLabel("Last Name: ");
			lastName = new JTextField(15);
			
			pw = new JLabel("Password: ");
			password = new JTextField(15);
			
			JLabel confirmPw = new JLabel("Confirm Password: ");
			confirm = new JTextField(15);
			
			major = new JLabel("Enter a major: ");
			majorTbox = new JTextField(15);
			
			JLabel remBal = new JLabel("Remaining Balance: ");
			remBalTbox = new JTextField(15);
			
			
			
			verticalBox2.add(fName);
			verticalBox2.add(firstName);
			verticalBox2.add(lName);
			verticalBox2.add(lastName);
			verticalBox2.add(pw);
			verticalBox2.add(password);
			verticalBox2.add(confirmPw);
			verticalBox2.add(confirm);
			verticalBox2.add(major);
			verticalBox2.add(majorTbox);
			verticalBox2.add(remBal);
			verticalBox2.add(remBalTbox);
			verticalBox2.add(spacer);
			verticalBox2.add(add);
			
			west.add(verticalBox2);
			
			add(west,BorderLayout.WEST);
			
			//buttons - south side of panel
			
			JPanel south = new JPanel();
			JLabel pwreq = new JLabel("Passwords must be between 8 and 10 characters, contain at least 1 upper case letter, 1 lower case letter and 1 digit. Cannot contain your first or last name.");
			JLabel pwreq2 = new JLabel("Cannot contain your first or last name.");
			south.add(pwreq);
			south.add(pwreq2);
			
			add(south, BorderLayout.SOUTH);
			
			
		}
		
		
	private class ButtonHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
				//if button is clicked, fulltime is selected and password is valid,
				if(button == add && fullTime.isSelected() && isPasswordValid(password.getText())==true && !majorTbox.getText().equals("")) {
					System.out.println("add pressed");
					//set the color of text back to black
					password.setForeground(Color.BLACK);
					confirm.setForeground(Color.BLACK);
					major.setForeground(Color.BLACK);
					
					//try
					try {
						//converting input to student
						Student s = new Student(lastName.getText(),firstName.getText(),generateEmail(firstName.getText(),lastName.getText())
						,password.getText(),Project.highestBannerID(),Student.StudentType.FullTime,majorTbox.getText(),Float.parseFloat(remBalTbox.getText()));
						Project.data.add(s);
						System.out.println("student added");
						JOptionPane.showMessageDialog(null, "Success");
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						System.out.println("Student Not added");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Student Not added");
					}
					
					//else if button is clicked, part time is selected and password is valid
				} else if(button == add && partTime.isSelected()&& isPasswordValid(password.getText())==true && !majorTbox.getText().equals("")) {
					//set color to black
					password.setForeground(Color.BLACK);
					confirm.setForeground(Color.BLACK);
					try {
						//try to make a student with this input
						Student s = new Student(lastName.getText(),firstName.getText(),generateEmail(firstName.getText(),lastName.getText())
						,password.getText(),Project.highestBannerID(),Student.StudentType.PartTime,majorTbox.getText(),Float.parseFloat(remBalTbox.getText()));
						Project.data.add(s);
						System.out.println("student added");
						JOptionPane.showMessageDialog(null, "Success");
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						System.out.println("Student Not added");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Student Not added");
					}
							
					//else if button is clicked, no major is selected, password is valid and major is not declared
				} else if(button == add && noMajor.isSelected()&& isPasswordValid(password.getText())==true && majorTbox.getText().equals(""))  {
					password.setForeground(Color.BLACK);
					confirm.setForeground(Color.BLACK);
					major.setForeground(Color.BLACK);
					try {
						//try to create student
						Student s = new Student(lastName.getText(),firstName.getText(),generateEmail(firstName.getText(),lastName.getText())
						,password.getText(),Project.highestBannerID(),Student.StudentType.NoMajor,Float.parseFloat(remBalTbox.getText()));
						Project.data.add(s);
						System.out.println("student added");
						JOptionPane.showMessageDialog(null, "Success");
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						System.out.println("Student Not added");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Student Not added");
					}
					//else if fulltime and password invalid
				} else if(button == add && fullTime.isSelected() && isPasswordValid(password.getText())==false) {
					password.setForeground(Color.RED);
					confirm.setForeground(Color.RED);
					System.out.println("Student not added");
					JOptionPane.showMessageDialog(null, "Password does not meet the criteria or does not match.");
					
					//else if part time and password invalid
				} else if(button == add && partTime.isSelected() && isPasswordValid(password.getText())==false) {
					password.setForeground(Color.RED);
					confirm.setForeground(Color.RED);
					System.out.println("Student not added");
					JOptionPane.showMessageDialog(null, "Password does not meet the criteria or does not match.");
					//else if no major and password invalid
				} else if(button == add && noMajor.isSelected() && isPasswordValid(password.getText())==false) {
					password.setForeground(Color.RED);
					confirm.setForeground(Color.RED);
					System.out.println("Student not added");
					JOptionPane.showMessageDialog(null, "Password does not meet the criteria or does not match.");
					
					//else if no major and major is declared
				}else if(button == add && noMajor.isSelected() && !majorTbox.getText().equals("")) {
					major.setForeground(Color.RED);
					System.out.println("Student not added");
					JOptionPane.showMessageDialog(null, "Cannot declare a major for type No Major.");
					
					//else if add and no major selected and password invalid 
				} else if(button == add && noMajor.isSelected() && isPasswordValid(password.getText())==false) {
					password.setForeground(Color.RED);
					confirm.setForeground(Color.RED);
					System.out.println("student not added");
					JOptionPane.showMessageDialog(null, "Password does not meet the criteria or does not match.");
				}
				
				//control full time part time must declare major
				//else if fulltime and password valid and major not declared
				else if(button == add && fullTime.isSelected() && isPasswordValid(password.getText())==true && majorTbox.getText().equals("")) {
					major.setForeground(Color.RED);
					JOptionPane.showMessageDialog(null, "Must declare a major for type: Fulltime");
				} else if(button == add && partTime.isSelected() && isPasswordValid(password.getText())==true && majorTbox.getText().equals("")) {
					major.setForeground(Color.RED);
					JOptionPane.showMessageDialog(null, "Must declare a major for type: Part Time");
				}
			
			
					
				
			}
			
			//method that will determine if password is valid and matches confirm password
			public boolean validatePasswordConfirmation(String password) {
				String passwordValiation= confirm.getText();
				
				//password must not contain first name 
				if(password.equals(passwordValiation)) {
					return true;
				} else {
					return false;
				}

			}
			
			//method will check if password meets criteria
			//Passwords must be between 8 and 10 characters, contain at least 1 upper case letter, 1 lower
			//case letter and 1 digit.
			public boolean validatePasswordCriteria(String password) {
				boolean hasUpperCase = false;
				boolean isLength = false;
				boolean hasLowerCase = false;
				boolean hasDigit = false;
				boolean containsName = false;
				
				//check for uppercase,lowercase and digit
				for(int i=0;i<password.length();i++) {
					if(Character.isUpperCase(password.charAt(i))) {
						hasUpperCase = true;
					} 
					
					if(Character.isLowerCase(password.charAt(i))) {
						hasLowerCase = true;
					} 
					
					if(Character.isDigit(password.charAt(i))) {
						hasDigit = true;
					} 
					
				}
				
				//check for length
				if(password.length() <10 || password.length()>8) {
					isLength =true;
				} 
				
				//check for name 
				//if the password (set to lowercase incase first name was set to an uppercase and password has it in lowercase)
				//indexOf returns the index of the first occurrence of the specified substring, or -1 if there is no such occurrence - PER JAVADOC
				//in the case of this example, the password will contain the first or last name if -1 is returned
				if(password.toLowerCase().indexOf(firstName.getText()) != -1 || password.toLowerCase().indexOf(lastName.getText()) != -1) {
					containsName = true;
				}
				
				
				//return value
				if(hasUpperCase == true && isLength == true && hasLowerCase == true && hasDigit == true && containsName == false ) {
					return true;
				} else {
					return false;
				}
				
				
				
			}
			
			public boolean isPasswordValid(String password) {
				if(validatePasswordCriteria(password)== true && validatePasswordConfirmation(password) == true) {
					return true;
				} else {
					return false;
				}
				
			}
			
			
			
			//method that will generate email
			public String generateEmail(String f, String l) {
				
				return f.charAt(0)+l+"@mcc.edu";
				
			}
			
			
	}
}
