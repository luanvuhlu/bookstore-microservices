--liquibase formatted sql
--changeset liquibase:0102.import_alter_book_status
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.columns WHERE table_name = 'BOOK' AND column_name = 'STATUS' LIMIT 1;
ALTER TABLE BOOK ADD STATUS VARCHAR(25) NOT NULL;