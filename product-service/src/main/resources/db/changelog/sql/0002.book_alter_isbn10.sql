--liquibase formatted sql
--changeset liquibase:0002.alter_book_isbn10
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.columns WHERE table_name = 'BOOK' AND column_name = 'ISBN_10' LIMIT 1;
ALTER TABLE BOOK ADD ISBN_10 VARCHAR(100) NOT NULL UNIQUE;