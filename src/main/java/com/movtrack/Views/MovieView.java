package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.WatchListButton;
import com.movtrack.List.WatchedListButton;
import com.movtrack.MediaBar.MediaBar;
import com.movtrack.RestClient.Movie.Movie;
import com.movtrack.RestClient.RestClient;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

// View showing detailed movie information
@Route("movie")
public class MovieView extends VerticalLayout implements HasUrlParameter<String> {

    private RestClient restClient;

    // Design
    private final Banner banner;
    private final HorizontalLayout hlMainInfo;
    private final VerticalLayout vlInfo;
    private final HorizontalLayout hlTitle;
    private final HorizontalLayout hlButtons;
    private final Image imgPoster;
    private final Label lblTitle;
    private final Label lblGenre;
    private final Label lblPlot;
    private final MediaBar recommended;

    @Autowired
    private WatchedListButton btnWatched;

    @Autowired
    private WatchListButton btnWatchList;


    public MovieView() {
        restClient = RestClient.getInstance();

        banner = new Banner();
        hlMainInfo = new HorizontalLayout();
        vlInfo = new VerticalLayout();
        hlTitle = new HorizontalLayout();
        hlButtons = new HorizontalLayout();
        imgPoster = new Image();
        lblTitle = new Label();
        lblGenre = new Label();
        lblPlot = new Label();
        recommended = new MediaBar("Recommendation");


        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        hlMainInfo.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        hlTitle.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        vlInfo.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        setHorizontalComponentAlignment(Alignment.CENTER, banner);

        hlMainInfo.add(imgPoster, vlInfo);
        hlTitle.add(lblTitle);
        vlInfo.add(hlTitle, lblGenre, lblPlot);
        vlInfo.getElement().getStyle().set("background", "#E7EBEF");

        add(banner, hlMainInfo, hlButtons, recommended);
    }

    @PostConstruct
    void init(){
        setHorizontalComponentAlignment(Alignment.START, btnWatched, btnWatchList);
        hlButtons.add(btnWatched, btnWatchList);
    }

    private void refreshInfo(Movie movie){
        recommended.clear();

        if(movie.getPosterPath() == null){
            imgPoster.setSrc("poster.png");
        } else {
            imgPoster.setSrc("https://image.tmdb.org/t/p/w300" + movie.getPosterPath());
        }

        lblTitle.getElement().setProperty("innerHTML","<h1>"+movie.getTitle() + (movie.getReleaseDate() == null?"":" (" + movie.getReleaseDate() + ")") +"</h1>");
        lblGenre.getElement().setProperty("innerHTML","<b>Genres: " + movie.getGenres().get(0).getName()+"</b>");
        lblPlot.getElement().setProperty("innerHTML","<i>"+movie.getOverview()+"</i>");

        // Update buttons
        btnWatched.init("movie", movie.getId());
        btnWatchList.init("movie", movie.getId());

        // Recommendation list
        recommended.setTitle("If you like " + movie.getTitle() + ", check out...");
        recommended.showRecommended(movie.getId(), "movie");
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info by id
        if(parameter.chars().allMatch(Character::isDigit)) {
            refreshInfo(restClient.getMovieByID(parameter));
        }
    }
}
