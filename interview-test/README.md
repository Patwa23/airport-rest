**Travix - Problem to be solved**
 
##### Sonarqube Coverage

### The Goal
BusyFlights is a flights search solution which aggregates flight results initially from 2 different suppliers (CrazyAir and ToughJet). A future iteration (not part of the test) may add more suppliers.

### Stack
* Java 8
* Spring Boot/MVC
* Lombok
* Commons Lang/Collections
* JUnit 5
* Mockito 2
* Maven 3
* Tomcat 8

### Prerequisites
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)


### Run the application from command line
you can clean, compile, run the unit test and  build a single executable JAR file that contains all the necessary dependencies, classes, and resources, 
```
mvn clean install
```
Then you can run the JAR file with:
```
java -jar target/travix-rest-0.1.0.jar
```

*Instead of `mvn` you can also use the maven-wrapper `./mvnw` to ensure you have everything necessary to run the Maven build.*
````
./mvnw clean package && java -jar target/travix-rest-0.1.0.jar
````

**NOTE:** Docker is the alternative option to run the application

### Run Docker file 
* docker -v
* docker build -f Dockerfile -t travix-rest-0.1.0 .
* docker images
* docker run -p 8080:8080 travix-rest-0.1.0
* docker ps -a
* docker stop <container-id>

### Swagger UI
- http://localhost:8080/swagger-ui.html

### Monitoring & Management over HTTP 
- http://localhost:9091/manage

### Endpoint
**Busy Flights API**

**Request**

| Name | Description |
| ------ | ------ |
| origin | 3 letter IATA code(eg. LHR, AMS) |
| destination | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_LOCAL_DATE format |
| returnDate | ISO_LOCAL_DATE format |
| numberOfPassengers | Maximum 4 passengers |

```
curl --request GET http://localhost:8080/busyflight/flights?origin=AMS&destination=DEL&departureDate=2018-09-25&returnDate=2018-09-27&numberOfPassengers=4
```

**Response**

| Name | Description |
| ------ | ------ |
| airline | Name of Airline |
| supplier | Eg: CrazyAir or ToughJet |
| fare | Total price rounded to 2 decimals |
| departureAirportCode | 3 letter IATA code(eg. LHR, AMS) |
| destinationAirportCode | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_DATE_TIME format |
| arrivalDate | ISO_DATE_TIME format |

Response:
````
Response Body:
[
  {
    "airline": "Emirates",
    "supplier": "CrazyAir",
    "fare": 650,
    "departureAirportCode": "AMS",
    "destinationAirportCode": "DEL",
    "departureDate": "2018-09-25T10:13:14.743",
    "arrivalDate": "2018-09-27T10:13:14.743"
  },
  {
    "airline": "KLM",
    "supplier": "CrazyAir",
    "fare": 1000,
    "departureAirportCode": "AMS",
    "destinationAirportCode": "DEL",
    "departureDate": "2018-09-25T12:18:14.743",
    "arrivalDate": "2018-09-27T18:45:20.743"
  },
  {
    "airline": "KLM",
    "supplier": "ToughJet",
    "fare": 300,
    "departureAirportCode": "AMS",
    "destinationAirportCode": "DEL",
    "departureDate": "2018-09-25T08:13:14.743",
    "arrivalDate": "2018-09-27T08:13:14.743"
  }
]
````

**CrazyAir API**

**Request**

| Name | Description |
| ------ | ------ |
| origin | 3 letter IATA code(eg. LHR, AMS) |
| destination | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_LOCAL_DATE format |
| returnDate | ISO_LOCAL_DATE format |
| passengerCount | Number of passengers |

```
curl --request GET http://localhost:8080/crazyair/flights?origin=AMS&destination=DEL&departureDate=2018-09-25&returnDate=2018-09-27&passengerCount=1
```
**Response**


| Name | Description |
| ------ | ------ |
| airline | Name of the airline |
| price | Total price |
| cabinclass | E for Economy and B for Business |
| departureAirportCode | Eg: LHR |
| destinationAirportCode | Eg: LHR |
| departureDate | ISO_LOCAL_DATE_TIME format |
| arrivalDate | ISO_LOCAL_DATE_TIME format |

````
Response Body:
[
  {
    "airline": "Emirates",
    "price": 650,
    "cabinclass": "Economy",
    "departureAirportCode": "AMS",
    "destinationAirportCode": "DEL",
    "departureDate": "2018-09-25T10:13:14.743",
    "arrivalDate": "2018-09-27T10:13:14.743"
  },
  {
    "airline": "KLM",
    "price": 1000,
    "cabinclass": "Business",
    "departureAirportCode": "AMS",
    "destinationAirportCode": "DEL",
    "departureDate": "2018-09-25T12:18:14.743",
    "arrivalDate": "2018-09-27T18:45:20.743"
  }
]
````

**ToughJet API**

**Request**

| Name | Description |
| ------ | ------ |
| from | 3 letter IATA code(eg. LHR, AMS) |
| to | 3 letter IATA code(eg. LHR, AMS) |
| outboundDate |ISO_LOCAL_DATE format |
| inboundDate | ISO_LOCAL_DATE format |
| numberOfAdults | Number of passengers |

```
curl --request GET http://localhost:8080/toughjet/flights?from=AMS&to=DEL&outboundDate=2018-09-25&inboundDate=2018-09-27&numberOfAdults=5
```

**Response**

| Name | Description |
| ------ | ------ |
| carrier | Name of the Airline |
| basePrice | Price without tax(doesn't include discount) |
| tax | Tax which needs to be charged along with the price |
| discount | Discount which needs to be applied on the price(in percentage) |
| departureAirportName | 3 letter IATA code(eg. LHR, AMS) |
| arrivalAirportName | 3 letter IATA code(eg. LHR, AMS) |
| outboundDateTime | ISO_INSTANT format |
| inboundDateTime | ISO_INSTANT format |

````
Response Body:
[
  {
    "carrier": "KLM",
    "basePrice": 300,
    "tax": 200,
    "discount": 100,
    "departureAirportName": "AMS",
    "arrivalAirportName": "DEL",
    "outboundDateTime": "2018-09-25T08:13:14.743Z",
    "inboundDateTime": "2018-09-27T08:13:14.743Z"
  }
]
````
### Author
 - Prakash Patwa

### Screenshot
 - screenshot git url :
 
### Remaining Task
- Write more Unit Test & Integration Test
- Increase the Code Quality coverage by SonarQube 
- Security Test by using tool such HP Fortify
- Performance,Load,Regration Test
- Smoke Test