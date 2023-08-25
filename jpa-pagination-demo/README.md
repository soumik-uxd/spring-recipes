# Paged Response Demo Api
This API demonstrates how to return a subset of the data or a page, when there is a significant amount of data to be returned, using the pagination and sorting mechanism provided by [Spring Data JPA](https://spring.io/projects/spring-data-jpa).

Before the application can be started the following checks need to be done:

## How to
### 1. Clone the application
Clone the repository
```bash
git clone <repo>
```
#### 2. Build the application (optional)
Before we build, we need to first install the dependencies
```bash
./mvnw clean install
```
Then we build the package
```bash
./mvnw -V -B -DskipTests clean package verify
```
#### 3. Run the application 
If the application package has been built, it can be run by using:
```bash
./mvnw spring-boot:run
```

#### 4. Test the application
Once the application is started the endpoint is available at `http://localhost:8080/data/products`. Now we can send our a GET request for all products and we get a response like:
```json
{
  "recordCount": 200,
  "response": [
    {
      "id": 1,
      "name": "product1",
      "quantity": 34,
      "price": 36622
    },
    {
      "id": 2,
      "name": "product2",
      "quantity": 26,
      "price": 40430
    },
    {
      "id": 3,
      "name": "product3",
      "quantity": 81,
      "price": 18646
    },
    ...
    {
      "id": 200,
      "name": "product200",
      "quantity": 10,
      "price": 48181
    }
  ]
}
```
When the app is started , 200 records are automatically loaded in to the in-memory H2 database. So we get 200 records as the response. We can request the response to be sorted on the quantity of the items e.g.

```bash
curl -X GET 'http://localhost:8080/data/products/quantity'
```
And the response will have 200 items but sorted on the value of the field `quantity`:
```json
{
  "recordCount": 200,
  "response": [
    {
      "id": 36,
      "name": "product36",
      "quantity": 0,
      "price": 10361
    },
    {
      "id": 51,
      "name": "product51",
      "quantity": 0,
      "price": 32122
    },
    {
      "id": 175,
      "name": "product175",
      "quantity": 2,
      "price": 45557
    },
    {
      "id": 150,
      "name": "product150",
      "quantity": 3,
      "price": 2535
    },
    {
      "id": 153,
      "name": "product153",
      "quantity": 4,
      "price": 36131
    },
    ...
    {
      "id": 18,
      "name": "product18",
      "quantity": 99,
      "price": 37267
    },
    {
      "id": 70,
      "name": "product70",
      "quantity": 99,
      "price": 35922
    }
  ]
}
```

We can also request a page of the results instead of the entire 200 records, the pageSize and pageOffset are provided as the path variables:  
```bash
curl -X GET 'http://localhost:8080/data/products/pagination/2/20'
```
We get back then the 2nd page (out of 10) of 20 items:
```json
{
  "recordCount": 20,
  "response": {
    "content": [
      {
        "id": 41,
        "name": "product41",
        "quantity": 95,
        "price": 38391
      },
      {
        "id": 42,
        "name": "product42",
        "quantity": 35,
        "price": 24056
      },
      ...
      {
        "id": 60,
        "name": "product60",
        "quantity": 41,
        "price": 23727
      }
    ],
    "pageable": {
      "sort": {
        "sorted": false,
        "empty": true,
        "unsorted": true
      },
      "pageNumber": 2,
      "pageSize": 20,
      "offset": 40,
      "paged": true,
      "unpaged": false
    },
    "last": false,
    "totalPages": 10,
    "totalElements": 200,
    "size": 20,
    "number": 2,
    "sort": {
      "sorted": false,
      "empty": true,
      "unsorted": true
    },
    "first": false,
    "numberOfElements": 20,
    "empty": false
  }
}
```
For testing the application a [sample postman collection json](./JPA%20Pagination%20Demo.postman_collection.json) has been also provided.
