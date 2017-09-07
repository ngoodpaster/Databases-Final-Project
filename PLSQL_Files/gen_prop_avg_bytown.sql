CREATE OR REPLACE FUNCTION gen_prop_avg_bytown(town_param Rental_Property.city%type) 
RETURN INTEGER IS
output_l INTEGER;
CURSOR prop_cur IS 
	SELECT monthlyRent FROM Rental_Property 
	WHERE city = town_param;
prop_count INTEGER;
rent_total_amt INTEGER;
prop_info prop_cur%rowtype;
BEGIN
	prop_count := 0;
	rent_total_amt := 0;
	IF NOT prop_cur%ISOPEN THEN
		OPEN prop_cur;
	END IF;
LOOP 
	FETCH prop_cur INTO prop_info;
	EXIT WHEN prop_cur%NOTFOUND;
	prop_count := prop_count + 1;
	rent_total_amt := rent_total_amt + prop_info.monthlyRent;
END LOOP;
output_l := rent_total_amt / prop_count;
RETURN output_l;
END;
/
show errors;
