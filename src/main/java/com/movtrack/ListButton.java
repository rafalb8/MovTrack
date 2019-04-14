package com.movtrack;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

// Button to add/remove movie to WatchList
public class ListButton extends Button {
    private final Icon icoAdd = new Icon(VaadinIcon.CHECK_SQUARE_O);
    private final Icon icoRemove = new Icon(VaadinIcon.CHECK_SQUARE);
    private boolean boolAdded;
    private String strList;

    public ListButton(String listName) {
        super();

        strList = listName;

        // Add click listener
        addClickListener(click -> refresh());   // TODO: replace refresh with click event

        // Get status from watchlist
        boolAdded = false;

        refresh();
    }

    private void refresh(){
        if(!boolAdded){
            setIcon(icoAdd);
            setText("Add Movie to " + strList);
        } else {
            setIcon(icoRemove);
            setText("Remove Movie from " + strList);
        }

        boolAdded = !boolAdded;
    }
}
