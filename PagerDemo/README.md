# Paged Response Demo Api 
This API demonstrates how to return a subset of the data or a page, when there is a significant amount of data to be returned. The API returns the first page (or a subset) of the data and provides a token for the next page, which can then be added as a parameter to the next API call to retrieve the next page, and so on and so forth, until we reach to the end of the data. 

Before the application can be started the following checks need to be done:

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
The application requires a postgres datasource, there are two spring profiles `local` and `docker`. The `docker` profile is configured to use the services run via `docker-compose`. If we wish to use the `docker` profile (the easier approach), we can refer the `docker-compose.yml`.
For using the docker profile we can simply run:
```bash
docker-compose up -d
```
#### 3a. Run the application locally
For the `local` spring profile we need to create our own postgres datasource, and load the necessary tables into it. The script to load these tables for the datasource are in directory `initdb` where the script `load-db.sh` uses the `dvdrental.tar`. The datasource can be created by:
```bash
docker run -d --rm --name sampledb -v $PWD/load-db.sh/:/docker-entrypoint-initdb.d/load-db.sh -v $PWD/dvdrental.tar:/home/data/dvdrental.tar -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:12-alpine
```
Now we can start the app:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

#### 4. Test the application
Once the application is started the endpoint is available at `http:\\localhost:8080\api\v1\data\all`. Now we can send our first GET request and we get a response like:
```json
{
    "nextPage": "ef810f33-948e-479d-8207-bc743a5ce0ef",
    "items": [
        {
            "firstName": "Mary",
            "lastName": "Smith"
        },
        {
            "firstName": "Patricia",
            "lastName": "Johnson"
        },
        {
            "firstName": "Linda",
            "lastName": "Williams"
        },
        {
            "firstName": "Barbara",
            "lastName": "Jones"
        },
 ...
    ]
}
```
The API is configured to return by default 40 items, with a limit of 10 items per page. For the next page we need to use the parameter `nextPage` and the use value of the `nextPageToken` from the response JSON. 
```bash
curl -X GET'http://localhost:8080/api/v1/data/all?nextPage=298a399d-66f0-4b18-a068-7d3ab748191b'
```
And we get the second page of data, with another 10 items:
```json
{
    "nextPage": "298a399d-66f0-4b18-a068-7d3ab748191b",
    "items": [
        {
            "firstName": "Lisa",
            "lastName": "Anderson"
        },
        {
            "firstName": "Nancy",
            "lastName": "Thomas"
        },
 ...
    ]
}
```
And we can repeat the same step again to get the next page until we reach the last page where we get a page with the final 10 items but no `nextPageToken` in the response JSON.
```json
{
    "items": [
        {
            "firstName": "Brenda",
            "lastName": "Wright"
        },
        {
            "firstName": "Amy",
            "lastName": "Lopez"
        },
        {
            "firstName": "Anna",
            "lastName": "Hill"
        },
...
    ]
}
```
For testing the application a [sample postman collection json](./Spring%20Pagination.postman_collection.json) has been also provided.

#### 5. Stop the application
- If the applications was started via `docker-compose` then it can be stopped using.
```bash
docker-compose down
```
In case the volumes need to be removed we can use:
```bash
docker-compose down --volumes
```
- If the application was started locally please ensure the datasource is stopped after stopping the application.


