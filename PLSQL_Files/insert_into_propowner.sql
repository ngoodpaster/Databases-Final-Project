CREATE OR REPLACE PROCEDURE insert_into_propowner(propOwner_param Property_Owner.propertyOwnerId%type, name_param Property_Owner.name%type, street_param Property_Owner.permStreet%type , city_param Property_Owner.permCity%type, zip_param Property_Owner.permZip%type) AS
BEGIN
	INSERT INTO Property_Owner VALUES(propOwner_param, name_param, city_param, street_param, zip_param);
End;
/
show errors;
