CREATE OR REPLACE TRIGGER lease_status_trigger
AFTER INSERT ON Lease_Agreement
FOR EACH ROW
--DECLARE 
BEGIN
	UPDATE Rental_Property R_P
	SET status = 'leased'
	WHERE R_P.propNumber = :new.propNumber;
END;
/
show errors;
