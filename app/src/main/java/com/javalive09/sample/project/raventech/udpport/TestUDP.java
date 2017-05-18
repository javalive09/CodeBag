package com.javalive09.sample.project.raventech.udpport;

import com.javalive09.codebag.Entry;

/**
 * Created by peter on 2017/1/13.
 */

public class TestUDP extends Entry {

    public void startUDP() {
        new UDPNettyReceiver().start();
    }

}
