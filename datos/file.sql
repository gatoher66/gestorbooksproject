PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE profesores (id integer primary key, usuario text, password text);
INSERT INTO "profesores" VALUES(1,'jonas','jonas');
INSERT INTO "profesores" VALUES(2,'pablo','pablo');
COMMIT;
