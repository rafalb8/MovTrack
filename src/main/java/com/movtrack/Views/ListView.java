package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.MediaEntity;
import com.movtrack.List.ListType;
import com.movtrack.MediaBar.MoviePoster;
import com.movtrack.TextLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("list")
public class ListView extends VerticalLayout implements HasUrlParameter<String> {

    private final Banner banner;
    private final VerticalLayout vlPosters;
    private final TextLayout txtListTitle;
    private ListType type;
    private Button btnSwitch;

    @Autowired
    ListManager listManager;

    public ListView(){
        banner = new Banner();
        vlPosters = new VerticalLayout();
        txtListTitle = new TextLayout("<b>Showing</b>");
        vlPosters.getElement().getStyle().set("background", "#E7EBEF");
        vlPosters.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        btnSwitch = new Button("WatchList");
        btnSwitch.addClickListener(click -> btnClickEvent());

        setHorizontalComponentAlignment(Alignment.CENTER, banner, txtListTitle);
        add(banner, txtListTitle, btnSwitch, vlPosters);
    }

    private void show(List<MediaEntity> mediaList){
        vlPosters.removeAll();
        if(mediaList.isEmpty()){
            TextLayout txt = new TextLayout("<b>Nothing to show</b>");
            txt.setBackground("#E7EBEF");
            vlPosters.add(txt);
            return;
        }

        HorizontalLayout hlPosters = null;
        for (int i = 0; i < mediaList.size(); i++) {
            if(i % 6 == 0){
                hlPosters = new HorizontalLayout();
                vlPosters.add(hlPosters);
            }

            hlPosters.add(new MoviePoster(mediaList.get(i).getMediaID(), mediaList.get(i).getMediaType()));
        }
    }

    private void btnClickEvent(){
        if(type == ListType.WatchList){
            getUI().ifPresent(ui -> ui.navigate("list/" + ListType.Watched.toString()));
        } else {
            getUI().ifPresent(ui -> ui.navigate("list/" + ListType.WatchList.toString()));
        }
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {

        if (parameter.equals( ListType.WatchList.toString() )){
            type = ListType.WatchList;
            txtListTitle.setText("<h1>Showing Watchlist</h1>");
            btnSwitch.setText("Watched");
        } else {
            type = ListType.Watched;
            txtListTitle.setText("<h1>Showing Watched list</h1>");
            btnSwitch.setText("Watchlist");
        }

        show(listManager.getAllByListType(type));
    }
}
