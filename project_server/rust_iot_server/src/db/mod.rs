use sea_orm::{Database, DbErr,ConnectOptions};
use tokio::time::Duration;

use std::env;

pub async fn init() -> Result<(), DbErr> {
    let database_url = env::var("DATABASE_URL").expect("DATABASE_URL must be set");
    let mut opt = ConnectOptions::new(&database_url);
    opt.max_connections(100)
    .min_connections(5)
    .connect_timeout(Duration::from_secs(8))
    .acquire_timeout(Duration::from_secs(8))
    .idle_timeout(Duration::from_secs(8))
    .max_lifetime(Duration::from_secs(8))
    .sqlx_logging(true)
    .sqlx_logging_level(log::LevelFilter::Debug);
    let _db = Database::connect(opt).await?;
 
    Ok(())
}

