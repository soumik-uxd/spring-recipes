# spring-postgres-rest-sample
A sample spring boot REST with a Postgres DB 

Before the application can be started the following checks need to be done:

#### 1. Clone the application
Clone the repository
```bash
git clone <repo>
```
#### 2. Build the application (optional)
The application will be automatically built if `docker-compose` is used (see below). But if we wish to do a local build, we need to first install the dependencies
```bash
./mvnw clean install
```
Then we build the packages
```bash
./mvnw -V -B -DskipTests clean package verify
```
#### 3. Run the application
The aplication requires a postgres datasource, there are two spring profiles default and docker. The docker profile is configured to use a local container running a postgres instance. If you wish to use the docker profile (the easier approach), please refer the `docker-compose` below.

For the default spring profile you need to create your own postgres datasource and create a `.env` file with the below details.
```env
DB_URL=jdbc:postgresql://<hostname>>:<port>/<database name>
DB_USERNAME=<username>
DB_PASSWORD=<passsword>
``` 

To create your own local postgres instance you can use the below commands as a sample.
```bash
docker run -d --name testdb -v testdb-data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:10.13
docker exec -it testdb psql -U postgres -c "CREATE DATABASE employeedb;"
docker exec -it testdb psql -U postgres -c "\c employeedb"               
```
The env file then can be adjusted tp
```env
DB_URL=jdbc:postgresql://localhost:5342/employeedb
DB_USERNAME=postgres
DB_PASSWORD=root
``` 

We the load the env vars
```bash
export $(cat .env | xargs)
```
Then start the application using
```bash
./mvnw spring-boot:run
```

The easier way would be to use `docker-compose`.
```env
docker-compose up -d
```

Once done the endpoint is available at `http:\\localhost:8080\api\v1\employee\`

For testing the application a [sample postman collection json](./EmployeeAPI.postman_collection.json) has been also provided.

When done the app and the db can be stopped using
```env
docker-compose down
```