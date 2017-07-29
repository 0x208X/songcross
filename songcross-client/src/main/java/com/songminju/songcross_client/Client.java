package com.songminju.songcross_client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author宋民举
 */
public class Client 
{
    public static void main( String[] args )
    {
        if(args==null || args.length!=4) {
        	System.out.println("请输入配置：\n服务器Ip,\n服务器端口\n转发目标地址\n转发目标端口");
        }
        while(true) {
        	try {
				Socket server = new Socket(args[0], Integer.parseInt(args[1]));
				System.out.println("连接到服务器");
				InputStream in = server.getInputStream();
				while(in.available()<1) {
					System.out.print(".");
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("准备收数据");
				Socket client = new Socket(args[2], Integer.parseInt(args[3]));
				new TransferDataThread(server, client).start();
				new TransferDataThread(client, server).start();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
