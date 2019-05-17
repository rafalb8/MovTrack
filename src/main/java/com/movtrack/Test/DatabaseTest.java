package com.movtrack.Test;

import com.movtrack.List.DB.Movie;
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
    void insert(){
        int size = list.getAll().size();

        list.save(new Movie(100, 550, ListType.WatchList));

        assertTrue(list.getAll().size() - size > 0, "Size didn't change");
        assertEquals(list.getByID(100).getMovieID(), 550, "Incorrect MovieID");
    }

    @Test
    void drop(){
        list.save(new Movie(101, 550, ListType.WatchList));

        assertTrue(list.getAll().size() > 0, "Size  == 0");

        list.deleteByID(101);

        assertTrue(list.getAll().size() == 0, "Size  != 0");
    }
}
