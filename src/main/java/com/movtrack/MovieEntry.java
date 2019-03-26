package com.movtrack;

import com.movtrack.RestClient.Search;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class MovieEntry extends HorizontalLayout {
    private Image imgPoster;
    private VerticalLayout vlInfo;
    private Label lblTitle;
    private Label lblType;


    public MovieEntry(Search movie) {
        imgPoster = new Image(movie.getPoster(), movie.getPoster());
        vlInfo = new VerticalLayout();
        lblTitle = new Label(movie.getTitle() + " (" + movie.getYear() +")");
        lblType = new Label(movie.getType().toUpperCase());


        vlInfo.add(lblTitle);
        add(imgPoster, vlInfo, lblType);
    }
}
