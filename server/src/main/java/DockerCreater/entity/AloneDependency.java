package DockerCreater.entity;

/**
 * Created by Jon on 2017/5/4.
 */
public class AloneDependency {
    private String groupId;
    private String artifactId;
    private String version;

    public AloneDependency() {
    }

    public AloneDependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public AloneDependency(String artifactId, String groupId) {
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.version = "1.0";
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
}
