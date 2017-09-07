package greenfield;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DisplayController {
		DisplayView displayPanel;
		GenPropPerBranchView branchPanel;
		GenPropSpecView criteriaPanel;
		JDBCConnection start;
		JLabel text;
		
		public DisplayController(JDBCConnection start, DisplayView displayPanel, GenPropPerBranchView branchPanel, GenPropSpecView criteriaPanel){
			this.displayPanel = displayPanel;
			this.branchPanel = branchPanel;
			this.criteriaPanel = criteriaPanel;
			this.start = start;
			
			this.branchPanel.addSearchListener(new SearchListener());
			this.criteriaPanel.addSearchListener2(new SearchListener2());
			
			//this.branchPanel.addSearchListener2(new SearchListener2());
			 
		}
		/*
		public void updateCriteriaDisplay(String str){
			criteriaPanel.displayPanel.add(new JLabel(str), BorderLayout.CENTER);
		}
		
		public void updateTextOnDisplay(String str){
			JLabel label = new JLabel(str);
			displayPanel.setVisible(false);
			//displayPanel.remove(JLabel);
			System.out.println(str);
			displayPanel.add(label);
			displayPanel.setVisible(true);
		}
		
		public void updateBranchDisplay(String text){
			//branchPanel.displayPanel.removeAll();
			branchPanel.displayPanel.add(new JButton(text), BorderLayout.CENTER);
		}
		*/
		
		
	
		//ActionListener for the branchPanel 
		class SearchListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("aye");
				String temp = "";
				ArrayList<String> properties = new ArrayList<String>();
				String fieldText = branchPanel.inpField.field.getText().toString();
				String labelText = branchPanel.inpField.label.getText().toString();
				switch(labelText){
					case "RenterId: ":
						temp = start.genLease(fieldText);
						if(temp != null){
							String[] sup_props = temp.split("[;]+");
							//parse temp into properties
							for(int i = 0;i < sup_props.length; i++){
								properties.add(sup_props[i]);
								System.out.println(sup_props[i]);
							}
						}
						branchPanel.displayPanel.listView(properties, "Leases for Renter Id");
						break;
					case "City: ":
						Integer special = start.genPropAvgByTown(fieldText);
						if(special != null){
							properties.add("Property Average in " + fieldText + ": " + special.toString());
						}
						branchPanel.displayPanel.listView(properties, "Average Rent by City");
						break;
					case "OwnerId: ":
						temp = start.genPropByOwner(fieldText);
						if(temp != null){
							String[] sup_props1 = temp.split("[;]+");
							//parse temp into properties
							for(int i = 0;i < sup_props1.length; i++){
								properties.add(sup_props1[i]);
								System.out.println(sup_props1[i]);
							}
						}
						//parse into properties
						branchPanel.displayPanel.listView(properties, "Properties for Owner Id");
						break;
					default:
						break;
				}
				
				//call the correct database function (may need to declare a variable for this
				// and use if statements for different function calls)
				
				//load database values into this arrayList of strings
				
				
			}

		}
		/*class SearchListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e){
				String fieldText = branchPanel.inpField.field.getText().toString();
				String properties = start.genPropListPerBranch(fieldText);
				updateBranchDisplay(properties);
			}
		}*/
		/*
		class SearchListener2 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("im here");
				
				updateCriteriaDisplay(properties);
			}
		}
		*/
		//ActionListener for the criteriaPanel
		class SearchListener2 implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("aye");
				//call database function
				String properties = "";
				String city;
				
				String noOfRooms1;
				int noOfRooms;
				
				String rentMin1;
				int rentMin;
				
				String rentMax1;
				int rentMax;
				
				if(criteriaPanel.city.checkbox.isSelected() && criteriaPanel.noOfRooms.checkbox.isSelected() && criteriaPanel.rent.checkbox.isSelected()){
					city = criteriaPanel.city.field1.getText().toString();
					
					noOfRooms1 = criteriaPanel.noOfRooms.field1.getText().toString();
					noOfRooms = Integer.parseInt(noOfRooms1);
					
					rentMin1 = criteriaPanel.rent.field1.getText().toString();
					rentMin = Integer.parseInt(rentMin1);
					
					rentMax1 = criteriaPanel.rent.field2.getText().toString();
					rentMax = Integer.parseInt(rentMax1);
					
					properties = start.genPropBySpec_all(city, noOfRooms, rentMin, rentMax);
				} else if(criteriaPanel.city.checkbox.isSelected() && criteriaPanel.noOfRooms.checkbox.isSelected()){
					city = criteriaPanel.city.field1.getText().toString();
					noOfRooms1 = criteriaPanel.noOfRooms.field1.getText().toString();
					noOfRooms = Integer.parseInt(noOfRooms1);
					
					properties = start.genPropBySpec_city_room(city, noOfRooms);
				} else if(criteriaPanel.noOfRooms.checkbox.isSelected() && criteriaPanel.rent.checkbox.isSelected()){
					noOfRooms1 = criteriaPanel.noOfRooms.field1.getText().toString();
					noOfRooms = Integer.parseInt(noOfRooms1);
					rentMin1 = criteriaPanel.rent.field1.getText().toString();
					rentMin = Integer.parseInt(rentMin1);
					
					rentMax1 = criteriaPanel.rent.field2.getText().toString();
					rentMax = Integer.parseInt(rentMax1);
					
					properties = start.genPropBySpec_room_rent(noOfRooms, rentMin, rentMax);
					
				} else if(criteriaPanel.city.checkbox.isSelected() && criteriaPanel.rent.checkbox.isSelected()){
					city = criteriaPanel.city.field1.getText().toString();
					rentMin1 = criteriaPanel.rent.field1.getText().toString();
					rentMin = Integer.parseInt(rentMin1);
					
					rentMax1 = criteriaPanel.rent.field2.getText().toString();
					rentMax = Integer.parseInt(rentMax1);
					properties = start.genPropBySpec_city_rent(city, rentMin, rentMax);
				} else if(criteriaPanel.city.checkbox.isSelected()){
					city = criteriaPanel.city.field1.getText().toString();
					
					properties = start.genPropBySpec_city(city);
				} else if(criteriaPanel.noOfRooms.checkbox.isSelected()){
					noOfRooms1 = criteriaPanel.noOfRooms.field1.getText().toString();
					noOfRooms = Integer.parseInt(noOfRooms1);
					
					properties = start.genPropBySpec_room(noOfRooms);
				} else if(criteriaPanel.rent.checkbox.isSelected()){
					rentMin1 = criteriaPanel.rent.field1.getText().toString();
					rentMin = Integer.parseInt(rentMin1);
					
					rentMax1 = criteriaPanel.rent.field2.getText().toString();
					rentMax = Integer.parseInt(rentMax1);
					
					properties = start.genPropBySpec_rent(rentMin, rentMax);
				}
				ArrayList<String> testlist = new ArrayList<String>();
				if(properties != null){
					String[] sup_props = properties.split("[;]+");
					//parse temp into properties
					for(int i = 0;i < sup_props.length; i++){
						testlist.add(sup_props[i]);
						//System.out.println(sup_props[i]);
					}
					//load database values (properties) into this arrayList of strings
					//testlist.add(properties);
				}
				criteriaPanel.displayPanel.listView(testlist, "Properties by Specification");
			}

		}
}
