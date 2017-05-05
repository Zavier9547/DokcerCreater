package DockerCreater.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Jon on 2017/5/4.
 */

@Document(collection = "images")
public class Image {

    @Id
    private String id;

    private String name;
    private String type;
    private String version;

    private List<AloneDependency> dependency;

    public Image() {
    }

    public Image(String name, String type, String version, List<AloneDependency> dependency) {
        this.name = name;
        this.type = type;
        this.version = version;
        this.dependency = dependency;
    }

    public Image(String name, List<AloneDependency> dependency) {
        this.name = name;
        this.dependency = dependency;
        this.type="type_one";
        this.version="1.0";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<AloneDependency> getDependency() {
        return dependency;
    }

    public void setDependency(List<AloneDependency> dependency) {
        this.dependency = dependency;
    }
}
