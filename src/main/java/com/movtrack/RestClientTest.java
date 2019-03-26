package com.movtrack;

import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    Client client;
    WebTarget webtarget;

    public RestClientTest() {
        client = ClientBuilder.newClient();
        webtarget = client.target("http://www.omdbapi.com/").queryParam("apikey", "b45ac754");
    }

    @Test
    void idRequest(){
        Movie m = webtarget.queryParam("i", "tt3896198").request(MediaType.APPLICATION_JSON).get(Movie.class);

        assertEquals("Guardians of the Galaxy Vol. 2", m.getTitle(), "Title not correct");
    }

    @Test
    void searchRequest() {
        SearchResult s = webtarget.queryParam("s", "avengers").request(MediaType.APPLICATION_JSON).get(SearchResult.class);

    }
}