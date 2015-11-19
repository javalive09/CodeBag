package com.codebag.code.mycode.function.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AIDLService extends Service {

    private IaidlClient clientCb;
    
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    
    private final IaidlService.Stub mBinder = new IaidlService.Stub() {

        @Override
        public void doSomethind() throws RemoteException {
            Log.i("peter", "aidlservice doSomethind");
            if(clientCb != null) {
                clientCb.finish();
            }
        }

        @Override
        public String getData() throws RemoteException {
            return "aidlservice data";
        }

        @Override
        public void regiestCallback(IaidlClient cb) throws RemoteException {
            clientCb = cb;
            Log.i("peter", "regiestCallback :" + cb);
        }

        @Override
        public void unregiestCallback(IaidlClient cb) throws RemoteException {
            Log.i("peter", "unregiestCallback :" + cb);
            clientCb = null;
        }
        
        
        
    };

}
