package com.movtrack;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

// Horizontal list showing movies to watch
public class WatchlistBar extends HorizontalLayout {

    private Html lblText;

    public WatchlistBar() {
        super();

        lblText = new Html("<b>Nothing to show</b>");

        add(lblText);

        // Set gray background
        getElement().getStyle().set("background", "#E7EBEF");
    }
}
