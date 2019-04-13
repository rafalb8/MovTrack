package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.RestClient.Movie;
import com.movtrack.RestClient.RestClient;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import javax.swing.text.html.HTML;
import javax.swing.text.html.parser.ContentModel;

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

        hlMainInfo.add(imgPoster, vlInfo);
        hlTitle.add(lblTitle, lblRated);
        vlInfo.add(hlTitle, lblGenre, lblDirector, lblWriters, lblActors);
        add(banner, hlMainInfo, lblPlot);
    }

    private void refreshInfo(Movie movie){
        imgPoster.setSrc(movie.getPoster());
        lblTitle.setText(movie.getTitle() + " (" + movie.getYear() +")");
        lblRated.setText(movie.getRated());
        lblGenre.setText("Genres: " + movie.getGenre());
        lblDirector.setText("Director: " + movie.getDirector());
        lblWriters.setText("Writers: " + movie.getWriter());
        lblActors.setText("Actors: " + movie.getActors());
        lblPlot.setText(movie.getPlot());
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info based on IMDB id
        refreshInfo(restClient.getMovieByID(parameter));
    }
}
