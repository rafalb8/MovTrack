package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.ListType;
import com.movtrack.RestClient.RestClient;
import com.movtrack.List.ListButton;
import com.movtrack.RestClient.TV.TvShow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

// View showing detailed tv show information
@Route("tv")
public class TvShowView extends VerticalLayout implements HasUrlParameter<String> {

    private RestClient restClient;

    // Design
    private Banner banner;
    private HorizontalLayout hlMainInfo;
    private VerticalLayout vlInfo;
    private HorizontalLayout hlTitle;
    private Image imgPoster;
    private Label lblTitle;
    private Label lblGenre;
    private Label lblPlot;
    private ListButton btnWatch;
    private ListButton btnToWatch;


    public TvShowView() {
        restClient = RestClient.getInstance();

        banner = new Banner();
        hlMainInfo = new HorizontalLayout();
        vlInfo = new VerticalLayout();
        hlTitle = new HorizontalLayout();
        imgPoster = new Image();
        lblTitle = new Label();
        lblGenre = new Label();
        lblPlot = new Label();
        btnWatch = new ListButton(ListType.Watched);
        btnToWatch = new ListButton(ListType.WatchList);

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        hlMainInfo.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        hlTitle.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        vlInfo.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        setHorizontalComponentAlignment(Alignment.CENTER, banner);
        setHorizontalComponentAlignment(Alignment.START, btnWatch, btnToWatch);

        hlMainInfo.add(imgPoster, vlInfo);
        hlTitle.add(lblTitle);
        vlInfo.add(hlTitle, lblGenre, lblPlot);
        vlInfo.getElement().getStyle().set("background", "#E7EBEF");

        add(banner, hlMainInfo, btnWatch, btnToWatch);
    }

    private void refreshInfo(TvShow tvShow){
        if(tvShow.getPosterPath() == null){
            imgPoster.setSrc("poster.png");
        } else {
            imgPoster.setSrc("https://image.tmdb.org/t/p/w300" + tvShow.getPosterPath());
        }

        lblTitle.getElement().setProperty("innerHTML","<h1>"+tvShow.getName() + " (" + tvShow.getFirstAirDate() +")</h1>");
        lblGenre.getElement().setProperty("innerHTML","<b>Genres: " + tvShow.getGenres().get(0).getName()+"</b>");
        lblPlot.getElement().setProperty("innerHTML","<i>"+tvShow.getOverview()+"</i>");
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info by id
        refreshInfo(restClient.getTVShowByID(parameter));
    }
}
