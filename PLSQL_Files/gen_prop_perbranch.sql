CREATE OR REPLACE FUNCTION gen_prop_perbranch
RETURN VARCHAR IS 
CURSOR branch_cur IS 
	SELECT branchNumber FROM Branch;
CURSOR prop_cur IS 
	SELECT employeeId FROM Rental_Property R_P
	WHERE R_P.status = 'available';
CURSOR emp_cur IS
	SELECT employeeId, branchNumber FROM Employee E
	WHERE E.jobDesignation = 'supervisor';
branch_info branch_cur%rowtype;
prop_info prop_cur%rowtype;
emp_info emp_cur%rowtype;
prop_count Integer;
output_l VARCHAR(10000);
BEGIN
	IF NOT prop_cur%ISOPEN THEN
		OPEN prop_cur;
	END IF;
	
	IF NOT emp_cur%ISOPEN THEN 
		OPEN emp_cur;
	END IF;
	
	IF NOT branch_cur%ISOPEN THEN
		OPEN branch_cur;
	END IF;
LOOP
	prop_count := 0;
	FETCH branch_cur INTO branch_info;
	EXIT WHEN branch_cur%NOTFOUND;
	output_l := output_l || 'Branch #:' || branch_info.branchNumber || ':';
	CLOSE emp_cur;
	IF NOT emp_cur%ISOPEN THEN
		OPEN emp_cur;
	END IF;
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
					prop_count := prop_count + 1;
				END IF;
			END LOOP;
			IF prop_cur%ISOPEN THEN
				CLOSE prop_cur;
			END IF;
		END IF;
	END LOOP;
	output_l := output_l || 'Property Count: ' || prop_count || ';';
END LOOP;
RETURN output_l;
END;
/
show errors;
