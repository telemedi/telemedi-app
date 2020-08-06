-- Add uuid functions for autogen
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- common enums
CREATE TYPE sex_enum AS ENUM ('Female', 'Male');
CREATE TYPE identity_document_type_enum AS ENUM ('National', 'Other', 'Passport');
CREATE TYPE storage_type_enum AS ENUM ('LOCAL', 'GOOGLE_DRIVE');

CREATE TABLE patient (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  identity_document VARCHAR(20) NOT NULL,
  identity_document_type identity_document_type_enum NOT NULL,
  name VARCHAR(60) NOT NULL,
  birth_date date NOT NULL,
  sex sex_enum NOT NULL,
  UNIQUE (identity_document, identity_document_type)
);

CREATE TABLE note (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  title varchar NOT NULL,
  note_text text NOT NULL
);

CREATE TABLE test (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  patient_id UUID NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  name varchar NOT NULL,
  storageUri varchar NOT NULL,
  storageType storage_type_enum NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE TABLE patient_tag (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  name varchar NOT NULL UNIQUE,
  short_name varchar(15) NOT NULL UNIQUE,
  description text NOT NULL
);

CREATE TABLE note_tag (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  name varchar NOT NULL UNIQUE,
  short_name varchar(15) NOT NULL UNIQUE,
  description text NOT NULL
);

CREATE TABLE test_tag (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  name varchar NOT NULL UNIQUE,
  short_name varchar(15) NOT NULL UNIQUE,
  description text NOT NULL
);

-- CREATE TABLE patient_tags (
--   patient_id UUID NOT NULL,
--   patient_tag_id UUID NOT NULL,
--   created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
--   FOREIGN KEY (patient_id) REFERENCES patient(id),
--   FOREIGN KEY (patient_tag_id) REFERENCES patient_tag(id)
-- );
--
-- CREATE TABLE note_tags (
--   note_id UUID NOT NULL,
--   note_tag_id UUID NOT NULL,
--   created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
--   FOREIGN KEY (note_id) REFERENCES note(id),
--   FOREIGN KEY (note_tag_id) REFERENCES note_tag(id)
-- );
--
-- CREATE TABLE test_tags (
--   test_id UUID NOT NULL,
--   test_tag_id UUID NOT NULL,
--   created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
--   FOREIGN KEY (test_id) REFERENCES test(id),
--   FOREIGN KEY (test_tag_id) REFERENCES test_tag(id)
-- );
