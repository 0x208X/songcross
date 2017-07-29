# UA

songcross-server 部署在公网服务器，启动Server类，后跟参数

songcross-client 运行在自己电脑，启动Client类，后跟参数

下载后打开target文件夹，进入songcross_server,运行：
    
        java com/songminju/songcress_server/Server 80 81
    
 即可运行服务端，此时对外端口为80，对内端口为81，服务端将把来自80端口的流量转发至客户端，而81端口是在等候客户端的连接时用到的
 
 然后打开target文件夹，进入songcross_client ，运行：
 
 
        java com/songminju/songcross_client/Client 127.0.0.1 81 127.0.0.1 8080
 
 两个127.0.0.1分别为服务器IP和要转发的IP
 此时客户端连接到服务器的81端口
 然后所有来自80端口的数据会被转发到8080端口上面
 
 运行过程中会抛异常，不管就行，不稳定，仅适合个人开发调试
 
 
