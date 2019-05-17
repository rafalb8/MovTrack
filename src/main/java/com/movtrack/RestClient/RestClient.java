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
        webtarget = client.target("https://api.themoviedb.org/3").queryParam("api_key", "300a820bd8fc751e731843bb6e5a22d4");
    }

    public static RestClient getInstance(){
        if(singleton == null)
            singleton = new RestClient();

        return singleton;
    }

    public Movie getMovieByID(String param){
        return webtarget.path("/movie/" + param).request(MediaType.APPLICATION_JSON).get(Movie.class);
    }

    public Search searchMovieByTitle(String param){
        return webtarget.path("/search/multi").queryParam("query", param).request(MediaType.APPLICATION_JSON).get(Search.class);
    }

}
