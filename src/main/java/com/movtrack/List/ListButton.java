package com.movtrack.List;

import com.movtrack.List.DB.Media;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.List;

// Button to add/remove movie to WatchList
public class ListButton extends Button {
    private final Icon icoAdd = new Icon(VaadinIcon.CHECK_SQUARE_O);
    private final Icon icoRemove = new Icon(VaadinIcon.CHECK_SQUARE);
    protected boolean boolAdded;

    // Media Entity values
    protected int ID;
    protected int mediaID;
    protected String mediaType;
    protected final ListType listType;

    public ListButton(ListType type){
        super();
        boolAdded = false;
        listType = type;
        refresh();
    }

    protected void findMedia(List<Media> mediaList){
        // Get status from list
        for(Media m : mediaList){
            if(m.getListType() == listType && m.getMediaType().equals(mediaType)){
                boolAdded = true;
                ID = m.getID();
                break;
            }
        }
    }

    // Set Icon and text
    protected void refresh(){
        if(!boolAdded){
            setIcon(icoAdd);
            setText(listType.toString());
        } else {
            setIcon(icoRemove);
            setText(listType.toString());
        }
    }

}
