--Nationality
INSERT INTO nationality(id, description) VALUES (1,'Argentina');
INSERT INTO nationality(id, description) VALUES (2,'Brasilera');
INSERT INTO nationality(id, description) VALUES (3,'Peruana');
INSERT INTO nationality(id, description) VALUES (4,'Paraguaya');


--Aircrafts
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (1,'Cessna','152','LV-OEE','A');
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (2,'Cessna','152','LV-AMS','A');
--Aircraft Insurance
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (1,'Sancor','ABC-4444224422','Terceros Completo','2016-07-03','2017-09-03',1);
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (2,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-09-03',2);

--Members
INSERT INTO member(id, date_of_birth,date_of_creation,identity_document_number,identity_document_type,name,category_id,nationality_id) VALUES (1,NULL,'2017-01-03',NULL,NULL,'Socio 1',NULL,1);
INSERT INTO member(id, date_of_birth,date_of_creation,identity_document_number,identity_document_type,name,category_id,nationality_id) VALUES (2,NULL,'2017-01-03',NULL,NULL,'Socio 2',NULL,2);

