package DockerCreater.serviceData;

import java.util.List;

/**
 * Created by Jon on 2017/5/7.
 */
public class TreeResponse {
    private String name;
    private List<TreeResponse> children;

    public TreeResponse() {
    }

    public TreeResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeResponse> getChildren() {
        return children;
    }

    public void setChildren(List<TreeResponse> children) {
        this.children = children;
    }
}
