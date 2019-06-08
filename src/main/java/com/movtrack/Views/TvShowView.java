package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.WatchListButton;
import com.movtrack.List.WatchedListButton;
import com.movtrack.MediaBar.MediaBar;
import com.movtrack.RestClient.RestClient;
import com.movtrack.RestClient.TV.Genre;
import com.movtrack.RestClient.TV.Season;
import com.movtrack.RestClient.TV.TvShow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

// View showing detailed tv show information
@Route("tv")
public class TvShowView extends VerticalLayout implements HasUrlParameter<String> {

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
    private final Label lblSeasons;
    private final MediaBar recommended;

    @Autowired
    private WatchedListButton btnWatched;
    @Autowired
    private WatchListButton btnWatchList;


    public TvShowView() {
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
        lblSeasons = new Label();
        recommended = new MediaBar("Recommended");


        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        hlMainInfo.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        hlTitle.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        vlInfo.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        setHorizontalComponentAlignment(Alignment.CENTER, banner);

        hlMainInfo.add(imgPoster, vlInfo);
        hlTitle.add(lblTitle);
        vlInfo.add(hlTitle, lblSeasons, lblGenre, lblPlot);
        vlInfo.getElement().getStyle().set("background", "#E7EBEF");

        add(banner, hlMainInfo, hlButtons, recommended);
    }

    @PostConstruct
    void init(){
        setHorizontalComponentAlignment(Alignment.START, btnWatched, btnWatchList);
        hlButtons.add(btnWatched, btnWatchList);
    }


    private void refreshInfo(TvShow tvShow){
        recommended.clear();

        if(tvShow.getPosterPath() == null){
            imgPoster.setSrc("poster.png");
        } else {
            imgPoster.setSrc("https://image.tmdb.org/t/p/w300" + tvShow.getPosterPath());
        }

        // Set TV show title label
        lblTitle.getElement().setProperty("innerHTML","<h1>"+tvShow.getName() + (tvShow.getFirstAirDate() == null?"":" (" + tvShow.getFirstAirDate() + ")") +"</h1>");

        // Build string with genres from the list
        if(!tvShow.getGenres().isEmpty()) {
            StringBuilder allGenres = new StringBuilder();

            for(Genre genre : tvShow.getGenres()){
                if(genre != tvShow.getGenres().get(0))
                    allGenres.append(", ");

                allGenres.append(genre.getName());
            }

            lblGenre.getElement().setProperty("innerHTML", "<b>Genres: " + allGenres.toString() + "</b>");
        }

        // Set plot info
        lblPlot.getElement().setProperty("innerHTML","<i>"+tvShow.getOverview()+"</i>");


        // Get season and episode count
        int seasonCount = tvShow.getSeasons().size();
        int episodeCount = 0;
        for(Season season : tvShow.getSeasons()){
            episodeCount += season.getEpisodeCount();
        }

        // Set seasons label
        lblSeasons.getElement().setProperty("innerHTML", "<b> Seasons: " + seasonCount + " | Episodes: " + episodeCount + "</b>");

        // Update buttons
        btnWatched.init("tv", tvShow.getId());
        btnWatchList.init("tv", tvShow.getId());

        // Recommended list
        recommended.setTitle("If you like " + tvShow.getName() + ", check out...");
        recommended.showRecommended(tvShow.getId(), "tv");
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info by id
        if(parameter.chars().allMatch(Character::isDigit)) {
            refreshInfo(restClient.getTVShowByID(parameter));
        }
    }
}
