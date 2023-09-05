package edu.escuelaing.arep.app.webserver;

import java.io.IOException;

public class ApplicationExample {
    public static void main(String[] args) throws IOException{
        WebServer serverInstance = WebServer.getInstance();
        serverInstance.registerOperation("/prueba", (request, response) -> {response.setTypeForResponse("html");
        return request;});

        serverInstance.registerOperation("/pruebaHTML", (request, response) -> {
            response.setTypeForResponse("html");
            return "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>My web page</title>\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <h1>Hello, world!</h1>\n" +
                    "        <p>This is my first web page.</p>\n" +
                    "        <p>It contains a \n" +
                    "             <strong>main heading</strong> and <em> paragraph </em>.\n" +
                    "        </p>\n" +
                    "    </body>\n" +
                    "</html>"
                    ;}
        );

        serverInstance.registerOperation("/pruebaPostHTML", (request, response) -> {
            response.setTypeForResponse("html");
            return "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "   <body>\n" +
                    "       <h1>The post request has been done succesfully</h1>\n" +
                    "   </body>\n" +
                    "</html>\n";
        });

        serverInstance.registerOperation("/pruebaJSON", (request, response) -> {
            response.setTypeForResponse("json");
            return "{\n" +
                    "  \"id\": 12345,\n" +
                    "  \"nombre\": \"Juan Perez\",\n" +
                    "  \"correoElectronico\": \"juan.perez@example.com\",\n" +
                    "  \"edad\": 30,\n" +
                    "  \"direccion\": {\n" +
                    "    \"calle\": \"Calle Falsa 123\",\n" +
                    "    \"ciudad\": \"Ciudad Ficticia\",\n" +
                    "    \"codigoPostal\": \"12345\"\n" +
                    "  },\n" +
                    "  \"intereses\": [\n" +
                    "    \"Tecnologia\",\n" +
                    "    \"Viajes\",\n" +
                    "    \"Deportes\"\n" +
                    "  ]\n" +
                    "}\n"
                    ;}
        );

        serverInstance.registerOperation("/pruebaCSS", (request, response)->{response.setTypeForResponse("css"); return "body {\n" +
                "    font-family: Arial, sans-serif;\n" +
                "    background-color: #f0f0f0;\n" +
                "}\n" +
                "\n" +
                ".container {\n" +
                "    max-width: 1200px;\n" +
                "    margin: 0 auto;\n" +
                "    padding: 20px;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "    color: #0078d4;\n" +
                "    text-decoration: none;\n" +
                "}\n" +
                "\n" +
                ".button {\n" +
                "    display: inline-block;\n" +
                "    padding: 10px 20px;\n" +
                "    background-color: #0078d4;\n" +
                "    color: #fff;\n" +
                "    border: none;\n" +
                "    border-radius: 4px;\n" +
                "}\n" +
                ".button:hover {\n" +
                "    background-color: #005a9e;\n" +
                "}\n";});



        serverInstance.run();
    }
}
