package com.movtrack.RestClient;

import com.movtrack.RestClient.Movie.Movie;
import com.movtrack.RestClient.Movie.Recommendation.MovieRecommendations;
import com.movtrack.RestClient.Search.Search;
import com.movtrack.RestClient.TV.Recommendation.TvShowRecommendations;
import com.movtrack.RestClient.TV.TvShow;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RestClient {
    public static RestClient singleton;

    private Client client;
    private WebTarget webtarget;

    private RestClient(){
        client = ClientBuilder.newClient();
        webtarget = client.target("https://api.themoviedb.org/3").queryParam("api_key", "300a820bd8fc751e731843bb6e5a22d4");
    }

    public static RestClient getInstance(){
        if(singleton == null)
            singleton = new RestClient();

        return singleton;
    }

    // Get information about movie
    public Movie getMovieByID(String id){
        return webtarget.path("/movie/" + id).request(MediaType.APPLICATION_JSON).get(Movie.class);
    }

    // Get information about Tv Show
    public TvShow getTVShowByID(String id){
        return webtarget.path("/tv/" + id).queryParam("append_to_response","external_ids").request(MediaType.APPLICATION_JSON).get(TvShow.class);
    }

    // Search for movie or tv show
    public Search searchByTitle(String title){
        return webtarget.path("/search/multi").queryParam("query", title).request(MediaType.APPLICATION_JSON).get(Search.class);
    }

    // Get MediaEntity Recommendations
    public MovieRecommendations getMovieRecommendations(String id){
        return webtarget.path("/movie/" + id +"/recommendations").request(MediaType.APPLICATION_JSON).get(MovieRecommendations.class);
    }

    // Get Tv Show Recommendations
    public TvShowRecommendations getTvShowRecommendations(String id){
        return webtarget.path("/tv/" + id +"/recommendations").request(MediaType.APPLICATION_JSON).get(TvShowRecommendations.class);
    }

}
