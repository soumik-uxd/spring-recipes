# CSV2DB Job
Demo Spring Batch Job to copy data from CSV file to DB
## How to
### 1. Clone the application
Clone the repository
```bash
git clone <repo>
```
#### 2. Build the application (optional)
The application will be automatically built if `docker-compose` is used. But if we wish to do a local build, we need to first install the dependencies
```bash
./mvnw clean install
```
Then we build the package
```bash
./mvnw -V -B -DskipTests clean package verify
```
#### 3. Run the application
The application requires a postgres datasource, there are two spring profiles local and docker. The docker profile is configured to use the services run via `docker-compose`. If we wish to use the `docker` profile (the easier approach), we can refer the `docker-compose.yml`.
For using the docker profile we can simply run:
```bash
docker-compose up -d
```
#### 3a. Run the application locally
For the `local` spring profile we need to create our own postgres datasource and the necessary tables. The script to load these tables for the datasource are in directory `db` (outside the directory of this job) inside `initDB.sh` which in turn uses the `initDB.sql`, there is also a `Dockerfile` to build the necessary image. The datasource can be created by either:
```bash
docker run -d --rm --name sbpgdb -v <db-directory>/initDB.sh/:/docker-entrypoint-initdb.d/initDB.sh -v <db-directory>/initDB.sql:/home/data/initDB.sql -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:12-alpine
```
Or we can build the image locally and then run it
```bash
docker build -t <DataSource Image Name> ../db
docker run -d --rm --name  <DataSource Container Name> -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:12-alpine
```
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

#### 4. Stop the application
- If the applications was started via `docker-compose` then it can be stopped using.
```bash
docker-compose down
```
In case the volumes need to be removed we can use:
```bash
docker-compose down --volumes
```
- If the application was started locally please ensure the datasource is stopped after stopping the application.