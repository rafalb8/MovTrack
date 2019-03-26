package com.movtrack;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RestClient {
    Client client;
    WebTarget webtarget;

    /*
        t - Get movie by title
        i - Get movie by id
        s - Search movie by title
    */

    public RestClient(){
        client = ClientBuilder.newClient();
        webtarget = client.target("http://www.omdbapi.com/").queryParam("apikey", "b45ac754");
    }

    public Movie getMovieByTitle(String param){
        return webtarget.queryParam("t", param).request(MediaType.APPLICATION_JSON).get(Movie.class);
    }

    public Movie getMovieByID(String param){
        return webtarget.queryParam("i", param).request(MediaType.APPLICATION_JSON).get(Movie.class);
    }

    public SearchResult searchMovieByTitle(String param){
        return webtarget.queryParam("s", param).request(MediaType.APPLICATION_JSON).get(SearchResult.class);
    }

}
