# Spring Batch Examples 
#### **Please note** This version i.e. V1 has been deprecated. And a streamlined reworked version is available as V2. Please refer to V2 for the latest and correct changes

This repository contains examples of Spring Batch jobs. The jobs are detailed below.

| Job                                                                                              | Description                                                                                                                                                                                                                                                                                                                                                   |
|--------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[CSV2DB](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/CSV2DB.java)**           | Inserts data from a CSV file to the database                                                                                                                                                                                                                                                                                                                  |
| **[TaskExec](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/TaskExec.java)**       | Inserts data from a CSV file to the database but uses a [TaskExecutor](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/task/TaskExecutor.html) for concurrency                                                                                                                                                      |
| **[DBExtractor](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/DBExtractor.java)** | Reads data from a table in the database to a csv file                                                                                                                                                                                                                                                                                                         |
| **[ExecContext](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/ExecContext.java)** | Inserts data from a CSV file to the database but uses a [ExecutionContext](https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/item/ExecutionContext.html) to pass parameters                                                                                                                                                      |
| **[Listener](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/Listener.java)**       | Inserts data from a CSV file to the database but uses [JobExecutionListener](https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/JobExecutionListener.html) and [StepExecutionListener](https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/StepExecutionListener.html) to trace the job flow |
| **[EmailSender](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/EmailSender.java)** | Reads email addresses from a CSV file and sends emails(dummy step).                                                                                                                                                                                                                                                                                           |
| **[FixedTxt2DB](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/FixedTxt2DB.java)** | Inserts data from a fixed length file to the database                                                                                                                                                                                                                                                                                                         |
| **[DBCleaner](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/DBCleaner.java)**     | Clears data from a table in the database using [Tasklet](https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/step/tasklet/Tasklet.html)                                                                                                                                                                                       |
| **[AgeSummary](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/AgeSummary.java)**   | Aggregates data from a table in the database using [Tasklet](https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/step/tasklet/Tasklet.html)                                                                                                                                                                                   |
| **[CSV2Topic](./src/main/java/io/github/soumikuxd/springbatchexamples/jobs/CSV2Topic.java)**     | Inserts data from a CSV file to a [Kafka topic](https://kafka.apache.org/intro)                                                                                                                                                                                                                                                                               |

## How to
### 1. Clone the application
Clone the repository
```bash
git clone <repo>
```
#### 2. Build the application (optional)
The application will be automatically built if `docker-compose` is used (see below). 
But if we wish to do a local build, we need to first install the dependencies
```bash
./mvnw clean install
```
Then we build the packages
```bash
./mvnw -V -B -DskipTests clean package verify
```
#### 3. Run the application
The application requires a postgres datasource, zookeeper, and a kafka broker (the `docker-compose.yml` has all 3 plus a kafka manager service ), there are two spring profiles local and docker. The docker profile is configured to use the services run via `docker-compose`. If you wish to use the docker profile (the easier approach), please refer the `docker-compose.yml`.
For using the docker profile simply run:
```bash
docker-compose up -d
```
#### 3a. Run the application locally
For the local spring profile you need to create your own postgres datasource and create the necessary tables, and also run your own kafka services (e.g. zookeeper & brokers etc.). The script to load for these tables for the datasource are in directory `db` inside `initDB.sh` which in turn uses the `initDB.sql`, there is also a `Dockerfile` to build the necessary image. The datasource can be created by either:
```bash
docker run -d --rm --name sbpgdb -v $PWD/initDB.sh/:/docker-entrypoint-initdb.d/initDB.sh -v $PWD/initDB.sql:/home/data/initDB.sql -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:12-alpine
```
Or you can build the image locally and then run it 
```bash
docker build -t <DataSource Image Name> ./db
docker run -d --rm --name  <DataSource Container Name> -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:12-alpine
```
For the Kafka services you can use this [guide](https://developer.confluent.io/quickstart/kafka-docker/). Once done the local version can be started
**Then start the application using
```bash
../mvnw spring-boot:run -Dspring-boot.run.profiles=local
```
#### 4. Submit the jobs
The jobs can be submitted via the endpoint available at `http:\\localhost:8080` :
```bash
curl -LX POST 'localhost:8080/job/<job name>'
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
- If the application was started locally please ensure the datasource and the kafka services are stopped after stopping the application.

