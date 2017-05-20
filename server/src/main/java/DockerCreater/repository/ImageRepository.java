package DockerCreater.repository;

import DockerCreater.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jon on 2017/5/5.
 */

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {

    @Query(value = "{name:?0,version:?1}")
    Image findByTwoPoints(String name,String version);

    List<Image> findByType(String type);
}
