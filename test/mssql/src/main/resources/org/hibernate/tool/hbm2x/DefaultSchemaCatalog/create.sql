CREATE SCHEMA OVRTEST
CREATE TABLE OVRTEST.CATMASTER ( ID CHAR NOT NULL, NAME VARCHAR(20), PRIMARY KEY (ID) )
CREATE TABLE OVRTEST.CATCHILD  ( CHILDID CHAR NOT NULL, MASTERREF CHAR, PRIMARY KEY (CHILDID), FOREIGN KEY (MASTERREF) REFERENCES OVRTEST.CATMASTER(ID) )
CREATE SCHEMA HTT
CREATE TABLE HTT.MASTER ( ID CHAR NOT NULL, NAME VARCHAR(20), PRIMARY KEY (ID) )
CREATE TABLE HTT.CHILD  ( CHILDID CHAR NOT NULL, MASTERREF CHAR, PRIMARY KEY (CHILDID), FOREIGN KEY (MASTERREF) REFERENCES HTT.MASTER(ID) )			
