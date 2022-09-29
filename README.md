# thryve-data-collector
thryve-data-collector

This project was created using H2 database, which means that all the database is flushed when the application restarts

To run the project you just to need to execute the following command on your local machine
```
mvn spring-boot:run
```

The application has 3 endpoints and is running by default on the 8080 door.

The first endpoint is to insert the data
The URL for the POST operation
```
localhost:8080/datasources
```
and the JSON
```
{
    "authenticationToken": "123456",
    "dataSources": [
      {
        "dataSource": 8,
        "data": [
          {
            "startTimestampUnix": 1605595355000,
            "endTimestampUnix": 1605595394000,
            "createdAtUnix": 1605597330067,
            "dynamicValueType": 3000,
            "value": "80",
            "valueType": "LONG"
          } 
        ]
      }
    ]
  }
```
The second endpoint is to fetch the data
The URL for the GET operation
```
localhost:8080/datasources
```

There's a third enpoint which returns the AVERAGE for the value for all user datasources
The URL for the GET operation
```
localhost:8080/datasources
```

## Just a few notes
The User can have multiple datasources.

The Application is taken the token as User
