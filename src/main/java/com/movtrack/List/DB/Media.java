package com.movtrack.List.DB;

import com.movtrack.List.ListType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Media {
    @Id
    @GeneratedValue
    private int ID;
    private int mediaID;
    private String mediaType;
    private ListType listType;

    public Media(){ mediaID = 0; listType = null; mediaType = "movie"; }

    public Media(int mediaID, String mediaType, ListType type){
        this.mediaID = mediaID;
        listType = type;
        this.mediaType = mediaType;
    }

    public int getID(){ return ID; }
    public long getMediaID(){ return mediaID; }
    public ListType getListType(){ return listType; }
    public String getMediaType(){ return mediaType; }

}
