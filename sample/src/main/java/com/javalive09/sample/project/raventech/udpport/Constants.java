package com.javalive09.sample.project.raventech.udpport;


/**
 * Created by wuxiaodong on 16/7/19.
 */
public interface Constants {
    boolean TEST = false;



    String SP_KEY_AUDIO_STATE = "connect_audio";
    String SP_KEY_TV_STATE = "connect_tv";
    String SP_KEY_CONTROLLER_STATE = "connect_controller";
    String SP_ALREADY_SET = "already_set";

    int STATE_UNDO = 0;
    int STATE_DOING = 1;
    int STATE_DONE = 2;

    String AUDIO_CABLE_CONNECTED = "audio_cable_connected";  //记录盒子是否连接上了音响
    String ONBOARD_CONNECTED = "onboard_connected";

    String CONTROLLER_STATE = "controller_state";

    String AUDIO_WIRELESS_CONNECTED = "audio_wireless_connected";
    String TV_CONNECTED = "tv_connected";

    //opt begin
    String STATE_SUBSCRIBE = "box_statSubscribe";
    //opt end


    String DEVICE_KIND_NAME = "deviceKindName";
    String DEVICE_TYPE_INDEX = "deviceTypeIndex";

    String LANGUAGE_FLAG = "language_flag";

    String PUSH_TOKEN = "push_token";

    String LATEST_BOX = "latest_box";
}
