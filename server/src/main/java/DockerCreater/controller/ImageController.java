package DockerCreater.controller;

import DockerCreater.algorithm.DepTools;
import DockerCreater.entity.AloneDependency;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.repository.ImageRepository;
import DockerCreater.serviceData.ImageRequest;
import DockerCreater.serviceData.Response;
import DockerCreater.serviceData.TableResponse;
import DockerCreater.serviceData.TreeResponse;
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

    //给出一个依赖查找指定镜像依赖是否冲突
    @RequestMapping(value = "/images/{id}", method = RequestMethod.POST,produces = "application/json")
    public Response checkImageCrash(@RequestBody AloneDependency aloneDependency, @PathVariable String id){
        List<AloneDependency> list = new ArrayList<>();
        for ( AloneDependency temp : imageRepository.findOne(id).getDependency()){
            list.addAll(DepTools.findAllDep(dependencyRepository,temp));
        }
        Response re = new Response();
        for(AloneDependency aDep: list){
            if (aDep.getArtifactId().equals(aloneDependency.getArtifactId())){
                re.setStatus("crashed");
                re.setInfo(aDep.toString());
            }
        }
        return re;
    }

    @RequestMapping(value = "/images/{id}/tree", method = RequestMethod.GET, produces = "application/json")
    public List<TreeResponse> getTree(@PathVariable String id){
        List<TreeResponse> list = new ArrayList<>();
        for (AloneDependency dep : imageRepository.findOne(id).getDependency()) {
            TreeResponse temp = new TreeResponse(dep.toString());
            temp.setChildren(DepTools.setChildrenTree(dependencyRepository,dep));
            list.add(temp);
        }

        return list;
    }

    @RequestMapping(value = "/images/{id}/table", method = RequestMethod.GET, produces = "application/json")
    public List<TableResponse> getTable(@PathVariable String id){
        List<TableResponse> list = new ArrayList<>();
        for (AloneDependency dep : imageRepository.findOne(id).getDependency()) {
            TableResponse temp = new TableResponse(dep.toString());
            list.add(temp);
            DepTools.setTable(dependencyRepository,dep,list,0);
        }

        return list;
    }

}
