use axum::{
    extract::{Json, Query}
};
use serde::Deserialize;
use crate::dto::resp::ApiResponse;

#[derive(Deserialize)]
pub struct PostReq {
    pub id: i64,
}
pub async fn post(Json(req): Json<PostReq>)->Json<ApiResponse<String>> {
    let id =  req.id; 
    return Json(ApiResponse::succ(Some(id.to_string())))
}

#[derive(Deserialize)]
pub struct GetReq {
    pub id: i64,
}
pub async fn get(req: Query<GetReq>,)->Json<ApiResponse<String>> {
    let id =  req.id; 
    return Json(ApiResponse::succ(Some(id.to_string())))
}
