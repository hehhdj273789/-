package com.briup.env.server;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collection;

import com.briup.env.bean.Environment;

public class DBStoreImpl implements DBStore{
	//ctrl+alt+向下箭头:快速复制
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username="briup";
	private String password="briup";

	@SuppressWarnings("deprecation")
	@Override
	public void saveDB(Collection<Environment> c) throws Exception {
		// TODO Auto-generated method stub
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		PreparedStatement ps=null;
		
		//设置不自动提交事务
		conn.setAutoCommit(false);
		String sql=null;
		//固定批处理条数
		int batchSize=50;
		//计数
		int count=0;
		//当前数据所属的day，也就是哪一天的数据
		int day=-1;
		for(Environment env:c){
			//当前要插入的数据所属的day和上一条是否相同
			if(day!=env.getGather_date().getDate()){
				if(ps!=null){
					ps.executeBatch();//执行掉之前的sql
					ps.close();//关闭之前的ps,因为更换了sql语句
					count=0;//执行后count计数归零
				}
				day=env.getGather_date().getDate();
				sql="insert into e_detail_"+day+"(name,srcId,desId,devId,sersorAddress,count,cmd,status,data,gather_date) values(?,?,?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
			}
				
				ps.setString(1, env.getName());
				ps.setString(2, env.getSrcId());
				ps.setString(3, env.getDesId());
				ps.setString(4, env.getDevId());
				ps.setString(5, env.getSersorAddress());
				ps.setInt(6, env.getCount());
				ps.setString(7, env.getCmd());
				ps.setInt(8, env.getStatus());
				ps.setFloat(9, env.getData());
				ps.setTimestamp(10, env.getGather_date());
				
				ps.addBatch();
				count++;
				
				//如果当前批处理条数满足了固定条数
				if(count==batchSize){
					ps.executeBatch();//需要执行
					count=0;	
			}

		}
		//循环外再执行一次批处理，以防出现遗漏
		if(ps!=null){
			ps.executeBatch();
		}
		conn.commit();
		//关闭资源
		if(ps!=null)ps.close();
		if(conn!=null)conn.close();
	}

}
