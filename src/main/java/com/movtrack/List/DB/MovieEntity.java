package com.movtrack.List.DB;

import com.movtrack.List.ListType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MovieEntity {
    @Id
    @GeneratedValue
    private int ID;
    private long MovieID;
    private ListType Type;

    public MovieEntity(){ MovieID = 0; Type = null; };

    public MovieEntity(long movieID, ListType type){
        MovieID = movieID; Type = type;
    }

    public int getID(){ return ID; }
    public long getMovieID(){ return MovieID; }
    public ListType getType(){ return Type; }

}
