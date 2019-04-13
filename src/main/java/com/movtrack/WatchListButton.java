package com.movtrack;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

// Button to add/remove movie to WatchList
public class WatchListButton extends Button {
    private final Icon icoAdd = new Icon(VaadinIcon.CHECK_SQUARE_O);
    private final Icon icoRemove = new Icon(VaadinIcon.CHECK_SQUARE);
    private boolean boolWatched;

    public WatchListButton() {
        super();

        // Add click listener
        addClickListener(click -> refresh());   // TODO: replace refresh with click event

        // Get status from watchlist
        boolWatched = false;

        refresh();
    }

    private void refresh(){
        if(!boolWatched){
            setIcon(icoAdd);
            setText("Add Movie");
        } else {
            setIcon(icoRemove);
            setText("Remove Movie");
        }

        boolWatched = !boolWatched;
    }
}
