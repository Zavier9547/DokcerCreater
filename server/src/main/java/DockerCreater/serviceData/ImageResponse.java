package DockerCreater.serviceData;

/**
 * Created by Jon on 2017/5/20.
 */
public class ImageResponse {
    private String name;
    private String version;
    private String dependencies;

    public ImageResponse() {
    }

    public ImageResponse(String name, String version) {
        this.name = name;
        this.version = version;
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

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }
}
