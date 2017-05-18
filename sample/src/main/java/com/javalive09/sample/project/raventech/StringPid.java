package com.javalive09.sample.project.raventech;

import android.text.TextUtils;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

/**
 * Created by peter on 2016/12/12.
 */

public class StringPid extends Entry {

    public void getName() {
        String str = "u0_a59    11142 3583  1740208 157280 SyS_epoll_ f709a1cc S net.myvst.v2:vstmain";
        String[] ids = str.split(" ");
        showTxt(str);
        for(String id: ids) {
            if(!TextUtils.isEmpty(id) && TextUtils.isDigitsOnly(id)) {
                Log.e(id);
                break;
            }

        }
    }


}
