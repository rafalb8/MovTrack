package com.movtrack.List.DB;

import com.movtrack.List.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListManager {

    @Autowired
    ListRepository movieRepository;

    public List<MovieEntity> getAll(){
        List<MovieEntity> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public MovieEntity getByID(int id){
        return movieRepository.findById(id).get();
    }

    public List<MovieEntity> getAllByType(ListType type){
        List<MovieEntity> movies = new ArrayList<>();
        movieRepository.findAll().forEach( movie -> {if(movie.getType() == type)movies.add(movie);});
        return movies;
    }

    public void save(MovieEntity movie){
        movieRepository.saveAndFlush(movie);
    }

    public void deleteByID(int id){
        movieRepository.deleteById(id);
    }

    public void delete(MovieEntity movie){
        movieRepository.delete(movie);
    }
}
