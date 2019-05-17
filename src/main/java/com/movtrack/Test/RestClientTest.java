package com.movtrack.Test;

import com.movtrack.RestClient.Movie.Movie;
import com.movtrack.RestClient.RestClient;
import com.movtrack.RestClient.Search.Search;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    private RestClient restClient;

    private RestClientTest() {
        restClient = RestClient.getInstance();
    }

    @Test
    void idRequest(){
        Movie m = restClient.getMovieByID("550");

        assertEquals("Fight Club", m.getTitle(), "Incorrect title");
    }

    @Test
    void searchRequest() {
        Search s = restClient.searchByTitle("avengers");
        assertNotNull(s.getResults(),"Search is empty");
        assertEquals("Avengers: Endgame", s.getResults().get(0).getTitle() , "Incorrect title");
    }
}