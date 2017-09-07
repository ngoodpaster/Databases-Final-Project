CREATE OR REPLACE FUNCTION gen_expire_soon
RETURN VARCHAR AS
output_l Varchar(10000);
CURSOR lease_cur IS
	SELECT leaseId, renterId, employeeId, startDate, endDate, depositAmount, rentAmount FROM Lease_Agreement L_A
	WHERE MONTHS_BETWEEN(endDate, SYSDATE) < 2 and endDate > SYSDATE;
lease_info lease_cur%rowtype;
BEGIN
IF NOT lease_cur%ISOPEN THEN
		OPEN lease_cur;
	END IF;
LOOP
	FETCH lease_cur INTO lease_info;
	EXIT WHEN lease_cur%NOTFOUND;
	output_l := output_l || lease_info.leaseId || ',' || lease_info.renterId || ',' || lease_info.employeeId || ',' || lease_info.startDate || ',' || lease_info.endDate || ';';
END LOOP;
RETURN output_l;
END;
/
show errors;