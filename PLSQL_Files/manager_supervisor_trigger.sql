CREATE OR REPLACE TRIGGER manager_supervisor_trigger 
BEFORE INSERT OR UPDATE ON Employee
FOR EACH ROW
DECLARE
CURSOR emp_cur IS
	SELECT branchNumber FROM EMPLOYEE WHERE jobDesignation = 'manager';
emp_info emp_cur%rowtype;
flag CHAR(1);
BEGIN
	flag := 'N';
	IF NOT emp_cur%ISOPEN THEN
		OPEN emp_cur;
	END IF;
	IF :NEW.jobDesignation = 'supervisor' THEN
		LOOP
			FETCH emp_cur INTO emp_info;
			EXIT WHEN emp_cur%NOTFOUND;
			IF :NEW.branchNumber = emp_info.branchNumber THEN
				flag := 'Y';
			END IF;
		END LOOP;

		IF flag = 'N' THEN
			RAISE_APPLICATION_ERROR(-20000, 'Branch Has No Manager');
		END IF;
	END IF;
END;
/
show errors;

