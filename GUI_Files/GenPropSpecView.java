package greenfield;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GenPropSpecView extends JPanel{
	
	JButton search;
	multiLabelCheckPanel city, noOfRooms, rent;
	JPanel selectorPanel;
	DisplayView displayPanel;
	JLabel text;
	
	public GenPropSpecView(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		
		selectorPanel = new JPanel(new GridLayout(4,1));
		selectorPanel.setBackground(Color.DARK_GRAY);
		
		displayPanel = new DisplayView();
		displayPanel.setBackground(Color.DARK_GRAY);
		
		search = new JButton("Search");
		
		city = new multiLabelCheckPanel("City");
		noOfRooms = new multiLabelCheckPanel("Number of Rooms");
		rent = new multiLabelCheckPanel("Rent", "Max", "Min");
		
		selectorPanel.add(city);
		selectorPanel.add(noOfRooms);
		selectorPanel.add(rent);
		selectorPanel.add(search);
		
		text = new JLabel();
		displayPanel.add(text);
		
		this.add(selectorPanel, BorderLayout.NORTH);
		this.add(displayPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void addSearchListener2(ActionListener listenForSearchButton) {
		search.addActionListener(listenForSearchButton);
	}
	
}