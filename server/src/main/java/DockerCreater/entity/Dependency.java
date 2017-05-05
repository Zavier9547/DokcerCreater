package DockerCreater.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Jon on 2017/5/3.
 */
@Document(collection = "dependencies")
public class Dependency {

    @Id
    private String id;

    private String groupId;
    private String artifactId;
    private String version;

    private List<AloneDependency> parents;

    public Dependency() {
    }

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public Dependency(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = "1.0";
    }

    public Dependency(String groupId, String artifactId, String version, List<AloneDependency> parents) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.parents = parents;
    }

    public Dependency(String groupId, String artifactId, List<AloneDependency> parents) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = "1.0";
        this.parents = parents;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
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
}
