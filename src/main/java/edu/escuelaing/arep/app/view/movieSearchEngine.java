package edu.escuelaing.arep.app.view;
/**
* The movieSearchEngine class generates an HTML page for a simple movie search engine.
* It provides a web interface to search for movie information using the provided movie name.
*/
public class movieSearchEngine {

    /**
    * Generates the HTML content for the movie search engine's home page.
    *
    * @return A string containing the HTML content for the movie search engine's home page.
    */
    public static String getHomePage() {
        
        String html =  "HTTP/1.1 200 OK\r\n" +
        "Content-Type: text/html\r\n" +
        "\r\n" +
        "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "  <title>Movie Search Engine</title>\n" +
        "  <meta charset=\"UTF-8\">\n" +
        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "  <style>\n" +
        "    body {\n" +
        "      font-family: Arial, sans-serif;\n" +
        "      text-align: center;\n" +
        "    }\n" +
        "    h1 {\n" +
        "      color: #333;\n" +
        "    }\n" +
        "    form {\n" +
        "      margin-top: 20px;\n" +
        "    }\n" +
        "    input[type=\"text\"] {\n" +
        "      width: 300px;\n" +
        "      padding: 10px;\n" +
        "      border: 1px solid #ccc;\n" +
        "      border-radius: 5px;\n" +
        "      font-size: 16px;\n" +
        "    }\n" +
        "    button {\n" +
        "      padding: 10px 20px;\n" +
        "      font-size: 16px;\n" +
        "      background-color: #007bff;\n" +
        "      color: #fff;\n" +
        "      border: none;\n" +
        "      border-radius: 5px;\n" +
        "      cursor: pointer;\n" +
        "    }\n" +
        "    button:hover {\n" +
        "      background-color: #0056b3;\n" +
        "    }\n" +
        "  </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "  <h1>Movie Search Engine</h1>\n" +
        "  <form action=\"/search\" method=\"get\">\n" +
        "    <input type=\"text\" name=\"q\" placeholder=\"Enter the name of the movie...\">\n" +
        "    <br><br>\n" +
        "    <button type=\"button\" onclick=\"searchMovie()\">Search</button>\n" +
        "  </form>\n" +
        "  <h1 id=\"movie\">Movie</h1>         " +
        "  <script>\n" +
        "    function searchMovie() {\n" +
        "      var input = document.querySelector('input[name=\"q\"]');\n" +
        "      var movieName = input.value.replace(/ /g, \"+\");\n" +
        "      var url = \"http://localhost:16000/movie/\" + movieName;\n" +
        "      fetch(url)\n" +
        "        .then(response => response.text())\n" +
        "        .then(data => (document.getElementById(\"movie\").innerHTML = data))\n" +
        "        .catch(error => console.error('Error:', error));\n" +
        "    }\n" +
        "  </script>\n" +
        "</body>\n" +
        "</html>";

        return html;          
    }
}
