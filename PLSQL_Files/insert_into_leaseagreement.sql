CREATE OR REPLACE PROCEDURE insert_into_leaseagreement(lease_param Lease_Agreement.leaseId%type, renter_param Lease_Agreement.renterId%type, prop_param Lease_Agreement.propNumber%type, 
start_param Lease_Agreement.startDate%type, end_param Lease_Agreement.endDate%type, deposit_param Lease_Agreement.depositAmount%type) AS

rent_amt Lease_Agreement.rentAmount%type;
emp_Id Lease_Agreement.employeeId%type;
BEGIN

	SELECT monthlyRent INTO rent_amt FROM Rental_Property R_P WHERE R_P.propNumber = prop_param;
	SELECT employeeId INTO emp_Id FROM Rental_Property R_P WHERE R_P.propNumber = prop_param;
	INSERT INTO Lease_Agreement VALUES(lease_param, renter_param, start_param, end_param, deposit_param, rent_amt, emp_Id, prop_param);
End;
/
show errors;
