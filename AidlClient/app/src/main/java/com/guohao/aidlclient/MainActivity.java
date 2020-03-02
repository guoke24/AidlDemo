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

    // 创建跟服务端的连接
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取远程服务Binder的代理：service
            Log.d("guohao","onServiceConnected");
            // service 转化为 本地的 mIBookManager 接口
            // asInterface 返回的是代理类 IBookManager.Stub.Proxy
            // 通过 IBookManager 接口依赖
            mIBookManager = IBookManager.Stub.asInterface(service);
            // 注意：本地的 IBookManager 接口，只定义了两个函数，
            // 而服务端的  IBookManager 接口，定义了三个函数，
            // 但是，这里不会因为函数不一致而报错
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("guohao","onServiceDisconnected");
        }
    };
    // end 创建跟服务端的连接

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 发起绑定服务
    private boolean bindBookService(){
        Intent intent = new Intent();
        // 指定 action，要绑定的服务类，在服务端的清单文件里定义
        intent.setAction("com.guohao.aidl.server.aidlService");
        // 指定 包名，服务端的包名
        intent.setPackage("com.guohao.aidlserver");
        startService(intent);
        return bindService(intent, mConnection, BIND_AUTO_CREATE);
    }
    // end 发起绑定服务

    public void test(View v){
        Log.d("guohao","test2");
        // 调用 绑定服务
        boolean b = bindBookService();
        if(b){
            Toast.makeText(this,"bind succ",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"bind fail",Toast.LENGTH_SHORT).show();
        }
    }

    public void test2(View v){
        Log.d("guohao","test2");
        if(mIBookManager == null) {
            Log.d("guohao","未绑定服务");
            Toast.makeText(this,"未绑定服务" ,Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            // 发起 远程调用
            int res = mIBookManager.add(1,1);
            Log.d("guohao","res = " + res);
            Toast.makeText(this,"mIBookManager.add(1,1) = " + res,Toast.LENGTH_SHORT).show();

        } catch (RemoteException e) {
            Log.d("guohao","e = " + e.toString());
            e.printStackTrace();
        }

    }

    public void test3(View v){
        Log.d("guohao","test3");
        if(mIBookManager == null) {
            Log.d("guohao","未绑定服务");
            Toast.makeText(this,"未绑定服务" ,Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            // 发起 远程调用
            int res = mIBookManager.wrong_add(1);
            Log.d("guohao","res = " + res);
            Toast.makeText(this,"mIBookManager.add(1) = " + res,Toast.LENGTH_SHORT).show();

        } catch (RemoteException e) {
            Log.d("guohao","e = " + e.toString());
            e.printStackTrace();
        }

    }

}
