use diesel_derive_enum::DbEnum;

#[derive(Debug, PartialEq, DbEnum)]
#[DieselType = "Sex_enum"]
pub enum Sex {
  Female,
  Male,
}

#[derive(Debug, PartialEq, DbEnum)]
#[DieselType = "Identity_document_type_enum"]
pub enum IdentityDocumentType {
  National,
  Other,
  Passport,
}

#[derive(Debug, PartialEq, DbEnum)]
#[DieselType = "Storage_type_enum"]
pub enum StorageType {
  GoogleDrive,
  Local,
}

