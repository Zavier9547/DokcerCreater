package DockerCreater.controller;

import DockerCreater.algorithm.DepTools;
import DockerCreater.entity.AloneDependency;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.repository.ImageRepository;
import DockerCreater.serviceData.ImageRequest;
import DockerCreater.serviceData.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/4/16.
 */

@CrossOrigin
@RestController
public class ImageController {

    private String return_str;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DependencyRepository dependencyRepository;

    //找到镜像id
    @RequestMapping(value = "/images", method = RequestMethod.POST,produces = "application/json")
    public Response getImageId(@RequestBody ImageRequest imageRequest){
        return (new Response(imageRepository.findByTwoPoints(imageRequest.getName(),imageRequest.getVersion()).getId()));
    }

    @RequestMapping(value = "/images/{id}", method = RequestMethod.POST,produces = "application/json")
    public Response getImageId(@RequestBody AloneDependency aloneDependency, @PathVariable String id){
        List<AloneDependency> list = new ArrayList<>();
        for ( AloneDependency temp : imageRepository.findOne(id).getDependency()){
            list.addAll(DepTools.findAllDep(dependencyRepository,temp));
        }
        Response re = new Response();
        if(list.contains(aloneDependency)){
            re.setStatus("crashed");
            re.setInfo(aloneDependency.toString());
        }
        return re;
    }

}
