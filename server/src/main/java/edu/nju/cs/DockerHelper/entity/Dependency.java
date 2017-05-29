package edu.nju.cs.DockerHelper.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Jon on 2017/5/27.
 */
@Document(collection = "dependencies")
public class Dependency implements Comparable<Dependency>{
    @Id
    private String id;

    private String name;
    private String version;
    private String architecture;

    private List<ConDependency> depends;
    private List<ConDependency> conflicts;

    public Dependency() {
    }

    public Dependency(String name) {
        this.name = name;
        this.version = "1.0";
        this.architecture = "i386";
    }

    public Dependency(String name, String version) {
        this.name = name;
        this.version = version;
        this.architecture = "i386";
    }

    public Dependency(String name, String version, String architecture) {
        this.name = name;
        this.version = version;
        this.architecture = architecture;
    }

    public Dependency(String name, List<ConDependency> depends) {
        this.name = name;
        this.version = "1.0";
        this.architecture = "i386";
        this.depends = depends;
    }

    public Dependency(String name, List<ConDependency> depends, List<ConDependency> conflicts) {
        this.name = name;
        this.version = "1.0";
        this.architecture = "i386";
        this.depends = depends;
        this.conflicts = conflicts;
    }

    public Dependency(String name, String version, String architecture, List<ConDependency> depends) {
        this.name = name;
        this.version = version;
        this.architecture = architecture;
        this.depends = depends;
    }

    public Dependency(String name, String version, String architecture, List<ConDependency> depends, List<ConDependency> conflicts) {
        this.name = name;
        this.version = version;
        this.architecture = architecture;
        this.depends = depends;
        this.conflicts = conflicts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public List<ConDependency> getDepends() {
        return depends;
    }

    public void setDepends(List<ConDependency> depends) {
        this.depends = depends;
    }

    public List<ConDependency> getConflicts() {
        return conflicts;
    }

    public void setConflicts(List<ConDependency> conflicts) {
        this.conflicts = conflicts;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass()!=obj.getClass())
            return false;
        Dependency temp=(Dependency)obj;
        if (name.equals(temp.getName())
                &&version.equals(temp.getVersion())
                &&architecture.equals(temp.getArchitecture()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return name+"_"+version+"_"+architecture;
    }

    @Override
    public int compareTo(Dependency o) {
        return name.compareTo(o.getName());
    }
}
