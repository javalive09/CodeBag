package com.javalive09.sample.project.raventech.udpport;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * 盒子发送过来的事件
 * {
 * "resp":"xxx"
 * "info":"xxx"
 * }
 */
public class BoxResponse {
    public String resp;
    public JsonObject info;

    //发送给谁
    public transient String sendTo;

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public JsonObject getInfo() {
        return info;
    }

    public void setInfo(JsonObject info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "BoxResponse{" +
                "resp='" + resp + '\'' +
                ", info=" + info +
                ", sendTo='" + sendTo + '\'' +
                '}';
    }
}
