package com.movtrack;

import com.movtrack.RestClient.Search;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


// Class representing entry in searched movies list
public class MovieEntry extends HorizontalLayout {
    private Search movie;
    private Image imgPoster;
    private VerticalLayout vlInfo;
    private Html lblTitle;
    private Html lblType;
    private ListButton btnWatch;

    public MovieEntry(Search movie) {
        this.movie = movie;

        imgPoster = new Image(movie.getPoster(), "Image not found");
        vlInfo = new VerticalLayout();
        lblTitle = new Html("<h1>"+movie.getTitle() + " (" + movie.getYear() +")</h1>");
        lblType = new Html("<h2>"+movie.getType().toUpperCase()+"</h2>");
        btnWatch = new ListButton(ListType.WatchList);

        vlInfo.add(lblTitle, lblType, btnWatch);
        add(imgPoster, vlInfo);

        imgPoster.getElement().addEventListener("click", event -> click());
        vlInfo.getElement().addEventListener("click", event -> click());
        lblType.getElement().addEventListener("click", event -> click());
    }

    // Change view to MovieView
    public void click() {
        getUI().get().navigate("movie/" + movie.getImdbID());

    }
}
