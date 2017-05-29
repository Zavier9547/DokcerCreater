package edu.nju.cs.DockerHelper.tool;

import edu.nju.cs.DockerHelper.entity.ConDependency;
import edu.nju.cs.DockerHelper.entity.Dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jon on 2017/5/28.
 */
public class Tools {

    //判断重名的依赖是否满足对应的条件依赖
    public static boolean judgeCondition(String a, String b){
        if (a==null) return false;
        //如果没有规定条件依赖的版本条件，则肯定满足
        if (b==null) return true;
        String[] str = b.split(" ");
        switch (str[0]){
            case "<<":
                return str[1].compareTo(a)>0;
            case "<=":
                return str[1].compareTo(a)>=0;
            case "=":
                return str[1].equals(a);
            case ">=":
                return a.compareTo(str[1])>=0;
            case ">>":
                return a.compareTo(str[1])>0;
            default:
                System.out.println(b+" is wrong!");
        }
        return false;
    }

    //查出指定依赖的所有的引用项
    public static List<String> findReDepends(String d,List<Dependency> dList){
        List<String> re=new ArrayList<>();
        for (Dependency i:dList){
            if (i.getDepends()!=null) {
                for (ConDependency j : i.getDepends()) {
                    if (d.equals(j.getName()))
                        re.add(i.toString() + " : " + j.toString());
                }
            }
        }
        return re;
    }

    //子集判断
    public static int compareThem(List<Dependency> aList, List<Dependency> bList) {
        Collections.sort(aList);
        Collections.sort(bList);

        Iterator i = aList.iterator();
        for (Iterator j=bList.iterator();j.hasNext();) {
            Dependency jTemp =(Dependency) j.next();
            while (i.hasNext()) {
                Dependency iTemp = (Dependency) i.next();
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
