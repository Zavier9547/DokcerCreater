package DockerCreater.serviceData;

/**
 * Created by Jon on 2017/5/7.
 */
public class TableResponse {
    private String name;

    public TableResponse() {
    }

    public TableResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
