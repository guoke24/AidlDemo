// IBookManager.aidl
//package com.guohao.aidlclient;
package com.guohao.aidl.server;// 此处必须跟server端的包名保持一致！

// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int add(int num1,int num2);
}
