package com.movtrack;

import com.movtrack.RestClient.Search.Result;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


// Class representing entry in searched movies list
public class SearchedMovieEntry extends HorizontalLayout {
    private Result movie;
    private Image imgPoster;
    private VerticalLayout vlInfo;
    private Html lblTitle;
    private Html lblType;
    private ListButton btnWatch;
    private ListButton btnToWatch;

    public SearchedMovieEntry(Result movie) {
        this.movie = movie;

        imgPoster = new Image(movie.getPosterPath(), "Image not found");
        vlInfo = new VerticalLayout();
        lblTitle = new Html("<h1>"+movie.getTitle() + " (" + movie.getReleaseDate() +")</h1>");
        lblType = new Html("<h2>"+movie.getMediaType().toUpperCase()+"</h2>");
        btnWatch = new ListButton(ListType.Watched);
        btnToWatch = new ListButton(ListType.WatchList);

        vlInfo.add(lblTitle, lblType, btnWatch, btnToWatch);
        add(imgPoster, vlInfo);

        imgPoster.getElement().addEventListener("click", event -> click());
        vlInfo.getElement().addEventListener("click", event -> click());
        lblType.getElement().addEventListener("click", event -> click());
    }

    // Change view to MovieView
    public void click() {
        getUI().get().navigate("movie/" + movie.getId());

    }
}
