--Nationality
INSERT INTO nationality(id, description) VALUES (1,'Argentina');
INSERT INTO nationality(id, description) VALUES (2,'Brasilera');
INSERT INTO nationality(id, description) VALUES (3,'Peruana');
INSERT INTO nationality(id, description) VALUES (4,'Paraguaya');
INSERT INTO nationality(id, description) VALUES (5,'Uruguaya');
INSERT INTO nationality(id, description) VALUES (6,'Chilena');
INSERT INTO nationality(id, description) VALUES (7,'Boliviana');
INSERT INTO nationality(id, description) VALUES (8,'Colombiana');
INSERT INTO nationality(id, description) VALUES (9,'Ecuatoriana');
INSERT INTO nationality(id, description) VALUES (10,'Venezolana');
INSERT INTO nationality(id, description) VALUES (11,'Mexicana');

--Category
INSERT INTO category(id, description) VALUES (1,'Socio');
INSERT INTO category(id, description) VALUES (2,'Empleado');
INSERT INTO category(id, description) VALUES (3,'Instructor');

--Members
INSERT INTO member(id, date_of_birth,date_of_creation,identity_card_number,identity_card_type,name,category_id,nationality_id,status) VALUES (1,'1974-08-02','2017-01-03','24036873','DNI','SANTIAGO, Esteban',1,1, 'ACTIVE');
INSERT INTO member(id, date_of_birth,date_of_creation,identity_card_number,identity_card_type,name,category_id,nationality_id,status) VALUES (2,'1989-01-27','2017-01-03','CC123331','PASSPORT','SANTIAGO, Pablo',1,2,'ACTIVE');

--Medical Certifications
INSERT INTO member_medical_certification(id,medical_certification_class,observations,validity_from,validity_to,member_id) VALUES (1,'CLASS_I','Observaciones!!!','2017-01-04','2020-01-04',1);
INSERT INTO member_medical_certification(id,medical_certification_class,observations,validity_from,validity_to,member_id) VALUES (2,'CLASS_I','Observaciones!!!','2017-01-04','2020-01-04',2);

--Aircrafts
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (1,'Cessna','152','LV-OEE','ACTIVE');
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (2,'Cessna','152','LV-AMS','ACTIVE');
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (3,'Cessna','150','LV-LGF','ACTIVE');
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (4,'Pipper','PA-11','LV-YMV','OUT_OF_ORDER');

--Aircraft Insurance
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (1,'Sancor','ABC-4444224422','Terceros Completo','2016-07-03','2017-09-03',1);
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (2,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-09-03',2);
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (3,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-11-03',3);
--INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (4,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-11-03',4);

--Payment Methods
INSERT INTO payment_method(id,charge,description,discount,fee,payment_type) VALUES (1,0,'Efectivo',0,0,'CASH');
INSERT INTO payment_method(id,charge,description,discount,fee,payment_type) VALUES (2,0.1,'VISA',0,0,'CREDIT_CARD');
