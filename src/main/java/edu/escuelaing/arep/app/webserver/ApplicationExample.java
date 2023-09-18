package edu.escuelaing.arep.app.webserver;

import edu.escuelaing.arep.app.annotation.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ApplicationExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
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



        serverInstance.run(getComponents());
    }

    public static List<String> getComponents() throws IOException, ClassNotFoundException {
        String path = "edu/escuelaing/arep/app/webserver";
        List<String> componentClasses = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File directory = new File(resource.getFile());
            File[] files = directory.listFiles();
            for (File file : files) {
                String fileName = file.getName();
                if (file.getName().endsWith(".class")) {
                    String className = fileName.substring(0, fileName.length() - 6);
                    Class<?> _class = Class.forName(path.replace("/", ".") + "." + className);
                    if (_class.isAnnotationPresent(Component.class)) {
                        componentClasses.add(_class.getName());
                    }
                }
            }
        }
        return componentClasses;
    }

}
