package greenfield;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class multiLabelCheckPanel extends JPanel{
	JTextField field1, field2;
	JLabel label1, label2, label3;
	JCheckBox checkbox;
	
	//Constructor #1 to create a basic panel that includes one label and one text field
	public multiLabelCheckPanel(String str){
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(400,30));
		this.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		
		label1 = new JLabel(str);
		label1.setEnabled(false);
		cons.gridx = 3;
		cons.gridy = 0;
		cons.gridwidth = 2;
		this.add(label1, cons);
		
		checkbox = new JCheckBox();
		checkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		checkbox.addActionListener(new Checked1());
		checkbox.setSelected(false);
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		this.add(checkbox, cons);
		
		field1 = new JTextField();
		field1.setPreferredSize(new Dimension(90,30));
		field1.setEnabled(false);
		cons.gridx = 6;
		cons.gridy = 0;
		cons.gridwidth = 10;
		this.add(field1, cons);
		
		setVisible(true);
	}
	
	//Constructor #2 to create a panel that includes 3 Labels, 2 text fields, one checkbox
	public multiLabelCheckPanel(String str3, String str1, String str2){
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		this.setPreferredSize(new Dimension(400,30));

		checkbox = new JCheckBox();
		checkbox.setHorizontalTextPosition(SwingConstants.LEFT);
		checkbox.addActionListener(new Checked2());
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 3;
		this.add(checkbox, cons);
		
		label3 = new JLabel(str3 + ":    ");
		label3.setEnabled(false);
		cons.gridx = 6;
		cons.gridy = 0;
		cons.gridwidth = 10;
		this.add(label3, cons);
		
		label1 = new JLabel(str1);
		label1.setEnabled(false);
		cons.gridx = 55;
		cons.gridy = 0;
		cons.gridwidth = 8;
		this.add(label1, cons);
		
		field1 = new JTextField();
		field1.setPreferredSize(new Dimension(80,30));
		field1.setEnabled(false);
		cons.gridx = 37;
		cons.gridy = 0;
		cons.gridwidth = 10;
		this.add(field1, cons);
		
		label2 = new JLabel(str2);
		label2.setEnabled(false);
		cons.gridx = 26;
		cons.gridy = 0;
		cons.gridwidth = 8;
		this.add(label2, cons);
		
		field2 = new JTextField();
		field2.setPreferredSize(new Dimension(80,30));
		field2.setEnabled(false);
		cons.gridx = 66;
		cons.gridy = 0;
		cons.gridwidth = 10;
		this.add(field2, cons);
	
		setVisible(true);
	}

	//actionListener for constructor #1 
	public class Checked1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean next = false;
			if (checkbox.isSelected()){
				next = true;
			}
			field1.setEnabled(next);
			label1.setEnabled(next);
		}
	
	}
	
	//action listener for constructor #2
	public class Checked2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean next = false;
			if (checkbox.isSelected()){
				next = true;
			}
			label1.setEnabled(next);
			field1.setEnabled(next);
			field2.setEnabled(next);
			label2.setEnabled(next);
			label3.setEnabled(next);
		}
	}

}
