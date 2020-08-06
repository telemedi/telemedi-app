extern crate actix_web;
#[macro_use]
extern crate diesel;
extern crate diesel_derive_enum;
extern crate uuid;
extern crate wundergraph;

mod model;

use diesel::r2d2::{ConnectionManager, Pool};
use structopt::StructOpt;

#[derive(Debug, StructOpt)]
#[structopt(name = "telemedi-app")]
struct Opt {
  #[structopt(short = "u", long = "db-url")]
  databaseUrl: String,
  #[structopt(short = "s", long = "server-url", default_value= "0.0.0.0:27786")]
  serverUrl: String,
}

fn main() {
  let opt:Opt = Opt::from_args();
  ::std::env::set_var("RUST_LOG", "actix_web=info");
  println!("{}", opt.serverUrl);
  let manager = ConnectionManager::<DBConnection>::new(opt.databaseUrl);
}
