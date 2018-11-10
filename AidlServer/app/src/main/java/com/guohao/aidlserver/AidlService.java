package com.guohao.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.guohao.aidl.server.IBookManager;

public class AidlService extends Service {
    public AidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        // 返回接口 IBookManager.Stub 实例
        return aidlServer;
    }

    // 声明并实现 接口的stub对象
    IBookManager.Stub aidlServer = new IBookManager.Stub(){

        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.d("guohao","add...num1 = " + num1 + " ,num2 = " + num2 );
            return ( num1 + num2 );
        }

        @Override
        public int wrong_add(int num1, int num2) throws RemoteException {
            Log.d("guohao","wrong_add...num1 = " + num1 + " ,num2 = " + num2 );
            return ( num1 + num2 );
        }

        @Override
        public int more_fun() throws RemoteException {
            Log.d("guohao","more_fun..." );
            return 0;
        }

    };
    // end 声明并实现 接口的stub对象
}
