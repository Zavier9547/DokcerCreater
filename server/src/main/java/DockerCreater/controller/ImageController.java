package DockerCreater.controller;

import DockerCreater.algorithm.DepTools;
import DockerCreater.entity.AloneDependency;
import DockerCreater.entity.Image;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.repository.ImageRepository;
import DockerCreater.serviceData.*;
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

    @RequestMapping(value = "/images", method = RequestMethod.GET, produces = "application/json")
    public List<ImageResponse> getAllImages() {
        List<ImageResponse> list = new ArrayList<ImageResponse>();
        for (Image temp: imageRepository.findAll()){
            ImageResponse iR=new ImageResponse(temp.getName(),temp.getVersion());
            List<String> sL = new ArrayList<String>();
            for (AloneDependency aD : temp.getDependency()){
                sL.add(aD.toString());
            }
            iR.setDependencies(String.join("<br>",sL));
            list.add(iR);
        }
        return list;
    }

    //找到镜像id
    @RequestMapping(value = "/images", method = RequestMethod.POST, produces = "application/json")
    public Response getImageId(@RequestBody ImageRequest imageRequest) {
        return (new Response(imageRepository.findByTwoPoints(imageRequest.getName(), imageRequest.getVersion()).getId()));
    }

    //给出一个依赖查找指定镜像依赖是否冲突
    @RequestMapping(value = "/images/{id}", method = RequestMethod.POST, produces = "application/json")
    public Response checkImageCrash(@RequestBody AloneDependency aloneDependency, @PathVariable String id) {
        List<AloneDependency> list = new ArrayList<AloneDependency>();
        for (AloneDependency temp : imageRepository.findOne(id).getDependency()) {
            list.addAll(DepTools.findAllDep(dependencyRepository, temp));
        }
        Response re = new Response();
        for (AloneDependency aDep : list) {
            if (aDep.getName().equals(aloneDependency.getName())) {
                re.setStatus("crashed");
                re.setInfo(aDep.toString());
            }
        }
        return re;
    }

    @RequestMapping(value = "/images/{id}/tree", method = RequestMethod.GET, produces = "application/json")
    public List<TreeResponse> getTree(@PathVariable String id) {
        List<TreeResponse> list = new ArrayList<TreeResponse>();
        for (AloneDependency dep : imageRepository.findOne(id).getDependency()) {
            TreeResponse temp = new TreeResponse(dep.toString());
            temp.setChildren(DepTools.setChildrenTree(dependencyRepository, dep));
            list.add(temp);
        }

        return list;
    }

    @RequestMapping(value = "/images/{id}/table", method = RequestMethod.GET, produces = "application/json")
    public List<TableResponse> getTable(@PathVariable String id) {
        List<TableResponse> list = new ArrayList<TableResponse>();
        for (AloneDependency dep : imageRepository.findOne(id).getDependency()) {
            TableResponse temp = new TableResponse(dep.toString());
            list.add(temp);
            DepTools.setTable(dependencyRepository, dep, list, 0);
        }

        return list;
    }

    @RequestMapping(value = "/images/{id}/recommend", method = RequestMethod.POST, produces = "application/json")
    public Response getRecommend(@RequestBody List<AloneDependency> list, @PathVariable String id) {
        List<AloneDependency> aList = new ArrayList<AloneDependency>();

        for (AloneDependency temp:list){
            aList.addAll(DepTools.findAllDep(dependencyRepository,temp));
        }

        int oldLength = aList.size();

        Image base = imageRepository.findOne(id);
        for (AloneDependency temp:base.getDependency()){
            aList.addAll(DepTools.findAllDep(dependencyRepository,temp));
        }

        int newLength = aList.size();

        List<Image> queue = imageRepository.findByType(base.getType());

        queue.remove(base);

        Response re = new Response();
        re.setStatus("fail");

        int record = newLength-oldLength;
        for (Image temp : queue) {
            List<AloneDependency> bList=new ArrayList<AloneDependency>();
            for (AloneDependency aDep:temp.getDependency()){
                bList.addAll(DepTools.findAllDep(dependencyRepository,aDep));
            }
            int i=DepTools.compareThem(aList,bList);
            if (record < i) {
                record=i;
                re.setStatus("success");
                re.setInfo(temp.toString());
            }
        }
        return re;
    }

}
