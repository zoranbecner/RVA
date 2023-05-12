-- fakultet
INSERT INTO "fakultet"("id", "naziv", "sediste")
VALUES (nextval('fakultet_seq'), 'Fakultet tehnickih nauka', 'Novi Sad');

INSERT INTO "fakultet"("id", "naziv", "sediste")
VALUES (nextval('fakultet_seq'), 'Prirodno matematicki fakultet', 'Novi Sad');

INSERT INTO "fakultet"("id", "naziv", "sediste")
VALUES (nextval('fakultet_seq'), 'Ekonomski fakultet', 'Novi Sad');

INSERT INTO "fakultet"("id", "naziv", "sediste")
VALUES (nextval('fakultet_seq'), 'Filozofski fakultet', 'Novi Sad');


-- status
INSERT INTO "status"("id", "naziv", "oznaka")
VALUES (nextval('status_seq'), 'budzet', 'B');
INSERT INTO "status"("id", "naziv", "oznaka")
VALUES (nextval('status_seq'), 'samofinansiranje', 'S');

--departman
INSERT INTO "departman"("id", "naziv", "oznaka", "fakultet")
VALUES (nextval('departman_seq'), 'Departman za industijsko inzenjerstvo', 'IIM', 1);
INSERT INTO "departman"("id", "naziv", "oznaka", "fakultet")
VALUES (nextval('departman_seq'), 'Departman za arhitekturu i urbanizam', 'AIU', 1);

INSERT INTO "departman"("id", "naziv", "oznaka", "fakultet")
VALUES (nextval('departman_seq'), 'Departman za fiziku', 'F', 2);
INSERT INTO "departman"("id", "naziv", "oznaka", "fakultet")
VALUES (nextval('departman_seq'), 'Departman za hemiju', 'H', 2);

INSERT INTO "departman"("id", "naziv", "oznaka", "fakultet")
VALUES (nextval('departman_seq'), 'Departman za poslovnu ekonomiju', 'PE', 3);

INSERT INTO "departman"("id", "naziv", "oznaka", "fakultet")
VALUES (nextval('departman_seq'), 'Departman za psihologiju', 'P', 4);

--student
INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Marko', 'Markovic', 'IT10/2023', 1, 1);
INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Marija', 'Maric', 'IT61/2023', 2, 1);

INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Nikola', 'Nikolic', 'AR10/2023', 1, 2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Dana', 'Danic', 'AR40/2023', 2, 2);

INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Danka', 'Danicic', 'F30/2023', 1, 3);
INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Dalibor', 'Maric', 'F50/2023', 2, 3);

INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Petar', 'Perovic', 'M40/2023', 2, 4);
INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Vojin', 'Vojinovic', 'M10/2023', 1, 4);

INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Jovana', 'Danicic', 'E22/2023', 1, 5);
INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Jovan', 'Micic', 'E42/2023', 2, 5);

INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Milana', 'Milic', 'P77/2023', 2, 6);
INSERT INTO "student"("id", "ime", "prezime", "broj_indexa", "status", "departman")
VALUES (nextval('student_seq'), 'Marko', 'Lakic', 'P17/2023', 1, 6);
