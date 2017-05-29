package edu.nju.cs.DockerHelper;

import edu.nju.cs.DockerHelper.entity.Dependency;
import edu.nju.cs.DockerHelper.tool.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/5/27.
 */
public class Main {
    public static void main(String[] args){
        List<Dependency> aList=new ArrayList<>();
        List<Dependency> bList=new ArrayList<>();

        aList.add(new Dependency("2"));
        aList.add(new Dependency("1"));

        bList.add(new Dependency("1"));
        bList.add(new Dependency("2"));

        System.out.println(Tools.compareThem(aList,bList));
    }
}
