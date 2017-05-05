package DockerCreater;

import DockerCreater.algorithm.DepTools;
import DockerCreater.entity.AloneDependency;
import DockerCreater.entity.Dependency;
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
        return_str= DepTools.findAllDep(dependencyRepository,new AloneDependency("twist","twist_one","1.0")).toString();
        return return_str;
    }

}
