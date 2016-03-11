package org.kungfu.core;

/**
 * Created by yangfq on 15/11/19.
 */
public class Result {

    private int code;
    private String description;
    private Object detail;

    public Result() {
    }

    public Result(int code, String description, Object detail) {
        this.code = code;
        this.description = description;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
