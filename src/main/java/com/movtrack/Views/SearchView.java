package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.MovieEntry;
import com.movtrack.RestClient.RestClient;
import com.movtrack.RestClient.Search;
import com.movtrack.RestClient.SearchResult;
import com.vaadin.flow.component.Key;
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
    private Banner banner;
    private TextField txtSearchBar;
    private VerticalLayout vlSearchResults;


    public SearchView() {
        restClient = RestClient.getInstance();
        banner = new Banner();
        txtSearchBar = new TextField();
        vlSearchResults = new VerticalLayout();

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        setHorizontalComponentAlignment(Alignment.CENTER, banner);

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

    public void refreshList(SearchResult result){
        vlSearchResults.removeAll();
        for(Search search: result.getSearch()){
            MovieEntry movie = new MovieEntry(search);
            vlSearchResults.add(movie);
        }
    }
}
