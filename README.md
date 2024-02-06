## Crosskey codetest 2024 made by Anna Östman

Webinterface with AWS: [mortgage-lb-673383458.eu-north-1.elb.amazonaws.com](http://mortgage-lb-673383458.eu-north-1.elb.amazonaws.com)

### Run Application locally
Before we can run the app locally using an image we need to configure the test database. 

##### Creates a postgres image and run it as container
```console
cd src/main/docker
docker-compose up --no-deps db -d
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
