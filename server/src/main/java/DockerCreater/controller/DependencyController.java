package DockerCreater.controller;

import DockerCreater.algorithm.DepTools;
import DockerCreater.entity.AloneDependency;
import DockerCreater.entity.Dependency;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.serviceData.DepResponse;
import DockerCreater.serviceData.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/5/7.
 */

@CrossOrigin
@RestController
public class DependencyController {

    @Autowired
    private DependencyRepository dependencyRepository;

    @RequestMapping(value = "/dep", method = RequestMethod.GET,produces = "application/json")
    public List<DepResponse> getAllDeps(){
        List<DepResponse> list = new ArrayList<DepResponse>();
        for (Dependency temp: dependencyRepository.findAll()){
            DepResponse dR = new DepResponse(temp.getName(),temp.getVersion(),temp.getArchitecture());
            List<String> sL = new ArrayList<String>();
            for (AloneDependency aD : temp.getParents()){
                sL.add(aD.toString());
            }
            dR.setDependencies(String.join("<br>",sL));
            list.add(dR);
        }
        return list;
    }

    //检验依赖是否存在,若存在则返回依赖id
    @RequestMapping(value = "/dep", method = RequestMethod.POST,produces = "application/json")
    public Response checkDep(@RequestBody AloneDependency aloneDependency){
        String name=aloneDependency.getName();
        String ver=aloneDependency.getVersion();
        String arc=aloneDependency.getArchitecture();
        Response re =new Response();
        Dependency dependency = dependencyRepository.findByThreePoints(name,ver,arc);
        if(dependency==null){
            re.setStatus("fail");
            return re;
        }
        re.setInfo(dependency.getId());
        return re;
    }

    //检验给出依赖组和目标依赖是否冲突
    @RequestMapping(value = "/dep/{id}",method = RequestMethod.POST,produces = "application/json")
    public Response checkDepsCrash(@RequestBody List<AloneDependency> list,@PathVariable String id){
        List<AloneDependency> queue = new ArrayList<AloneDependency>();
        for (AloneDependency temp :list){
            queue.addAll(DepTools.findAllDep(dependencyRepository,temp));
        }
        Dependency target = dependencyRepository.findOne(id);

        Response re = new Response();
        for (AloneDependency temp: queue){
            if (temp.getName().equals(target.getName())){
                re.setStatus("crashed");
                re.setInfo(temp.toString());
                return re;
            }
        }

        return re;
    }
}
