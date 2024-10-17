use axum::{
    routing::{get, post},
    http::StatusCode,
    Json, Router,
};
use serde::{Deserialize, Serialize};

#[tokio::main]
async fn main() {
    // initialize tracing
    tracing_subscriber::fmt::init();

    // build our application with a route
    let app = Router::new()
        // `GET /` goes to `root`
        .route("/", get(root))
        // `POST /users` goes to `create_user`
        .route("/users", post(create_user));

    // run our app with hyper, listening globally on port 3000
    let listener = tokio::net::TcpListener::bind("0.0.0.0:3000").await.unwrap();
    axum::serve(listener, app).await.unwrap();
}

// basic handler that responds with a static string
async fn root() -> &'static str {
    "Hello, World!"
}

async fn create_user(
    // this argument tells axum to parse the request body
    // as JSON into a `CreateUser` type
    Json(payload): Json<CreateUser>,
) -> (StatusCode, Json<User>) {
    // insert your application logic here
    let user = User {
        id: 1337,
        username: payload.username,
    };

    // this will be converted into a JSON response
    // with a status code of `201 Created`
    (StatusCode::CREATED, Json(user))
}

// the input to our `create_user` handler
#[derive(Deserialize)]
struct CreateUser {
    username: String,
}

// the output to our `create_user` handler
#[derive(Serialize)]
struct User {
    id: u64,
    username: String,
}






// 9A 00 01 8F 58 2A 04 3C 28 3C 3C A9 67 A9 95 00 01 8F 58 2A 00 18 FF B0 C0 E7 00 B8 C0 29 64 64 06 04 00 17 00 00 00 2E 03 18 00 02 9E 7E 28 49 59 
// 9A 00 01 92 3C 2A 04 3C 28 3C 3C B0 6B A9 95 00 01 92 3C 2A 00 18 FF B0 C0 E8 00 5D C0 13 28 64 06 01 00 17 00 00 00 0D 80 4F 00 00 E1 40 12 2E 59 
// 9A 00 01 8B 57 2A 04 3C 28 3C 3C 2C E3 A9 95 00 01 8B 57 2A 00 18 FF B0 C0 E6 00 62 C0 12 28 64 06 01 00 17 00 00 00 0D 6E 59 00 00 DA CB 17 6C 59 
// 9A 00 01 8C 0C 2A 04 3C 28 3C 3C 4A 43 A9 95 00 01 8C 0C 2A 00 18 FF B0 C0 E7 00 60 C0 13 28 64 06 01 00 17 00 00 00 0D 79 9D 00 00 DF D7 4C B2 59 
// 9A 00 01 8E 1E 2A 04 3C 28 3C 3C 92 7D A9 95 00 01 8E 1E 2A 00 18 FF B0 C0 E8 00 5D C0 12 28 64 06 01 00 17 00 00 00 0D 76 7D 00 00 DB 64 B8 CB 59 
// 9A 00 01 7F 49 2A 04 3C 28 3C 3C 6B F0 A9 95 00 01 7F 49 2A 00 18 FF B0 C0 E5 00 5F C0 13 28 64 06 01 00 17 00 00 00 0D 51 19 00 00 DE 9E 25 6F 59 
// 9A 00 01 8A F3 2A 04 3C 28 3C 3C 6F F9 A9 95 00 01 8A F3 2A 00 18 FF B0 C0 E7 00 60 C0 13 28 64 06 01 00 17 00 00 00 0D 79 C5 00 00 DC 55 01 13 59 
// 9A 00 01 8C BB 2A 04 3C 28 3C 3C A1 73 A9 95 00 01 8C BB 2A 00 18 FF B0 C0 E6 00 5C C0 13 28 64 06 01 00 17 00 00 00 0D 7C 5E 00 00 E1 69 D3 FF 59 
// 9A 00 01 8C AC 2A 04 3C 28 3C 3C 8F 4C A9 95 00 01 8C AC 2A 00 18 FF B0 C0 E8 00 5C C0 12 28 64 06 01 00 17 00 00 00 0D 77 1E 00 00 DB AE D7 CC 59 
// 9A 00 01 92 63 2A 04 3C 28 3C 3C D0 15 A9 95 00 01 92 63 2A 00 18 FF B0 C0 E7 00 60 C0 13 28 64 06 01 00 17 00 00 00 0D 7D BA 00 00 DD C2 A6 8B 59 






