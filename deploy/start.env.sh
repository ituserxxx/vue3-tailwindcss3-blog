#!/bin/bash

DIR="$PWD/mysql"
# 如果目录不存在，则创建
[ -d "$DIR" ] || mkdir -p "$DIR"

DIR="$PWD/meilisearch"
# 如果目录不存在，则创建
[ -d "$DIR" ] || mkdir -p "$DIR"

docker compose -f $PWD/docker-compose-env.yml up -d