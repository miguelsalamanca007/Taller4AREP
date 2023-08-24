# ECI Web Server

ECI Web Server application, a simple web server that can be used to store and serve static files, such as HTML, CSS, JavaScript, and images. Along with the web server, we also provide a basic web application that utilizes these static files to display an interactive web page.

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
git clone https://github.com/miguelsalamanca007/Taller2AREP.git
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

You have already installed the program in the Installing section, now you just need to run the following command  
```
java -cp .\target\primer-taller-1.0-SNAPSHOT.jar edu.escuelaing.arep.app.webserver.WebServer
```

Now you can access the program from your favorite internet browser on port 16000, for that, enter the next URL in the browser:

```
http://localhost:16000/homepage
```

If you have done everything correctly, the program should look like this:

![image](demo.png)

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

