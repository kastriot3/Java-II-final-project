import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DownloadStatistics extends JFrame{
	
	//fields 
	JButton submit;
	JTextField input;

	
	DownloadStatistics(String title) {
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
		JLabel instructions = new JLabel("Download Statistics - Enter a file path to download data statistics. ");
		
		north.add(instructions);
		
		add(north,BorderLayout.NORTH);
		
		
		//textbox - west 
		JPanel west = new JPanel();
		
		input = new JTextField(15);
		
		
		west.add(input);
		
		add(west,BorderLayout.WEST);
		
		
		
	
		
		//button - east
		
		JPanel east = new JPanel();
		
		//instantiate button
		submit = new JButton("Submit");
				
				
				
		
		
		ButtonHandler bh = new ButtonHandler();
		submit.addActionListener(bh);
		
		east.add(submit);
		
		add(east,BorderLayout.EAST);

		
		
	}
	
	
private class ButtonHandler implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			if(button == submit) {
				try {
					PrintWriter pw = new PrintWriter(input.getText());
					
					//print initial data
					pw.print("Unsorted: "+"\n");
					for(String x: Project.toStringDataStatistics(Project.data)) {
						//print the contents of the arraylist on each new line
						pw.print(x+"\n");
					}
					
					//sort by Last name
					pw.print("\n"+"Sorted by Last Name: "+"\n");
					//convert data to person data
					Project.convertToPersonArray(Project.data);
					Collections.sort(Project.personData);
					for(String x : Project.toStringPersonDataStatistics(Project.personData)) {
						pw.print(x+"\n");
					}
					
					
					//sorts by banner ID
					pw.print("\n"+"Sorted by BannerID: "+"\n");
					Collections.sort(Project.data,new Compare());
					for(String x : Project.toStringDataStatistics(Project.data)) {
						pw.print(x+"\n");
					}
						
					pw.close();
					
					//will always be success since it will write to a new file if needed.
					JOptionPane.showMessageDialog(null, "Success");

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
			
			
		}
	
	
}	
	
	
	
	
}
