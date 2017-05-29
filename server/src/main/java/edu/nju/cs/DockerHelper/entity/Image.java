package edu.nju.cs.DockerHelper.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Jon on 2017/5/27.
 */
@Document(collection = "images")
public class Image {
    @Id
    private String id;

    private String name;
    private String version;

    private String architecture;

    private List<Dependency> dependencies;

    public Image() {
    }

    public Image(String name, List<Dependency> dependencies) {
        this.name = name;
        this.version = "1.0";
        this.architecture = "i386";
        this.dependencies = dependencies;
    }

    public Image(String name, String version, String architecture, List<Dependency> dependencies) {
        this.name = name;
        this.version = version;
        this.architecture = architecture;
        this.dependencies = dependencies;
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

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public String toString() {
        return name+"_"+version;
    }
}
