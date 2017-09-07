CREATE OR REPLACE PROCEDURE insert_into_empphone(employeeId_param Emp_Phone.employeeId%type, phone_param Emp_Phone.phone%type) AS
BEGIN
	INSERT INTO Emp_Phone VALUES(employeeId_param,phone_param);
End;
/
show errors;

