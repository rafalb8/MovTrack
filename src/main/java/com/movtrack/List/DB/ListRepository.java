package com.movtrack.List.DB;

import com.movtrack.List.ListType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ListRepository extends CrudRepository<Media, Integer> {
    List<Media> findByListType(ListType listType);
    List<Media> findByMediaID(long mediaID);
    List<Media> findByMediaType(String mediaType);
}
