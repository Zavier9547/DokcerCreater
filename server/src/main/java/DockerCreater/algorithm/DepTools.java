package DockerCreater.algorithm;

import DockerCreater.entity.AloneDependency;
import DockerCreater.repository.DependencyRepository;

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
}
