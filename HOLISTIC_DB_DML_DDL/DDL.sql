-- public.appointment_type definition

-- Drop table

-- DROP TABLE public.appointment_type;

CREATE TABLE public.appointment_type (
	id_appointment_type serial4 NOT NULL,
	type_name varchar(20) NOT NULL,
	CONSTRAINT appointment_type_pk PRIMARY KEY (id_appointment_type),
	CONSTRAINT appointment_type_un UNIQUE (type_name)
);


-- public.drug definition

-- Drop table

-- DROP TABLE public.drug;

CREATE TABLE public.drug (
	id_drug serial4 NOT NULL,
	drug_lab varchar(255) NOT NULL,
	name varchar(255) NULL DEFAULT NULL::character varying,
	active varchar(255) NOT NULL DEFAULT NULL::character varying,
	active_percentage numeric(10) NOT NULL,
	CONSTRAINT drug_pk PRIMARY KEY (id_drug)
);


-- public."role" definition

-- Drop table

-- DROP TABLE public."role";

CREATE TABLE public."role" (
	id_rol serial4 NOT NULL,
	name varchar(20) NOT NULL,
	CONSTRAINT rol_pk PRIMARY KEY (id_rol),
	CONSTRAINT rol_un UNIQUE (name)
);


-- public.shift definition

-- Drop table

-- DROP TABLE public.shift;

CREATE TABLE public.shift (
	id_shift serial4 NOT NULL,
	start_hour time NOT NULL,
	finish_hour time NOT NULL,
	CONSTRAINT shift_pk PRIMARY KEY (id_shift)
);


-- public.test definition

-- Drop table

-- DROP TABLE public.test;

CREATE TABLE public.test (
	id_test serial4 NOT NULL,
	name varchar(255) NOT NULL,
	gender bpchar(1) NULL,
	start_age int4 NULL,
	frequency int4 NULL,
	CONSTRAINT test_pk PRIMARY KEY (id_test)
);


-- public.vaccine definition

-- Drop table

-- DROP TABLE public.vaccine;

CREATE TABLE public.vaccine (
	id_vaccine serial4 NOT NULL,
	name varchar(20) NOT NULL,
	required_doses int4 NOT NULL,
	CONSTRAINT vaccine_pk PRIMARY KEY (id_vaccine),
	CONSTRAINT vaccine_un UNIQUE (name)
);


-- public.area definition

-- Drop table

-- DROP TABLE public.area;

CREATE TABLE public.area (
	id_area serial4 NOT NULL,
	name varchar(20) NOT NULL,
	id_shift int4 NOT NULL,
	CONSTRAINT area_pk PRIMARY KEY (id_area),
	CONSTRAINT area_un UNIQUE (name),
	CONSTRAINT area_fk FOREIGN KEY (id_shift) REFERENCES public.shift(id_shift) ON DELETE RESTRICT ON UPDATE CASCADE
);


-- public.inmunization definition

-- Drop table

-- DROP TABLE public.inmunization;

CREATE TABLE public.inmunization (
	id_inmunization serial4 NOT NULL,
	id_vaccine int4 NOT NULL,
	age int4 NOT NULL,
	CONSTRAINT inmunization_pk PRIMARY KEY (id_inmunization),
	CONSTRAINT inmunization_fk FOREIGN KEY (id_vaccine) REFERENCES public.vaccine(id_vaccine) ON DELETE RESTRICT ON UPDATE CASCADE
);


-- public.person definition

-- Drop table

-- DROP TABLE public.person;

CREATE TABLE public.person (
	id_person serial4 NOT NULL,
	name varchar(30) NOT NULL,
	last_name varchar(50) NOT NULL,
	status bool NOT NULL,
	email varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	id_rol int4 NOT NULL,
	gender bpchar(1) NOT NULL,
	birthdate date NOT NULL,
	id_area int4 NULL,
	CONSTRAINT person_pk PRIMARY KEY (id_person),
	CONSTRAINT person_un UNIQUE (username),
	CONSTRAINT person_fk FOREIGN KEY (id_rol) REFERENCES public."role"(id_rol) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT person_fk_1 FOREIGN KEY (id_area) REFERENCES public.area(id_area) ON DELETE RESTRICT ON UPDATE CASCADE
);


-- public."token" definition

-- Drop table

-- DROP TABLE public."token";

CREATE TABLE public."token" (
	id_token serial4 NOT NULL,
	"content" varchar NOT NULL,
	active bool NOT NULL DEFAULT true,
	"timestamp" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	id_person int4 NOT NULL,
	CONSTRAINT token_pk PRIMARY KEY (id_token),
	CONSTRAINT token_fk FOREIGN KEY (id_person) REFERENCES public.person(id_person)
);


-- public.appointment definition

-- Drop table

-- DROP TABLE public.appointment;

CREATE TABLE public.appointment (
	id_appointment serial4 NOT NULL,
	id_patient int4 NOT NULL,
	id_doctor int4 NULL,
	appointment_time timestamp NOT NULL,
	status bool NULL DEFAULT false,
	appointment_details varchar NULL,
	id_appointment_type int4 NOT NULL,
	id_inmunization int4 NULL,
	id_test int4 NULL,
	id_area int4 NOT NULL,
	id_vaccine int4 NULL,
	CONSTRAINT appointment_pk PRIMARY KEY (id_appointment),
	CONSTRAINT appointment_fk FOREIGN KEY (id_patient) REFERENCES public.person(id_person) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT appointment_fk_2 FOREIGN KEY (id_appointment_type) REFERENCES public.appointment_type(id_appointment_type) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT appointment_fk_3 FOREIGN KEY (id_test) REFERENCES public.test(id_test) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT appointment_fk_4 FOREIGN KEY (id_inmunization) REFERENCES public.inmunization(id_inmunization) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT appointment_fk_5 FOREIGN KEY (id_area) REFERENCES public.area(id_area) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT appointment_fk_6 FOREIGN KEY (id_vaccine) REFERENCES public.vaccine(id_vaccine)
);


-- public.prescription definition

-- Drop table

-- DROP TABLE public.prescription;

CREATE TABLE public.prescription (
	id_prescription serial4 NOT NULL,
	id_drug int4 NOT NULL,
	indication varchar NULL DEFAULT 'No hay instrucciones adicionales'::character varying,
	daily_amount numeric NOT NULL,
	quantity int4 NOT NULL,
	id_appointment int4 NOT NULL,
	CONSTRAINT prescription_pk PRIMARY KEY (id_prescription),
	CONSTRAINT prescription_fk FOREIGN KEY (id_drug) REFERENCES public.drug(id_drug) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT prescription_fk_2 FOREIGN KEY (id_appointment) REFERENCES public.appointment(id_appointment) ON DELETE RESTRICT ON UPDATE CASCADE
);