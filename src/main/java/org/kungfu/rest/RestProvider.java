package org.kungfu.rest;

import org.apache.log4j.Logger;
import org.kungfu.core.Controller;


/**
 * created by xiaofeixiang on 2016/1/21 10:48:18.
 */
public class RestProvider extends Controller {
	
	private static Logger LOG = Logger.getLogger(RestProvider.class);
	
	protected String getControllerKey() {
		return this.getAttr("ControllerKey");
	}

	private Double toDouble(String value, Double defaultValue) {
		if (value == null || "".equals(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return -Double.parseDouble(value.substring(1));
		return Double.parseDouble(value);
	}

	/**
	 * Get para from url and conver to Double with default value if it is null.
	 */
	public Double getParaToDouble(int index, Double defaultValue) {
		return toDouble(getPara(index), defaultValue);
	}

	/**
	 * Returns the value of a request parameter and convert to Double.
	 */
	public Double getParaToDouble(String name) {
		LOG.info("to double");
		return toDouble(getPara(name), null);
	}
	
	private Short toShort(String value, Short defaultValue) {
		if (value == null || "".equals(value.trim()))
			return defaultValue;
		if (value.startsWith("N") || value.startsWith("n"))
			return (short) -Short.parseShort(value.substring(1));
		return Short.parseShort(value);
	}

	/**
	 * Get para from url and conver to Short with default value if it is null.
	 */
	public Short getParaToShort(int index, Short defaultValue) {
		return toShort(getPara(index), defaultValue);
	}

	/**
	 * Returns the value of a request parameter and convert to Short.
	 */
	public Short getParaToShort(String name) {
		LOG.info("to short");
		return toShort(getPara(name), null);
	}
	
}
