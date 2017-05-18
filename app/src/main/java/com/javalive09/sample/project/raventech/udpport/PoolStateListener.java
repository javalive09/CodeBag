package com.javalive09.sample.project.raventech.udpport;

import android.support.annotation.NonNull;

/**
 * tcp连接池增加或删除连接
 */
public interface PoolStateListener {
    void onAdd(@NonNull Box box);

    void onRemove(@NonNull Box box);

    void updateBoxName(Box box);
}