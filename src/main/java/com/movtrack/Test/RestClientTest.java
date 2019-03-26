package com.movtrack.Test;

import com.movtrack.RestClient.Movie;
import com.movtrack.RestClient.SearchResult;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    private Client client;
    private WebTarget webtarget;

    private RestClientTest() {
        client = ClientBuilder.newClient();
        webtarget = client.target("http://www.omdbapi.com/").queryParam("apikey", "b45ac754");
    }

    @Test
    void idRequest(){
        Movie m = webtarget.queryParam("i", "tt3896198").request(MediaType.APPLICATION_JSON).get(Movie.class);

        assertEquals("Guardians of the Galaxy Vol. 2", m.getTitle(), "Incorrect title");
    }

    @Test
    void searchRequest() {
        SearchResult s = webtarget.queryParam("s", "avengers").request(MediaType.APPLICATION_JSON).get(SearchResult.class);

        assertEquals("The Avengers", s.getSearch().get(0).getTitle() , "Incorrect title");
    }
}