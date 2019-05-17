package com.movtrack.List.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListManager {

    @Autowired
    ListRepository movieRepository;

    public List<Movie> getAll(){
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public Movie getByID(int id){
        return movieRepository.findById(id).get();
    }

    public void save(Movie movie){
        movieRepository.save(movie);
    }

    public void deleteByID(int id){
        movieRepository.deleteById(id);
    }
}
