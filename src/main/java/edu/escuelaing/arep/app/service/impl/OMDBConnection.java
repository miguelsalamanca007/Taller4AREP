/**
 * The OMDBConnection class is an implementation of the Service interface.
 * It provides methods for interacting with the OMDB API to retrieve movie information.
 */
package edu.escuelaing.arep.app.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.escuelaing.arep.app.service.Service;

public class OMDBConnection implements Service {

    // User agent for HTTP requests.
    private static final String USER_AGENT = "Mozilla/5.0";

    // API key for accessing the OMDB API.
    private static final String API_KEY = "ae22c37d";

    // Base URL for making OMDB API requests.
    private static final String GET_URL = "http://www.omdbapi.com/?t=";

    /**
     * Default constructor for the OMDBConnection class.
     */
    public OMDBConnection() {
    }

    /**
     * Retrieves movie information by its name using the OMDB API.
     *
     * @param name The name of the movie for which information is to be retrieved.
     * @return A string containing the movie information.
     * @throws IOException If an I/O exception occurs while retrieving the movie information.
     */
    @Override
    public String getMovieByName(String name) throws IOException {

        String movieInformation = "";
        URL movieURL = new URL(GET_URL + name + "&apikey=" + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) movieURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        // Perform the connection implicitly before getting the response code.
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            movieInformation = movieInformation + response.toString();
            System.out.println(response.toString());
        } else {
            movieInformation = movieInformation + "GET request did not work";
            System.out.println("GET request did not work");
        }
        System.out.println("GET DONE");

        return movieInformation;
    }
}
