package DockerCreater.algorithm;

import DockerCreater.entity.AloneDependency;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.serviceData.TableResponse;
import DockerCreater.serviceData.TreeResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/5/5.
 */

public class DepTools {

    public static List<AloneDependency> findAllDep(DependencyRepository dependencyRepository, AloneDependency dependency) {
        List<AloneDependency> list = new ArrayList<>();
        list.add(dependency);

        String aId, gId, ver;

        aId = dependency.getArtifactId();
        gId = dependency.getGroupId();
        ver = dependency.getVersion();
        List<AloneDependency> queue = dependencyRepository.findByThreePoints(aId, gId, ver).getParents();
        while (!(queue.isEmpty())) {
            int i = queue.size() - 1;
            aId = queue.get(i).getArtifactId();
            gId = queue.get(i).getGroupId();
            ver = queue.get(i).getVersion();

            list.add(queue.get(i));

            queue.remove(i);

            queue.addAll(dependencyRepository.findByThreePoints(aId, gId, ver).getParents());

        }
        return list;
    }

    public static List<TreeResponse> setChildrenTree(DependencyRepository dependencyRepository, AloneDependency aloneDependency) {
        List<TreeResponse> list = new ArrayList<>();
        String aId = aloneDependency.getArtifactId();
        String gId = aloneDependency.getGroupId();
        String ver = aloneDependency.getVersion();
        List<AloneDependency> aList = dependencyRepository.findByThreePoints(aId, gId, ver).getParents();
        if (aList.isEmpty())
            return list;
        else {
            for (AloneDependency temp:aList){
                TreeResponse treeResponse = new TreeResponse(temp.toString());
                treeResponse.setChildren(setChildrenTree(dependencyRepository,temp));
                list.add(treeResponse);
            }
        }
        return list;
    }

    private static String preStr="+";

    public static void setTable(DependencyRepository dependencyRepository, AloneDependency aloneDependency, List<TableResponse> list,int depth){

        depth=depth+1;

        String aId = aloneDependency.getArtifactId();
        String gId = aloneDependency.getGroupId();
        String ver = aloneDependency.getVersion();
        List<AloneDependency> aList = dependencyRepository.findByThreePoints(aId, gId, ver).getParents();
        if (aList.isEmpty()) return;
        else{
            for(AloneDependency dep:aList){
                String str="";
                for (int i=0;i<=depth-1;i++){
                    str+=preStr;
                }
                list.add(new TableResponse(str+dep.toString()));
                setTable(dependencyRepository,dep,list,depth);
            }
        }

    }
}
