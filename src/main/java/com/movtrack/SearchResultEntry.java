package com.movtrack;

import com.movtrack.List.ListButton;
import com.movtrack.List.ListType;
import com.movtrack.RestClient.Search.Result;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


// Class representing entry in searched movies list
public class SearchResultEntry extends HorizontalLayout {
    private Result result;
    private Image imgPoster;
    private VerticalLayout vlInfo;
    private Html lblTitle;
    private Html lblType;
    private ListButton btnWatch;
    private ListButton btnToWatch;

    public SearchResultEntry(Result result) {
        this.result = result;
        vlInfo = new VerticalLayout();

        if (result.getPosterPath() == null) {
            imgPoster = new Image("poster.png", "");
        } else {
            imgPoster = new Image("https://image.tmdb.org/t/p/w300" + result.getPosterPath(), "");
        }

        if(result.getMediaType().equals("tv")) {
            lblTitle = new Html("<h1>" + result.getName() + " (" + result.getFirstAirDate() + ")</h1>");
        } else {
            lblTitle = new Html("<h1>" + result.getTitle() + " (" + result.getReleaseDate() + ")</h1>");
        }

        lblType = new Html("<h2>"+result.getMediaType().toUpperCase()+"</h2>");
        btnWatch = new ListButton(ListType.Watched, result.getMediaType(), result.getId());
        btnToWatch = new ListButton(ListType.WatchList, result.getMediaType(), result.getId());

        vlInfo.add(lblTitle, lblType, btnWatch, btnToWatch);
        add(imgPoster, vlInfo);

        imgPoster.getElement().addEventListener("click", event -> click());
        vlInfo.getElement().addEventListener("click", event -> click());
        lblType.getElement().addEventListener("click", event -> click());
    }

    // Change view to MovieView or TvShowView
    public void click() {
        if(result.getMediaType().equals("tv")){
            getUI().ifPresent(ui -> ui.navigate("tv/" + result.getId()));
        } else {
            getUI().ifPresent(ui -> ui.navigate("movie/" + result.getId()));
        }
    }
}
