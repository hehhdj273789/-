package com.briup.env.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.briup.env.bean.Environment;
import com.briup.env.util.Backup;
import com.briup.env.util.BackupImpl;

public class GatherImpl implements Gather {

	// 数据文件路径
	private String dataFilePath="src/main/resources/data-file";
	
	private Backup backup=new BackupImpl();

	@Override
	public Collection<Environment> gather() throws Exception {
		
		File file = new File(dataFilePath);
		if (!file.exists()) {
			throw new Exception("读取数据文件路径不存在" + dataFilePath);
		}
		//当前文件长度
		long nowlen=file.length();
		//需要跳过去的字节数
		
		Object skiplen=backup.load("file-len.bak", Backup.LOAD_REMOVE);
		
		//文件长度
		Collection<Environment>  c = new ArrayList<>();

		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = null;
		//跳过上次读取的字节数
		if(skiplen!=null)in.skip((Long)skiplen);
		
		while ((line = in.readLine()) != null) {
			// 100|101|2|16|1|3|5d606f7802|1|1516323596029
			String[] arr = line.split("[|]");

			if (arr.length != 9) {
				// 记录异常数据
				continue;
			}

			Environment env = new Environment();
			env.setSrcId(arr[0]);
			env.setDesId(arr[1]);
			env.setDevId(arr[2]);
			// 表示数据类型：（温度,湿度）,二氧化碳,光照强度
			env.setSersorAddress(arr[3]);
			env.setCount(Integer.parseInt(arr[4]));
			env.setCmd(arr[5]);
			env.setStatus(Integer.parseInt(arr[7]));
			env.setGather_date(new Timestamp(Long.parseLong(arr[8])));
			
			String dataStr = arr[6].substring(0, 4);			//截取数据
			int data = Integer.parseInt(dataStr, 16);			//这个16表示16进制
			//5d606f7802
			switch (env.getSersorAddress()) {
			case "16":// 溫度、湿度
				env.setName("温度");
				env.setData((float)(data*0.00268127 - 46.85));
				
				//复制上面封装好的env（温度），使用envCopy表示湿度对象
				Environment envCopy = envCopy(env);
				
				//截取数据中的第三四个字节，用来表示湿度数据
				String dataStrHumidity = arr[6].substring(4, 8);
				int dataHumidity = Integer.parseInt(dataStrHumidity, 16);
				
				//封装湿度对象
				envCopy.setName("湿度");
				envCopy.setData((float)(dataHumidity*0.00190735 - 6));
				
				c.add(env);		//添加温度对象
				c.add(envCopy);		//添加湿度对象
				
				break;
			case "256":// 光照强度
				env.setName("光照强度");
				env.setData(data);
				
				c.add(env);
				
				break;
			case "1280":// 二氧化碳
				
				env.setName("二氧化碳");
				env.setData(data);
				
				c.add(env);
				break;
			default:
				//数据异常
				break;
			}
		}
		
		in.close();//关闭流
		//本次读完后，备份一下读取的文件长度，以便下次读取的时候进行跳过
		backup.store("file-len.bak", nowlen, Backup.STORE_OVERRIDE);
		return c;
	}

	private Environment envCopy(Environment env) {
		Environment newEnv = new Environment();
		newEnv.setSrcId(env.getSrcId());
		newEnv.setDesId(env.getDesId());
		newEnv.setDevId(env.getDevId());
		newEnv.setSersorAddress(env.getSersorAddress());
		newEnv.setCount(env.getCount());
		newEnv.setCmd(env.getCmd());
		newEnv.setStatus(env.getStatus());
		newEnv.setGather_date(env.getGather_date());
		return env;
	}

}
