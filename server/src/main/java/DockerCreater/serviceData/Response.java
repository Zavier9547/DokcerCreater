package DockerCreater.serviceData;

/**
 * Created by Jon on 2017/5/5.
 */
public class Response {
    private String status;
    private String info;

    public Response() {
        this.status = "success";
    }

    public Response(String info) {
        this.status = "success";
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
