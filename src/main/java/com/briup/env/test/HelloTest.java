package com.briup.env.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;

import com.briup.env.bean.Environment;
import com.briup.env.client.Gather;
import com.briup.env.client.GatherImpl;
import com.briup.env.server.DBStore;
import com.briup.env.server.DBStoreImpl;
import com.briup.env.util.Backup;
import com.briup.env.util.BackupImpl;
import com.briup.env.util.Log;
import com.briup.env.util.LogImpl;

public class HelloTest {
	public static void main(String[] args) throws IOException {
		
	/*//	System.out.println("hello world");
		DBStore dbStore=new DBStoreImpl();
		try {
			dbStore.saveDB(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		/*Backup backup=new BackupImpl();
		
		Environment e=new Environment();
		e.setName("测试");
		e.setGather_date(new Timestamp(1000000000));
		
		try {
			//backup.store("a.txt", e, Backup.STORE_OVERRIDE);
			Object obj=backup.load("a.txt", Backup.LOAD_REMOVE);
			System.out.println(obj);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		/*try {
			File file=new File("src/main/resources/data-file");
			BufferedReader in=new BufferedReader(new FileReader(file));
			in.skip(395);
			String line=null;
			while((line=in.readLine())!=null){
				System.out.println(line);
			}
			in.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		/*Gather g=new GatherImpl();
		try {
			Collection<Environment> c=g.gather();
			System.out.println(c.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Log logger=new LogImpl();
		logger.debug("debug日志测试");
		logger.info("info日志测试");
		logger.warn("warn日志测试");
		logger.error("error日志测试");
		logger.fatal("fatal日志测试");
		
	}
}
