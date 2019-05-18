package com.movtrack.List.DB;

import com.movtrack.List.ListType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ListRepository extends CrudRepository<MediaEntity, Integer> {
    List<MediaEntity> findByListType(ListType listType);
    List<MediaEntity> findByMediaID(int mediaID);
    List<MediaEntity> findByMediaType(String mediaType);
}
