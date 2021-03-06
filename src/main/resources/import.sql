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

--Organization
INSERT INTO organization (id, name, status, tax_id) VALUES(1,'Centro Universitario de Aviación','ACTIVE','30-12345678-2');

--Category
INSERT INTO category(id, description) VALUES (1,'Socio');
INSERT INTO category(id, description) VALUES (2,'Vitalicio');
INSERT INTO category(id, description) VALUES (3,'Comisión Directiva');
INSERT INTO category(id, description) VALUES (4,'Comisión Directiva Vitalicio');


--Member Profile
INSERT INTO member_profile (id, status, category_id) VALUES(100, 'ACTIVE', 1);
INSERT INTO member_profile (id, status, category_id) VALUES(101, 'ACTIVE', 1);
INSERT INTO member_profile (id, status, category_id) VALUES(102, 'ACTIVE', 1);
INSERT INTO member_profile (id, status, category_id) VALUES(103, 'ACTIVE', 1);

--Pilot Profile
INSERT INTO pilot_profile (id, licence) VALUES(100, '24036873');
INSERT INTO pilot_profile (id, licence) VALUES(101, '28036873');
INSERT INTO pilot_profile (id, licence) VALUES(102, '28036873');

--Person
INSERT INTO person (id, date_of_birth, date_of_creation, identity_card_number, identity_card_type, name, organization_id,status, customer_id, employee_id, member_id, pilot_id, nationality_id) VALUES (100, '1974-08-02', '2017-02-03', '24036873', 'DNI', 'SANTIAGO, Esteban', 1,'ACTIVE',NULL, NULL, 100, 100,1);
INSERT INTO person (id, date_of_birth, date_of_creation, identity_card_number, identity_card_type, name, organization_id,status, customer_id, employee_id, member_id, pilot_id, nationality_id) VALUES (101, '1987-01-27', '2017-02-03', '32036874', 'DNI', 'SANTIAGO, Pablo', 1,'ACTIVE',NULL, NULL, 101, NULL,2);
INSERT INTO person (id, date_of_birth, date_of_creation, identity_card_number, identity_card_type, name, organization_id,status, customer_id, employee_id, member_id, pilot_id, nationality_id) VALUES (102, '1974-08-02', '2017-02-03', '28036873', 'DNI', 'SANTIAGO, Guillermo', 1,'ACTIVE',NULL, NULL, 102, 101,1);
INSERT INTO person (id, date_of_birth, date_of_creation, identity_card_number, identity_card_type, name, organization_id,status, customer_id, employee_id, member_id, pilot_id, nationality_id) VALUES (103, '1987-01-27', '2017-02-03', '28036873', 'DNI', 'SANTIAGO, Gustavo', 1, 'ACTIVE',NULL, NULL, 103, 102,2);

--Users
INSERT INTO users (id, name, password, profile, status) VALUES (100,'esantiago','passwd','USER','ACTIVE');
INSERT INTO users (id, name, password, profile, status) VALUES (101,'psantiago','passwd','ADMINISTRATOR','ACTIVE');

--Medical Certifications
INSERT INTO pilot_medical_certification(id,medical_certification_class,observations,validity_from,validity_to,pilot_id) VALUES (1,'CLASS_I','Observaciones!!!','2017-01-04','2020-01-04',100);
INSERT INTO pilot_medical_certification(id,medical_certification_class,observations,validity_from,validity_to,pilot_id) VALUES (2,'CLASS_I','Observaciones!!!','2017-01-04','2020-01-04',101);
INSERT INTO pilot_medical_certification(id,medical_certification_class,observations,validity_from,validity_to,pilot_id) VALUES (3,'CLASS_I','Observaciones!!!','2017-01-04','2020-01-04',102);

--Payment Methods
INSERT INTO payment_method(id, description) VALUES (1,'Efectivo');
INSERT INTO payment_method(id, description) VALUES (2,'Cheque Bancario');
INSERT INTO payment_method(id, description) VALUES (3,'Cuenta Corriente');
INSERT INTO payment_method(id, description) VALUES (4,'Tarjeta de Crédito');
INSERT INTO payment_method(id, description) VALUES (5,'Tarjeta de Débito');

