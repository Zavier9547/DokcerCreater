package edu.nju.cs.DockerHelper.controllerData;

/**
 * Created by Jon on 2017/5/28.
 */
public class DependencyRequest {
    private String name;
    private String version;
    private String architecture;

    public DependencyRequest() {
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
}
