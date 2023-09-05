# ECI MicroFramework

Web Server that supports functionality similar to Spark. The application allows the registration of GET and POST services using lambda functions. Built with the basic Java API.

### Architecture

The ECI Web Server application is divided in 4 main components:

#### Web Server with REST API Facade

The web server is divided in 3 components, controllers, used as a intermediary for the webserver and service classes, service, providing the necessary movie services for the app to work, and the webserver, providing the support for communication with the client.

####  Java Test Client

A client responsible for testing the application web server

### Prerequisites

To run the software you will need to have these installed in your machine:

* [Java](https://www.java.com/)
* [Maven](https://maven.apache.org/)

### Installing

Start cloning the GitHub repository, you can use the following command to do so:

```
git clone https://github.com/miguelsalamanca007/Taller3AREP.git
```

Locate yourself in the directory you downloaded the repository in

```
cd /path/to/repo
```

Build and package the project using Maven's package command

```
mvn package
```

## Running the tests

In order to run the test, execute the command

```
mvn test
```

## Running the program

Run the following command to clean any old artifacts and compile and install the new artifacts
```
mvn clean install
```
Now run the program using the command:
```
java -cp .\target\primer-taller-1.0-SNAPSHOT.jar edu.escuelaing.arep.app.webserver.ApplicationExample

```

## Built With

* [Java](https://www.java.com/) - The programming language used
* [HTML](https://html.com/document/) - The markup language used for the home's page structure
* [JavaScript](https://www.javascript.com/) - The programming language used for the front's page logic
* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit](https://junit.org/junit5/) - Used to create unit tests

## Versioning

I use [Git](https://git-scm.com/) for versioning.

## Authors

* **Miguel Angel Salamanca**  - [GitHub](https://github.com/miguelsalamanca007) - [LinkedIn](https://linkedin.com/miguel)

