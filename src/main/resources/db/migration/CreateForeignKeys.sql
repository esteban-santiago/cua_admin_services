begin;
-- CREATE LINK "flight_record_issued_member_id_fk" -------------
ALTER TABLE "public"."flight_record_issued"
	ADD CONSTRAINT "flight_record_issued_member_id_fk" FOREIGN KEY ( "member_id" )
	REFERENCES "public"."member" ( "id" ) MATCH FULL
	ON DELETE Cascade
	ON UPDATE Cascade;
-- CREATE LINK "crew_member_member_id_fk" ----------------------
ALTER TABLE "public"."crew_member"
	ADD CONSTRAINT "crew_member_member_id_fk" FOREIGN KEY ( "member_id" )
	REFERENCES "public"."member" ( "id" ) MATCH FULL
	ON DELETE Cascade
	ON UPDATE Cascade;
-- CREATE LINK "medical_certification_member_id_fk" ------------
ALTER TABLE "public"."member_medical_certification"
	ADD CONSTRAINT "medical_certification_member_id_fk" FOREIGN KEY ( "member_id" )
	REFERENCES "public"."member" ( "id" ) MATCH FULL
	ON DELETE Cascade
	ON UPDATE Cascade;
-- -------------------------------------------------------------
commit;