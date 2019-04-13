package com.movtrack.Views;

import com.movtrack.Banner;
import com.movtrack.RestClient.RestClient;
import com.movtrack.SearchResults;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

// View showing search results
@Route("search")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class SearchView extends VerticalLayout implements HasUrlParameter<String> {

    private RestClient restClient;
    private Banner banner;
    private TextField txtSearchBar;
    private SearchResults srList;


    public SearchView() {
        restClient = RestClient.getInstance();
        banner = new Banner();
        txtSearchBar = new TextField();
        srList = new SearchResults();

        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        setHorizontalComponentAlignment(Alignment.CENTER, banner);

        txtSearchBar.addKeyDownListener(Key.ENTER, event -> search());

        add(banner, txtSearchBar, srList);

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
