CREATE OR REPLACE PROCEDURE insert_into_employee(employeeId_param Employee.employeeId%type, name_param Employee.name%type, jobDes_param Employee.jobDesignation%type , start_param Employee.startDate%type, branch_param Employee.branchNumber%type) AS
BEGIN
	INSERT INTO Employee VALUES(employeeId_param, name_param, jobDes_param, start_param, branch_param);
End;
/
show errors;

