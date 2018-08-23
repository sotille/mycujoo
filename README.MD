# Mycujoo Assessment

This project was made by Felipe Sotille for Mycujoo Assessment.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for 
development and testing purposes.

### Prerequisites

* Java 8
* Gradle 4.5 or above
* Internet Connection
* Git (optional)

### Installing

A step by step  that tell you how to get this project up and running

Clone the github repository

```
git clone https://github.com/sotille/mycujoo.git 
```

Or download and extract

```
https://github.com/sotille/mycujoo/archive/master.zip
```
## Running
 
Go to the folder of the project and type
```
/opt/javaprojects/mycujoo$gradle clean build run
```

After running the application will create a output.sql file on root project folder based on json schemas consumed.

## Running the tests

Go to the folder of the project and type
```
/opt/javaprojects/mycujoo$gradle clean build test
```

## Built With

* [Gradle](https://gradle.org/releases//) - Dependency Management
* [Google Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5) - Serialize and parse JsonObjects
* [Android Json](https://mvnrepository.com/artifact/com.vaadin.external.google/android-json/0.0.20131108.vaadin1) - Manipulation of JsonObjects and JsonArrays
* [Junit 4.12](https://mvnrepository.com/artifact/junit/junit/4.12) - Unit Tests

## Author

* **Felipe Sotille** 

## License

This project is licensed under the MIT License - see the [LICENSE.md]
