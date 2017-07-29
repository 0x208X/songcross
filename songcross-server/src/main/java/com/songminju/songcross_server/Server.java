package com.songminju.songcross_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author宋民举
 */
public class Server 
{
    public static void main( String[] args )
    {
        if(args==null || args.length!=2) {
        	System.out.println("请加上参数，顺序为\n对外端口\n对内端口");
        	return;
        }
        try {
        	int clientPort = Integer.parseInt(args[1]);
			ServerSocket clientServer = new ServerSocket(clientPort);
			int serverPort = Integer.parseInt(args[0]);
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("服务已经启动，开始监听"+args[1]+"...");
			while(true) {
				try {
					Socket client = clientServer.accept();
					System.out.println("接受一个客户端连接");
					Socket outer = serverSocket.accept();
					System.out.println("接受一个外网连接");
					new TransferDataThread(outer, client).start();
					new TransferDataThread(client, outer).start();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("端口绑定失败！");
			e.printStackTrace();
		}
    }
}
