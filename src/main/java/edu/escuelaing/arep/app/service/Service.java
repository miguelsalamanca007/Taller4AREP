/**
 * The Service interface defines the contract for interacting with different types of services.
 * It includes a method for retrieving movie information by name.
 */
package edu.escuelaing.arep.app.service;

import java.io.IOException;

public interface Service {
    /**
     * Retrieves movie information by its name.
     *
     * @param name The name of the movie for which information is to be retrieved.
     * @return A string containing the movie information.
     * @throws IOException If an I/O exception occurs while retrieving the movie information.
     */
    public String getMovieByName(String name) throws IOException;
}
