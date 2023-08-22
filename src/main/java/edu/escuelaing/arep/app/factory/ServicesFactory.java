/**
 * The ServicesFactory class is responsible for creating instances of different service implementations
 * based on the specified service type.
 */
package edu.escuelaing.arep.app.factory;

import edu.escuelaing.arep.app.service.Service;
import edu.escuelaing.arep.app.service.impl.MovieServices;
import edu.escuelaing.arep.app.service.impl.OMDBConnection;

public class ServicesFactory {
    
    /**
     * Default constructor for the ServicesFactory class.
     */
    public ServicesFactory() {}

    /**
     * Creates and returns an instance of a service implementation based on the specified service type.
     *
     * @param type The type of service to create ("MOVIE" or "OMDB").
     * @return An instance of the Service interface's implementation.
     *         Returns null if the specified type is not recognized.
     */
    public Service createServices(String type) {
        switch(type) {
            case "MOVIE":
                return new MovieServices();
            case "OMDB":
                return new OMDBConnection();
            default:
                return null;
        }
    }
}
