package com.briup.env.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BackupImpl implements Backup{
	
	private String filePath="src/main/resources/";

	@Override
	public Object load(String fileName, boolean del) throws Exception {
		// TODO Auto-generated method stub
		File file=new File(filePath,fileName);
		if(file.exists()){
			return null;
		}
		ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
		Object obj=in.readObject();
		if(del){
			file.deleteOnExit();
		}
		in.close();
		
		return null;
	}

	@Override
	public void store(String fileName, Object obj, boolean append) throws Exception {
		// TODO Auto-generated method stub
		File file=new File(filePath, fileName);
		
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file,append));
		
		out.writeObject(obj);
		out.flush();
		
		out.close();
		
	}

}
