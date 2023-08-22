/**
 * The MovieController class serves as a controller for interacting with movie-related services.
 * It facilitates the retrieval of movie information based on their names.
 */
package edu.escuelaing.arep.app.controller;

import java.io.IOException;

import edu.escuelaing.arep.app.factory.ServicesFactory;
import edu.escuelaing.arep.app.service.Service;

public class MovieController {

    // An instance of the Service interface to interact with movie-related services.
    private Service movieServices = (new ServicesFactory()).createServices("MOVIE");

    /**
     * Default constructor for the MovieController class.
     * Initializes the movieServices instance with the appropriate service implementation.
     */
    public MovieController() {}

    /**
     * Retrieves movie information by its name.
     *
     * @param name The name of the movie for which information is to be retrieved.
     * @return A string containing the movie information.
     * @throws IOException If an I/O exception occurs while retrieving the movie information.
     */
    public String getMovieByName(String name) throws IOException {
        return movieServices.getMovieByName(name);
    }
}
