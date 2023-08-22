/**
 * The MovieServices class is an implementation of the Service interface.
 * It provides methods for retrieving movie information by using an OMDB service.
 */
package edu.escuelaing.arep.app.service.impl;

import java.io.IOException;

import edu.escuelaing.arep.app.factory.ServicesFactory;
import edu.escuelaing.arep.app.service.Service;

public class MovieServices implements Service {

    /**
     * Default constructor for the MovieServices class.
     */
    public MovieServices() {
    }

    // An instance of the Service interface to interact with the OMDB service.
    private Service omdbConnection = (new ServicesFactory()).createServices("OMDB");

    /**
     * Retrieves movie information by its name using the OMDB service.
     *
     * @param name The name of the movie for which information is to be retrieved.
     * @return A string containing the movie information.
     * @throws IOException If an I/O exception occurs while retrieving the movie information.
     */
    @Override
    public String getMovieByName(String name) throws IOException {
        return omdbConnection.getMovieByName(name);
    }
}
