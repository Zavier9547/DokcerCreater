package DockerCreater.algorithm;

import DockerCreater.entity.AloneDependency;
import DockerCreater.repository.DependencyRepository;
import DockerCreater.serviceData.TableResponse;
import DockerCreater.serviceData.TreeResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jon on 2017/5/5.
 */

public class DepTools {

    public static List<AloneDependency> findAllDep(DependencyRepository dependencyRepository, AloneDependency dependency) {
        List<AloneDependency> list = new ArrayList<AloneDependency>();
        list.add(dependency);

        String name, ver, arc;

        arc = dependency.getArchitecture();
        name = dependency.getName();
        ver = dependency.getVersion();
        List<AloneDependency> queue = dependencyRepository.findByThreePoints(name, ver, arc).getParents();
        while (!(queue.isEmpty())) {
            int i = queue.size() - 1;
            arc = queue.get(i).getArchitecture();
            name = queue.get(i).getName();
            ver = queue.get(i).getVersion();

            list.add(queue.get(i));

            queue.remove(i);

            queue.addAll(dependencyRepository.findByThreePoints(name, ver, arc).getParents());

        }
        return list;
    }

    public static List<TreeResponse> setChildrenTree(DependencyRepository dependencyRepository, AloneDependency aloneDependency) {
        List<TreeResponse> list = new ArrayList<TreeResponse>();
        String arc = aloneDependency.getArchitecture();
        String name = aloneDependency.getName();
        String ver = aloneDependency.getVersion();
        List<AloneDependency> aList = dependencyRepository.findByThreePoints(name, ver, arc).getParents();
        if (aList.isEmpty())
            return list;
        else {
            for (AloneDependency temp : aList) {
                TreeResponse treeResponse = new TreeResponse(temp.toString());
                treeResponse.setChildren(setChildrenTree(dependencyRepository, temp));
                list.add(treeResponse);
            }
        }
        return list;
    }

    private static String preStr = "+";

    public static void setTable(DependencyRepository dependencyRepository, AloneDependency aloneDependency, List<TableResponse> list, int depth) {

        depth = depth + 1;

        String arc = aloneDependency.getArchitecture();
        String name = aloneDependency.getName();
        String ver = aloneDependency.getVersion();
        List<AloneDependency> aList = dependencyRepository.findByThreePoints(name, ver, arc).getParents();
        if (aList.isEmpty()) return;
        else {
            for (AloneDependency dep : aList) {
                String str = "";
                for (int i = 0; i <= depth - 1; i++) {
                    str += preStr;
                }
                list.add(new TableResponse(str + dep.toString()));
                setTable(dependencyRepository, dep, list, depth);
            }
        }

    }

    public static int compareThem(List<AloneDependency> aList, List<AloneDependency> bList) {
        Collections.sort(aList);
        Collections.sort(bList);

        Iterator i = aList.iterator();
        for (Iterator j=bList.iterator();j.hasNext();) {
            AloneDependency jTemp =(AloneDependency) j.next();
            while (i.hasNext()) {
                AloneDependency iTemp = (AloneDependency) i.next();
                if (iTemp.equals(jTemp)) {
                    if (!(i.hasNext()) && !(j.hasNext()))
                        return bList.size();
                    else
                        break;
                }
            }
            if (!(i.hasNext()))
                return -1;
        }
        return bList.size();
    }
}
