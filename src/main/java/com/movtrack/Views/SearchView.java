package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.SearchedMovieEntry;
import com.movtrack.RestClient.RestClient;
import com.movtrack.RestClient.Result;
import com.movtrack.RestClient.Search;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

// View showing search results
@Route("search")
public class SearchView extends VerticalLayout implements HasUrlParameter<String> {

    private RestClient restClient;
    private Banner banner;
    private TextField txtSearchBar;
    private VerticalLayout vlSearchResults;
    private Label lblEnd;


    public SearchView() {
        restClient = RestClient.getInstance();
        banner = new Banner();
        txtSearchBar = new TextField();
        vlSearchResults = new VerticalLayout();
        lblEnd = new Label();

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        vlSearchResults.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        setHorizontalComponentAlignment(Alignment.CENTER, banner);
        vlSearchResults.setHorizontalComponentAlignment(Alignment.CENTER, lblEnd);

        add(banner, txtSearchBar, vlSearchResults);

        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        refreshList(restClient.searchMovieByTitle(parameter));
        txtSearchBar.setValue(parameter);
    }

    private void search(){
        if(txtSearchBar.getValue() != null){
            refreshList(restClient.searchMovieByTitle(txtSearchBar.getValue()));
        }
    }

    public void refreshList(Search search){
        vlSearchResults.removeAll();

        if(search.getResults() == null){
            lblEnd.setText("No results found");
            vlSearchResults.add(lblEnd);
            return;
        }

        for(Result result: search.getResults()){
            SearchedMovieEntry movie = new SearchedMovieEntry(result);
            vlSearchResults.add(movie);
        }

        lblEnd.setText("End of results");
        vlSearchResults.add(lblEnd);
    }
}
