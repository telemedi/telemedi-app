-- This file should undo anything in `up.sql`

-- DROP TABLE note_tags;
-- DROP TABLE test_tags;
-- DROP TABLE patient_tags;
DROP TABLE note_tag;
DROP TABLE test_tag;
DROP TABLE patient_tag;
DROP TABLE note;
DROP TABLE test;
DROP TABLE patient;
DROP TYPE storage_type_enum;
DROP TYPE identity_document_type_enum;
DROP TYPE sex_enum;
