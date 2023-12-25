# kotlinboot
A Demo Spring Boot REST API in Kotlin

Before the application can be started the following checks need to be done:

#### 1. Clone the application
Clone the repository
```bash
git clone <repo>
```
#### 2. Build the application (optional)
For a local build, we need to first install the dependencies
```bash
./mvnw clean install
```
Then we build the packages
```bash
./mvnw -V -B -DskipTests clean package verify
```
For a docker build we run:
```bash
docker build -t <image name> .
```

#### 3. Run the application
If the application package has been built, it can be run by using: 
```bash
./mvnw spring-boot:run
```
A dockerfile has been created to build the image. After building the image it can be started with: 
```bash
docker run -d --name kotlinbootapp -p 8080:8080 <image name>
```
Once done the endpoint is available at `http:\\localhost:8080\hotels\`
