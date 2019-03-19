package com.movtrack;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "MovTrack - Track watched Movies and TV Shows", shortName = "MovTrack")
public class MainView extends VerticalLayout {

    RestClient restClient;
    HorizontalLayout hlHeader;
    ProgressBar progressBar;
    Label lblTitle;
    TextField txtSearchBar;
    Button btnWatchList;

    public MainView() {
        restClient = new RestClient();
        hlHeader = new HorizontalLayout();
        progressBar = new ProgressBar();
        lblTitle = new Label("MovTrack");
        txtSearchBar = new TextField("Search");
        btnWatchList = new Button("WatchList");

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        // Add components to Layout
        add(hlHeader, txtSearchBar, progressBar);
        hlHeader.add(lblTitle, btnWatchList);

        // lblTitle style
        lblTitle.setWidthFull();
        lblTitle.getElement().getStyle().set("text-align", "center");
        lblTitle.getElement().getStyle().set("font-size", "40px");

        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());
    }

    private void search(){
        Notification.show(restClient.getMovieByTitle(txtSearchBar.getValue()).getPlot());
    }
}
