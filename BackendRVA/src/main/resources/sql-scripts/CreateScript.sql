DROP TABLE IF EXISTS fakultet CASCADE;
DROP TABLE IF EXISTS departman CASCADE;
DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS status CASCADE;

DROP SEQUENCE IF EXISTS fakultet_seq;
DROP SEQUENCE IF EXISTS departman_seq;
DROP SEQUENCE IF EXISTS student_seq;
DROP SEQUENCE IF EXISTS status_seq;

CREATE TABLE fakultet(
	id integer not null,
    naziv varchar(100) not null,
    sediste varchar(50)
);

CREATE TABLE departman(
	id integer not null,
    naziv varchar(100) not null,
    oznaka varchar(10) not null,
    fakultet int not null
);

CREATE TABLE student(
    id integer not null,
    ime varchar(50) not null,
    prezime varchar(50) not null,
    broj_indexa varchar(20) not null,
    status integer not null,
    departman integer not null
);

CREATE TABLE status(
	id integer not null,
    naziv varchar(100) not null,
    oznaka varchar(10) not null

);

ALTER TABLE fakultet ADD CONSTRAINT
PK_fakultet PRIMARY KEY(id);
ALTER TABLE departman ADD CONSTRAINT
PK_departman PRIMARY KEY(id);
ALTER TABLE student ADD CONSTRAINT
PK_student PRIMARY KEY(id);
ALTER TABLE status ADD CONSTRAINT
PK_status PRIMARY KEY(id);

ALTER TABLE student ADD CONSTRAINT
FK_student_departman FOREIGN KEY(departman) REFERENCES departman(id);
ALTER TABLE student ADD CONSTRAINT
FK_student_status FOREIGN KEY(status) REFERENCES status(id);

ALTER TABLE departman ADD CONSTRAINT
FK_departman_fakultet FOREIGN KEY(fakultet) REFERENCES fakultet(id);

CREATE INDEX IDXFK_departman_fakultet
ON departman(fakultet);
CREATE INDEX IDXFK_student_departman
ON student(departman);
CREATE INDEX IDXFK_student_status
ON student(status);

CREATE SEQUENCE fakultet_seq
INCREMENT 1;
CREATE SEQUENCE departman_seq
INCREMENT 1;
CREATE SEQUENCE student_seq
INCREMENT 1;
CREATE SEQUENCE status_seq
INCREMENT 1;
