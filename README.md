# MyPhones-API

Basic API Rest for import mobile phones from CSV file.
In this API you can import phones, view import statistics and view mobile phone items. 

#### Technology stack

- Java 8
- Maven
- SpringBoot
- H2 Database (in memory)
- EhCache

#### Requirements

- Maven
- JDK 1.8

## Run the application

```
$ cd myphones-api
$ mvn spring-boot:run
```
SpringBoot application up in port 5000, check health endpoint:

    GET http://localhost:5000/myphones-api/actuator/health

## Run the tests

```
$ cd myphones-api
$ mvn test
```


## API

For SouthAfrican mobile phones validation was used this rules:
  - Only numbers
  - Numbers with max length: 11 digits
  - Numbers must start with 6, 7 or 8

#### Import File Register endpoints index:

  - [Import data from CSV file](#import_data_from_csv_file)
  - [Get all import file registers](#get_all_import_file_registers)
  - [Get import file register detail](#get_import_file_register_detail)
  
#### Mobile Numbers endpoints index:
  - [Get all mobile numbers](#get_all_mobile_numbers)
  - [Get mobile number detail](#get_mobile_number_detail)


### Import File Register endpoints

<a name="import_data_from_csv_file"></a>
#### Import data from CSV file

    POST http://localhost:5000/myphones-api/mobile_numbers/import_files
    
##### Request

Generate request param form-data (MultipartFile) with key: "file" and value: "the csv file"

##### Response

``` json
{
    "id": 1,
    "imported_date": "2019-07-13T19:13:55Z",
    "file_name": "magic_numbers.csv",
    "statistics": {
        "valid_numbers": 2,
        "fixed_numbers": 0,
        "invalid_numbers": 3,
        "total_numbers": 5
    },
    "numbers": [
        {
            "id": 1,
            "number": "6478342944",
            "status": "VALID"
        },
        {
            "id": 2,
            "number": "84528784843",
            "status": "VALID"
        },
        {
            "id": 3,
            "number": "263716791426",
            "status": "INVALID",
            "validation_comment": "Is not a number || has not valid length || not start with correct number"
        },
        {
            "id": 4,
            "number": "27736529279",
            "status": "INVALID",
            "validation_comment": "Is not a number || has not valid length || not start with correct number"
        },
        {
            "id": 5,
            "number": "27718159078",
            "status": "INVALID",
            "validation_comment": "Is not a number || has not valid length || not start with correct number"
        }
    ]
}
```

<a name="get_all_import_file_registers"></a>
#### Get all import file registers

    GET http://localhost:5000/myphones-api/mobile_numbers/import_files
    
##### Response

``` json
[
  {
    "id": 1,
    "imported_date": "2019-07-10T19:13:55Z",
    "file_name": "magic_numbers.csv"
  },
  {
    "id": 2,
    "imported_date": "2019-07-11T22:13:55Z",
    "file_name": "southafrica_numbers.csv"
  }
  {
    "id": 3,
    "imported_date": "2019-07-12T08:13:55Z",
    "file_name": "today_numbers.csv"
  }
]
```

<a name="get_import_file_register_detail"></a>
#### Get import file register detail

    GET http://localhost:5000/myphones-api/mobile_numbers/import_files/{import_file_register_id}
    
##### Response

``` json
{
    "id": 1,
    "imported_date": "2019-07-13T19:13:55Z",
    "file_name": "magic_numbers.csv",
    "statistics": {
        "valid_numbers": 2,
        "fixed_numbers": 0,
        "invalid_numbers": 3,
        "total_numbers": 5
    },
    "numbers": [
        {
            "id": 1,
            "number": "6478342944",
            "status": "VALID"
        },
        {
            "id": 2,
            "number": "84528784843",
            "status": "VALID"
        },
        {
            "id": 3,
            "number": "263716791426",
            "status": "INVALID",
            "validation_comment": "Is not a number || has not valid length || not start with correct number"
        },
        {
            "id": 4,
            "number": "27736529279",
            "status": "INVALID",
            "validation_comment": "Is not a number || has not valid length || not start with correct number"
        },
        {
            "id": 5,
            "number": "27718159078",
            "status": "INVALID",
            "validation_comment": "Is not a number || has not valid length || not start with correct number"
        }
    ]
}
```



### Mobile Numbers endpoints

<a name="get_all_mobile_numbers"></a>
#### Get all mobile numbers

    GET http://localhost:5000/myphones-api/mobile_numbers

##### Response

``` json
[
  {
    "id": 1,
    "number": "6478342944",
    "status": "VALID"
  },
  {
    "id": 2,
    "number": "84528784843",
    "status": "VALID"
  },
  {
    "id": 3,
    "number": "263716791426",
    "status": "INVALID",
    "validation_comment": "Is not a number || has not valid length || not start with correct number"
  },
  {
    "id": 4,
    "number": "27736529279",
    "status": "INVALID",
    "validation_comment": "Is not a number || has not valid length || not start with correct number"
  },
  {
    "id": 5,
    "number": "27718159078",
    "status": "INVALID",
    "validation_comment": "Is not a number || has not valid length || not start with correct number"
  }
]
```

<a name="get_mobile_number_detail"></a>
#### Get mobile number detail

    GET http://localhost:5000/myphones-api/mobile_numbers/{mobile_number_id}
    
##### Response

``` json
{
  "id": 1,
  "number": "6478342944",
  "status": "VALID"
}
```

## ToDo

List of possible improvements:

- [ ] Securize API with user authentication
- [ ] Write unit tests for all classes
- [ ] Building a deployment process
- [ ] Analize SouthAfrican correct validation number and improve validation
- [ ] Paginate list responses
- [ ] Add filters in list responses


## Documentation

Baeldung is my best friend :D

- [H2 Database in memory](https://www.baeldung.com/spring-boot-h2-database)
- [Restful basics](https://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration)
- [CSV file](https://www.baeldung.com/java-csv-file-array)
- [Check string number](https://www.baeldung.com/java-check-string-number)