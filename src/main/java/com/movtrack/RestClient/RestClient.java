package com.movtrack.RestClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RestClient {
    public static RestClient singleton;

    private Client client;
    private WebTarget webtarget;

    /*
        t - Get movie by title
        i - Get movie by id
        s - Search movie by title
    */

    private RestClient(){
        client = ClientBuilder.newClient();
        webtarget = client.target("http://www.omdbapi.com/").queryParam("apikey", "b45ac754");
    }

    public static RestClient getInstance(){
        if(singleton == null)
            singleton = new RestClient();

        return singleton;
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
