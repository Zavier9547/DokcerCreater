package DockerCreater.serviceData;

/**
 * Created by Jon on 2017/5/5.
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
