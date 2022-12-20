#!/bin/sh

docker run \
-e SPRING_PROFILES_ACTIVE='prod' \
-e USERS_DATASOURCE_URL='jdbc:postgresql://pg:5432/users?createDatabaseIfNotExist=true' \
-e USERS_DATASOURCE_USERNAME='usr' \
-e USERS_DATASOURCE_PASSWORD='pwd' \
-p 8080:8080 \
--rm --network docker_default --name users-rest \
sawasemykin/skillbox-ms-arch-users:0.0.2-SNAPSHOT
