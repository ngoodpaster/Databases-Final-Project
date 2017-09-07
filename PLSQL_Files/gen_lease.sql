CREATE OR REPLACE FUNCTION gen_lease(renter_param Lease_Agreement.leaseId%type)
RETURN VARCHAR AS
output_l Varchar(10000);
CURSOR lease_cur IS 
	SELECT leaseId, renterId, employeeId, startDate, endDate, depositAmount, rentAmount FROM Lease_Agreement L_A
	WHERE L_A.renterId = renter_param;
lease_info lease_cur%rowtype; 
BEGIN
	IF NOT lease_cur%ISOPEN THEN
		OPEN lease_cur;
	END IF;
LOOP
	FETCH lease_cur INTO lease_info;
	EXIT WHEN lease_cur%NOTFOUND;
	output_l := output_l || lease_info.leaseId || ':' || lease_info.renterId ||  ',' || lease_info.employeeId ||  ',' || lease_info.startDate ||  ',' || lease_info.endDate || ',' || lease_info.depositAmount || ',' || lease_info.rentAmount;
END LOOP;
RETURN output_l;
END;
/
show errors;