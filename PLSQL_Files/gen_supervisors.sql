CREATE OR REPLACE FUNCTION gen_supervisors
RETURN varchar IS
output_l varchar(10000);
CURSOR prop_cur IS
		SELECT propNumber, employeeId, city, street, zip FROM Rental_Property R_P;
CURSOR emp_cur IS
		SELECT employeeId, name FROM Employee E 
		WHERE E.jobDesignation = 'supervisor';
emp_info emp_cur%rowtype;
prop_info prop_cur%rowtype;
BEGIN
	IF NOT prop_cur%ISOPEN THEN
		OPEN prop_cur;
	END IF;
	IF NOT emp_cur%ISOPEN THEN
		OPEN emp_cur;
	END IF;
LOOP
	FETCH emp_cur INTO emp_info;
	EXIT WHEN emp_cur%NOTFOUND;
	--DBMS_OUTPUT.put_line(emp_info.employeeId || ': ');
	output_l := output_l || 'Name:' || emp_info.name || '(' || emp_info.employeeId || ')' || '-';
	CLOSE prop_cur;
	OPEN prop_cur;
	LOOP
		FETCH prop_cur INTO prop_info;
		EXIT WHEN prop_cur%NOTFOUND;
		--DBMS_OUTPUT.put_line(emp_info.employeeId || ':' || prop_info.employeeId);
		IF prop_info.employeeId = emp_info.employeeId THEN 
			output_l := output_l || prop_info.propNumber || prop_info.street || prop_info.city || prop_info.zip || ',';
--DBMS_OUTPUT.put_line(prop_info.propNumber || ',' || prop_info.street || ','|| prop_info.city || ',' || prop_info.zip);
		END IF;
	END LOOP;
	output_l := output_l || ';';
END LOOP;
RETURN output_l;
END;
/
show errors;

