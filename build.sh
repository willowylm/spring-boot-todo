
./gradlew clean bootRepackage

docker build --rm . --tag yangliumingtest/user-service:1

docker stack deploy todo -c docker-compose.yml
