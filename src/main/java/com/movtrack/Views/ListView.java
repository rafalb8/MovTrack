package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.MediaEntity;
import com.movtrack.List.ListType;
import com.movtrack.MediaBar.MoviePoster;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("list")
public class ListView extends VerticalLayout implements HasUrlParameter<ListType> {

    private final Banner banner;
    private final VerticalLayout vlPosters;
    private final Grid<MoviePoster> posterGrid;

    @Autowired
    ListManager listManager;

    public ListView(){
        banner = new Banner();
        vlPosters = new VerticalLayout();
        vlPosters.getElement().getStyle().set("background", "#E7EBEF");
        posterGrid = new Grid<>();




        add(banner, vlPosters);
        vlPosters.add(posterGrid);
    }

    private void show(List<MediaEntity> mediaList){
        posterGrid.removeAllColumns();

        List<MoviePoster> posters = new ArrayList<>();

        for(MediaEntity media : mediaList){
            posters.add(new MoviePoster(media.getMediaID(), media.getMediaType()));
        }

        posterGrid.setItems(posters);
    }

    @Override
    public void setParameter(BeforeEvent event, ListType parameter) {
        show(listManager.getAllByListType(parameter));
    }
}
