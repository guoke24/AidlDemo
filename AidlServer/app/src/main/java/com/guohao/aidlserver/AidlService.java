package com.guohao.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.guohao.aidl.server.IBookManager;

public class AidlService extends Service {
    public AidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return aidlServer;
    }

    IBookManager.Stub aidlServer = new IBookManager.Stub(){

        @Override
        public int add(int num1, int num2) throws RemoteException {
            return ( num1 + num2 );
        }
    };

}
