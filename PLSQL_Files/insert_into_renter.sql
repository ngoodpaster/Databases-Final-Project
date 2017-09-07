CREATE OR REPLACE PROCEDURE insert_into_renter(renterId_param Renter.renterId%type, name_param Renter.name%type, hphone_param Renter.homePhone%type, 
wphone_param Renter.workPhone%type, contName_param Renter.contactName%type, contPhone_param Renter.contactPhone%type) AS
BEGIN
	INSERT INTO Renter VALUES(renterId_param, name_param, hphone_param, wphone_param, contName_param, contPhone_param);
End;
/
show errors;
