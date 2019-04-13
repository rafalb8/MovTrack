package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.WatchlistBar;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "MovTrack - Track watched Movies and TV Shows", shortName = "MovTrack")
public class MainView extends VerticalLayout {

    private Banner banner;
    private TextField txtSearchBar;
    private Label lblWatchList;
    private WatchlistBar watchlistBar;

    public MainView() {
        banner = new Banner();
        txtSearchBar = new TextField("Search");
        lblWatchList = new Label("WatchList");
        watchlistBar = new WatchlistBar();

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        setHorizontalComponentAlignment(Alignment.CENTER, banner);

        // Add components to Layout
        add(banner, txtSearchBar, lblWatchList, watchlistBar);

        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());
    }

    private void search(){
        getUI().get().navigate("search/" + txtSearchBar.getValue());
    }
}
