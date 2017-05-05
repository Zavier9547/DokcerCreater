package DockerCreater;

import DockerCreater.repository.DependencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jon on 2017/4/16.
 */

@CrossOrigin
@RestController
public class MyRestController {

    @Autowired
    private DependencyRepository dependencyRepository;

    private String return_str;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(){
        return_str= "Test success!";
        return return_str;
    }

    @RequestMapping(value = "/dep",method = RequestMethod.GET)
    public String hello2(){
        return_str= dependencyRepository.findByArtifactId("scala-library").getVersion();
        return return_str;
    }

}
