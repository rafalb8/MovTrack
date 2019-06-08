package com.movtrack.Test;

import com.movtrack.RestClient.Movie.Movie;
import com.movtrack.RestClient.Movie.Recommendation.MovieRecommendations;
import com.movtrack.RestClient.RestClient;
import com.movtrack.RestClient.Search.Search;
import com.movtrack.RestClient.TV.Recommendation.TvShowRecommendations;
import com.movtrack.RestClient.TV.TvShow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    private RestClient restClient;

    private RestClientTest() {
        restClient = RestClient.getInstance();
    }

    @Test
    void movieByIdRequest(){
        Movie m = restClient.getMovieByID("550");

        assertEquals("Fight Club", m.getTitle(), "Incorrect title");
    }

    @Test
    void tvShowByIdRequest(){
        TvShow m = restClient.getTVShowByID("63926");

        assertEquals("One-Punch Man", m.getName(), "Incorrect name");
        assertEquals("tt4508902", m.getExternalIds().getImdbId(), "Incorrect imdb ID");
    }

    @Test
    void searchRequest(){
        Search s = restClient.searchByTitle("avengers");

        assertNotNull(s.getResults(),"Search is empty");
        assertEquals("Avengers: Endgame", s.getResults().get(0).getTitle() , "Incorrect title");
    }

    @Test
    void movieRecommendationRequest(){
        MovieRecommendations mr = restClient.getMovieRecommendations("550");
        assertNotNull(mr.getResults(),"No recommendations");
    }

    @Test
    void tvShowRecommendationRequest(){
        TvShowRecommendations tvr = restClient.getTvShowRecommendations("63926");
        assertNotNull(tvr.getResults(),"No recommendations");
    }
}