package greenfield;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.*;

public class SystemWindow extends JFrame implements ActionListener{
	//Declarations
		JDBCConnection start;
		private JPanel basePanel, adminTabPanel, adminPanel, customerPanel, customerLeftPanel, cards, cards2, cards3, showPropertiesPanel, propLeftPanel, createLeasePanel, showLeasePanel, leaseLeftPanel;
		private JLabel greenfieldTitle, welcomeLabel, text;
		private JTabbedPane adminCustomerTabbedPane, adminTabbedPane;
		private JButton showPropertiesButton, createLeaseButton, showLeaseButton, renterLease, multRenters, avgRent, expLease;
		private GenPropSpecView criteriaPanel2, criteriaPanel3;
		private GenPropPerBranchView branchPanel2, branchPanel3;
		private DisplayView displayPanel2, displayPanel3;
		private DisplayController displayController2, displayController3;
		private JPanel addBranchPanel, addEmployeePanel, addRenterPanel, addPropertyOwnerPanel, addLeaseAgreementPanel, addRentalPropertyPanel, deletePropertyPanel;
		//Buttons for Show Properties Available Button
		private JButton allProps, supsProps, specOwnProps, criteriaProps, numProps;
		
		//Below are the buttons for the adminPanel
		private JButton addBranch, addEmployee, addPropOwner, addRenter, addRentalProp, addLease;
		public SystemWindow(JDBCConnection start){
			super("Rental Management System");
			setSize(1500, 700);
			this.start = start;
			//Container and Panels
			Container container = getContentPane();
			container.setLayout(new BorderLayout());
			basePanel = new JPanel();
			basePanel.setLayout(new BorderLayout());
			
			customerPanel = new JPanel();
			customerPanel.setLayout(new BorderLayout());
			
			adminTabPanel = new JPanel();
			adminTabPanel.setLayout(new BorderLayout());//TODO: Needs to set adminPanel as north, and cards2 as center
			
			adminPanel = new JPanel();
			adminPanel.setLayout(new FlowLayout());
			
			customerLeftPanel = new JPanel();
			customerLeftPanel.setLayout(new GridLayout(3,1));
			
			showPropertiesPanel = new JPanel();
			showPropertiesPanel.setLayout(new BorderLayout());//has a left side with buttons(propLeftPanel) and a right side with a view
			
			showLeasePanel = new JPanel();
			showLeasePanel.setLayout(new BorderLayout());//has a left side with buttons(leaseLeftPanel) and a right side with a view
			
			createLeasePanel = new JPanel();
			createLeasePanel.setLayout(new GridLayout(8,1));
			
			propLeftPanel = new JPanel();
			propLeftPanel.setLayout(new GridLayout(5,1));
			
			leaseLeftPanel = new JPanel();
			leaseLeftPanel.setLayout(new GridLayout(4,1));
			
			displayPanel2 = new DisplayView();
			displayPanel2.setLayout(new BorderLayout());
			
			displayPanel3 = new DisplayView();
			displayPanel3.setLayout(new BorderLayout());
			
			
			branchPanel2 = new GenPropPerBranchView();
			branchPanel3 = new GenPropPerBranchView();
			//displayPanel.setVisible(true);
			
			criteriaPanel2 = new GenPropSpecView();
			criteriaPanel3 = new GenPropSpecView();
			
			displayController2 = new DisplayController(start, displayPanel2, branchPanel2, criteriaPanel2);
			displayController3 = new DisplayController(start, displayPanel3, branchPanel3, criteriaPanel3);
			
			
			cards = new JPanel(new CardLayout());
			JPanel defaultPanel1 = new JPanel();
			JPanel defaultPanel2 = new JPanel();
			JPanel defaultPanel3 = new JPanel();
			
			//defaultPanel.setName("defaultPanelLol");
			cards.add(defaultPanel1, "default");
			cards.add(showPropertiesPanel, "showPropertiesPanel");
			//cards.add(createLeasePanel, "createLeasePanel");
			cards.add(showLeasePanel, "showLeasePanel");
			
			cards2 = new JPanel(new CardLayout());
			cards2.setBackground(Color.blue);
			cards2.add(defaultPanel2, "default");
			cards2.add(displayPanel2, "displayPanel");
			cards2.add(branchPanel2, "branchPanel");
			cards2.add(criteriaPanel2, "criteriaPanel");
			
			cards3 = new JPanel(new CardLayout());
			cards3.add(defaultPanel3, "default");
			cards3.add(displayPanel3, "displayPanel");
			cards3.add(branchPanel3, "branchPanel");
			cards3.add(criteriaPanel3, "criteriaPanel");
			
			ArrayList<String> deletePropInputs = new ArrayList<String>();
			deletePropInputs.add("Property Number: ");
			
			ArrayList<String> branchInputs = new ArrayList<String>();
			branchInputs.add("Branch Number: ");
			branchInputs.add("Phone: ");
			branchInputs.add("Street Address: ");
			branchInputs.add("City: ");
			branchInputs.add("Zip: ");
			ArrayList<String> employeeInputs = new ArrayList<String>();
			employeeInputs.add("EmployeeId: ");
			employeeInputs.add("Name: ");
			employeeInputs.add("Job Designation: ");
			employeeInputs.add("Start Date (dd-mm-yy): ");
			employeeInputs.add("Branch Number: ");
			employeeInputs.add("Phone (optional): ");
			ArrayList<String> renterInputs = new ArrayList<String>();
			renterInputs.add("Renter Id: ");
			renterInputs.add("Name: ");
			renterInputs.add("Home Phone: ");
			renterInputs.add("Work Phone: ");
			renterInputs.add("Contact Name: ");
			renterInputs.add("Contact Phone: ");
			ArrayList<String> propertyOwnerInputs = new ArrayList<String>();
			propertyOwnerInputs.add("Property Owner Id: ");
			propertyOwnerInputs.add("Name: ");
			propertyOwnerInputs.add("Street Address: ");
			propertyOwnerInputs.add("City: ");
			propertyOwnerInputs.add("Zip: ");
			ArrayList<String> leaseInputs = new ArrayList<String>();
			leaseInputs.add("Lease Id: ");
			leaseInputs.add("Renter Id: ");
			leaseInputs.add("Property Number: ");
			leaseInputs.add("Start Date(dd-mm-yy)");
			leaseInputs.add("End Date(dd-mm-yy)");
			leaseInputs.add("Deposit Amount");
			ArrayList<String> propertyInputs = new ArrayList<String>();
			propertyInputs.add("Property Number: ");
			propertyInputs.add("Street Address: ");
			propertyInputs.add("City: ");
			propertyInputs.add("Zip: ");
			propertyInputs.add("Number of Rooms: ");
			propertyInputs.add("Initial Monthly Rent");
			propertyInputs.add("Status");
			propertyInputs.add("Start Date(mm-dd-yy): " );
			propertyInputs.add("Fee: ");
			propertyInputs.add("Employee Id: ");
			propertyInputs.add("Property Owner Id: ");
			
			addBranchPanel = new InputGridPanel(start, branchInputs, 1);
			addEmployeePanel = new InputGridPanel(start, employeeInputs, 2);
			addRenterPanel = new InputGridPanel(start, renterInputs, 3);
			addPropertyOwnerPanel = new InputGridPanel(start, propertyOwnerInputs, 4);
			addLeaseAgreementPanel = new InputGridPanel(start, leaseInputs, 5);
			addRentalPropertyPanel = new InputGridPanel(start, propertyInputs,6, 68);
			deletePropertyPanel = new InputGridPanel(start, deletePropInputs, 7);

			
			adminTabbedPane = new JTabbedPane();
			adminTabbedPane.addTab("Add Branch", addBranchPanel);
			adminTabbedPane.addTab("Add Employee", addEmployeePanel);
			adminTabbedPane.addTab("Add Renter", addRenterPanel);
			adminTabbedPane.addTab("Add Property Owner", addPropertyOwnerPanel);
			adminTabbedPane.addTab("Add Rental Property",  addRentalPropertyPanel);
			adminTabbedPane.addTab("Add Lease Agreement", addLeaseAgreementPanel);
			adminTabbedPane.addTab("Delete Rental Property", deletePropertyPanel);
			
			//Labels
			greenfieldTitle = new JLabel("Greenfield's Rental Management Inc.");
			greenfieldTitle.setFont(new Font("Copperplate", Font.BOLD, 24));
			greenfieldTitle.setHorizontalAlignment(0);
			greenfieldTitle.setBorder(BorderFactory.createBevelBorder(1));
			basePanel.add(greenfieldTitle, BorderLayout.NORTH);
			welcomeLabel = new JLabel("Choose any option below to get started.");
			customerLeftPanel.add(welcomeLabel);
			
			//Tabbed Pane
			adminCustomerTabbedPane = new JTabbedPane();
			adminCustomerTabbedPane.addTab("Customer", customerPanel);
			adminCustomerTabbedPane.addTab("Admin", adminTabPanel);
			basePanel.add(adminCustomerTabbedPane, BorderLayout.CENTER);
			adminTabPanel.add(adminTabbedPane, BorderLayout.CENTER);
			//adminTabPanel.add(adminPanel, BorderLayout.NORTH);
			
			




			
			
			
			
			//Buttons for adminPanel
			addBranch = new JButton("Add Branch");
			addBranch.addActionListener(this);
			addBranch.setActionCommand("addBranch");
			adminPanel.add(addBranch);
			addEmployee = new JButton("Add Employee");
			addEmployee.addActionListener(this);
			addEmployee.setActionCommand("addEmployee");
			adminPanel.add(addEmployee);
			addPropOwner = new JButton("Add Property Owner");
			addPropOwner.addActionListener(this);
			addPropOwner.setActionCommand("addPropOwner");
			adminPanel.add(addPropOwner);
			addRenter = new JButton("Add Renter");
			addRenter.addActionListener(this);
			addRenter.setActionCommand("addRenter");
			adminPanel.add(addRenter);
			addRentalProp = new JButton("Add Rental Property");
			addRentalProp.addActionListener(this);
			addRentalProp.setActionCommand("addRentalProp");
			adminPanel.add(addRentalProp);
			addLease = new JButton("Add Lease Agreement");
			addLease.addActionListener(this);
			addLease.setActionCommand("addLease");
			adminPanel.add(addLease);
			
			
			//Buttons for customerLeftPanel
			showPropertiesButton = new JButton("Show Properties Available");
			showPropertiesButton.addActionListener(this);
			showPropertiesButton.setActionCommand("showPropertiesButton");
			customerLeftPanel.add(showPropertiesButton);
			createLeaseButton = new JButton("Create Lease Agreement");
			createLeaseButton.addActionListener(this);
			createLeaseButton.setActionCommand("createLeaseButton");
			//customerLeftPanel.add(createLeaseButton);
			showLeaseButton = new JButton("Show Lease Agreement");
			showLeaseButton.addActionListener(this);
			showLeaseButton.setActionCommand("showLeaseButton");
			customerLeftPanel.add(showLeaseButton);
			
			//Buttons for propLeftPanel
			allProps = new JButton("Show All Properties Available");
			allProps.addActionListener(this);
			allProps.setActionCommand("allProps");
			propLeftPanel.add(allProps);
			
			supsProps = new JButton("Supervisors and Their Properties");
			supsProps.addActionListener(this);
			supsProps.setActionCommand("supsProps");
			propLeftPanel.add(supsProps);
			
			specOwnProps = new JButton("All Properties for Specific Owner");
			specOwnProps.addActionListener(this);
			specOwnProps.setActionCommand("specOwnProps");
			propLeftPanel.add(specOwnProps);
			
			criteriaProps = new JButton("Enter Criteria for Properties");
			criteriaProps.addActionListener(this);
			criteriaProps.setActionCommand("criteriaProps");
			propLeftPanel.add(criteriaProps);
			
			numProps = new JButton("Number of Properties Available by Branch");
			numProps.addActionListener(this);
			numProps.setActionCommand("numProps");
			propLeftPanel.add(numProps);
			
			//Buttons for leaseLeftPanel
			renterLease = new JButton("Show Lease for a Given Renter");
			renterLease.addActionListener(this);
			renterLease.setActionCommand("renterLease");
			leaseLeftPanel.add(renterLease);
			multRenters = new JButton("Show Renters Who Rented >1 Property");
			multRenters.addActionListener(this);
			multRenters.setActionCommand("multRenters");
			leaseLeftPanel.add(multRenters);
			avgRent = new JButton("Show Average Rent for Properties in a Town");
			avgRent.addActionListener(this);
			avgRent.setActionCommand("avgRent");
			leaseLeftPanel.add(avgRent);
			expLease = new JButton("Show Properties Whose Leases Expire in <2 Months");
			expLease.addActionListener(this);
			expLease.setActionCommand("expLease");
			leaseLeftPanel.add(expLease);
			
			
			
			
			
			//Adding to Container and Panels
			container.add(basePanel);
			customerPanel.add(customerLeftPanel, BorderLayout.WEST);
			customerPanel.add(cards, BorderLayout.CENTER);
			showPropertiesPanel.add(propLeftPanel, BorderLayout.WEST);
			showPropertiesPanel.add(cards2, BorderLayout.CENTER);//LOOK: Replace cards2 with something else. A panel that contains other stuff.		
			//showPropertiesPanel.add(displayPanel, BorderLayout.CENTER);
			showLeasePanel.add(leaseLeftPanel, BorderLayout.WEST);
			showLeasePanel.add(cards3, BorderLayout.CENTER);
			
			
			
			
			
			//Show the frame in the center of the screen
			this.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//Three Buttons on the Left
			if (e.getActionCommand().equals("showPropertiesButton")){
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, "showPropertiesPanel");
			} else if (e.getActionCommand().equals("createLeaseButton")){
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "createLeasePanel");
			} else if (e.getActionCommand().equals("showLeaseButton")){
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "showLeasePanel");
			}
			
			else if(e.getActionCommand().equals("allProps")){
				CardLayout cl = (CardLayout)(cards2.getLayout());
				String temp = start.genAllProps();
				displayController2.displayPanel.removeAll();
				//parse into arraylist
				//load properties into this array
				ArrayList<String> properties = new ArrayList<String>();
				if(temp != null){
					String[] sup_props = temp.split("[;]+");
					for(int i = 0;i < sup_props.length; i++){
						properties.add(sup_props[i]);
						System.out.println(sup_props[i]);
					}
				}
				displayController2.displayPanel.listView(properties, "Available Properties by Branch");
				displayController2.displayPanel.repaint();
				displayController2.displayPanel.revalidate();
				
				cl.show(cards2,"displayPanel");
				
			} else if(e.getActionCommand().equals("supsProps")){
				CardLayout cl = (CardLayout)(cards2.getLayout());
				displayController2.displayPanel.removeAll();
				ArrayList<String> properties = new ArrayList<String>();
				String temp = start.genSupervisors();
				//parse into arraylist SUPERVISORS empNAme (empNo): 
				if(temp != null){	
					String[] sup_props = temp.split("[;]+");
					for(int i = 0;i < sup_props.length; i++){
						properties.add(sup_props[i]);
						System.out.println(sup_props[i]);
					}
				}
				//properties.add("garbage");
				displayController2.displayPanel.listView(properties, "List of Supervisors and Properties");
				displayController2.displayPanel.repaint();
				displayController2.displayPanel.revalidate();
				cl.show(cards2, "displayPanel");
				
				
			}else if(e.getActionCommand().equals("specOwnProps")){
				CardLayout cl = (CardLayout)(cards2.getLayout());
				displayController2.branchPanel.inpField.label.setText("OwnerId: ");
				cl.show(cards2, "branchPanel");
				
			}else if(e.getActionCommand().equals("criteriaProps")){
				// all the rest is done inside of the criteriaPanel's action listener in the 
				//displayController
				CardLayout cl = (CardLayout)(cards2.getLayout());
				cl.show(cards2, "criteriaPanel");
				//displayController2.criteriaPanel.revalidate();
			}else if(e.getActionCommand().equals("numProps")){
				displayController2.displayPanel.removeAll();
				CardLayout cl = (CardLayout)(cards2.getLayout());
				String temp = start.genPropPerBranch();
				ArrayList<String> properties = new ArrayList<String>();
				String[] sup_props = temp.split("[;]+");
				//parse temp into properties
				for(int i = 0;i < sup_props.length; i++){
					properties.add(sup_props[i]);
					System.out.println(sup_props[i]);
				}
				
				displayController2.displayPanel.listView(properties, "Number of Properties Per Branch");
				displayController2.displayPanel.repaint();
				displayController2.displayPanel.revalidate();
				cl.show(cards2, "displayPanel");
				
			}
			//Show Lease Buttons
			else if (e.getActionCommand().equals("renterLease")){
				CardLayout cl = (CardLayout)(cards3.getLayout());
				displayController3.branchPanel.displayPanel.removeAll();
				displayController3.branchPanel.inpField.label.setText("RenterId: ");
				displayController3.branchPanel.displayPanel.repaint();
				displayController3.branchPanel.displayPanel.revalidate();
				cl.show(cards3, "branchPanel");
			}else if (e.getActionCommand().equals("multRenters")){
				displayController3.displayPanel.removeAll();
				CardLayout cl = (CardLayout)(cards3.getLayout());
				String temp = start.genMultLease();
				ArrayList<String> properties = new ArrayList<String>();
				//parse temp into properties
				if(temp != null){
					String[] sup_props = temp.split("[;]+");
					//parse temp into properties
					for(int i = 0;i < sup_props.length; i++){
						properties.add(sup_props[i]);
						System.out.println(sup_props[i]);
					}
				}
				displayController3.displayPanel.listView(properties, "Renters with Multiple Properties:");
				displayController3.displayPanel.repaint();
				displayController3.displayPanel.revalidate();
				cl.show(cards3, "displayPanel");
			}else if (e.getActionCommand().equals("avgRent")){
				displayController3.branchPanel.displayPanel.removeAll();
				CardLayout cl = (CardLayout)(cards3.getLayout());
				displayController3.branchPanel.inpField.label.setText("City: ");
				displayController3.branchPanel.displayPanel.repaint();
				displayController3.branchPanel.displayPanel.revalidate();
				cl.show(cards3, "branchPanel");
			}else if (e.getActionCommand().equals("expLease")){
				displayController3.displayPanel.removeAll();

				CardLayout cl = (CardLayout)(cards3.getLayout());
				String temp = start.genExpireSoon();
				ArrayList<String> properties = new ArrayList<String>();
				//parse temp into properties
				if(temp != null){
					String[] sup_props = temp.split("[;]+");
					//parse temp into properties
					for(int i = 0;i < sup_props.length; i++){
						properties.add(sup_props[i]);
						System.out.println(sup_props[i]);
					}
				}
				displayController3.displayPanel.listView(properties, "Leases that Expire Soon:");
				displayController3.displayPanel.repaint();
				displayController3.displayPanel.revalidate();
				cl.show(cards3, "displayPanel");
			}
			//Admin Buttons
			else if (e.getActionCommand().equals("addBranch")){
				
			} else if (e.getActionCommand().equals("addEmployee")){
				
			} else if (e.getActionCommand().equals("addPropOwner")){
				
			} else if (e.getActionCommand().equals("addRenter")){
				
			} else if (e.getActionCommand().equals("addRentalProp")){
				
			} else if (e.getActionCommand().equals("addLease")){
				
			}
		}
		
		public void setBlocked(boolean next) {
			this.setVisible(true);
		}	
	
}
	
