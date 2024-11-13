
mod dto;
mod controller;
use entity;
mod db;
mod router;

use dotenv::dotenv;
use std::env;
#[tokio::main]
async fn main() {

    dotenv().ok();

    // debug log
    tracing_subscriber::fmt()
        .with_max_level(tracing::Level::DEBUG)
        .with_test_writer()
        .init();

    // db
    if let Err(err) = db::init().await {
        panic!("{}", err);
    }
    // server port
    let server_port = env::var("SERVER_PORT").expect("DATABASE_URL must be set");

    // router
    let app = router::init();
    let listener = tokio::net::TcpListener::bind(format!("0.0.0.0:{}",server_port)).await.unwrap();
    axum::serve(listener, app).await.unwrap();
}