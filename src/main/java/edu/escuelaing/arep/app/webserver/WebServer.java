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
import java.util.HashMap;
import edu.escuelaing.arep.app.service.HTTPOperation;
import edu.escuelaing.arep.app.service.impl.HttpResponse;

public class WebServer {

    private static WebServer _instance = new WebServer();

    private HashMap<String, HTTPOperation> operations = new HashMap<>();

    private HashMap<String, HttpResponse> responses = new HashMap<>();

    public static WebServer getInstance() {
        return _instance;
    }


    public void registerOperation(String path, HTTPOperation operation){
        operations.put(path, operation);
    }

    public void run() throws IOException {

        // Set up server resources and components.
        ServerSocket serverInstance = null;

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
            String resource = "";
            String method = "";

            HttpResponse response = new HttpResponse();

            boolean isFirstLine = true;

            // Process the client request.
            int contentLength = -1; // Variable para el contenido de longitud
            StringBuilder requestBody = new StringBuilder();

            while ((inputLine = clientRequestReader.readLine()) != null) {
                if (isFirstLine) {
                    System.out.println("Input line " + inputLine);
                    method = inputLine.split("/")[0].split(" ")[0];
                    resource = inputLine.split("/")[1].split(" ")[0];
                    isFirstLine = false;
                    System.out.println("METHOD IS: " + method);
                }

                if (inputLine.trim().isEmpty()) {
                    // Si la línea está vacía, se asume que la solicitud ha terminado.
                    break;
                }

                if (inputLine.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(inputLine.split(":")[1].trim());
                }

                if (contentLength != -1) {
                    // Leer el cuerpo de la solicitud y guardarlo en requestBody
                    char[] buffer = new char[1024]; // Tamaño del búfer para lectura
                    int bytesRead;
                    while ((bytesRead = clientRequestReader.read(buffer, 0, Math.min(buffer.length, contentLength))) > 0) {
                        requestBody.append(buffer, 0, bytesRead);
                        contentLength -= bytesRead;

                        if (contentLength == 0) {
                            // Se ha leído todo el cuerpo de la solicitud
                            break;
                        }
                    }
                    break;
                }
            }

            // El cuerpo de la solicitud se encuentra en requestBody.toString()
            String requestBodyContent = requestBody.toString();

            System.out.println("The Request Body is:" + requestBodyContent);
            System.out.println("The Content-Length is: " + contentLength);


            System.out.println("GOT OUT OF THE WHILE LOOP");

            // Handle different types of resources and responses.
            if (operations.containsKey("/"+resource)){

                System.out.println("Entered containsKey");

                if(method.equals("GET")){
                    response.setStatus("HTTP/1.1 200 OK");
                    response.setBody(operations.get("/"+resource).handle("", response));
                    clientResponseWriter.println(response.getStatus());
                    clientResponseWriter.println(response.type());
                    clientResponseWriter.println();
                    clientResponseWriter.println(response.getBody());

                } else if (method.equals("POST")){
                    System.out.println("Input Line Is: "+inputLine);
                    HttpResponse newResponse = new HttpResponse();
                    newResponse.setStatus("HTTP/1.1 200 OK");
                    newResponse.setBody(operations.get("/"+resource).handle("", newResponse));
                    clientResponseWriter.println(newResponse.getStatus());
                    clientResponseWriter.println(newResponse.type());
                    clientResponseWriter.println();
                    clientResponseWriter.println(newResponse.getBody());
                    responses.put("/"+resource, newResponse);
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
