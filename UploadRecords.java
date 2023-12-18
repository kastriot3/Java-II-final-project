import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Option 2, If the user chooses this option, the program will display a window that allows the user to type the
 * name/path of the file containing the college records. The program will read the file and store its
 * contents in appropriate classes. The file has a mix of students, faculty and courses. Your code should
 * be able to determine the type of row from the data provided.
 * 
 */
public class UploadRecords extends JFrame{
	Project project = new Project();

	JTextField tbox,tboxAdd;
	JLabel enterPath,errorLabel;
	JButton submit,addFile;
	
	
	
	//create an arraylist of string[]. the string[] will hold our individual datapoints for each line (by index). 
	ArrayList<ArrayList<String>> masterList = new ArrayList<ArrayList<String>>();
	
	
	
	
	
	//constructor
	public UploadRecords() {
		this.setTitle("Upload Records From File");
		this.setSize(600,600);
		this.setResizable(false);
		buildPanel();
		this.setVisible(true);
	}
	
	//buildPanel
	public void buildPanel() {
		
		setLayout(new BorderLayout());
		
		//enter file path label - north
		JPanel north = new JPanel();
		enterPath = new JLabel("Enter File Path: ");
		north.add(enterPath);
		add(north,BorderLayout.NORTH);
		
		//text box and submit button - center
		JPanel center = new JPanel();
		tbox = new JTextField(15);
		
		//button handler
		ButtonHandler bh = new ButtonHandler();
		submit = new JButton("Submit");
		submit.addActionListener(bh);
		
		//textbox and add button - center
		tboxAdd = new JTextField(15);
		addFile =  new JButton("Add File");
		addFile.addActionListener(bh);
		
		center.add(tbox);
		center.add(submit);
		center.add(tboxAdd);
		center.add(addFile);
		add(center,BorderLayout.CENTER);
		
		
		
		
	}
	
	
 private class ButtonHandler implements ActionListener{
	 	
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if(button == submit) {
				uploadFile(tbox.getText());				
				
			} else if(button == addFile) {
				//debuging purposes
				System.out.println("addfile pressed");
				//if add, append file contents(through writing file).
				addFile(tbox.getText());
				
				
			}
				
			
		}
		
		public void uploadFile(String path) {
			//path is set to whatever the user typed into the textbox
			File f = new File(path);
			//rows will be used to convert the rows of data to a String[] for our masterList
			ArrayList<String> rows = new ArrayList<String>();
			//read file
			try {
				//scan through file
				Scanner scan = new Scanner(f);			
				
				//while scanner has the line
				while(scan.hasNextLine()) {
					//add line to rows
					rows.add(scan.nextLine());
				}
				
				
				//for each iteration, until there are no more rows
				for(int i=0;i<rows.size();i++) {
					//str is equal to a row
					String str = rows.get(i);
					//splice the row by comma and space
					String substrings[] = str.split(", ");
					
					//add the dataset of the row to the masterList
					masterList.add(new ArrayList<String>(Arrays.asList(substrings)));
				}
				
//				for each datapoint in list,
				for(int i = 0; i<rows.size();i++) {
					//determine whether or not it is of type person or type course
					try {
						
						if(project.isCourse(masterList.get(i).get(0)) == true) {
							//if it is a course, create and store that type into field data in project.
							Project.data.add(new Course(Integer.parseInt(masterList.get(i).get(0)),masterList.get(i).get(1),Integer.parseInt(masterList.get(i).get(2))));
							
						} else if(project.isCourse(masterList.get(i).get(1))==false) {
							//else if method returned false, check if data set is student.
							if(Project.isStudent(masterList.get(i).get(5))==true) {
								try {
									// if it is a student, create student, with regular constructor 
									Project.data.add(new Student(masterList.get(i).get(0),masterList.get(i).get(1),masterList.get(i).get(2),masterList.get(i).get(3),
									Integer.parseInt(masterList.get(i).get(4)),masterList.get(i).get(5),masterList.get(i).get(6),Float.parseFloat(masterList.get(i).get(7))));
								
								} catch (IndexOutOfBoundsException e2) {
									//if it throws an error, try this constructor if no major and no department
									Project.data.add(new Student(masterList.get(i).get(0),masterList.get(i).get(1),masterList.get(i).get(2),masterList.get(i).get(3),Integer.parseInt(masterList.get(i).get(4))
											,masterList.get(i).get(5), Float.parseFloat(masterList.get(i).get(6))));
									
								}
							
							} else if(Project.isStudent(masterList.get(i).get(5))==false) {
								
								try {
									//else if faculty condition, create faculty object 
									Project.data.add(new Faculty(masterList.get(i).get(0), masterList.get(i).get(1), masterList.get(i).get(2), masterList.get(i).get(3),
											Integer.parseInt(masterList.get(i).get(4)), masterList.get(i).get(5), masterList.get(i).get(6)));
								
								} catch (NumberFormatException e3) {
									//if it throws an error, try this constructor if no major and no department
									Project.data.add(new Faculty(masterList.get(i).get(0), masterList.get(i).get(1), masterList.get(i).get(2), 
											masterList.get(i).get(3),masterList.get(i).get(4), masterList.get(i).get(5)));
									
								}
								
								
								
							}
							
						} 
						
						
						JOptionPane.showMessageDialog(null,"Success");
						
						//Arraylist of strings, holds bad strings, set to one big string. 
					
						
					
					} catch (Exception e1) {
						ArrayList<String> badStrings = new ArrayList<String>();
						//if the data doesn't fit, throw this message
						System.out.println(e1.getMessage());
						String str = e1.getMessage() +"\n";
						badStrings.add(str);
						JOptionPane.showMessageDialog(null,badStrings.toString());
					}	
				}
				
				
				
			} catch (IOException e1) {
				//if file not found, display error in JOptionPane
				JOptionPane.showMessageDialog(null,"Invalid File Name - Please Try Again.");
				System.out.println(e1.getMessage());
				
				//display success message
				
			}
			
			//second round of filtering data. At this point,objects have been instantiated within the "data" ArrayList. We can now apply further filters to get the desired result
			for(int k=0;k<Project.data.size();k++) {
				try {
					Student s = (Student) Project.data.get(k);
					//if major is null, and type is not NoMajor, remove
					if(s.getMajor()==null && !s.getType().equals(Student.StudentType.NoMajor)) {
						Project.data.remove(k);
						//else if major is declared and type is NoMajor, remove
					}
					if(s.getMajor()!= null && s.getTypeInString().equals("NoMajor")) {
						Project.data.remove(k);
					}

					
				} catch (Exception e1) {
					try {
						Faculty fac = (Faculty)Project.data.get(k);
					} catch (Exception e2) {
						
					}
				}
			}
			
			System.out.println(Project.data.toString());
			
		}
	
		//method used to append the existing uploaded file with the contents of a new file. Ex, if final.txt was uploaded, and final1.txt was added, it would add final.txt+final1.txt into final.txt
		public void addFile(String str) {
			
			
			//write to file in tbox
			try {
				PrintWriter pw = new PrintWriter(str);
				//read uploaded file in add file tbox - verify that it is correct.
				uploadFile(tboxAdd.getText());			
				
				//for each string in data passed through toStringData method
				for(String x: Project.toStringData(Project.data)) {
					//print the contents of the arraylist on each new line
					pw.print(x+"\n");
				}
				
				//close pw for good measure
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	
}	
	
	
	
}
