package com.movtrack.Test;

import com.movtrack.List.DB.ListManager;
import com.movtrack.List.DB.MediaEntity;
import com.movtrack.List.ListType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    ListManager list;

    @Test
    void drop(){
        MediaEntity m = new MediaEntity(550, "movie", ListType.WatchList);
        list.save(m);

        assertTrue(list.getAll().size() > 0, "Size > 0");

        list.deleteByID(m.getID());

        assertTrue(list.getAll().size() == 0, "Size == 0");
    }

    @Test
    void insert(){
        int size = list.getAll().size();

        MediaEntity m = new MediaEntity(550, "movie", ListType.WatchList);

        list.save(m);

        assertTrue(list.getAll().size() - size > 0, "Size didn't change");
        assertEquals(list.getAll().get(0).getMediaID(), 550, "Incorrect MovieID");
        list.delete(m);
    }

    @Test
    void getByListType(){
        MediaEntity m1 = new MediaEntity(100, "movie", ListType.WatchList);
        MediaEntity m2 = new MediaEntity(101, "movie", ListType.Watched);
        MediaEntity m3 = new MediaEntity(102, "movie", ListType.WatchList);

        list.save(m1);
        list.save(m2);
        list.save(m3);

        assertEquals(list.getAllByListType(ListType.WatchList).size(), 2, "Incorrect movie count");

        list.delete(m1);
        list.delete(m2);
        list.delete(m3);
    }

    @Test
    void getByMediaType(){
        MediaEntity m1 = new MediaEntity(100, "tv", ListType.WatchList);
        MediaEntity m2 = new MediaEntity(101, "tv", ListType.Watched);
        MediaEntity m3 = new MediaEntity(102, "movie", ListType.WatchList);

        list.save(m1);
        list.save(m2);
        list.save(m3);

        assertEquals(list.getAllByMediaType("tv").size(), 2, "Incorrect tv count");

        list.delete(m1);
        list.delete(m2);
        list.delete(m3);
    }

}
