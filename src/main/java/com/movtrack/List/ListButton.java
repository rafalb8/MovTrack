package com.movtrack.List;

import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.Media;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Button to add/remove movie to WatchList
public class ListButton extends Button {
    private final Icon icoAdd = new Icon(VaadinIcon.CHECK_SQUARE_O);
    private final Icon icoRemove = new Icon(VaadinIcon.CHECK_SQUARE);
    private boolean boolAdded;
    private Media media;

    @Autowired
    ListManager listManager;

    public ListButton(ListType type){
        super();

        boolAdded = false;
        media = new Media(0, "movie", type);

        // Add clickEvent listener
        addClickListener(click -> clickEvent());

        refresh();
    }

    public ListButton(ListType type, String mediaType, int mediaID){
        super();

        boolAdded = false;
        media = new Media(mediaID, mediaType, type);

        // Add clickEvent listener
        addClickListener(click -> clickEvent());

        setMediaID(mediaID);
    }

    public void setMediaID(int mediaID){
        // Get status from watchlist
        List<Media> mediaList = listManager.getAllByMediaID(mediaID);

        for(Media m : mediaList){
            if(m.getListType() == media.getListType()){
                boolAdded = true;
                media = m;
                break;
            }
        }

        refresh();
    }

    // Set Icon and text
    private void refresh(){
        if(!boolAdded){
            setIcon(icoAdd);
            if(media.getListType() != ListType.Watched) {
                setText("Add Media to " + media.getListType().toString());
            } else {
                setText(media.getListType().toString());
            }
        } else {
            setIcon(icoRemove);
            if(media.getListType() != ListType.Watched) {
                setText("Remove Media from " + media.getListType().toString());
            } else {
                setText(media.getListType().toString());
            }
        }
    }

    private void clickEvent(){
        if(!boolAdded){
            listManager.save(media);
        } else {
            listManager.delete(media);
        }

        refresh();
        boolAdded = !boolAdded;
    }
}
