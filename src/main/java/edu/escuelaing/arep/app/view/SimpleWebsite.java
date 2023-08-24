package edu.escuelaing.arep.app.view;

/**
* The movieSearchEngine class generates an HTML page for a simple movie search engine.
* It provides a web interface to search for movie information using the provided movie name.
*/
public class SimpleWebsite {

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
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "<meta charset=\"UTF-8\">\n" +
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "<title>Mi Página con Carga Dinámica de Componentes</title>\n" +
        "<style>\n" +
        "  body {\n" +
        "    font-family: Arial, sans-serif;\n" +
        "    background-color: #f0f0f0;\n" +
        "    margin: 0;\n" +
        "    padding: 0;\n" +
        "  }\n" +
        "  header {\n" +
        "    background-color: #333;\n" +
        "    color: white;\n" +
        "    text-align: center;\n" +
        "    padding: 1rem;\n" +
        "  }\n" +
        "</style>\n" +
        "</head>\n" +
        "<body>\n" +
        "<header>\n" +
        "  <h1>Mi Pagina </h1>\n" +
        "</header>\n" +
        "<div id=\"dynamicContent\">\n" +
        "  <!-- El contenido se cargará dinámicamente aquí -->\n" +
        "</div>\n" +
        "<script>\n" +
        "  function loadComponent(type, url) {\n" +
        "    return new Promise((resolve, reject) => {\n" +
        "      const element = document.createElement(type);\n" +
        "      if (type === \"link\") {\n" +
        "        element.rel = \"stylesheet\";\n" +
        "        element.href = url;\n" +
        "      } else if (type === \"script\") {\n" +
        "        element.src = url;\n" +
        "      } else if (type === \"img\") {\n" +
        "        element.src = url;\n" +
        "      }\n" +
        "      element.onload = resolve;\n" +
        "      element.onerror = reject;\n" +
        "      document.head.appendChild(element);\n" +
        "    });\n" +
        "  }\n" +
        "\n" +
        "  Promise.all([\n" +
        "    loadComponent(\"link\", \"http://localhost:16000/file/styles.css\"),\n" +
        "    loadComponent(\"script\", \"http://localhost:16000/file/scripts.js\"),\n" +
        "    loadComponent(\"img\", \"http://localhost:16000/file/file4.jpg\"),\n" +
        "  ]).then(() => {\n" +
        "    // Aquí puedes realizar cualquier inicialización necesaria después de cargar los componentes\n" +
        "  }).catch(error => {\n" +
        "    console.error(\"Error:\", error);\n" +
        "  });\n" +
        "</script>\n" +
        "</body>\n" +
        "</html>";

        return html;          
    }
}
