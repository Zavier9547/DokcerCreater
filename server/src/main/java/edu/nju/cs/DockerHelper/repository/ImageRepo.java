package edu.nju.cs.DockerHelper.repository;

import edu.nju.cs.DockerHelper.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Jon on 2017/5/28.
 */
@Repository
public interface ImageRepo extends MongoRepository<Image, String>{
    @Query(value = "{name:?0,version:?1}")
    Image findByCoordinate(String name,String version);
}
