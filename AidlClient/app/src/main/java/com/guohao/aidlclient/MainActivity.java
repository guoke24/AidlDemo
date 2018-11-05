package com.guohao.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.guohao.aidl.server.IBookManager;

public class MainActivity extends AppCompatActivity {

    private IBookManager mIBookManager;

    //创建连接
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取远程服务Binder的代理
            Log.d("guohao","onServiceConnected");
            mIBookManager = IBookManager.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("guohao","onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean bindBookService(){
        Intent intent = new Intent();
        intent.setAction("com.guohao.aidl.server.aidlService");
        intent.setPackage("com.guohao.aidlserver");
        startService(intent);
        return bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void test(View v){
        Log.d("guohao","test2");
        boolean b = bindBookService();
        if(b){
            Toast.makeText(this,"bind succ",Toast.LENGTH_SHORT).show();
        }
    }

    public void test2(View v){
        Log.d("guohao","test2");
        try {
            int res = mIBookManager.add(1,1);
            Log.d("guohao","res = " + res);
            Toast.makeText(this,"mIBookManager.add(1,1)=" + res,Toast.LENGTH_SHORT).show();

        } catch (RemoteException e) {
            Log.d("guohao","e = " + e.toString());
            e.printStackTrace();
        }

    }

}
