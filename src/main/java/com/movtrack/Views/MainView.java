package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.DB.ListManager;
import com.movtrack.List.ListType;
import com.movtrack.MediaBar.MediaBar;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@PWA(name = "MovTrack - Track watched Movies and Tv Shows", shortName = "MovTrack")
public class MainView extends VerticalLayout implements BeforeEnterObserver {

    private Banner banner;
    private TextField txtSearchBar;
    private MediaBar watchlistBar;

    @Autowired
    ListManager listManager;

    public MainView() {
        banner = new Banner();
        watchlistBar = new MediaBar("Watch List:");

        txtSearchBar = new TextField("Search");
        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());


        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        setHorizontalComponentAlignment(Alignment.CENTER, banner);


        // Add components to Layout
        add(banner, txtSearchBar, watchlistBar);
    }

    private void search(){
        getUI().ifPresent(ui -> ui.navigate("search/" + txtSearchBar.getValue()));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        watchlistBar.show(listManager.getAllByListType(ListType.WatchList));
    }
}
