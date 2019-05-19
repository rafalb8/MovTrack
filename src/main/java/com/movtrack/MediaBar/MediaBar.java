package com.movtrack.MediaBar;

import com.movtrack.LabelLayout;
import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.MediaEntity;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.List;

// Horizontal list showing movies to watch

public class MediaBar extends VerticalLayout {

    private final HorizontalLayout hlMovies;
    private final LabelLayout lblTitle;
    private final LabelLayout lblText;

    public MediaBar(String title) {
        super();
        hlMovies = new HorizontalLayout();

        lblTitle = new LabelLayout( "<h3>" + title + "</h3>");
        lblText = new LabelLayout("<b>Nothing to show</b>");
        lblText.setVisible(false);

        // Set gray background
        getElement().getStyle().set("background", "#E7EBEF");
        lblText.setBackground("#E7EBEF");
        lblTitle.setBackground("#E7EBEF");

        add(lblTitle, hlMovies);
        hlMovies.add(lblText);
    }

    public void setTitle(String title){
        lblTitle.getElement().setProperty("innerHTML", "<h3>" + title + "</h3>");
    }

    public void show(List<MediaEntity> mediaList){
        for(MediaEntity m : mediaList){
            hlMovies.add(new MoviePoster(m.getMediaID(), m.getMediaType()));
        }

        if(mediaList.size() > 0 ){
            lblText.setVisible(true);
        } else {
            lblText.setVisible(false);
        }
    }

    public void showPairs(List<Pair<Integer, String>> pairList){
        for(Pair<Integer, String> pair : pairList){
            hlMovies.add(new MoviePoster(pair.getFirst(), pair.getSecond()));
        }

        if(pairList.size() > 0 ){
            lblText.setVisible(true);
        } else {
            lblText.setVisible(false);
        }
    }
}
