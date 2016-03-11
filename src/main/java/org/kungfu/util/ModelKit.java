package org.kungfu.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;

public class ModelKit {
	
	public static boolean recordNotBlank(Object obj) {
		if (obj instanceof String )
			return obj != null && !"".equals(((String) obj).trim());
		
		return obj != null;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Record toRecord(Model model) {
		Record record = new Record();
		Set<Entry<String, Object>> attrs = model._getAttrsEntrySet();
		for (Entry<String, Object> entry : attrs) {
			record.set(entry.getKey(), entry.getValue());
		}
		return record;
	}

	@SuppressWarnings("rawtypes")
	public static Model set(Model model, Object... attrsAndValues) {
		int length = attrsAndValues.length;
		if (!(length % 2 == 0)) {
			throw new RuntimeException("attrsAndValues length must be even number");
		}
		
		for (int i = 0; i < length; i = i + 2) {
			Object attr = attrsAndValues[i];
			
			if (!(attr instanceof String)) {
				throw new RuntimeException("the odd number of attrsAndValues  must be String");
			}
			model.set((String) attr, attrsAndValues[i + 1]);
		}
		return model;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> toMap(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Entry<String, Object>> attrs = model._getAttrsEntrySet();
		for (Entry<String, Object> entry : attrs) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	public static int hashCode(Model<?> model) {
		final int prime = 31;
		int result = 1;
		Table tableinfo = TableMapping.me().getTable(model.getClass());
		Set<Entry<String, Object>> attrsEntrySet = model._getAttrsEntrySet();
		for (Entry<String, Object> entry : attrsEntrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			Class<?> clazz = tableinfo.getColumnType(key);
			if (clazz == Integer.class) {
				result = prime * result + (Integer) value;
			} else if (clazz == Short.class) {
				result = prime * result + (Short) value;
			} else if (clazz == Long.class) {
				result = prime * result + (int) ((Long) value ^ ((Long) value >>> 32));
			} else if (clazz == Float.class) {
				result = prime * result + Float.floatToIntBits((Float) value);
			} else if (clazz == Double.class) {
				long temp = Double.doubleToLongBits((Double) value);
				result = prime * result + (int) (temp ^ (temp >>> 32));
			} else if (clazz == Boolean.class) {
				result = prime * result + ((Boolean) value ? 1231 : 1237);
			} else if (clazz == Model.class) {
				result = hashCode((Model<?>) value);
			} else {
				result = prime * result + ((value == null) ? 0 : value.hashCode());
			}
		}
		return result;
	}

	public static boolean equals(Model<?> model, Object obj) {
		if (model == obj)
			return true;
		if (obj == null)
			return false;
		if (model.getClass() != obj.getClass())
			return false;
		Model<?> other = (Model<?>) obj;
		Table table = TableMapping.me().getTable(model.getClass());
		Set<Entry<String, Object>> attrsEntrySet = model._getAttrsEntrySet();
		for (Entry<String, Object> entry : attrsEntrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			Class<?> clazz = table.getColumnType(key);
			if (clazz == Float.class) {
			} else if (clazz == Double.class) {
			} else if (clazz == Model.class) {
			} else {
				if (value == null) {
					if (other.get(key) != null)
						return false;
				} else if (!value.equals(other.get(key)))
					return false;
			}
		}
		return true;
	}
}
