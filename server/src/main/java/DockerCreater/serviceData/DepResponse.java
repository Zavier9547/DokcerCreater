package DockerCreater.serviceData;

/**
 * Created by Jon on 2017/5/20.
 */
public class DepResponse {
    private String name;
    private String version;
    private String architecture;
    private String dependencies;

    public DepResponse() {
    }

    public DepResponse(String name, String version, String architecture) {
        this.name = name;
        this.version = version;
        this.architecture = architecture;
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

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }
}
