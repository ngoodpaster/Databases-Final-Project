CREATE OR REPLACE TRIGGER one_manager_trigger
BEFORE INSERT OR UPDATE ON Employee 
FOR EACH ROW
DECLARE
CURSOR emp_cur IS
	SELECT branchNumber FROM Employee WHERE jobDesignation = 'manager';
emp_info emp_cur%rowtype;
BEGIN
	IF NOT emp_cur%ISOPEN THEN
		OPEN emp_cur;
	END IF;
LOOP
	FETCH emp_cur INTO emp_info;
	EXIT WHEN emp_cur%NOTFOUND;
	IF :new.jobDesignation = 'manager' AND :new.branchNumber = emp_info.branchNumber THEN
		RAISE_APPLICATION_ERROR(-20000, 'Only one manager per branch');
	END IF;
END LOOP;
END;
/
show errors;
