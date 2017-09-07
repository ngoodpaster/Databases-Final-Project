CREATE OR REPLACE FUNCTION gen_prop_byowner(owner_param Property_Owner.propertyOwnerId%type)
RETURN VARCHAR AS
output_l Varchar(1000);
CURSOR prop_cur IS 
	SELECT propNumber, city, street, zip FROM Rental_Property R_P
	WHERE R_P.propertyOwnerId = owner_param;
prop_info prop_cur%rowtype;
prop_own_name Property_Owner.name%type;
BEGIN
	SELECT name into prop_own_name FROM Property_Owner WHERE propertyOwnerId =  owner_param;
	IF NOT prop_cur%ISOPEN THEN
		OPEN prop_cur;
	END IF;
LOOP
	FETCH prop_cur INTO prop_info;
	EXIT WHEN prop_cur%NOTFOUND;
	output_l := output_l || 'Property Owner: ' || prop_own_name || ' - Prop #' || prop_info.propNumber || ': ' || prop_info.street ||  prop_info.city ||  prop_info.zip || ';';
END LOOP;
RETURN output_l;
END;
/
show errors;

