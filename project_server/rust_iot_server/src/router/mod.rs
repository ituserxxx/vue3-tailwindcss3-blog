use axum::{
    routing::{
        get,post
    },
    Router,
};
use crate::{
    controller::demo
};
pub fn init()->Router{
    
    return Router::new()
        .route("/get", get(demo::get))
        .route("/post", post(demo::post));
}