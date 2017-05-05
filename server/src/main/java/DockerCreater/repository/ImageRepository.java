package DockerCreater.repository;

import DockerCreater.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jon on 2017/5/5.
 */

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {
    Image findByName(String name);
}
