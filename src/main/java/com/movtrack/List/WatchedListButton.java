package com.movtrack.List;

import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.MediaEntity;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

// Button to add/remove movie to WatchList
@SpringComponent
@UIScope
public class WatchedListButton extends ListButton {
    @Autowired
    ListManager listManager;

    public WatchedListButton() {
        super(ListType.Watched);

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
            // Create entity
            MediaEntity media = new MediaEntity(mediaID, mediaType, listType);

            // Add Entity to DB
            ID = listManager.save(media);

            // Show notification to user
            Notification.show("Added to Watchlist");
        } else {
            // Remove entry in DB
            listManager.deleteByID(ID);

            // Show notification to user
            Notification.show("Removed from Watchlist");
        }

        boolAdded = !boolAdded;
        refresh();
    }
}
