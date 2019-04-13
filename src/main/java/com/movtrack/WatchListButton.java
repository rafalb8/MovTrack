package com.movtrack;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

// Button to add/remove movie to WatchList
public class WatchListButton extends Button {
    private static final Icon icoAdd = new Icon(VaadinIcon.CHECK_SQUARE_O);
    private static final Icon icoRemove = new Icon(VaadinIcon.CHECK_SQUARE);

    public WatchListButton() {
        super("Add/Remove Movie", icoAdd);
        addClickListener(click -> clickEvent());
    }

    private void clickEvent(){
        setIcon(icoRemove);
    }
}
