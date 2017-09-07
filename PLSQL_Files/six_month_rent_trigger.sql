CREATE OR REPLACE TRIGGER six_mon_rent_trigger
BEFORE INSERT OR UPDATE ON Lease_Agreement
FOR EACH ROW
DECLARE
dateDiff Integer;
BEGIN
	SELECT MONTHS_BETWEEN(:new.endDate, :new.startDate) INTO dateDiff FROM dual;
	IF dateDiff = 6 THEN
		:new.rentAmount := 1.1 * :new.rentAmount;
	END IF;
END;
/
show errors;
