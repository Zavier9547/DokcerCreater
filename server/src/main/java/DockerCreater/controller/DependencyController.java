package DockerCreater.controller;

import DockerCreater.entity.AloneDependency;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.serviceData.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jon on 2017/5/7.
 */

@CrossOrigin
@RestController
public class DependencyController {

    @Autowired
    private DependencyRepository dependencyRepository;

    //检验依赖是否存在
    @RequestMapping(value = "/dep", method = RequestMethod.POST,produces = "application/json")
    public Response checkDep(@RequestBody AloneDependency aloneDependency){
        String aId=aloneDependency.getArtifactId();
        String gId=aloneDependency.getGroupId();
        String ver=aloneDependency.getVersion();
        if(dependencyRepository.findByThreePoints(aId,gId,ver)==null){
            Response re =new Response();
            re.setStatus("fail");
            return re;
        }
        return new Response();
    }
}
