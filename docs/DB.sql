DROP TABLE USUARIOS;


CREATE TABLE USUARIOS 
(
  ID NUMBER NOT NULL,
  NAME VARCHAR2(255 BYTE), 
  CORREO VARCHAR2(255 BYTE), 
  ROL NUMBER,
  PRIMARY KEY (ID)

);



INSERT INTO USUARIOS(ID,NAME,CORREO,ROL) values (1,'Torres','tores@xd.com',0);

COMMIT;


