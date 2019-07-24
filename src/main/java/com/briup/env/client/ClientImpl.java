package com.briup.env.client;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

import com.briup.env.bean.Environment;

public class ClientImpl implements Client{
	private String host="127.0.0.1";
	private int port=8888;

	@Override
	public void send(Collection<Environment> c) throws Exception {
		// TODO Auto-generated method stub
		
		
		@SuppressWarnings("resource")
		Socket socket=new Socket(host, port);
		ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		out.writeObject(c);
		
	}
	

}
