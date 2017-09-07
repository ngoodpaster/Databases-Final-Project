CREATE OR REPLACE TRIGGER lease_rent_trigger
AFTER INSERT ON Lease_Agreement
FOR EACH ROW 
DECLARE 
BEGIN
	UPDATE Rental_Property R_P
	SET monthlyRent = monthlyRent * 1.1
	WHERE propNumber = :new.propNumber;
END;
/
show errors;
 
