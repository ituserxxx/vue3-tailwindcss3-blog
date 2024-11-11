use axum::{
    routing::{
        get,post
    },
    Router,
};
mod dto;
mod controller;

use crate::{
    controller::demo
};

#[tokio::main]
async fn main() {
    tracing_subscriber::fmt()
    .with_max_level(tracing::Level::DEBUG)
    .with_test_writer()
    .init();

    let app = Router::new()
        .route("/get", get(demo::get))
        .route("/post", post(demo::post));


    let listener = tokio::net::TcpListener::bind("0.0.0.0:6068").await.unwrap();
    axum::serve(listener, app).await.unwrap();
}