package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.ListType;
import com.movtrack.RestClient.Movie;
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
    private Label lblDirector;
    private Label lblWriters;
    private Label lblActors;
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
        lblDirector = new Label();
        lblWriters = new Label();
        lblActors = new Label();
        lblPlot = new Label();
        btnWatch = new ListButton(ListType.Watched);
        btnToWatch = new ListButton(ListType.WatchList);

        imgPoster.setAlt("Image not found");

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        hlMainInfo.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        hlTitle.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        vlInfo.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        setHorizontalComponentAlignment(Alignment.CENTER, banner);
        setHorizontalComponentAlignment(Alignment.START, btnWatch);

        hlMainInfo.add(imgPoster, vlInfo);
        hlTitle.add(lblTitle);
        vlInfo.add(hlTitle, lblRated, lblGenre, lblDirector, lblWriters, lblActors, lblPlot);
        vlInfo.getElement().getStyle().set("background", "#E7EBEF");

        add(banner, hlMainInfo, btnWatch, btnToWatch);
    }

    private void refreshInfo(Movie movie){
        imgPoster.setSrc(movie.getPoster());
        lblTitle.getElement().setProperty("innerHTML","<h1>"+movie.getTitle() + " (" + movie.getYear() +")</h1>");
        lblRated.getElement().setProperty("innerHTML","<h3>Rated: "+movie.getRated()+"</h3>");
        lblGenre.getElement().setProperty("innerHTML","<b>Genres: " + movie.getGenre()+"</b>");
        lblDirector.getElement().setProperty("innerHTML","<b>Director: " + movie.getDirector()+"</b>");
        lblWriters.getElement().setProperty("innerHTML","<b>Writers: " + movie.getWriter()+"</b>");
        lblActors.getElement().setProperty("innerHTML","<b>Actors: " + movie.getActors()+"</b>");
        lblPlot.getElement().setProperty("innerHTML","<i>"+movie.getPlot()+"</i>");
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info based on IMDB id
        refreshInfo(restClient.getMovieByID(parameter));
    }
}
