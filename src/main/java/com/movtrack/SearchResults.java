package com.movtrack;

import com.movtrack.RestClient.Search;
import com.movtrack.RestClient.SearchResult;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SearchResults extends VerticalLayout {

    public void refreshList(SearchResult result){
        removeAll();
        for(Search search: result.getSearch()){
            MovieEntry movie = new MovieEntry(search);
            add(movie);

        }
    }
}
