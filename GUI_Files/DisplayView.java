package greenfield;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.util.*;

public class DisplayView extends JPanel{
	JLabel text, titleBanner;
	JPanel listView;
	ArrayList<JLabel> label;
	
	DisplayView(){
		JPanel listView = new JPanel();
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.DARK_GRAY));
		this.add(listView, BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	public void listView(ArrayList<String> entries, String title){
		this.revalidate();
		titleBanner = new JLabel(title);
		titleBanner.setFont(new Font("Copperplate", Font.BOLD, 18));
		titleBanner.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY,  Color.blue));
		titleBanner.setHorizontalAlignment(0);
		listView = new JPanel(new GridLayout(entries.size() + 1, 1));
		listView.add(titleBanner);
		for(int i = 0; i < entries.size(); i++){
			text = new JLabel();
			text.setText(entries.get(i));
			text.setFont(new Font("Copperplate", Font.PLAIN, 12));
			text.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.DARK_GRAY));
			listView.add(text);
		}
		this.add(listView);
		this.revalidate();
	}
	
}

