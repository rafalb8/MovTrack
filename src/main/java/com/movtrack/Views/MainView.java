package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.WatchlistBar;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "MovTrack - Track watched Movies and Tv Shows", shortName = "MovTrack")
public class MainView extends VerticalLayout {

    private Banner banner;
    private TextField txtSearchBar;
    private Html lblWatchList;
    private WatchlistBar watchlistBar;

    public MainView() {
        banner = new Banner();
        txtSearchBar = new TextField("Search");
        lblWatchList = new Html("<h3>Watch List:</h3>");
        watchlistBar = new WatchlistBar();

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        setHorizontalComponentAlignment(Alignment.CENTER, banner);

        // Add components to Layout
        add(banner, txtSearchBar, lblWatchList, watchlistBar);

        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());
    }

    private void search(){
        getUI().ifPresent(ui -> ui.navigate("search/" + txtSearchBar.getValue()));
    }
}
