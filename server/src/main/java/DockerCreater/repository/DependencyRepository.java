package DockerCreater.repository;

import DockerCreater.entity.Dependency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Jon on 2017/5/3.
 */
@Repository
public interface DependencyRepository extends MongoRepository<Dependency,String> {

    @Query(value = "{name:?0,version:?1,architecture:?2}")
    Dependency findByThreePoints(String name,String version,String architecture);
}
