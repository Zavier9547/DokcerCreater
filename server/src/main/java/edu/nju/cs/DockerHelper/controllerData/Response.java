package edu.nju.cs.DockerHelper.controllerData;

/**
 * Created by Jon on 2017/5/28.
 */
public class Response {
    private String status;
    private String info;

    public Response() {
        this.status = "success";
        this.info = "";
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

    public void addInfo(String info){
        this.info = this.info + info;
    }
}
