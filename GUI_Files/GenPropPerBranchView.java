package greenfield;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class GenPropPerBranchView extends JPanel{
	BasicInputField inpField;
	JButton search;
	JPanel selectorPanel;
	JLabel text;
	DisplayView displayPanel;
	
	public GenPropPerBranchView(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		
		selectorPanel = new JPanel(new GridLayout(2,1));
		selectorPanel.setBackground(Color.DARK_GRAY);
		
		displayPanel = new DisplayView();
		displayPanel.setBackground(Color.DARK_GRAY);
		
		//need to add actionListener to take in the input from the text field
		search = new JButton("Search");

		inpField = new BasicInputField("Branch Number: ");
		
		text = new JLabel();
		
		selectorPanel.add(inpField);
		selectorPanel.add(search);
		
		displayPanel.add(text, BorderLayout.CENTER);
		
		this.add(selectorPanel, BorderLayout.NORTH);
		this.add(displayPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void addSearchListener(ActionListener listenForSearchButton) {
		search.addActionListener(listenForSearchButton);
	}

}