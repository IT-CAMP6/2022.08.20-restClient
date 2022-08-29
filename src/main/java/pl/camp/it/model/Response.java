package pl.camp.it.model;

public class Response {
    private String info;
    private int code;

    public Response(String info, int code) {
        this.info = info;
        this.code = code;
    }

    public Response() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
