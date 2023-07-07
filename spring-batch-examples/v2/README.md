# Spring Batch Examples 

This repository contains examples of Spring Batch jobs. The jobs are detailed below.

| Job | Description |
|-----|-------------|
| **[CSV2DB](./csv2db/)**           | Inserts data from a CSV file to the database |
| **[DBExtractor](./dbextractor/)** | Reads data from a table in the database to a csv file |
| **[TaskExec](./taskexec/)** | Inserts data from a CSV file to the database but uses a [TaskExecutor](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/task/TaskExecutor.html) for concurrency |