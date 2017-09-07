CREATE OR REPLACE PROCEDURE delete_rentprop(prop_param Rental_Property.propNumber%type) AS
BEGIN
	DELETE FROM RENTAL_PROPERTY WHERE propNumber = prop_param;
END;
/
Show Errors;
