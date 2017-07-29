package com.songminju.songcross_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author宋民举
 */

public class TransferDataThread extends Thread{
	private Socket inSocket = null;
	private Socket outSocket = null;
	InputStream in = null;
	OutputStream out = null;
	
	public TransferDataThread(Socket in,Socket out) {
		// TODO Auto-generated constructor stub
		this.inSocket = in;
		this.outSocket = out;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			in = inSocket.getInputStream();
			out = outSocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
			byte[] buffer = new byte[8];
			int len  = 0;
			while(true) {
				try {
					len = in.read(buffer);
					if(len<1) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						continue;
					}
					out.write(buffer,0,len);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					break;
				}
			}
			if(inSocket!=null && !inSocket.isClosed()) {
				try {
					inSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(outSocket!=null && !outSocket.isClosed()) {
				try {
					outSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
