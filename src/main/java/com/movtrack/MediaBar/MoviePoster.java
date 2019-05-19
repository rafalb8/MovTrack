package com.movtrack.MediaBar;

import com.movtrack.LabelLayout;
import com.movtrack.List.DB.MediaEntity;
import com.movtrack.RestClient.Movie.Movie;
import com.movtrack.RestClient.RestClient;
import com.movtrack.RestClient.TV.TvShow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MoviePoster extends VerticalLayout {
    private final String imgUrl = "https://image.tmdb.org/t/p/w200";
    private final Image img;
    private final LabelLayout lblTitle;

    private final RestClient client;

    public MoviePoster(int mediaID, String mediaType){
        client = RestClient.getInstance();

        // Get information from TMDB
        if(mediaType.equals("movie")) {
            Movie movie = client.getMovieByID(String.valueOf(mediaID));

            lblTitle = new LabelLayout("<b>" + movie.getTitle() + "</b>");

            if(movie.getPosterPath() != null) {
                img = new Image(imgUrl + movie.getPosterPath(), "");
            } else {
                img = new Image("poster.png", "");
            }

            // Add click event
            getElement().addEventListener("click", event ->
                getUI().ifPresent(ui -> ui.navigate("/movie/" + movie.getId()))
            );

        } else {
            TvShow tv = client.getTVShowByID(String.valueOf(mediaID));

            lblTitle = new LabelLayout("<b>" + tv.getName() + "</b>");

            if(tv.getPosterPath() != null) {
                img = new Image(imgUrl + tv.getPosterPath(), "");
            } else {
                img = new Image("poster.png", "");
            }

            // Add click event
            getElement().addEventListener("click", event ->
                    getUI().ifPresent(ui -> ui.navigate("/tv/" + tv.getId()))
            );
        }

        add(img, lblTitle);
    }

    public MoviePoster(String posterPath, String mediaTitle){
        lblTitle = new LabelLayout("<b>" + mediaTitle + "</b>");

        if(posterPath != null) {
            img = new Image(imgUrl + posterPath, "");
        } else {
            img = new Image("poster.png", "");
        }


        add(img, lblTitle);
    }
}
