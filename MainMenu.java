import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainMenu extends JFrame{

	//fields 
	JRadioButton uploadFile,addStudent,downloadStats,findInfo;
	JButton submit,exit;
	
	
	
	
	//constructor 
	
	public MainMenu(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setSize(600,600);
		this.setResizable(false);
		buildPanel();
		this.setVisible(true);
	}
	
	//build panel method 
	public void buildPanel() {
	
		setLayout(new BorderLayout(10,0));
		
		//welcome label
		JPanel north = new JPanel();
		JLabel welcome = new JLabel("Welcome to MCC's Record System. Please Choose From the Following Options:  ");
		
		north.add(welcome);
		
		add(north,BorderLayout.NORTH);
		
		
		//radio buttons - west 
		JPanel west = new JPanel();
		
		//javadoc, found a way to make radiobuttons vertical through implementation of Box without having to nest panels, with the inner panel containg a gridlayout set. 
		Box verticalBox = Box.createVerticalBox();
				
		uploadFile = new JRadioButton("Upload Records from File");
		addStudent = new JRadioButton("Add New Student");
		downloadStats = new JRadioButton("Download Statistics");
		findInfo = new JRadioButton("Find Information");
		
		verticalBox.add(uploadFile);
		verticalBox.add(addStudent);
		verticalBox.add(downloadStats);
		verticalBox.add(findInfo);
		
		
		west.add(verticalBox);
		
		add(west,BorderLayout.WEST);
		
		//button group
		ButtonGroup bg = new ButtonGroup();
		bg.add(uploadFile);
		bg.add(addStudent);
		bg.add(downloadStats);
		bg.add(findInfo);
		
		//radio button handler
		ButtonHandler bl = new ButtonHandler();
		uploadFile.addActionListener(bl);
		addStudent.addActionListener(bl);
		downloadStats.addActionListener(bl);
		findInfo.addActionListener(bl);
		
		//MCC campus picture - east
		
		JPanel east = new JPanel();
		
		ImageIcon image = new ImageIcon(getClass().getResource("mcc-square.png"));
		JLabel mccField = new JLabel(image);
		east.add(mccField);
		add(east,BorderLayout.EAST);
		
		//buttons - south side of panel
		
		JPanel south = new JPanel();
		
		//regular button handler
		ButtonHandler bh = new ButtonHandler();
		
		submit = new JButton("Submit");
		exit = new JButton("Exit");
		submit.addActionListener(bh);
		exit.addActionListener(bh);
		south.add(submit);
		south.add(exit);
		add(south, BorderLayout.SOUTH);
		
		
	}
	
	
private class ButtonHandler implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if(button == exit) {
				//exit
				System.exit(0);
			} else if(button == submit && uploadFile.isSelected()) {
				//open option 1
				UploadRecords x = new UploadRecords();
				
			} else if(button == submit && addStudent.isSelected()) {
				//open option 2
				AddStudent x = new AddStudent("Add Student");
				
			} else if(button == submit && downloadStats.isSelected()) { 
				//open option 3
				DownloadStatistics x = new DownloadStatistics("Download Statistics");
				
			} else if(button == submit && findInfo.isSelected()) {
				//open option 4
				FindInformation x = new FindInformation("Find Information");
			}
				
			
		}
	
	
}	
	
	
	
	public static void main(String[] args) {

		MainMenu m = new MainMenu("MCC Admin Tools");
		
	}


}
