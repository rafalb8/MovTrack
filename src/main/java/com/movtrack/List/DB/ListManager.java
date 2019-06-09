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

    public List<MediaEntity> getAll(){
        List<MediaEntity> movies = new ArrayList<>();
        mediaRepo.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public MediaEntity getByID(int id){
        return mediaRepo.findById(id).get();
    }

    public List<MediaEntity> getAllByMediaID(int mediaID){
        return mediaRepo.findByMediaID(mediaID);
    }

    public List<MediaEntity> getAllByListType(ListType type){
        return mediaRepo.findByListType(type);
    }

    public List<MediaEntity> getAllByMediaType(String type){
        return mediaRepo.findByMediaType(type);
    }

    public int save(MediaEntity movie){
        MediaEntity saved = mediaRepo.save(movie);
        return saved.getID();
    }

    public void deleteByID(int id){
        mediaRepo.deleteById(id);
    }

    public void delete(MediaEntity movie){
        mediaRepo.delete(movie);
    }
}
