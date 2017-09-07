package greenfield;
import java.sql.*;

public class JDBCConnection {
	
	static Connection con;
	
	/*
	public static void main(String[] args){
			if(establishJDBCConnection()){
				System.out.println("Created DB Connection...");
			} else{
				System.out.println("Unable to establish a connection.");
			}
			
			closeJDBCConnection();
	}
	*/
	
	public void closeJDBCConnection(){
		try {
			System.out.println("DB Connectin Closed");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean establishJDBCConnection(String username, String password){
		boolean success = true;
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@dagobah.engr.scu.edu:1521:DB11G",username,password);
            Statement stmt = con.createStatement();
            System.out.println("Created DB Connection....");
		} catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
			success = false;
            System.out.println("Invalid Username/password");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        	success = false;
        	System.out.println("Invalid Username/password");
        }
		return success;
	}
	
	//generates properties available by branch (taken as parameter)
	String genPropByBranch(int branchNumber){
		String properties = " ";
		CallableStatement gen_avail_prop;
		try {
			gen_avail_prop = con.prepareCall("begin ? := gen_proplist_perbranch(?); end;");
			gen_avail_prop.registerOutParameter(1, Types.CHAR);
			gen_avail_prop.setInt(2, branchNumber);
			gen_avail_prop.execute();
			properties = gen_avail_prop.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates properties based on specification (city, rooms, rent min/max)
	String genPropBySpec_all (String city, int noOfRooms, int rentMin, int rentMax){
		String properties = " ";
		CallableStatement gen_prop_spec;
		System.out.println(city + noOfRooms + rentMin + rentMax);
		try{
			gen_prop_spec = con.prepareCall("begin ? := gen_prop_spec(?,?,?,?); end;");
			gen_prop_spec.registerOutParameter(1, Types.CHAR);
			gen_prop_spec.setString(2, city);
			gen_prop_spec.setInt(3, noOfRooms);
			gen_prop_spec.setInt(4, rentMin);
			gen_prop_spec.setInt(5,rentMax);
			gen_prop_spec.execute();
			properties = gen_prop_spec.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates properties based on specification (city, rooms, rent min/max)
	String genPropBySpec_room_rent (int noOfRooms, int rentMin, int rentMax){
		String properties = " ";
		CallableStatement gen_prop_spec;
		try{
			gen_prop_spec = con.prepareCall("begin ? := gen_prop_spec_room_rent(?,?,?); end;");
			gen_prop_spec.registerOutParameter(1, Types.CHAR);
			gen_prop_spec.setInt(2, noOfRooms);
			gen_prop_spec.setInt(3, rentMin);
			gen_prop_spec.setInt(4,rentMax);
			gen_prop_spec.execute();
			properties = gen_prop_spec.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates properties based on specification (city, rent min/max)
	String genPropBySpec_city_rent (String city, int rentMin, int rentMax){
		String properties = " ";
		CallableStatement gen_prop_spec;
		try{
			gen_prop_spec = con.prepareCall("begin ? := gen_prop_spec_city_rent(?,?,?); end;");
			gen_prop_spec.registerOutParameter(1, Types.CHAR);
			gen_prop_spec.setString(2, city);
			gen_prop_spec.setInt(3, rentMin);
			gen_prop_spec.setInt(4,rentMax);
			gen_prop_spec.execute();
			properties = gen_prop_spec.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates properties based on specification (city, rooms)
	String genPropBySpec_city_room (String city, int noOfRooms){
		String properties = " ";
		CallableStatement gen_prop_spec;
		try{
			gen_prop_spec = con.prepareCall("begin ? := gen_prop_spec_city_room(?,?); end;");
			gen_prop_spec.registerOutParameter(1, Types.CHAR);
			gen_prop_spec.setString(2, city);
			gen_prop_spec.setInt(3, noOfRooms);
			gen_prop_spec.execute();
			properties = gen_prop_spec.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates properties based on specification (city)
	String genPropBySpec_city (String city){
		String properties = " ";
		CallableStatement gen_prop_spec;
		try{
			gen_prop_spec = con.prepareCall("begin ? := gen_prop_spec_city(?); end;");
			gen_prop_spec.registerOutParameter(1, Types.CHAR);
			gen_prop_spec.setString(2, city);
			gen_prop_spec.execute();
			properties = gen_prop_spec.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates properties based on specification (room)
	String genPropBySpec_room (int noOfRooms){
		String properties = " ";
		CallableStatement gen_prop_spec;
		try{
			gen_prop_spec = con.prepareCall("begin ? := gen_prop_spec_room(?); end;");
			gen_prop_spec.registerOutParameter(1, Types.CHAR);
			gen_prop_spec.setInt(2, noOfRooms);
			gen_prop_spec.execute();
			properties = gen_prop_spec.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates properties based on specification (rent)PerBranch()
	String genPropBySpec_rent (int rentMin, int rentMax){
		String properties = " ";
		CallableStatement gen_prop_spec;
		try{
			gen_prop_spec = con.prepareCall("begin ? := gen_prop_spec_rent(?,?); end;");
			gen_prop_spec.registerOutParameter(1, Types.CHAR);
			gen_prop_spec.setInt(2, rentMin);
			gen_prop_spec.setInt(3,rentMax);
			gen_prop_spec.execute();
			properties = gen_prop_spec.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates list of supervisors and their properties 
	//(:name(empid), prop1; prop2;  ... :name2(empid))
	String genSupervisors(){
		String supervisors = " ";
		CallableStatement gen_supervisors;
		try{
			gen_supervisors = con.prepareCall("begin ? := gen_supervisors(); end;");
			gen_supervisors.registerOutParameter(1, Types.VARCHAR);
			gen_supervisors.execute();
			supervisors = gen_supervisors.getString(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return supervisors;
	}
	
	//generates available properties returns string in format (branchNo:amt; branchNo:amt; ...)
	String genPropPerBranch(){
		String branch_counts = " ";
		CallableStatement gen_prop_perbranch;
		try{
			gen_prop_perbranch = con.prepareCall("begin ? := gen_prop_perbranch(); end;");
			gen_prop_perbranch.registerOutParameter(1, Types.VARCHAR);
			gen_prop_perbranch.execute();
			branch_counts = gen_prop_perbranch.getString(1);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return branch_counts;
	}
	
	//generates a list of properties for a specific owner
	//output format --> propOwnerNumber: propNumber, Street city zip; propNumber, Street city cip; 
	String genPropByOwner(String propertyOwnerId){
		String properties = " ";
		CallableStatement gen_prop_byowner;
		try{
			gen_prop_byowner = con.prepareCall("begin ? := gen_prop_byowner(?); end;");
			gen_prop_byowner.registerOutParameter(1, Types.VARCHAR);
			gen_prop_byowner.setString(2, propertyOwnerId);
			gen_prop_byowner.execute();
			properties = gen_prop_byowner.getString(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	//generates a lease string containing the following information: 
	// leaseId : renterId, employeeId, startdate, end date, deposit, monthlyRent
	String genLease(String leaseId){
		String lease = " ";
		CallableStatement gen_lease;
		try{
			gen_lease = con.prepareCall("begin ? := gen_lease(?); end;");
			gen_lease.registerOutParameter(1, Types.VARCHAR);
			gen_lease.setString(2, leaseId);
			gen_lease.execute();
			lease = gen_lease.getString(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return lease;
	}
	
	//Generates the average rent for properties in the town given as a parameter
	int genPropAvgByTown(String town){
		int avg = 0;
		CallableStatement gen_prop_avg;
		try{
			gen_prop_avg = con.prepareCall("begin ? := gen_prop_avg_bytown(?); end;");
			gen_prop_avg.registerOutParameter(1, Types.VARCHAR);
			gen_prop_avg.setString(2, town);
			gen_prop_avg.execute();
			avg = gen_prop_avg.getInt(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return avg;
	}
	
	//generates a list of properties per branch
	// output is in format: Managername (empId): propNumber, Street city zip;  
	String genPropListPerBranch(String branchNumber){
		String properties = " ";
		CallableStatement gen_prop_list;
		try{
			gen_prop_list = con.prepareCall("begin ? := gen_proplist_perbranch(?); end;");
			gen_prop_list.registerOutParameter(1, Types.VARCHAR);
			gen_prop_list.setString(2, branchNumber);
			gen_prop_list.execute();
			properties = gen_prop_list.getString(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}

	
	String genAllProps(){
		String properties = " ";
		CallableStatement gen_prop_list;
		try{
			gen_prop_list = con.prepareCall("begin ? := gen_all_props(); end;");
			gen_prop_list.registerOutParameter(1, Types.VARCHAR);
			gen_prop_list.execute();
			properties = gen_prop_list.getString(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	
	String genMultLease(){
		String properties = " ";
		CallableStatement gen_prop_list;
		try{
			gen_prop_list = con.prepareCall("begin ? := gen_having_prop(); end;");
			gen_prop_list.registerOutParameter(1, Types.VARCHAR);
			gen_prop_list.execute();
			properties = gen_prop_list.getString(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	String genExpireSoon(){
		String properties = " ";
		CallableStatement gen_prop_list;
		try{
			gen_prop_list = con.prepareCall("begin ? := gen_expire_soon(); end;");
			gen_prop_list.registerOutParameter(1, Types.VARCHAR);
			gen_prop_list.execute();
			properties = gen_prop_list.getString(1);			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return properties;
	}
	
	void insertIntoBranch(String branchNumber, String phone, String street, String city, String zip){
		CallableStatement insert_branch_stmt;
		try{
			insert_branch_stmt = con.prepareCall("begin insert_into_branch(?,?,?,?,?); end;");
			insert_branch_stmt.setString(1, branchNumber);
			insert_branch_stmt.setString(2, phone);
			insert_branch_stmt.setString(3, street);
			insert_branch_stmt.setString(4, city);
			insert_branch_stmt.setString(5, zip);
			insert_branch_stmt.execute();
						
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	void insertIntoEmployee(String employeeId, String name, String jobDesignation, String startDate, String branchNumber, String phone){
		CallableStatement insert_employee_stmt;
		CallableStatement insert_empphone_stmt;
		try{
			insert_employee_stmt = con.prepareCall("begin insert_into_employee(?,?,?,?,?); end;");
			insert_employee_stmt.setString(1, employeeId);
			insert_employee_stmt.setString(2, name);
			insert_employee_stmt.setString(3, jobDesignation);
			insert_employee_stmt.setString(4, startDate);
			insert_employee_stmt.setString(5, branchNumber);
			insert_employee_stmt.execute();
			if (phone != null){
				insert_empphone_stmt = con.prepareCall("begin insert_into_empphone(?,?); end;");
				insert_empphone_stmt.setString(1, employeeId);
				insert_empphone_stmt.setString(2, phone);
				insert_empphone_stmt.execute();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	void insertIntoPropOwner(String propOwnerId, String name, String street, String city, String zip){
		CallableStatement insert_propOwner_stmt;
		try{
			insert_propOwner_stmt = con.prepareCall("begin insert_into_propowner(?,?,?,?,?); end;");
			insert_propOwner_stmt.setString(1, propOwnerId);
			insert_propOwner_stmt.setString(2, name);
			insert_propOwner_stmt.setString(3, street);
			insert_propOwner_stmt.setString(4, city);
			insert_propOwner_stmt.setString(5, zip);
			insert_propOwner_stmt.execute();
						
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	void insertIntoRentalProp(String propNumber, String street, String city, String zip, String noOfRooms, String initRent, String status, String startDate, String fee, String employeeId, String propOwnerId){
		CallableStatement insert_rentalProp_stmt;
		try{
			insert_rentalProp_stmt = con.prepareCall("begin insert_into_rentalprop(?,?,?,?,?,?,?,?,?,?,?); end;");
			insert_rentalProp_stmt.setString(1, propNumber);
			insert_rentalProp_stmt.setString(2, street);
			insert_rentalProp_stmt.setString(3, city);
			insert_rentalProp_stmt.setString(4, zip);
			insert_rentalProp_stmt.setString(5, noOfRooms);
			insert_rentalProp_stmt.setString(6, initRent);
			insert_rentalProp_stmt.setString(7, status);
			insert_rentalProp_stmt.setString(8, startDate);
			insert_rentalProp_stmt.setString(9, fee);
			insert_rentalProp_stmt.setString(10, employeeId);
			insert_rentalProp_stmt.setString(11, propOwnerId);
			insert_rentalProp_stmt.execute();
						
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	void insertIntoRenter(String renterId, String name, String homephone, String workphone, String contact, String contactphone){
		CallableStatement insert_renter_stmt;
		try{
			insert_renter_stmt = con.prepareCall("begin insert_into_renter(?,?,?,?,?,?); end;");
			insert_renter_stmt.setString(1, renterId);
			insert_renter_stmt.setString(2, name);
			insert_renter_stmt.setString(3, homephone);
			insert_renter_stmt.setString(4, workphone);
			insert_renter_stmt.setString(5, contact);
			insert_renter_stmt.setString(6, contactphone);
			insert_renter_stmt.execute();
						
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	void insertIntoLeaseAgreement(String leaseId, String renterId, String propNumber, String startdate, String endDate, String deposit){
		CallableStatement insert_renter_stmt;
		try{
			insert_renter_stmt = con.prepareCall("begin insert_into_leaseagreement(?,?,?,?,?,?); end;");
			insert_renter_stmt.setString(1, leaseId);
			insert_renter_stmt.setString(2, renterId);
			insert_renter_stmt.setString(3, propNumber);
			insert_renter_stmt.setString(4, startdate);
			insert_renter_stmt.setString(5, endDate);
			insert_renter_stmt.setString(6, deposit);
			insert_renter_stmt.execute();
						
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	void deleteRentalProp(String propNumber){
		CallableStatement insert_renter_stmt;
		try{
			insert_renter_stmt = con.prepareCall("begin delete_rentprop(?); end;");
			insert_renter_stmt.setString(1, propNumber);
			insert_renter_stmt.execute();
						
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}

