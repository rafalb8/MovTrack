package com.movtrack.MediaBar;

import com.movtrack.LabelLayout;
import com.movtrack.List.DB.MediaEntity;
import com.movtrack.RestClient.Movie.Movie;
import com.movtrack.RestClient.RestClient;
import com.movtrack.RestClient.TV.TvShow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MoviePoster extends VerticalLayout {
    private final Image img;
    private final LabelLayout lblTitle;

    private final RestClient client;

    public MoviePoster(int mediaID, String mediaType){
        client = RestClient.getInstance();

        // Get information from TMDB
        if(mediaType.equals("movie")) {
            Movie movie = client.getMovieByID(String.valueOf(mediaID));

            img = new Image("https://image.tmdb.org/t/p/w200" + movie.getPosterPath(), "");
            lblTitle = new LabelLayout("<b>" + movie.getTitle() + "</b>");

            // Add click event
            getElement().addEventListener("click", event ->
                getUI().ifPresent(ui -> ui.navigate("/movie/" + movie.getId()))
            );

        } else {
            TvShow tv = client.getTVShowByID(String.valueOf(mediaID));

            img = new Image("https://image.tmdb.org/t/p/w200" + tv.getPosterPath(), "");
            lblTitle = new LabelLayout("<b>" + tv.getName() + "</b>");

            // Add click event
            getElement().addEventListener("click", event ->
                    getUI().ifPresent(ui -> ui.navigate("/tv/" + tv.getId()))
            );
        }

        add(img, lblTitle);
    }
}
