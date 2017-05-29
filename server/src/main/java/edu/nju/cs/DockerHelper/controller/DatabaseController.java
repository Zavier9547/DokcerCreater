package edu.nju.cs.DockerHelper.controller;

import edu.nju.cs.DockerHelper.controllerData.*;
import edu.nju.cs.DockerHelper.entity.ConDependency;
import edu.nju.cs.DockerHelper.entity.Dependency;
import edu.nju.cs.DockerHelper.entity.Image;
import edu.nju.cs.DockerHelper.repository.DependencyRepo;
import edu.nju.cs.DockerHelper.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/5/28.
 */
@CrossOrigin
@RestController
public class DatabaseController {

    @Autowired
    private ImageRepo iR;

    @Autowired
    private DependencyRepo dR;

    //找到镜像id
    @RequestMapping(value = "/images/id", method = RequestMethod.POST, produces = "application/json")
    public Response getImageId(@RequestBody ImageRequest r) {
        return (new Response(iR.findByCoordinate(r.getName(), r.getVersion()).getId()));
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET, produces = "application/json")
    public List<ImageResponse> getAllImages() {
        List<ImageResponse> list = new ArrayList<>();
        for (Image temp: iR.findAll()){
            ImageResponse iR=new ImageResponse(temp.getName(),temp.getVersion());
            List<String> sL = new ArrayList<>();
            for (Dependency d : temp.getDependencies()){
                sL.add(d.toString());
            }
            iR.setDependencies(String.join("<br>",sL));
            list.add(iR);
        }
        return list;
    }

    @RequestMapping(value = "/dep", method = RequestMethod.GET,produces = "application/json")
    public List<DepResponse> getAllDeps(){
        List<DepResponse> list = new ArrayList<>();
        for (Dependency temp: dR.findAll()){
            DepResponse dR = new DepResponse(temp.getName(),temp.getVersion(),temp.getArchitecture());
            List<String> sL = new ArrayList<>();
            if (temp.getDepends()!=null) {
                for (ConDependency cD : temp.getDepends()) {
                    sL.add(cD.toString());
                }
                dR.setDependencies(String.join("<br>", sL));
            }
            list.add(dR);
        }
        return list;
    }

    @RequestMapping(value = "/dep", method = RequestMethod.POST,produces = "application/json")
    public Response checkDep(@RequestBody DependencyRequest depR){
        Response re = new Response();
        if (dR.findByCoordinate(depR.getName(),depR.getVersion(),depR.getArchitecture())!=null){
            return re;
        }
        re.setStatus("fail");
        re.setInfo("依赖不存在，请检查输入");
        return re;
    }

}
