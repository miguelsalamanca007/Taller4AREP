/**
 * The WebServer class initializes a simple web server that handles client connections,
 * processes requests, and sends back responses.
 */
package edu.escuelaing.arep.app.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import edu.escuelaing.arep.app.controller.MovieController;
import edu.escuelaing.arep.app.service.impl.FileServices;
import edu.escuelaing.arep.app.view.SimpleWebsite;
import edu.escuelaing.arep.app.view.movieSearchEngine;

public class WebServer {

    public static void main(String[] args) throws IOException {

        // Set up server resources and components.
        ServerSocket serverInstance = null;
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        MovieController movieController = new MovieController();
        FileServices fileServices = new FileServices();
        SimpleWebsite simpleWebsite = new SimpleWebsite();

        try {
            serverInstance = new ServerSocket(16000);
        } catch (IOException error) {
            System.err.println("Couldn't listen on port 16000");
            System.exit(1);
        }

        Socket clientConnection = null;

        // Accept and handle incoming client connections.
        while (!(serverInstance.isClosed())) {
            try {
                System.out.println("\nListening...");
                clientConnection = serverInstance.accept();
                System.out.println("Connection Established!");
            } catch (IOException error2) {
                System.out.println("Couldn't listen on port 16000");
                System.exit(1);
            }

            PrintWriter clientResponseWriter = new PrintWriter(clientConnection.getOutputStream(), true);
            BufferedReader clientRequestReader = new BufferedReader(
                    new InputStreamReader(clientConnection.getInputStream()));

            String inputLine;
            String movieInformation = "";
            String resource = "";
            String movieName = "";

            boolean isFirstLine = true;

            // Process the client request.
            while ((inputLine = clientRequestReader.readLine()) != null) {

                if (isFirstLine) {
                    System.out.println("Input line " + inputLine);
                    resource = inputLine.split("/")[1].split(" ")[0];
                    movieName = inputLine.split("/")[2].split(" ")[0];
                    isFirstLine = false;
                }

                if (!clientRequestReader.ready()) {
                    break;
                }
            }

            // Handle different types of resources and responses.
            if (resource.equals("movie")) {
                if (cache.containsKey(movieName)) {
                    movieInformation = cache.get(movieName);
                } else {
                    movieInformation = movieController.getMovieByName(movieName);
                    cache.put(movieName, movieInformation);
                }
                clientResponseWriter.println("HTTP/1.1 200 OK");
                clientResponseWriter.println("Content-Type: application/json");
                clientResponseWriter.println("Content-Length: " + movieInformation.length());

            } else if (resource.equals("home") || resource.equals("") || resource.equals(" ")) {
                clientResponseWriter.println(movieSearchEngine.getHomePage());
            } else if (resource.equals("file")) {
                byte[] response = fileServices.readFileAsBytes(movieName);
                clientResponseWriter.println("HTTP/1.1 200 OK");
                clientResponseWriter.println(fileServices.getContentType(movieName));
                clientResponseWriter.println("Content-Length: " + response.length);
                clientResponseWriter.println();
                clientResponseWriter.flush(); // Asegura que todas las cabeceras se envíen

                try (OutputStream os = clientConnection.getOutputStream()) {
                    os.write(response); // Envía los bytes directamente al cliente
                } catch (IOException e) {
                    System.out.println("Error sending file response: " + e.getMessage());
                }
            } else if (resource.equals("homepage")) {
                byte[] response = fileServices.readFileAsBytes("homepage.html");
                clientResponseWriter.println("HTTP/1.1 200 OK");
                clientResponseWriter.println("Content-Type: text/html");
                clientResponseWriter.println();
                clientResponseWriter.flush();
                try (OutputStream os = clientConnection.getOutputStream()) {
                    os.write(response); // Envía los bytes directamente al cliente
                } catch (IOException e) {
                    System.out.println("Error sending file response: " + e.getMessage());
                }

            } else {
                System.out.println("Invalid resource\n");
            }

            // Close connections.
            clientResponseWriter.close();
            clientRequestReader.close();
            clientConnection.close();

        }

        // Close the server instance.
        serverInstance.close();
    }
}
