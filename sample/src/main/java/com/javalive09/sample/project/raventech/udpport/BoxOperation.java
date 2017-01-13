package com.javalive09.sample.project.raventech.udpport;


/**
 * 我们向盒子发送的事件
 * <p>
 * {
 * "opt":""
 * "info":""
 * }
 */
public class BoxOperation<T> {
    /* public String getFrom() {
         return from;
     }

     public void setFrom(String from) {
         this.from = from;
     }
 */
    public transient String sendTo;
    //!!!This is com.google.gson.JsonObject
    private T info;

    /*@SerializedName("from")
    private String from;*/
    private String opt;

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

}
