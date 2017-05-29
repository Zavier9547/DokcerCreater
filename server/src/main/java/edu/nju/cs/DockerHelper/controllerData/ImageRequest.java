package edu.nju.cs.DockerHelper.controllerData;

/**
 * Created by Jon on 2017/5/28.
 */
public class ImageRequest {
    private String name;
    private String version;

    public ImageRequest() {
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
}
