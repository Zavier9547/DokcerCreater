package edu.nju.cs.DockerHelper.repository;

import edu.nju.cs.DockerHelper.entity.Dependency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Jon on 2017/5/28.
 */
@Repository
public interface DependencyRepo extends MongoRepository<Dependency,String>{
    @Query(value = "{name:?0,version:?1,architecture:?2}")
    Dependency findByCoordinate(String name,String version,String architecture);
}
