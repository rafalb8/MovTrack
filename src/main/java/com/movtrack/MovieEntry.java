package com.movtrack;

import com.movtrack.RestClient.Search;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class MovieEntry extends HorizontalLayout {
    private Label lblTitle;

    public MovieEntry(Search movie) {
        lblTitle = new Label(movie.getTitle());

        add(lblTitle);
    }
}
