package com.movtrack.List.DB;

import com.movtrack.List.ListType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MediaEntity {
    @Id
    @GeneratedValue
    private int ID;
    private int mediaID;
    private String mediaType;
    private ListType listType;

    public MediaEntity(){ mediaID = 0; listType = null; mediaType = "movie"; }

    public MediaEntity(int mediaID, String mediaType, ListType type){
        this.mediaID = mediaID;
        listType = type;
        this.mediaType = mediaType;
    }

    public int getID(){ return ID; }
    public int getMediaID(){ return mediaID; }
    public ListType getListType(){ return listType; }
    public String getMediaType(){ return mediaType; }

}
