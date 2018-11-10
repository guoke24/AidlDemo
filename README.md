# AidlDemo

* 一个demo，测试aidl调用。
* server端是 aidl 的实现者。
* client端是 aidl 的调用者。
* 要注意的一点是，两个端的aidl文件的包名要完全一致
* 自己写好IBookManager.aidl文件，Andstudio 编译时会自动生成IBookManager.java文件
* IBookManager.aidl文件 替换为 IBookManager.java文件，编译会报错：找不到 IBookManager.java文件所在的包

### 测试了接口不一致，不会报错
* server 端的 IBookManager接口，函数声明为：
```
package com.guohao.aidl.server;
interface IBookManager {

    int add(int num1,int num2);

    int wrong_add(int num1,int num2);

    int more_fun();
}
```

* client 端的 IBookManager接口，函数声明为：
```

interface IBookManager {

    int add(int num1,int num2);

    // 服务端定义了该函数会多一个参数 wrong_add(int num1,int num2)
    int wrong_add(int num1);
}
```

* 结果：绑定服务不会报错，调用 wrong_add 函数也不会报错，缺失的参数 num2 默认为 0
