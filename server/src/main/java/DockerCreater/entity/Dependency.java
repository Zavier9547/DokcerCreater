package DockerCreater.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2017/5/3.
 */
@Document(collection = "dependencies")
public class Dependency {

    @Id
    private String id;

    private String name;
    private String version;
    private String architecture;


    private List<AloneDependency> parents;

    public Dependency() {
    }

    public Dependency(String name) {
        this.name = name;
        this.architecture = "default";
        this.version = "1.0";
        parents=new ArrayList<AloneDependency>();
    }

    public Dependency(String name, List<AloneDependency> parents) {
        this.name = name;
        this.architecture = "default";
        this.version = "1.0";
        this.parents = parents;
    }

    public Dependency(String name, String version, String architecture) {
        this.name = name;
        this.architecture = architecture;
        this.version = version;
        parents=new ArrayList<AloneDependency>();
    }

    public Dependency(String name, String version, String architecture, List<AloneDependency> parents) {
        this.name = name;
        this.architecture = architecture;
        this.version = version;
        this.parents = parents;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<AloneDependency> getParents() {
        return parents;
    }

    public void setParents(List<AloneDependency> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return this.getName()+"_"+this.getVersion()+"_"+this.getArchitecture();
    }
}
