CREATE OR REPLACE FUNCTION gen_all_props
RETURN VARCHAR IS
output_l VARCHAR(10000);
CURSOR branch_cur IS 
	SELECT branchNumber FROM Branch;
CURSOR emp_cur IS
	SELECT employeeId, jobDesignation, name, branchNumber FROM Employee E;
CURSOR prop_cur IS
	SELECT employeeId, propNumber, city, street, zip FROM Rental_Property WHERE status = 'available';
prop_info prop_cur%rowtype;
emp_info emp_cur%rowtype;
branch_info branch_cur%rowtype;
bool_char CHAR(1);
BEGIN
	IF NOT branch_cur%ISOPEN THEN
		OPEN branch_cur;
	END IF;
	IF NOT emp_cur%ISOPEN THEN
		OPEN emp_cur;
	END IF;
	IF NOT prop_cur%ISOPEN THEN
		OPEN prop_cur;
	END IF;
LOOP
	bool_char := 'N';
	FETCH branch_cur INTO branch_info;
	EXIT WHEN branch_cur%NOTFOUND;
	
	IF NOT emp_cur%ISOPEN THEN
		OPEN emp_cur;
	END IF;
	
	LOOP 
		FETCH emp_cur INTO emp_info;
		EXIT WHEN emp_cur%NOTFOUND;
		IF emp_info.branchNumber = branch_info.branchNumber AND emp_info.jobDesignation = 'manager' THEN
			output_l := output_l || 'Branch #' || branch_info.branchNumber || ' Manager: ' || emp_info.name || '(' || emp_info.employeeId || ')' ||  '-';
			bool_char := 'Y';
		END IF;		
	END LOOP;

	IF bool_char = 'N' THEN
		output_l := output_l || branch_info.branchNumber || '.' || 'NO MANAGER' || ':';
	END IF;

	CLOSE emp_cur;
	IF NOT emp_cur%ISOPEN THEN
		OPEN emp_cur;
	END IF;
	
	output_l := output_l ||' Properties: ';

	LOOP
		FETCH emp_cur INTO emp_info;
		EXIT WHEN emp_cur%NOTFOUND;
		
		IF NOT prop_cur%ISOPEN THEN
			OPEN prop_cur;
		END IF;
		IF emp_info.branchNumber = branch_info.branchNumber THEN
			LOOP
				FETCH prop_cur INTO prop_info;
				EXIT WHEN prop_cur%NOTFOUND;
				IF emp_info.employeeId = prop_info.employeeId THEN
					output_l := output_l || 'Prop #' || prop_info.propNumber || ',' || prop_info.street || prop_info.city || prop_info.zip || ':';
				END IF;
			END LOOP;
			IF prop_cur%ISOPEN THEN
				CLOSE prop_cur;
			END IF;
		END IF;
	END LOOP;
	output_l := output_l || ';';

	CLOSE emp_cur;

END LOOP;
RETURN output_l;
END;
/
show errors;
