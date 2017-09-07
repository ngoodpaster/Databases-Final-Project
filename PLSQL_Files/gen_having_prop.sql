CREATE OR REPLACE FUNCTION gen_having_prop 
RETURN VARCHAR AS
output_l Varchar(10000);
CURSOR renter_cur IS
	SELECT name, renterId FROM Renter
	WHERE renterId IN (SELECT renterId FROM Renter_And_Prop
			  GROUP BY renterId 
			  HAVING count(*) > 1);
renter_info renter_cur%rowtype;
BEGIN
	IF NOT renter_cur%ISOPEN THEN
		OPEN renter_cur;
	END IF;
LOOP
	FETCH renter_cur INTO renter_info;
	EXIT WHEN renter_cur%NOTFOUND;
	output_l := output_l || renter_info.name || '(' || renter_info.renterId ||  ')' || ',';
END LOOP;
RETURN output_l;
END;
/
show errors;
