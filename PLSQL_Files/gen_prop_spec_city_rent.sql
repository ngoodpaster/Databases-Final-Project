/*
*  generates properties based on specifications received as inputs
*/

CREATE OR REPLACE FUNCTION gen_prop_spec_city_rent(city_param Rental_Property.city%type, rent_min_param Rental_Property.monthlyRent%type, rent_max_param Rental_Property.monthlyRent%type) 
RETURN varchar IS
output_l varchar(1000);
CURSOR prop_cur IS
	SELECT propNumber, city, street, zip FROM Rental_Property R_P
	WHERE R_P.city = city_param and R_P.monthlyRent < rent_max_param and R_P.monthlyRent > rent_min_param;
prop_info prop_cur%rowtype;
BEGIN
	IF NOT prop_cur%ISOPEN THEN
		OPEN prop_cur;
	END IF;
LOOP
	FETCH prop_cur INTO prop_info;
	EXIT WHEN prop_cur%NOTFOUND;
	output_l := output_l || 'Property #' ||  prop_info.propNumber  || ':'|| prop_info.street || prop_info.city || prop_info.zip || ';';
END LOOP;
RETURN output_l;
END;
/
show errors;  
