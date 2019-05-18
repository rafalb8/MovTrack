package com.movtrack.Test;

import com.movtrack.List.DB.Media;
import com.movtrack.List.DB.ListManager;
import com.movtrack.List.ListType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    ListManager list;

    @Test
    void drop(){
        Media m = new Media(550, "movie", ListType.WatchList);
        list.save(m);

        assertTrue(list.getAll().size() > 0, "Size > 0");

        list.deleteByID(m.getID());

        assertTrue(list.getAll().size() == 0, "Size == 0");
    }

    @Test
    void insert(){
        int size = list.getAll().size();

        Media m = new Media(550, "movie", ListType.WatchList);

        list.save(m);

        assertTrue(list.getAll().size() - size > 0, "Size didn't change");
        assertEquals(list.getAll().get(0).getMediaID(), 550, "Incorrect MovieID");
        list.delete(m);
    }

    @Test
    void getByListType(){
        Media m1 = new Media(100, "movie", ListType.WatchList);
        Media m2 = new Media(101, "movie", ListType.Watched);
        Media m3 = new Media(102, "movie", ListType.WatchList);

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
        Media m1 = new Media(100, "tv", ListType.WatchList);
        Media m2 = new Media(101, "tv", ListType.Watched);
        Media m3 = new Media(102, "movie", ListType.WatchList);

        list.save(m1);
        list.save(m2);
        list.save(m3);

        assertEquals(list.getAllByMediaType("tv").size(), 2, "Incorrect tv count");

        list.delete(m1);
        list.delete(m2);
        list.delete(m3);
    }

}
