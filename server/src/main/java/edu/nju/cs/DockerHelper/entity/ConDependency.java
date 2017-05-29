package edu.nju.cs.DockerHelper.entity;

/**
 * Created by Jon on 2017/5/27.
 */
public class ConDependency {
    private String name;
    private String verCondition;

    public ConDependency() {
    }

    public ConDependency(String name) {
        this.name = name;
    }

    public ConDependency(String name, String verCondition) {
        this.name = name;
        this.verCondition = verCondition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerCondition() {
        return verCondition;
    }

    public void setVerCondition(String verCondition) {
        this.verCondition = verCondition;
    }

    @Override
    public String toString() {
        if (verCondition==null)
            return name;
        return name+" ("+verCondition+")";
    }
}
