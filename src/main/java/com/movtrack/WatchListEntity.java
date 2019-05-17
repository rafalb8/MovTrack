package com.movtrack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WatchListEntity {
    @Id
    @GeneratedValue
    private long ID;
    private long MovieID;

}
