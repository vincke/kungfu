package org.kungfu.core;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.jfinal.aop.Invocation;

/**
 * created by xiaofeixiang on 2016/1/9 15:28:18.
 */
public class Interceptor implements com.jfinal.aop.Interceptor {
	
	private static Logger LOG = Logger.getLogger(Interceptor.class);

	public void intercept(Invocation ai) {
		long start = System.currentTimeMillis();
		ai.invoke();
		long end = System.currentTimeMillis();
		LOG.info("[" + new Timestamp(start) + "] Call method " + ai.getController().getClass().getSimpleName() + "." + ai.getMethodName()+  "(), view path '" + ai.getActionKey() + "' , cost  " + (end - start) + " ms.");
	}
}
