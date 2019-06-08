package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.WatchListButton;
import com.movtrack.List.WatchedListButton;
import com.movtrack.MediaBar.MediaBar;
import com.movtrack.RestClient.Movie.Genre;
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
    private String imdbID;

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
    private final Label lblVotes;
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
        lblVotes = new Label();
        recommended = new MediaBar("Recommendation");

        // Add click event to title
        lblTitle.getElement().addEventListener("click", event -> goToIMDB());


        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        hlMainInfo.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        hlTitle.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        vlInfo.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        setHorizontalComponentAlignment(Alignment.CENTER, banner);

        hlMainInfo.add(imgPoster, vlInfo);
        hlTitle.add(lblTitle);
        vlInfo.add(hlTitle, lblVotes, lblGenre, lblPlot);
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

        // Set Title label
        lblTitle.getElement().setProperty("innerHTML","<h1>"+movie.getTitle() + (movie.getReleaseDate() == null?"":" (" + movie.getReleaseDate() + ")") +"</h1>");

        // Build string with all genres
        if(!movie.getGenres().isEmpty()){
            StringBuilder genres = new StringBuilder();

            for(Genre genre : movie.getGenres()){
                if(genre != movie.getGenres().get(0)){
                    genres.append(", ");
                }
                genres.append(genre.getName());
            }

            lblGenre.getElement().setProperty("innerHTML","<b>Genres: " + genres.toString() +"</b>");
        }

        // Set plot label
        lblPlot.getElement().setProperty("innerHTML","<i>"+movie.getOverview()+"</i>");

        // Set votes label
        lblVotes.getElement().setProperty("innerHTML", "<b> " + (int)(movie.getVoteAverage() * 10) + " % | " + movie.getVoteCount() + " votes</b>");

        // Update buttons
        btnWatched.init("movie", movie.getId());
        btnWatchList.init("movie", movie.getId());

        // Recommendation list
        recommended.setTitle("If you like " + movie.getTitle() + ", check out...");
        recommended.showRecommended(movie.getId(), "movie");

        // Save imdb ID
        imdbID = movie.getImdbId();
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info by id
        if(parameter.chars().allMatch(Character::isDigit)) {
            refreshInfo(restClient.getMovieByID(parameter));
        }
    }

    private void goToIMDB(){
        getUI().ifPresent(ui -> ui.getPage().executeJavaScript("window.open(\"http://imdb.com/title/"+imdbID+"\", \"_self\");"));
    }
}
