package com.briup.env.server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

import com.briup.env.bean.Environment;

public class ServerImpl implements Server {
	private int port=8888;

	@Override
	public void reciver() throws Exception {
		// TODO Auto-generated method stub
		ServerSocket server=new ServerSocket(port);
		Socket socket = server.accept();
		
		//socket.getInputStream() 获取基于网络客户端的字节输入流
		//并把该字节输入流包装成对象流，用来读取客户端发送过来的集合
		ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
		
		@SuppressWarnings("unchecked")//不需要检查泛型的问题，这是一个注解
		Collection<Environment> c=(Collection<Environment>)in.readObject();
		
		System.out.println("服务器端接受到客户端发送的数据有："+c.size()+"条");
		
		DBStore dbStore=new DBStoreImpl();
		long start=System.currentTimeMillis();
		dbStore.saveDB(c);
		long end=System.currentTimeMillis();
		System.out.println("入库完成，共"+c.size()+"条，用时"+(end-start)+"毫秒");
		
		
		
		
		in.close();
		socket.close();
		server.close();
	}

	@Override
	public void shutdown() throws Exception {
		// TODO Auto-generated method stub
		
	};
	

}
