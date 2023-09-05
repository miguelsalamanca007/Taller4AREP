package edu.escuelaing.arep.app.taller1;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

import edu.escuelaing.arep.app.controller.MovieController;

public class HttpClientTest{

    private MovieController movieController = new MovieController();

    public void testMovieService() throws IOException{
        assertEquals(movieController.getMovieByName("John Wick"),"{\"Title\":\"John Wick\",\"Year\":\"2014\",\"Rated\":\"R\",\"Released\":\"24 Oct 2014\",\"Runtime\":\"101 min\",\"Genre\":\"Action, Crime, Thriller\",\"Director\":\"Chad Stahelski, David Leitch\",\"Writer\":\"Derek Kolstad\",\"Actors\":\"Keanu Reeves, Michael Nyqvist, Alfie Allen\",\"Plot\":\"An ex-hitman comes out of retirement to track down the gangsters who killed his dog and stole his car.\",\"Language\":\"English, Russian, Hungarian\",\"Country\":\"United States, United Kingdom, China\",\"Awards\":\"5 wins & 10 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTU2NjA1ODgzMF5BMl5BanBnXkFtZTgwMTM2MTI4MjE@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.4/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"86%\"},{\"Source\":\"Metacritic\",\"Value\":\"68/100\"}],\"Metascore\":\"68\",\"imdbRating\":\"7.4\",\"imdbVotes\":\"707,438\",\"imdbID\":\"tt2911666\",\"Type\":\"movie\",\"DVD\":\"07 Jun 2016\",\"BoxOffice\":\"$43,037,835\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");
    }

    @Test
    public void testMovieServiceConcurrentRequests() throws InterruptedException {
        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        testMovieService();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }


}