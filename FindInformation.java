import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FindInformation extends JFrame {

	//fields 
		JRadioButton faculty,student,crn;
		JButton submit;
		JTextField find;
		JPanel south;
		JLabel info;
		
		
		
		
		//constructor 
		
		public FindInformation(String title) {
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
			
			//instruction label - north
			JPanel north = new JPanel();
			JLabel instructions = new JLabel("Find Information - Please Select Your Option and Enter it's Respective ID: ");
			
			north.add(instructions);
			
			add(north,BorderLayout.NORTH);
			
			
			//radio buttons - west 
			JPanel west = new JPanel();
			
			//javadoc, found a way to make radiobuttons vertical through implementation of Box without having to nest panels, with the inner panel containg a gridlayout set. 
			Box verticalBox = Box.createVerticalBox();
			
			JLabel selectType = new JLabel("Select a Type: ");
			JLabel spacer = new JLabel(" ");
			faculty = new JRadioButton("Faculty");
			student = new JRadioButton("Student");
			crn = new JRadioButton("CRN");
			
			verticalBox.add(selectType);
			verticalBox.add(spacer);
			verticalBox.add(faculty);
			verticalBox.add(student);
			verticalBox.add(crn);
			
			
			west.add(verticalBox);
			
			add(west,BorderLayout.WEST);
			
			//button group
			ButtonGroup bg = new ButtonGroup();
			bg.add(faculty);
			bg.add(student);
			bg.add(crn);
			
			//radio button handler
			ButtonHandler bl = new ButtonHandler();
			faculty.addActionListener(bl);
			student.addActionListener(bl);
			crn.addActionListener(bl);
			
			
			//find user - east
			
			JPanel east = new JPanel();
			
			Box vbox2 = Box.createVerticalBox();
			JLabel enterKey = new JLabel("Enter a Key: ");
			JLabel spacer2 = new JLabel(" ");
			find = new JTextField(15);
			submit = new JButton("Submit");
			
			ButtonHandler bh = new ButtonHandler();
			submit.addActionListener(bh);
			
			vbox2.add(enterKey);
			vbox2.add(find);
			vbox2.add(spacer2);
			vbox2.add(submit);
			
			east.add(vbox2);
			
			add(east,BorderLayout.EAST);

			
			
		}
		
		
	private class ButtonHandler implements ActionListener{

			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
				if(button == submit) {
					String str = Project.containsBannerID(find.getText());
					JOptionPane.showMessageDialog(null, str);
				}
				
			}
		
		
	}	
	
	
}
