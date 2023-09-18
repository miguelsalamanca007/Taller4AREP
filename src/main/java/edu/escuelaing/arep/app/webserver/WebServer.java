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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.escuelaing.arep.app.annotation.RequestMapping;
import edu.escuelaing.arep.app.service.HTTPOperation;
import edu.escuelaing.arep.app.service.impl.FileServices;
import edu.escuelaing.arep.app.service.impl.HttpResponse;

public class WebServer {

    private static WebServer _instance = new WebServer();
    private FileServices fileServices = new FileServices();

    private HashMap<String, HTTPOperation> operations = new HashMap<>();

    private HashMap<String, Method> methods = new HashMap<>();

    private HashMap<String, HttpResponse> responses = new HashMap<>();

    private ArrayList<String> files = new ArrayList<String>() {{
        add("/homepage.html");
        add("/insis.jpg");
        add("/scripts.js");
        add("/styles.css");
    }};

    public static WebServer getInstance() {
        return _instance;
    }


    public void registerOperation(String path, HTTPOperation operation){
        operations.put(path, operation);
    }

    public void run(List<String> args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        for (String className : args) {
            // Cargar clase con forName
            Class<?> c = Class.forName(className);
            Method[] classMethos = c.getMethods();
            for (Method method : classMethos) {  // Extraer una instancia del método
                if (method.isAnnotationPresent(RequestMapping.class)) { // Extraer métodos con @RequestMapping
                    String path = method.getAnnotation(RequestMapping.class).value(); // Extraer el valor del path (value de la anotación)
                    methods.put(path, method); // Poner en la tabla el método con llave path
                }
            }
        }

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
            BufferedReader clientRequestReader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));

            String inputLine;
            String resource = "";
            String method = "";

            boolean isFirstLine = true;

            // Process the client request.
            int contentLength = -1; // Variable para el contenido de longitud
            StringBuilder requestBody = new StringBuilder();

            while ((inputLine = clientRequestReader.readLine()) != null) {
                if (isFirstLine) {
                    System.out.println("Input line " + inputLine);
                    method = inputLine.split("/")[0].split(" ")[0];
                    resource = "/" + inputLine.split("/")[1].split(" ")[0];
                    isFirstLine = false;
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

            System.out.println("Resource: "+resource+" Method: "+method);

            // Handle different types of resources and responses.
            if (operations.containsKey(resource)){

                System.out.println("Entered containsKey");

                if(method.equals("GET")){
                    HttpResponse newResponse = new HttpResponse();
                    newResponse.setStatus("HTTP/1.1 200 OK");
                    newResponse.setBody(operations.get(resource).handle("", newResponse));
                    clientResponseWriter.println(newResponse.getStatus());
                    clientResponseWriter.println(newResponse.type());
                    clientResponseWriter.println();
                    clientResponseWriter.println(newResponse.getBody());

                } else if (method.equals("POST")){
                    HttpResponse newResponse = new HttpResponse();
                    newResponse.setStatus("HTTP/1.1 200 OK");
                    newResponse.setBody(operations.get(resource).handle("", newResponse));
                    clientResponseWriter.println(newResponse.getStatus());
                    clientResponseWriter.println(newResponse.type());
                    clientResponseWriter.println();
                    clientResponseWriter.println(newResponse.getBody());
                    responses.put("/"+resource, newResponse);
                }

            } else if (files.contains(resource)) {

                byte[] response = fileServices.readFileAsBytes(resource.replace("/",""));
                clientResponseWriter.println("HTTP/1.1 200 OK");
                clientResponseWriter.println(fileServices.getContentType(resource.replace("/","")));
                clientResponseWriter.println("Content-Length: " + response.length);
                clientResponseWriter.println();
                clientResponseWriter.flush();

                try (OutputStream os = clientConnection.getOutputStream()) {
                    os.write(response);
                } catch (IOException e) {
                    System.out.println("Error sending file response: " + e.getMessage());
                }

            } else if (methods.containsKey(resource)) {
                clientResponseWriter.println(methods.get(resource).invoke(null));
                System.out.println("Entered a component method");
            } else {
                HttpResponse newResponse = new HttpResponse();
                newResponse.setStatus("HTTP/1.1 404 Not Found");
                newResponse.setTypeForResponse("Content-type: text/html");
                newResponse.setBody("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Error 404 - No encontrado</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h1>Error 404 - Página no encontrada</h1>\n" +
                        "    <p>La página que estás buscando no existe en este servidor.</p>\n" +
                        "</body>\n" +
                        "</html>");
                clientResponseWriter.println(newResponse.getStatus());
                clientResponseWriter.println(newResponse.type());
                clientResponseWriter.println();
                clientResponseWriter.println(newResponse.getBody());
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
