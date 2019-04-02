package com.movtrack.Views;

import com.movtrack.RestClient.RestClient;
import com.movtrack.SearchResults;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

// View showing search results
@Route("search")
public class SearchView extends VerticalLayout implements HasUrlParameter<String> {

    private RestClient restClient;
    private HorizontalLayout hlHeader;
    private Label lblTitle;
    private TextField txtSearchBar;
    private SearchResults srList;


    public SearchView() {
        restClient = RestClient.getInstance();
        hlHeader = new HorizontalLayout();
        lblTitle = new Label("MovTrack");
        txtSearchBar = new TextField();
        srList = new SearchResults();

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());


        hlHeader.add(lblTitle);
        add(hlHeader, txtSearchBar, srList);

    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        srList.refreshList(restClient.searchMovieByTitle(parameter));
        txtSearchBar.setValue(parameter);
    }

    private void search(){
        if(txtSearchBar.getValue() != null){
            srList.refreshList(restClient.searchMovieByTitle(txtSearchBar.getValue()));
        }
    }
}
