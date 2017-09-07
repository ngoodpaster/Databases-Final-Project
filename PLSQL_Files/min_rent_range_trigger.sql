CREATE OR REPLACE TRIGGER min_rent_range_trigger
BEFORE INSERT OR UPDATE ON Lease_Agreement 
FOR EACH ROW
DECLARE
dateDiff Integer;
BEGIN
	SELECT MONTHS_BETWEEN(:new.endDate, :new.startDate) INTO dateDiff FROM dual;
	IF dateDiff < 6 THEN 
		RAISE_APPLICATION_ERROR(-20000, 'Must rent for at least 6 months');
	END IF;
END;
/
show errors;  
