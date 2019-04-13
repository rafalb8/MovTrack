package com.movtrack;

import com.movtrack.RestClient.Search;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


// Class representing entry in searched movies list
public class MovieEntry extends HorizontalLayout {
    private Search movie;
    private Image imgPoster;
    private VerticalLayout vlInfo;
    private Label lblTitle;
    private Label lblType;
    private WatchListButton btnWatch;

    public MovieEntry(Search movie) {
        this.movie = movie;

        imgPoster = new Image(movie.getPoster(), "Image not found");
        vlInfo = new VerticalLayout();
        lblTitle = new Label(movie.getTitle() + " (" + movie.getYear() +")");
        lblType = new Label(movie.getType().toUpperCase());
        btnWatch = new WatchListButton();

        vlInfo.add(lblTitle);
        add(imgPoster, vlInfo, lblType, btnWatch);

        setVerticalComponentAlignment(Alignment.END, btnWatch);

        getElement().addEventListener("click", event -> click());
    }

    // Change view to MovieView
    public void click() {
        getUI().get().navigate("movie/" + movie.getImdbID());

    }
}
