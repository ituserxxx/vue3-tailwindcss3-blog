#!/bin/bash

DIR="$PWD/mysql"

[ -d "$DIR" ] || mkdir -p "$DIR"   # 如果目录不存在，则创建

DIR="$PWD/meilisearch"
[ -d "$DIR" ] || mkdir -p "$DIR" 

DIR="$PWD/filerun/filerun-data"
[ -d "$DIR" ] || mkdir -p "$DIR"

DIR="$PWD/filerun/filerun-files"
[ -d "$DIR" ] || mkdir -p "$DIR" 

DIR="$PWD/filerun/db-data"
[ -d "$DIR" ] || mkdir -p "$DIR" 

docker compose -f $PWD/docker-compose-env.yml up -d

