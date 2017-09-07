CREATE OR REPLACE PROCEDURE insert_into_rentalprop(propNo_param Rental_Property.propNumber%type, street_param Rental_Property.street%type, 
city_param Rental_Property.city%type, zip_param Rental_Property.zip%type, noRooms_param Rental_Property.numOfRooms%type, 
rent_param Rental_Property.monthlyRent%type, status_param Rental_Property.status%type, start_param Rental_Property.startDate%type, 
fee_param Rental_Property.fee%type, empId_param Rental_Property.employeeId%type, propOwn_param Rental_Property.propertyOwnerId%type) AS
BEGIN
	INSERT INTO Rental_Property VALUES(propNo_param, city_param, street_param, zip_param, noRooms_param,rent_param, status_param, start_param, fee_param, empId_param, propOwn_param);
End;
/
show errors;