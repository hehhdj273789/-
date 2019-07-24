package com.briup.env.main;

import java.util.Collection;

import com.briup.env.bean.Environment;
import com.briup.env.client.Client;
import com.briup.env.client.ClientImpl;
import com.briup.env.client.Gather;
import com.briup.env.client.GatherImpl;

public class ClientMain {
	public static void main(String[] args) {
	     Gather g=new GatherImpl();
	    Client client=new ClientImpl();
		try {//客户端入口
			Collection<Environment> c = g.gather();
			client.send(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		
	}

}
