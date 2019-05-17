package com.movtrack.List.DB;

import com.movtrack.List.ListType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private int ID;
    private long MovieID;
    private ListType Type;

    public Movie(){ ID = 0; MovieID = 0; Type = null; };

    public Movie(int id, long movieID, ListType type){
        ID = id; MovieID = movieID; Type = type;
    }

    public int getID(){ return ID; }
    public long getMovieID(){ return MovieID; }

}