--Payment Terms
INSERT INTO  payment_term (id, charge, description, discount, payment_method_id) VALUES (1, 0,'Contado', 0,1);
INSERT INTO  payment_term (id, charge, description, discount, payment_method_id) VALUES (2, 0,'Hasta 30 dias', 0,2);
INSERT INTO  payment_term (id, charge, description, discount, payment_method_id) VALUES (3, 0.15,'Entre 31 y 60 dias', 0,2);
INSERT INTO  payment_term (id, charge, description, discount, payment_method_id) VALUES (4, 0.10,'1 Cuota', 0,4);
INSERT INTO  payment_term (id, charge, description, discount, payment_method_id) VALUES (5, 0.10,'3 Cuotas', 0,4);
INSERT INTO  payment_term (id, charge, description, discount, payment_method_id) VALUES (6, 0,'1 Pago', 0,5);

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
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(1900,1,1,3,3,'Cuotas a cobrar');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(2100,1,1,3,5,'Deudores por vuelos');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(7000,2,4,1,2,'Acreedores varios');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(8200,4,0,0,0,'Ingresos');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(8500,4,1,1,1,'Horas de vuelo');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(9000,4,1,2,1,'Cuotas y servicios');
INSERT INTO account (id,first_order_grouper, second_order_grouper, third_order_grouper, fourth_order_grouper,description) VALUES(10500,4,2,1,7,'Recargo financiero');

--Template Entry
INSERT INTO template_entry (id, document_type, description) VALUES(100,'FRI', 'Asiento automático de Ficha de Vuelo');
INSERT INTO template_entry (id, document_type, description) VALUES(101,'RCI', 'Asiento automático de Recibo');
INSERT INTO template_entry (id, document_type, description) VALUES(102,'CNI', 'Asiento automático de Nota de Crédito');
INSERT INTO template_entry (id, document_type, description) VALUES(103,'DNI', 'Asiento automático de Nota de Dédito');

--Template Entry Line FRI
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, document_type, factor, account_id, template_entry_id) VALUES(100, 'ENTRY_LINE_DOCUMENT_TYPE','DEBIT','FRI',1,2100,100);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, document_type, factor, account_id, template_entry_id) VALUES(101, 'ENTRY_LINE_DOCUMENT_TYPE','CREDIT','FRI',1,8500,100);
--Template Entry Line RCI
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, factor, account_id, payment_method_id, template_entry_id) VALUES(102, 'ENTRY_LINE_PAYMENT_METHOD','DEBIT',1,400,1,101);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, factor, account_id, payment_method_id, template_entry_id) VALUES(103, 'ENTRY_LINE_PAYMENT_METHOD','DEBIT',1,600,2,101);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, factor, account_id, payment_method_id, template_entry_id) VALUES(104, 'ENTRY_LINE_PAYMENT_METHOD','DEBIT',1,700,5,101);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, factor, account_id, payment_method_id, template_entry_id) VALUES(105, 'ENTRY_LINE_PAYMENT_METHOD','DEBIT',1,7000,3,101);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, document_type, factor, account_id, template_entry_id)     VALUES(106, 'ENTRY_LINE_COMPENSATED_DOC_TYPE','CREDIT','RCI',1,2100,101);
--Template Entry Line RCI Tarjeta
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, factor, account_id, payment_method_id, template_entry_id) VALUES(107, 'ENTRY_LINE_PAYMENT_METHOD','DEBIT',1,800,4,101);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, factor, account_id, payment_term_id, template_entry_id) VALUES(108, 'ENTRY_LINE_PAYMENT_TERM','DEBIT',0.1,10500,3,101);
--Template Entry Line CNI
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, document_type, factor, account_id, template_entry_id) VALUES(109, 'ENTRY_LINE_DOCUMENT_TYPE','DEBIT','CNI',1,8500,102);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, document_type, factor, account_id, template_entry_id) VALUES(110, 'ENTRY_LINE_DOCUMENT_TYPE','CREDIT','CNI',1,2100,102);
--Template Entry Line DNI
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, document_type, factor, account_id, template_entry_id) VALUES(111, 'ENTRY_LINE_DOCUMENT_TYPE','CREDIT','DNI',1,8500,103);
INSERT INTO template_entry_line (id, entry_line_discriminator, accounting_entry_item_type, document_type, factor, account_id, template_entry_id) VALUES(112, 'ENTRY_LINE_DOCUMENT_TYPE','DEBIT','DNI',1,2100,103);

