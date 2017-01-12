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
INSERT INTO member(id, date_of_birth,date_of_creation,identity_card_number,identity_card_type,name,category_id,nationality_id,status) VALUES (100,'1974-08-02','2017-01-03','24036873','DNI','SANTIAGO, Esteban',1,1, 'ACTIVE');
INSERT INTO member(id, date_of_birth,date_of_creation,identity_card_number,identity_card_type,name,category_id,nationality_id,status) VALUES (101,'1989-01-27','2017-01-03','CC123331','PASSPORT','SANTIAGO, Pablo',1,2,'ACTIVE');

--Medical Certifications
INSERT INTO member_medical_certification(id,medical_certification_class,observations,validity_from,validity_to,member_id) VALUES (1,'CLASS_I','Observaciones!!!','2017-01-04','2020-01-04',100);
INSERT INTO member_medical_certification(id,medical_certification_class,observations,validity_from,validity_to,member_id) VALUES (2,'CLASS_I','Observaciones!!!','2017-01-04','2020-01-04',101);

--Aircrafts
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (100,'Cessna','152','LV-OEE','ACTIVE');
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (101,'Cessna','152','LV-AMS','ACTIVE');
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (102,'Cessna','150','LV-LGF','ACTIVE');
INSERT INTO aircraft(id,brand,model,registration,status) VALUES (103,'Pipper','PA-11','LV-YMV','OUT_OF_ORDER');

--Aircraft Insurance
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (100,'Sancor','ABC-4444224422','Terceros Completo','2016-07-03','2017-09-03',100);
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (101,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-09-03',101);
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (102,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-11-03',102);
--INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (103,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-11-03',103);

--Payment Methods
INSERT INTO payment_method(id,charge,description,discount,fee,payment_type) VALUES (1,0,'Efectivo',0,0,'CASH');
INSERT INTO payment_method(id,charge,description,discount,fee,payment_type) VALUES (2,0.1,'VISA',0,0,'CREDIT_CARD');
INSERT INTO payment_method(id,charge,description,discount,fee,payment_type) VALUES (3,0,'Cuenta Corriente',0,0,'ACCOUNT');

--Flight Record
INSERT INTO flight_record(id,end_flight,landings,nature,purpose,start_flight,status,type,aircraft_id,airfield_destiny_id,airfield_origin_id) VALUES (100,'2017-01-06 20:54:05.296',0,'LDI','VP','2017-01-06 19:44:05.296','OPENED','ENT',100,NULL,NULL);
INSERT INTO flight_record(id,end_flight,landings,nature,purpose,start_flight,status,type,aircraft_id,airfield_destiny_id,airfield_origin_id) VALUES (101,'2017-01-06 20:54:05.296',0,'LDI','VP','2017-01-06 19:44:05.296','CLOSED','ENT',101,NULL,NULL);

--Crew Member
INSERT INTO crew_member(id,crew_member_role,member_id,flight_record_id) VALUES (100,'PIC',1,100);
INSERT INTO crew_member(id,crew_member_role,member_id,flight_record_id) VALUES (101,'PIC',1,101);
INSERT INTO crew_member(id,crew_member_role,member_id,flight_record_id) VALUES (102,'INS',2,101);

INSERT INTO flight_record_issued (id, amount, compensation_date, compensation_document, creation_date, currency, document_type, expiration_date, referenced_document_id, member_id, payment_method_id, user_id) VALUES (100, 2400,NULL,NULL,'2017-01-09','ARS','FRI','2017-02-09',101,100,3,NULL);

--Account
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(100,1,0,0,0,'Activo');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(200,1,1,0,0,'Caja y Bancos');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(300,1,1,1,0,'Caja');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(400,1,1,1,1,'Caja Pesos');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(500,1,1,1,2,'Caja U$s');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(600,1,1,1,3,'Valores a Depositar');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(700,1,1,1,4,'Tarjetas de debito');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(800,1,1,1,5,'Tarjetas de credito');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(900,1,1,1,6,'Fondo prevision');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(1000,1,1,1,7,'Fondo Fijo');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(1100,1,1,2,0,'Bancos');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(1200,1,1,2,1,'Banco Provincia Caja de Ahorro U$S');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(1300,1,1,2,2,'Banco Provincia Cuenta Corriente U$S');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(2100,1,1,3,5,'Deudores por vuelos');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(8200,4,0,0,0,'Ingresos');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(8500,4,1,1,1,'Horas de vuelo');

--Template Entry
INSERT INTO template_entry (id, document_type, description) VALUES(100,'FRI', 'Asiento automatico de Ficha de Vuelo');

--Template Entry Line
INSERT INTO template_entry_line(id, accounting_entry_item_type, factor, payment_type, account_id, template_entry_id) VALUES(100, 'CREDIT', 1, NULL,2100,100);
INSERT INTO template_entry_line(id, accounting_entry_item_type, factor, payment_type, account_id, template_entry_id) VALUES(101, 'DEBIT', 1, 'ACCOUNT',8500,100);
