package com.javalive09.sample.project.raventech;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by peter on 2016/12/6.
 */

public class VSTService extends AccessibilityService {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        if(eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("选集");

            if (list != null) {
                for (AccessibilityNodeInfo n : list) {
                    n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }

        }else if(eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
//            new shellTap().swipe();
//            new shellTap().tap();
//            new shellTap().am();
//            new shellTap().ping();
//            new shellTap().du();
//            new shellTap().reboot();
            new shellTap().cp();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void clickPlay(AccessibilityEvent event) {
        AccessibilityNodeInfo source = event.getSource();
        if (source == null) {
            return;
        }

        AccessibilityNodeInfo labelNode = source.getChild(0);

        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();

//            AccessibilityNodeInfo nodeInfo0 = nodeInfo.getChild(0);
//            AccessibilityNodeInfo nodeInfo00 = nodeInfo0.getChild(0);
//            AccessibilityNodeInfo nodeInfo01 = nodeInfo0.getChild(1);
//
//            AccessibilityNodeInfo nodeInfo1 = nodeInfo.getChild(1);
//            AccessibilityNodeInfo nodeInfo10 = nodeInfo1.getChild(0);
//            AccessibilityNodeInfo nodeInfo11 = nodeInfo1.getChild(1);
//
//            AccessibilityNodeInfo nodeInfo2 = nodeInfo.getChild(2);
//            AccessibilityNodeInfo nodeInfo20 = nodeInfo1.getChild(0);
//            AccessibilityNodeInfo nodeInfo21 = nodeInfo1.getChild(1);

        AccessibilityNodeInfo nodeInfo3 = nodeInfo.getChild(3);
        AccessibilityNodeInfo nodeInfo30 = nodeInfo3.getChild(0);
        AccessibilityNodeInfo nodeInfo300 = nodeInfo30.getChild(0);
        AccessibilityNodeInfo nodeInfo301 = nodeInfo30.getChild(1);
        AccessibilityNodeInfo nodeInfo302 = nodeInfo30.getChild(2);


//            String txt = "第4集：地球脉动第2季第4集：沙漠";
        String txt = "第4集 : 地球脉动第2季第4集：沙漠";
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(txt);
        if (list != null) {
            for (AccessibilityNodeInfo n : list) {
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);

            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