--Product Group
INSERT INTO product_group (description, id) VALUES ('Servicios de vuelo', 100);
INSERT INTO product_group (description, id) VALUES ('Servicios sociales', 101);

--Product SubGroup---Servicios de vuelo
INSERT INTO product_group_subgroup (description, group_id, id) VALUES ('C150', 100,100);
INSERT INTO product_group_subgroup (description, group_id, id) VALUES ('PA-11', 100,101);
INSERT INTO product_group_subgroup (description, group_id, id) VALUES ('C152', 100, 102);
INSERT INTO product_group_subgroup (description, group_id, id) VALUES ('PA-28', 100, 103);
--Product SubGroup---Servicios sociales
INSERT INTO product_group_subgroup (description, group_id, id) VALUES ('Cargos sociales', 101, 104);

--Product--Hora de vuelo
INSERT INTO product (currency, price, description, group_id, subgroup_id, type, id) VALUES('ARS', 1150, 'Cessna 150', 100, 100,'SERVICE', 100);
INSERT INTO product (currency, price, description, group_id, subgroup_id, type, id) VALUES('ARS', 1152,'Cessna 152', 100, 102,'SERVICE', 101);
INSERT INTO product (currency, price, description, group_id, subgroup_id, type, id) VALUES('ARS', 911,'PA-11', 100, 101,'SERVICE', 102);
INSERT INTO product (currency, price, description, group_id, subgroup_id, type, id) VALUES('ARS', 2100,'PA-28', 100, 103,'SERVICE', 103);
--Product---Cuota Social Mensual
INSERT INTO product (currency, price, description, group_id, subgroup_id, type, id) VALUES('ARS', 300,'Cuota mensual social', 101, 104,'SERVICE', 104);

--Product Profile--Horas de vuelo
INSERT INTO product_profile (id, group_id, subgroup_id,  product_id) VALUES (100,100,100,100);
INSERT INTO product_profile (id, group_id, subgroup_id,  product_id) VALUES (101,100,102,101);
INSERT INTO product_profile (id, group_id, subgroup_id,  product_id) VALUES (102,100,102,101);
INSERT INTO product_profile (id, group_id, subgroup_id,  product_id) VALUES (103,100,103,103);
--Product Profile--Cuota social
INSERT INTO product_profile (id, group_id, subgroup_id,  product_id) VALUES (104,101,104,104);


--Aircrafts
INSERT INTO aircraft(id,brand,model,registration,status, product_profile_id) VALUES (100,'Cessna','C-152','LV-OEE','ACTIVE', 101);
INSERT INTO aircraft(id,brand,model,registration,status, product_profile_id) VALUES (101,'Cessna','C-152','LV-AMS','ACTIVE', 102);
INSERT INTO aircraft(id,brand,model,registration,status, product_profile_id) VALUES (102,'Cessna','C-150','LV-LGF','ACTIVE',100);
INSERT INTO aircraft(id,brand,model,registration,status, product_profile_id) VALUES (103,'Pipper','PA-11','LV-YMV','OUT_OF_ORDER', 103);
INSERT INTO aircraft(id,brand,model,registration,status, product_profile_id) VALUES (104,'Pipper','PA-28','LV-AXH','ACTIVE', 103);

--Aircrafts Components
INSERT INTO aircraft_component (id, description,relocable,serial, type, aircraft_id) VALUES (100,'Motor 100HP',TRUE,'1MOTOR100HP','ENGINE',101);
INSERT INTO aircraft_component (id, description,relocable,serial, type, aircraft_id) VALUES (101,'Helice de madera',TRUE,'1HELICEMAD','PROPELLER',101);
INSERT INTO aircraft_component (id, description,relocable,serial, type, aircraft_id) VALUES (102,'Capsula C152',FALSE,'CAPSULAC152','CAPSULE',100);


