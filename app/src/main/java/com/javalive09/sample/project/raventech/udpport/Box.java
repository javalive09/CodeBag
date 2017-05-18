package com.javalive09.sample.project.raventech.udpport;

import android.support.annotation.Nullable;

public class Box {
    @Nullable
    public String imUid;
    public String uuid;
    public String name;
    public String ip;
    public int port;
    public String ver;
    public int buildVer;//盒子版本号
    public int lastBuildVer;//上一次盒子绑定时的版本号
    public String romVer;
    public String lastRomVer;
    //是否打开唤醒 1 on  0 off
    public int voiceWakeUp;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        Box that = (Box) o;
//        return uuid != null && that.uuid != null && uuid.equals(that.uuid);
//    }
}
