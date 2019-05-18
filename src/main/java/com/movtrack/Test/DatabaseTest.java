package com.movtrack.Test;

import com.movtrack.List.DB.MovieEntity;
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
        MovieEntity m = new MovieEntity(550, ListType.WatchList);
        list.save(m);

        assertTrue(list.getAll().size() > 0, "Size > 0");

        list.deleteByID(m.getID());

        assertTrue(list.getAll().size() == 0, "Size == 0");
    }

    @Test
    void insert(){
        int size = list.getAll().size();

        MovieEntity m = new MovieEntity(550, ListType.WatchList);

        list.save(m);

        assertTrue(list.getAll().size() - size > 0, "Size didn't change");
        assertEquals(list.getAll().get(0).getMovieID(), 550, "Incorrect MovieID");
        list.delete(m);
    }

    @Test
    void getByType(){
        MovieEntity m1 = new MovieEntity(100, ListType.WatchList);
        MovieEntity m2 = new MovieEntity(101, ListType.Watched);
        MovieEntity m3 = new MovieEntity(102, ListType.WatchList);

        list.save(m1);
        list.save(m2);
        list.save(m3);

        assertEquals(list.getAllByType(ListType.WatchList).size(), 2, "Incorrect movie count");

        list.delete(m1);
        list.delete(m2);
        list.delete(m3);
    }

}
