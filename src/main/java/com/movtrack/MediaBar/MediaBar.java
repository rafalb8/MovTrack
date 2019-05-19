package com.movtrack.MediaBar;

import com.movtrack.TextLayout;
import com.movtrack.List.DB.MediaEntity;
import com.movtrack.RestClient.RestClient;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Horizontal list showing movies to watch
public class MediaBar extends VerticalLayout {

    private final HorizontalLayout hlMovies;
    private final TextLayout lblTitle;
    private final TextLayout lblText;

    public MediaBar(String title) {
        super();
        hlMovies = new HorizontalLayout();

        lblTitle = new TextLayout( "<h3>" + title + "</h3>");
        lblText = new TextLayout("<b>Nothing to show</b>");

        // Set gray background
        getElement().getStyle().set("background", "#E7EBEF");
        lblText.setBackground("#E7EBEF");
        lblTitle.setBackground("#E7EBEF");

        add(lblTitle, hlMovies);
        hlMovies.add(lblText);
    }

    public void setTitle(String title){
        lblTitle.setText("<h3>" + title + "</h3>");
    }

    public void clear(){
        hlMovies.removeAll();
    }

    // Show Entries from Database
    public void show(List<MediaEntity> mediaList){
        for(MediaEntity m : mediaList){
            hlMovies.add(new MoviePoster(m.getMediaID(), m.getMediaType()));
        }

        if(mediaList.isEmpty()){
            lblText.setVisible(true);
        } else {
            lblText.setVisible(false);
        }
    }

    // Show Pairs of mediaID and MediaType
    public void showPairs(List<Pair<Integer, String>> pairList){
        for(Pair<Integer, String> pair : pairList){
            hlMovies.add(new MoviePoster(pair.getFirst(), pair.getSecond()));
        }

        if(pairList.isEmpty()){
            lblText.setVisible(true);
        } else {
            lblText.setVisible(false);
        }
    }

    // Show Pairs of PosterPath and Title
    public void showPoster(List<Pair<String, String>> pairList){
        for(Pair<String, String> pair : pairList){
            hlMovies.add(new MoviePoster(pair.getFirst(), pair.getSecond()));
        }

        if(pairList.isEmpty()){
            lblText.setVisible(true);
        } else {
            lblText.setVisible(false);
        }
    }

    // Show recommendations
    public void showRecommended(int mediaID, String mediaType){
        RestClient client = RestClient.getInstance();
        List<Pair<String, String>> pairs = new ArrayList<>();

        if(mediaType.equals("movie")){
            // Get Movie Recommendations
            client.getMovieRecommendations(String.valueOf(mediaID)).getResults().forEach(
                    movie -> pairs.add(Pair.of(movie.getPosterPath(), movie.getTitle()))
            );
        } else {
            // Get TV Recommendations
            client.getTvShowRecommendations(String.valueOf(mediaID)).getResults().forEach(
                    tv -> pairs.add(Pair.of(tv.getPosterPath(), tv.getName()))
            );
        }

        // Show 6 entries
        showPoster(pairs.stream().limit(6).collect(Collectors.toList()));
    }
}
