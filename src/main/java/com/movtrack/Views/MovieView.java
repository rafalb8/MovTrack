package com.movtrack.Views;

import com.movtrack.RestClient.Movie;
import com.movtrack.RestClient.RestClient;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("movie")
public class MovieView extends HorizontalLayout implements HasUrlParameter<String> {

    private RestClient restClient;
    private VerticalLayout vlInfo;
    private Image imgPoster;
    private Label lblTitle;
    private Text txtPlot;

    public MovieView() {
        restClient = RestClient.getInstance();
        vlInfo = new VerticalLayout();
        imgPoster = new Image();
        lblTitle = new Label();
        txtPlot = new Text("");

        vlInfo.add(lblTitle, txtPlot);
        add(imgPoster,vlInfo);
    }

    private void refreshInfo(Movie movie){
        imgPoster.setSrc(movie.getPoster());
        lblTitle.setTitle(movie.getTitle() + " (" + movie.getYear() +")");
        txtPlot.setText(movie.getPlot());
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info based on IMDB id
        refreshInfo(restClient.getMovieByID(parameter));
    }
}
