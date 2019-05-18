package com.movtrack.List.DB;

import com.movtrack.List.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListManager {

    @Autowired
    ListRepository mediaRepo;

    public List<Media> getAll(){
        List<Media> movies = new ArrayList<>();
        mediaRepo.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public Media getByID(int id){
        return mediaRepo.findById(id).get();
    }

    public List<Media> getAllByMediaID(int mediaID){
        return mediaRepo.findByMediaID(mediaID);
    }

    public List<Media> getAllByListType(ListType type){
        return mediaRepo.findByListType(type);
    }

    public List<Media> getAllByMediaType(String type){
        return mediaRepo.findByMediaType(type);
    }

    public void save(Media movie){
        mediaRepo.save(movie);
    }

    public void deleteByID(int id){
        mediaRepo.deleteById(id);
    }

    public void delete(Media movie){
        mediaRepo.delete(movie);
    }
}
