package DockerCreater.controller;

import DockerCreater.repository.ImageRepository;
import DockerCreater.serviceData.ImageRequest;
import DockerCreater.serviceData.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jon on 2017/4/16.
 */

@CrossOrigin
@RestController
public class ImageController {

    private String return_str;

    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/images", method = RequestMethod.POST,produces = "application/json")
    public Response getImageId(@RequestBody ImageRequest imageRequest){
        return (new Response(imageRepository.findByTwoPoints(imageRequest.getName(),imageRequest.getVersion()).getId()));
    }

//    @RequestMapping(value = "/images/{id}", method = RequestMethod.POST,produces = "application/json")
//    public Response getImageId(@RequestBody AloneDependency aloneDependency,@PathVariable String id){
//
//    }

}
