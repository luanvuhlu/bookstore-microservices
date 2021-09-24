--liquibase formatted sql
--changeset liquibase:0001.create_book
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'BOOK' LIMIT 1;
CREATE TABLE BOOK (
	ID INT NOT NULL AUTO_INCREMENT, 
	TITLE VARCHAR(100) NOT NULL, 
	AUTHOR VARCHAR(100) DEFAULT NULL, 
	GENRE VARCHAR(50) DEFAULT NULL, 
	HEIGHT INT NOT NULL, 
	PUBLISHER VARCHAR(100) DEFAULT NULL,
	PRIMARY KEY(ID));