package com.briup.env.util;

import org.apache.log4j.Logger;

public class LogImpl implements Log{
	
	private Logger RootLog=Logger.getRootLogger();

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		RootLog.debug(message);
		
	}

	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		RootLog.info(message);
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		RootLog.warn(message);
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		RootLog.error(message);
	}

	@Override
	public void fatal(String message) {
		// TODO Auto-generated method stub
		RootLog.fatal(message);
		
	}

}
