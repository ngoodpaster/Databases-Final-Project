package greenfield;

import javax.swing.*;
import java.awt.*;

public class BasicInputField extends JPanel{
	
	JLabel label;
	JTextField field;
	
	//Constructor to create a single label with a single field
	//Can be used to search by a single entity branch, employee, etc. 
	public BasicInputField(String str){
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		
		label = new JLabel(str);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 15;
		this.add(label, cons);
		
		field = new JTextField();
		field.setPreferredSize(new Dimension(90,30));
		cons.gridx = 20;
		cons.gridy = 0;
		cons.gridwidth = 50;
		this.add(field, cons);
		
		//this.add(label);
		//this.add(field);
		this.setVisible(true);
	}
	
	public BasicInputField(String str, int i){
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.VERTICAL;
		
		label = new JLabel(str);
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 15;
		this.add(label, cons);
		
		field = new JTextField();
		field.setPreferredSize(new Dimension(90,30));
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 50;
		this.add(field, cons);
		
		//this.add(label);
		//this.add(field);
		this.setVisible(true);
	}
}
