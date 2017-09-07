package greenfield;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InputGridPanel extends JPanel{
	
	JButton insertButton;
	JDBCConnection con;
	ArrayList<BasicInputField> inputFields;
	int paneNumber;
	BasicInputField temp;
	
	public InputGridPanel(JDBCConnection con, ArrayList<String> inputs, int pane){
		this.setLayout(new GridLayout(1, inputs.size()));
		this.inputFields = new ArrayList<BasicInputField>();
		for(int i = 0; i < inputs.size(); i++){
			temp = new BasicInputField(inputs.get(i), 0);
			this.add(temp);
			this.inputFields.add(temp);
			//this.add(new BasicInputField(inputs.get(i), 0));
		}
		this.paneNumber = pane;
		this.con = con;
		if (this.paneNumber == 7){
			insertButton = new JButton("delete");

		}else{
			insertButton = new JButton("insert");
		}
		insertButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doInsert();		
			}
			
		});
		this.add(insertButton);
		this.setVisible(true);
	}
	
	public InputGridPanel(JDBCConnection con,ArrayList<String> inputs, int pane, int q){
		this.setLayout(new GridLayout(2, inputs.size()));
		this.inputFields = new ArrayList<BasicInputField>();
		for(int i = 0; i < inputs.size(); i++){
			temp = new BasicInputField(inputs.get(i), 0);
			this.add(temp);
			this.inputFields.add(temp);
			//this.add(new BasicInputField(inputs.get(i), i));
		}
		this.paneNumber = pane;
		this.con = con;
		insertButton = new JButton("insert");
		insertButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doInsert();
				
			}
			
		});
		this.add(insertButton);
		this.setVisible(true);
	}
	
	public void addInsertListener(ActionListener listenForSearchButton) {
		insertButton.addActionListener(listenForSearchButton);
	}
	
	public void doInsert(){
		switch(paneNumber){
			case 1: 
				String branchNum1 = inputFields.get(0).field.getText().toString();
				String phone = inputFields.get(1).field.getText().toString();
				String street = inputFields.get(2).field.getText().toString();
				String city = inputFields.get(3).field.getText().toString();
				String zip = inputFields.get(4).field.getText().toString();
				//database
				con.insertIntoBranch(branchNum1, phone, street, city, zip);
				break;	
			case 2: 
				String employeeId = inputFields.get(0).field.getText().toString();
				String name = inputFields.get(1).field.getText().toString();
				String jobdescription = inputFields.get(2).field.getText().toString();
				String startdate = inputFields.get(3).field.getText().toString();
				String branchNum2 = inputFields.get(4).field.getText().toString();
				String phone_opt;
				if (inputFields.get(5).field.getText().isEmpty()){
					phone_opt = null;
				}else{
					phone_opt = inputFields.get(5).field.getText().toString();
				}
				con.insertIntoEmployee(employeeId, name, jobdescription, startdate, branchNum2, phone_opt);
				break;	
			case 3: 
				String renterId = inputFields.get(0).field.getText().toString();
				String name3 = inputFields.get(1).field.getText().toString();
				String homephone = inputFields.get(2).field.getText().toString();
				String workphone = inputFields.get(3).field.getText().toString();
				String contactname = inputFields.get(4).field.getText().toString();
				String contactphone = inputFields.get(5).field.getText().toString();
				con.insertIntoRenter(renterId, name3, homephone, workphone, contactname, contactphone);
				break;	
			case 4: 
				String propOwnerId = inputFields.get(0).field.getText().toString();
				String name4 = inputFields.get(1).field.getText().toString();
				String street4 = inputFields.get(2).field.getText().toString();
				String city4 = inputFields.get(3).field.getText().toString();
				String zip4 = inputFields.get(4).field.getText().toString();
				con.insertIntoPropOwner(propOwnerId, name4, street4, city4, zip4);
				break;	
			case 5: 
				String leaseId = inputFields.get(0).field.getText().toString();
				String renterId5 = inputFields.get(1).field.getText().toString();
				String propNumber5 = inputFields.get(2).field.getText().toString();
				String startdate5 = inputFields.get(3).field.getText().toString();
				String endDate5 = inputFields.get(4).field.getText().toString();
				String deposit = inputFields.get(5).field.getText().toString();
				con.insertIntoLeaseAgreement(leaseId, renterId5, propNumber5, startdate5, endDate5, deposit);
				break;	
			case 6: 
				String propNumber6 = inputFields.get(0).field.getText().toString();
				String street6 = inputFields.get(1).field.getText().toString();
				String city6 = inputFields.get(2).field.getText().toString();
				String zip6 = inputFields.get(3).field.getText().toString();
				String numRooms6 = inputFields.get(4).field.getText().toString();
				String initRent = inputFields.get(5).field.getText().toString();
				String status = inputFields.get(6).field.getText().toString();
				String startdate6 = inputFields.get(7).field.getText().toString();
				String fee = inputFields.get(8).field.getText().toString();
				String employeeId6 = inputFields.get(9).field.getText().toString();
				String propOwnerId6 = inputFields.get(10).field.getText().toString();
				con.insertIntoRentalProp(propNumber6, street6, city6, zip6, numRooms6, initRent, status, startdate6, fee, employeeId6, propOwnerId6);
				break;
			case 7:
				String propNumber7 = inputFields.get(0).field.getText().toString();
				con.deleteRentalProp(propNumber7);
				break;
			default:
				break;
		
		}
	}
}
