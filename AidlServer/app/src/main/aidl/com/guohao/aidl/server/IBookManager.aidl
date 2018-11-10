// IBookManager.aidl
// 声明一个接口，会被编译为 IBookManager.java extends android.os.IInterface
// IBookManager.java 的内部类 stub extends android.os.Binder implements com.guohao.aidl.server.IBookManager
package com.guohao.aidl.server;
interface IBookManager {

    int add(int num1,int num2);

    int wrong_add(int num1,int num2);

    int more_fun();
}
