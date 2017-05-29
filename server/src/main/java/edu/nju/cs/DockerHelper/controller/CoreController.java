package edu.nju.cs.DockerHelper.controller;

import edu.nju.cs.DockerHelper.controllerData.DependencyRequest;
import edu.nju.cs.DockerHelper.controllerData.Response;
import edu.nju.cs.DockerHelper.entity.ConDependency;
import edu.nju.cs.DockerHelper.entity.Dependency;
import edu.nju.cs.DockerHelper.entity.Image;
import edu.nju.cs.DockerHelper.repository.DependencyRepo;
import edu.nju.cs.DockerHelper.repository.ImageRepo;
import edu.nju.cs.DockerHelper.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jon on 2017/5/28.
 */
@CrossOrigin
@RestController
public class CoreController {

    @Autowired
    private ImageRepo iR;

    @Autowired
    private DependencyRepo dR;

    //检测一组依赖是否和指定镜像存在依赖冲突
    @RequestMapping(value = "/images/{id}", method = RequestMethod.POST, produces = "application/json")
    public Response checkImageConflict(@RequestBody List<DependencyRequest> rList, @PathVariable String id) {
        Response re = new Response();
        re.setStatus("fail");

        List<Dependency> aList = new ArrayList<>();
        for (DependencyRequest temp : rList) {
            aList.add(dR.findByCoordinate(temp.getName(), temp.getVersion(), temp.getArchitecture()));
        }
        List<Dependency> bList = iR.findOne(id).getDependencies();
        HashMap<String, String> bHash = new HashMap<>();

        for (Dependency b : bList) {
            bHash.put(b.getName(), b.getVersion());
        }
        for (Dependency a : aList) {
            if (bHash.get(a.getName()) != null) {
                re.addInfo("-----发现重名依赖：-----\n");
                re.addInfo("--基础镜像中： " + a.getName() + "_" + bHash.get(a.getName()) + "_" + a.getArchitecture() + "\n");
                re.addInfo("--新增依赖组中： " + a.toString() + "\n");
                re.addInfo("--引用依赖项：\n");
                List<String> sList = new ArrayList<>();
                sList.addAll(Tools.findReDepends(a.getName(), aList));
                sList.addAll(Tools.findReDepends(a.getName(), bList));
                for (String s : sList) {
                    re.addInfo("  " + s + "\n");
                }
            }
        }
        HashMap<String, String> allHash = new HashMap<>();
        allHash.putAll(bHash);
        for (Dependency d : aList) {
            allHash.put(d.getName(), d.getVersion());
        }
        List<Dependency> allList = new ArrayList<>();
        allList.addAll(aList);
        allList.addAll(bList);
        for (Dependency d : allList) {
            if (d.getConflicts() != null) {
                for (ConDependency temp : d.getConflicts()) {
                    if (Tools.judgeCondition(allHash.get(temp.getName()), temp.getVerCondition())) {
                        re.addInfo("-----声明依赖冲突：-----\n");
                        re.addInfo("--冲突依赖项： " + temp.getName() + "_" + allHash.get(temp.getName()) + "_" + d.getArchitecture() + "\n");
                        re.addInfo("--声明冲突依赖项： " + d.toString() + "\n");
                        re.addInfo("--冲突依赖条件： " + temp.toString() + "\n");
                    }
                }
            }
        }
        for (Dependency d : aList) {
            if (d.getDepends() != null) {
                for (ConDependency temp : d.getDepends()) {
                    if (!(Tools.judgeCondition(allHash.get(d.getName()), temp.getVerCondition()))) {
                        re.addInfo("-----条件依赖不满足：-----\n");
                        re.addInfo("--冲突依赖项： " + temp.getName() + "_" + allHash.get(temp.getName()) + "_" + d.getArchitecture() + "\n");
                        re.addInfo("--不满足依赖项： " + d.toString() + "\n");
                        re.addInfo("--依赖条件： " + temp.toString() + "\n");
                    }
                }
            }
        }
        if (re.getInfo().equals("")) {
            re.setStatus("success");
            re.setInfo("未检测出依赖冲突");
            return re;
        }
        return re;
    }

    @RequestMapping(value = "/images/{id}/recommend", method = RequestMethod.POST, produces = "application/json")
    public Response getRecommend(@RequestBody List<DependencyRequest> dList, @PathVariable String id) {
        Response re = new Response();
        re.setStatus("fail");
        re.setInfo("不存在更优的基础镜像");

        List<Dependency> aList = new ArrayList<>();
        for (DependencyRequest temp : dList) {
            aList.add(dR.findByCoordinate(temp.getName(), temp.getVersion(), temp.getArchitecture()));
        }

        List<Dependency> list = new ArrayList<>();
        list.addAll(aList);
        list.addAll(iR.findOne(id).getDependencies());

        int record = iR.findOne(id).getDependencies().size();
        for (Image i : iR.findAll()) {
            List<Dependency> tList = i.getDependencies();
            if (record >= tList.size()) continue;
            int temp = Tools.compareThem(list, tList);
            if (temp > record) {
                re.setStatus("success");
                re.setInfo("存在更优的基础镜像供使用：" + i.toString());
                record = temp;
            }
        }
        return re;
    }

}
