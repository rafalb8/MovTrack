package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.MediaEntity;
import com.movtrack.List.ListType;
import com.movtrack.MediaBar.MediaBar;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@Route("")
public class MainView extends VerticalLayout implements BeforeEnterObserver {

    private Banner banner;
    private TextField txtSearchBar;
    private MediaBar watchlistBar;
    private MediaBar recommendedBar;

    @Autowired
    ListManager listManager;

    public MainView() {
        banner = new Banner();
        watchlistBar = new MediaBar("Watch List:");
        recommendedBar = new MediaBar("Recommendations");

        txtSearchBar = new TextField("Search");
        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());


        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        setHorizontalComponentAlignment(Alignment.CENTER, banner);


        // Add components to Layout
        add(banner, txtSearchBar, watchlistBar, recommendedBar);
    }

    private void search(){
        getUI().ifPresent(ui -> ui.navigate("search/" + txtSearchBar.getValue()));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        watchlistBar.clear();
        recommendedBar.clear();

        List<MediaEntity> watchlist = listManager.getAllByListType(ListType.WatchList);
        watchlistBar.show(watchlist);

        if(!watchlist.isEmpty()){
            int idx = new Random().nextInt(watchlist.size());
            MediaEntity media = watchlist.get(idx);

            // Get recommendations
            recommendedBar.showRecommended(media.getMediaID(), media.getMediaType());
        }

    }
}
