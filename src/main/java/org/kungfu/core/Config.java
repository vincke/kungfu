package org.kungfu.core;

import com.jfinal.config.JFinalConfig;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * created by xiaofeixiang on 2016/1/9 15:28:18.
 */
public abstract class Config extends JFinalConfig {
	/**
	 * DruidPlugin
	 * @param jdbcUrl
	 * @param userName
	 * @param password
	 * @return
	 * DruidPlugin druidPluginOracle = createDruidPlugin(PropKit.get("oracle.jdbcUrl"), PropKit.get("oracle.user"), PropKit.get("oracle.password"));
	 * druidPluginSrc.start();
	 * ActiveRecordPlugin arpOracle = new ActiveRecordPlugin("oracle", druidPluginOracle);
	 * arpOracle.setDialect(new OracleDialect()); 
	 * arpOracle.setContainerFactory(new CaseInsensitiveContainerFactory()); 
	 * arpOracle.start();
	 */
	public static DruidPlugin createDruidPlugin(String jdbcUrl, String userName, String password) {
		// 配置Druid德鲁伊连接池
		DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, userName, password);
		
		// druid settings
		//# Connection Pool settings
		druidPlugin.setInitialSize(10);
		druidPlugin.setMaxPoolPreparedStatementPerConnectionSize(20);
		druidPlugin.setMaxActive(100);
		druidPlugin.setTimeBetweenConnectErrorMillis(1000);
	
		return druidPlugin;
	}
	
	/**
	 * C3p0Plugin
	 * @param jdbcUrl
	 * @param userName
	 * @param password
	 * @return
	 */
	public static C3p0Plugin createC3p0Plugin(String jdbcUrl, String userName, String password) {
		return new C3p0Plugin(jdbcUrl, userName, password);
	}
}
