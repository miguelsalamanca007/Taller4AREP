package edu.escuelaing.arep.app.webserver;

import edu.escuelaing.arep.app.annotation.Component;
import edu.escuelaing.arep.app.annotation.RequestMapping;

@Component
public class WebComponent {
    @RequestMapping("/json")
    public static String json() {
        return  "HTTP/1.1 200 OK\r\n" +
                "Content-type: application/json\r\n" +
                "\r\n" +
                "{ \"message\": \"This is a JSON response example!\" }";
    }

    @RequestMapping("/html")
    public static String html() {
        return  "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/html\r\n" +
                "\r\n"
                + "<html><body><h1>This is a HTML response example</h1></body></html>";
    }

    @RequestMapping("/css")
    public static String css() {
        return  "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/css\r\n" +
                "\r\n"
                + "\n" +
                "* {\n" +
                "    font-family: \"Roboto\", sans-serif;\n" +
                "    background-color: #f5f6fa;\n" +
                "}\n" +
                "\n" +
                "body {\n" +
                "    margin: 0;\n" +
                "}\n" +
                "\n" +
                "h1 {\n" +
                "    padding: 5px;\n" +
                "    margin: 7px 2px;\n" +
                "    font-size: 2.5em;\n" +
                "    background-color: white;\n" +
                "}";
    }
}
