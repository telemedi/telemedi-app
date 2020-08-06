table! {
    use diesel::sql_types::*;
    use crate::model::enums::*;

    note (id) {
        id -> Uuid,
        created_at -> Timestamp,
        updated_at -> Timestamp,
        title -> Varchar,
        note_text -> Text,
    }
}

table! {
    use diesel::sql_types::*;
    use crate::model::enums::*;

    note_tag (id) {
        id -> Uuid,
        name -> Varchar,
        short_name -> Varchar,
        description -> Text,
    }
}

table! {
    use diesel::sql_types::*;
    use crate::model::enums::*;

    patient (id) {
        id -> Uuid,
        identity_document -> Varchar,
        identity_document_type -> Identity_document_type_enum,
        name -> Varchar,
        birth_date -> Date,
        sex -> Sex_enum,
    }
}

table! {
    use diesel::sql_types::*;
    use crate::model::enums::*;

    patient_tag (id) {
        id -> Uuid,
        name -> Varchar,
        short_name -> Varchar,
        description -> Text,
    }
}

table! {
    use diesel::sql_types::*;
    use crate::model::enums::*;

    test (id) {
        id -> Uuid,
        patient_id -> Uuid,
        created_at -> Timestamp,
        updated_at -> Timestamp,
        name -> Varchar,
        storageuri -> Varchar,
        storagetype -> Storage_type_enum,
    }
}

table! {
    use diesel::sql_types::*;
    use crate::model::enums::*;

    test_tag (id) {
        id -> Uuid,
        name -> Varchar,
        short_name -> Varchar,
        description -> Text,
    }
}

joinable!(test -> patient (patient_id));

allow_tables_to_appear_in_same_query!(
    note,
    note_tag,
    patient,
    patient_tag,
    test,
    test_tag,
);
