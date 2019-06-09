package com.movtrack.MediaBar;

import com.movtrack.List.DB.MediaEntity;
import com.movtrack.RestClient.RestClient;
import com.movtrack.TextLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.javatuples.Pair;
import org.javatuples.Quartet;

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

        lblTitle = new TextLayout( "<b>" + title + "</b>");
        lblText = new TextLayout("<b>Nothing to show</b>");
        lblText.setVisible(false);

        // Set gray background
        getElement().getStyle().set("background", "#E7EBEF");
        lblText.setBackground("#E7EBEF");
        lblTitle.setBackground("#E7EBEF");

        setHorizontalComponentAlignment(Alignment.START, lblTitle);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(lblTitle, hlMovies, lblText);
    }

    public void setTitle(String title){
        lblTitle.setText("<b>" + title + "</b>");
    }

    public void clear(){
        hlMovies.removeAll();
    }

    // Show Entries from Database
    public void show(List<MediaEntity> mediaList){

        // Show 6 entries
        for(MediaEntity m : mediaList.stream().limit(6).collect(Collectors.toList())){
            hlMovies.add(new MoviePoster(m.getMediaID(), m.getMediaType()));
        }

        if(mediaList.isEmpty()){
            lblText.setVisible(true);
        }
    }

    // Show Pairs of mediaID and MediaType
    public void showPairs(List<Pair<Integer, String>> pairList){

        // Show 6 entries
        for(Pair<Integer, String> pair : pairList.stream().limit(6).collect(Collectors.toList())){
            hlMovies.add(new MoviePoster(pair.getValue0(), pair.getValue1()));
        }


        if(pairList.isEmpty()){
            lblText.setVisible(true);
        }
    }

    // Show Pairs of PosterPath and Title
    public void showPoster(List<Quartet<Integer, String, String, String>> args){

        // Show 6 entries
        for(Quartet<Integer, String, String, String> arg : args.stream().limit(6).collect(Collectors.toList())){
            hlMovies.add(new MoviePoster(arg));
        }


        if(args.isEmpty()){
            lblText.setVisible(true);
        }
    }

    // Show recommendations
    public void showRecommended(int mediaID, String mediaType){
        RestClient client = RestClient.getInstance();

        //  args : mediaID, title, type, posterPath
        List<Quartet<Integer, String, String, String>> args = new ArrayList<>();

        if(mediaType.equals("movie")){
            // Get Movie Recommendations
            client.getMovieRecommendations(String.valueOf(mediaID)).getResults().forEach(
                    movie -> args.add(Quartet.with(movie.getId(), movie.getTitle(), "movie", movie.getPosterPath()))
            );
        } else {
            // Get TV Recommendations
            client.getTvShowRecommendations(String.valueOf(mediaID)).getResults().forEach(
                    tv -> args.add(Quartet.with(tv.getId(), tv.getName(), "tv", tv.getPosterPath()))
            );
        }

        // Show 6 entries
        showPoster(args);
    }

    // Write text when empty
    public void showEmpty(){
        lblText.setVisible(true);
    }
}
