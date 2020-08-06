use uuid::Uuid;
use serde::Deserialize;
use crate::model::schema::patient;

#[derive(Debug, Queryable, QueryableByName, Deserialize)]
#[table_name = "patient"]
pub struct Patient {
  #[sql_type = "Uuid"]
  pub id: Uuid,
  pub identity_document: String,
  pub name: String,
}
