package com.codebag.code.mycode.function.aidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 *注意每次调用方法都会inflate 一个新的Invoke 对象。 所以共用属性用静态的方式。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends MyCode {

    private IaidlService mService;//获取binder引用，调用接口。 跨进程无法直接获取service对象引用

    private boolean isBound;
    
    public Invoke(InovkedViewActivity act) {
        super(act);
    }

    @Entry
    public void bindService() {
        Intent intent = new Intent(getActivity(), AIDLService.class);
        isBound = getActivity().bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        Log.i("peter", "isBound = " + isBound);
    }
    
    @Entry
    public void unBindService() {
        if(isBound) {
            isBound = false;
            getActivity().unbindService(mConnection);
            if(mService != null) {
                try {
                    mService.unregiestCallback(cb);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Entry
    public void service_doSomethind() {
        try {
            mService.doSomethind();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    @Entry
    public void service_getData() {
        try {
            String str = mService.getData();
            Log.i("peter", "service_getData str = " + str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IaidlService.Stub.asInterface(service);
            Log.i("peter", "onServiceConnected = " + mService);
            try {
                mService.regiestCallback(cb);
                mService.doSomethind();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("peter", "onServiceDisconnected = " + name);
            mService = null;
        }
    };

    private IaidlClient cb = new IaidlClient.Stub() {
        @Override
        public void finish() throws RemoteException {
            Log.i("peter", "invoke iaidlClient finish()");
        }
    };

}
