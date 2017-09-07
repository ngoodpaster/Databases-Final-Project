CREATE OR REPLACE PROCEDURE insert_into_branch(branchNo_param Branch.branchNumber%type, phone_param Branch.phone%type, street_param Branch.street%type , city_param Branch.city%type, zip_param Branch.zip%type) AS
BEGIN
	INSERT INTO Branch VALUES(branchNo_param, phone_param, city_param, street_param, zip_param);
End;
/
show errors;
