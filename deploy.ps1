./mvnw clean package -DskipTests -P prod
cp target/crosskey_codetest_spring-0.0.1-SNAPSHOT.jar src/main/docker
docker build -t mortgage-repository .\src\main\docker
docker tag mortgage-repository:latest 157359825155.dkr.ecr.eu-north-1.amazonaws.com/mortgage-repository:latest
docker push 157359825155.dkr.ecr.eu-north-1.amazonaws.com/mortgage-repository:latest

