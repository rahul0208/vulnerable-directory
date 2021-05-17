# Vulnerable Spring Boot Application

This application is an intentionally vulnerable Spring Boot application.
## Build

```
mvn install
```

## Run

```
java -jar target/directory-0.0.1-SNAPSHOT.jar
```

* Go to http://localhost:8080/
* Search for zip 21230

## Test

```
mvn clean test
```

## Details

The application runs using an in-memory H2 database. Schema and sample data should load on boot.
