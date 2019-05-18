package com.movtrack.List;

import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.MediaEntity;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

// Button to add/remove movie to WatchList
@SpringComponent
public class WatchListButton extends ListButton {
    @Autowired
    ListManager listManager;

    public WatchListButton() {
        super(ListType.WatchList);

        // Add clickEvent listener
        addClickListener(click -> clickEvent());
    }

    public void init(String mediaType, int mediaID){
        this.mediaID = mediaID;
        this.mediaType = mediaType;
        this.boolAdded = false;

        findMedia(listManager.getAllByMediaID(mediaID));
        refresh();
    }

    private void clickEvent(){
        if(!boolAdded){
            listManager.save(new MediaEntity(mediaID, mediaType, listType));
        } else {
            listManager.deleteByID(ID);
        }

        boolAdded = !boolAdded;
        refresh();
    }
}
