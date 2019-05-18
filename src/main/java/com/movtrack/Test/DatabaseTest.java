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

}
