package com.movtrack.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

// Button to add/remove movie to WatchList
public class ListButton extends Button {
    private final Icon icoAdd = new Icon(VaadinIcon.CHECK_SQUARE_O);
    private final Icon icoRemove = new Icon(VaadinIcon.CHECK_SQUARE);
    private boolean boolAdded;
    private ListType listType;

    public ListButton(ListType type) {
        super();

        listType = type;

        // Add click listener
        addClickListener(click -> refresh());   // TODO: replace refresh with click event

        // Get status from watchlist
        boolAdded = false;

        refresh();
    }

    private void refresh(){
        if(!boolAdded){
            setIcon(icoAdd);
            if(listType != ListType.Watched) {
                setText("Add MovieEntity to " + listType.toString());
            } else {
                setText(listType.toString());
            }
        } else {
            setIcon(icoRemove);
            if(listType != ListType.Watched) {
                setText("Remove MovieEntity from " + listType.toString());
            } else {
                setText(listType.toString());
            }
        }

        boolAdded = !boolAdded;
    }
}
