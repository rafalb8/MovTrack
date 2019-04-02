package com.movtrack.Test;

import com.movtrack.RestClient.Movie;
import com.movtrack.RestClient.SearchResult;
import com.movtrack.RestClient.RestClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    private RestClient restClient;

    private RestClientTest() {
        restClient = RestClient.getInstance();
    }

    @Test
    void idRequest(){
        Movie m = restClient.getMovieByID("tt3896198");

        assertEquals("Guardians of the Galaxy Vol. 2", m.getTitle(), "Incorrect title");
    }

    @Test
    void searchRequest() {
        SearchResult s = restClient.searchMovieByTitle("avengers");

        assertEquals("The Avengers", s.getSearch().get(0).getTitle() , "Incorrect title");
    }
}