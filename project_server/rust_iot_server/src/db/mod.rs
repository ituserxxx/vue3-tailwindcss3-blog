
let mut opt = ConnectOptions::new("mysql://blog:blogmima@172.16.9.103:6001/blog");

opt.max_connections(100)
    .min_connections(5)
    .connect_timeout(Duration::from_secs(8))
    .acquire_timeout(Duration::from_secs(8))
    .idle_timeout(Duration::from_secs(8))
    .max_lifetime(Duration::from_secs(8))
    .sqlx_logging(true)
    .sqlx_logging_level(log::LevelFilter::Info)
    .set_schema_search_path("my_schema"); // Setting default PostgreSQL schema

let db = Database::connect(opt).await?;

// Closing connection here
db.close().await?;