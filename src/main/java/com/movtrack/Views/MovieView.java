package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.ListType;
import com.movtrack.RestClient.Movie.Movie;
import com.movtrack.RestClient.RestClient;
import com.movtrack.ListButton;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

// View showing detailed movie information
@Route("movie")
public class MovieView extends VerticalLayout implements HasUrlParameter<String> {

    private RestClient restClient;

    // Design
    private Banner banner;
    private HorizontalLayout hlMainInfo;
    private VerticalLayout vlInfo;
    private HorizontalLayout hlTitle;
    private Image imgPoster;
    private Label lblTitle;
    private Label lblRated;
    private Label lblGenre;
    private Label lblPlot;
    private ListButton btnWatch;
    private ListButton btnToWatch;


    public MovieView() {
        restClient = RestClient.getInstance();

        banner = new Banner();
        hlMainInfo = new HorizontalLayout();
        vlInfo = new VerticalLayout();
        hlTitle = new HorizontalLayout();
        imgPoster = new Image();
        lblTitle = new Label();
        lblRated = new Label();
        lblGenre = new Label();
        lblPlot = new Label();
        btnWatch = new ListButton(ListType.Watched);
        btnToWatch = new ListButton(ListType.WatchList);

        imgPoster.setAlt("Image not found");

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        hlMainInfo.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        hlTitle.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        vlInfo.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        setHorizontalComponentAlignment(Alignment.CENTER, banner);
        setHorizontalComponentAlignment(Alignment.START, btnWatch, btnToWatch);

        hlMainInfo.add(imgPoster, vlInfo);
        hlTitle.add(lblTitle);
        vlInfo.add(hlTitle, lblRated, lblGenre, lblPlot);
        vlInfo.getElement().getStyle().set("background", "#E7EBEF");

        add(banner, hlMainInfo, btnWatch, btnToWatch);
    }

    private void refreshInfo(Movie movie){
        imgPoster.setSrc(movie.getPosterPath());
        lblTitle.getElement().setProperty("innerHTML","<h1>"+movie.getTitle() + " (" + movie.getReleaseDate() +")</h1>");
        lblRated.getElement().setProperty("innerHTML","<h3>Rated: "+movie.getAdult()+"</h3>");
        lblGenre.getElement().setProperty("innerHTML","<b>Genres: " + movie.getGenres().get(0).getName()+"</b>");
        lblPlot.getElement().setProperty("innerHTML","<i>"+movie.getOverview()+"</i>");
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info based on IMDB id
        refreshInfo(restClient.getMovieByID(parameter));
    }
}