--Aircraft Insurance
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (100,'Sancor','ABC-4444224422','Terceros Completo','2016-07-03','2017-09-03',100);
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (101,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-09-03',101);
INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (102,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-11-03',102);
--INSERT INTO aircraft_insurance(id,company,policy,type,validity_from,validity_to,aircraft_id) VALUES (103,'Sancor','ABC-4444224423','Terceros Completo','2016-07-03','2017-11-03',103);

--Flight Record
INSERT INTO flight_record(id,end_flight,landings,nature,purpose,start_flight,status,type,aircraft_id,airfield_destiny_id,airfield_origin_id) VALUES (100,'2017-01-06 20:54:05.296',0,'LDI','VP','2017-01-06 19:44:05.296','OPENED','ENT',100,NULL,NULL);
INSERT INTO flight_record(id,end_flight,landings,nature,purpose,start_flight,status,type,aircraft_id,airfield_destiny_id,airfield_origin_id) VALUES (101,'2017-01-06 20:54:05.296',0,'LDI','VP','2017-01-06 19:44:05.296','OPENED','ENT',101,NULL,NULL);
INSERT INTO flight_record(id,end_flight,landings,nature,purpose,start_flight,status,type,aircraft_id,airfield_destiny_id,airfield_origin_id) VALUES (102,'2017-01-06 20:54:05.296',0,'LDI','VP','2017-01-06 19:44:05.296','OPENED','ENT',102,NULL,NULL);

--Crew Member
INSERT INTO crew_member(id,crew_member_role,person_id,flight_record_id) VALUES (100,'PIC',100,100);
INSERT INTO crew_member(id,crew_member_role,person_id,flight_record_id) VALUES (101,'PIC',100,101);
INSERT INTO crew_member(id,crew_member_role,person_id,flight_record_id) VALUES (102,'INST',101,101);
INSERT INTO crew_member(id,crew_member_role,person_id,flight_record_id) VALUES (103,'PIC',100,102);

--Flight Record Issued
--INSERT INTO flight_record_issued (id, amount, compensation_date, compensated_by, creation_date, currency, document_type, expiration_date, referenced_document_id, person_id, payment_method_id, user_id) VALUES (100, 2400,NULL,NULL,'2017-01-09','ARS','FRI','2017-02-09',100,100,3,100);
--INSERT INTO flight_record_issued (id, amount, compensation_date, compensated_by, creation_date, currency, document_type, expiration_date, referenced_document_id, person_id, payment_method_id, user_id) VALUES (101, 2401,NULL,NULL,'2017-01-09','ARS','FRI','2017-02-09',100,100,3,101);
--INSERT INTO flight_record_issued (id, amount, compensation_date, compensation_document_id, creation_date, currency, document_type, expiration_date, referenced_document_id, member_id, payment_method_id, user_id) VALUES (102, 4110,NULL,NULL,'2017-01-09','ARS','FRI','2017-02-09',101,100,3,101);
--INSERT INTO flight_record_issued (id, compensation_date, creation_date, document_type, expiration_date, referenced_document_id, status, compensated_by, person_id, user_id, legal_id) VALUES 
--                                 (100, '2017-01-09',    '2017-01-09',   'FRI',         '2017-01-09' , 101,                    'COMPENSATED', 101, 100,NULL, 7000000);

--Receipt Issued
--INSERT INTO receipt_issued (id, amount, compensation_date, creation_date, currency, document_type, expiration_date, referenced_document_id, status, compensated_by, person_id, payment_method_id, payment_term_id, user_id) VALUES (200, -2400,NULL,'2017-01-09','ARS','RCI','2017-01-09', 0, 'OPENED' ,NULL, 100,1, NULL,100);
--INSERT INTO receipt_issued (id, amount, compensation_date, creation_date, currency, document_type, expiration_date, referenced_document_id, status, compensated_by, person_id, payment_method_id, payment_term_id, user_id) VALUES (201, -3400,NULL,'2017-01-09','ARS','RCI','2017-01-09', 0, 'OPENED' ,NULL, 100,4, 3,100);
--INSERT INTO receipt_issued (id, amount, compensation_date, creation_date, currency, document_type, expiration_date, referenced_document_id, status, compensated_by, person_id, payment_method_id, payment_term_id, user_id) VALUES 
--INSERT INTO receipt_issued (id, compensation_date, creation_date, document_type, expiration_date, referenced_document_id, status, compensated_by, person_id, user_id, legal_id) VALUES(102,'2017-01-09','2017-01-09','RCI','2017-01-09',0,                      'CLOSED' ,102,          100,        NULL,nextval('document_credit_note_issued_id_seq'));
--INSERT INTO receipt_issued (id, compensation_date, creation_date, document_type, expiration_date, referenced_document_id, status, compensated_by, person_id, user_id, legal_id) VALUES(101,'2017-01-09','2017-01-09','RCI','2017-01-09',0,'COMPENSATED' ,NULL,          100,        NULL,1000000);

--Airfields
INSERT INTO airfield (id, iata_code, name) VALUES(1,'ACB','Coronel Bogado Agroservicios');
INSERT INTO airfield (id, iata_code, name) VALUES(2,'ACH','General Acha');
INSERT INTO airfield (id, iata_code, name) VALUES(3,'ACM','Arrecifes La Cura Malal');
INSERT INTO airfield (id, iata_code, name) VALUES(4,'ADO','Puerto Deseado');
INSERT INTO airfield (id, iata_code, name) VALUES(5,'ADT','Bandera Agroservicio');
INSERT INTO airfield (id, iata_code, name) VALUES(6,'ADU','Bandera Dutto');
INSERT INTO airfield (id, iata_code, name) VALUES(7,'AER','Buenos Aires Aeroparque J Newbery');
INSERT INTO airfield (id, iata_code, name) VALUES(8,'AGI','Piedra del Aguila');
INSERT INTO airfield (id, iata_code, name) VALUES(9,'AGR','Alta Gracia');
INSERT INTO airfield (id, iata_code, name) VALUES(10,'AII','Chacabuco Las 2 A');
INSERT INTO airfield (id, iata_code, name) VALUES(11,'AJA','La Laja');
INSERT INTO airfield (id, iata_code, name) VALUES(12,'ALC','Ameghino La Chacra');
INSERT INTO airfield (id, iata_code, name) VALUES(13,'ALL','Allen');
INSERT INTO airfield (id, iata_code, name) VALUES(14,'ALR','Alejandro Roca');
INSERT INTO airfield (id, iata_code, name) VALUES(15,'ALT','ruz Alta');
INSERT INTO airfield (id, iata_code, name) VALUES(16,'ALV','Alvear Agroalvear');
INSERT INTO airfield (id, iata_code, name) VALUES(17,'AMA','Roberts La Amalia');
INSERT INTO airfield (id, iata_code, name) VALUES(18,'AME','Ameghino Sigfrido Rohr Aviagro');
INSERT INTO airfield (id, iata_code, name) VALUES(19,'AMG','Ameghino');
INSERT INTO airfield (id, iata_code, name) VALUES(20,'ANA','Parana Aeroclub');
INSERT INTO airfield (id, iata_code, name) VALUES(21,'MAT','Matanza');

--Contracts

--Hangars
INSERT INTO hangar(id, description) VALUES(1,'Hangar A');
INSERT INTO hangar(id, description) VALUES(2,'Hangar B');

--Dimensions
INSERT INTO dimension(id, description, side_one, side_two) VALUES(1, 'Avión ala baja', 4,4);
INSERT INTO dimension(id, description, side_one, side_two) VALUES(2, 'Avión ala alta', 4,4);
INSERT INTO dimension(id, description, side_one, side_two) VALUES(3, 'Helicóptero', 2,4);
INSERT INTO dimension(id, description, side_one, side_two) VALUES(4, 'Avión ala baja', 4,4);

--Positions
--(id, dimension_id, hangar_id , position_id
INSERT INTO position (id, dimension_id, hangar_id) VALUES(3,1,1);
INSERT INTO position (id, dimension_id, hangar_id) VALUES(4,1,1);
--INSERT INTO position (id, dimension_id, hangar_id) VALUES(5,1,1);
--INSERT INTO position (id, dimension_id, hangar_id, position_id) VALUES(3,1,1,3); 


--Hangar Positions
INSERT INTO hangar_positions (hangar_id, positions_id) VALUES(1,3);
INSERT INTO hangar_positions (hangar_id, positions_id) VALUES(1,4);
--INSERT INTO hangar_positions (hangar_id, positions_id) VALUES(1,4);
--INSERT INTO hangar_positions (hangar_id, positions_id) VALUES(1,5);
