package com.movtrack.Views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("movie")
public class MovieView extends VerticalLayout implements HasUrlParameter<String> {

    private Image imgPoster;
    private Label lblTitle;
    private Text txtPlot;

    public MovieView() {
        imgPoster = new Image();
        lblTitle = new Label();
        txtPlot = new Text();

    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        // Get info based on IMDB id


    }
}
