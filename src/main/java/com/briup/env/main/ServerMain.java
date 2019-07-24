package com.briup.env.main;

import com.briup.env.server.Server;
import com.briup.env.server.ServerImpl;

public class ServerMain {
	public static void main(String[] args) {
		Server server=new ServerImpl();
		try {//服务器端入口
			server.reciver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
