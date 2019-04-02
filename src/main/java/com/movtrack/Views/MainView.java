package com.movtrack.Views;

import com.movtrack.WatchlistBar;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class, variant = Lumo.DARK)
@Route("")
@PWA(name = "MovTrack - Track watched Movies and TV Shows", shortName = "MovTrack")
public class MainView extends VerticalLayout {

    private HorizontalLayout hlHeader;
    private WatchlistBar watchlistBar;
    private Label lblTitle;
    private TextField txtSearchBar;

    public MainView() {
        hlHeader = new HorizontalLayout();
        watchlistBar = new WatchlistBar();
        lblTitle = new Label("MovTrack");
        txtSearchBar = new TextField("Search");


        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        // Add components to Layout
        hlHeader.add(lblTitle);
        add(hlHeader, txtSearchBar, watchlistBar);

        // lblTitle style
        lblTitle.setWidthFull();
        lblTitle.getElement().getStyle().set("text-align", "center");
        lblTitle.getElement().getStyle().set("font-size", "40px");

        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());
    }

    private void search(){
        getUI().get().navigate("search/" + txtSearchBar.getValue());
    }
}
