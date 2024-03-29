## Crosskey codetest 2024 made by Anna Östman

## Requirements
- Java 17
- Maven
- PostgreSQL Database
- Docker

### Run Application locally
Before we can run the app locally using an image we need to configure the test database 

##### Run database with Docker-Compose
```console
cd src/main/docker
docker-compose up db -d
```

#### Create our crosskey_image
```console
cd ../../../
./mvnw clean package -DskipTests
cp target/crosskey_codetest_spring-0.0.1-SNAPSHOT.jar src/main/docker
docker build -t crosskey_image .\src\main\docker
```

#### Run our mortgage-app using crosskey_image we created
```console
cd src/main/docker
docker-compose up app -d
```

Go to url: [localhost:5001](http://localhost:5001)

**OBS: It may take a few minutes before the anything shows up on the url**
