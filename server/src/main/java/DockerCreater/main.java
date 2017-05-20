package DockerCreater;


import DockerCreater.algorithm.DepTools;
import DockerCreater.entity.AloneDependency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/5/4.
 */


public class main {




    public static void main(String[] args) {
        List<AloneDependency> aList=new ArrayList<AloneDependency>();
        List<AloneDependency> bList=new ArrayList<AloneDependency>();

        aList.add(new AloneDependency("one"));
        aList.add(new AloneDependency("two"));
        aList.add(new AloneDependency("three"));
        aList.add(new AloneDependency("four"));

        bList.add(new AloneDependency("three"));
        bList.add(new AloneDependency("one"));
        bList.add(new AloneDependency("four"));

        System.out.println(DepTools.compareThem(aList,bList));
    }
}
