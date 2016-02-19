package com.thomsonreuters.common.persistence.jdbc.paging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertMapKey {

	/**
	 * 把map对象的key全部转为小写形式
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> keyToLower(Map<String, Object> map) {
		Map<String, Object> r = new HashMap<String, Object>();
		if (map == null || map.size() == 0)
			return r;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			r.put(entry.getKey().toLowerCase(), entry.getValue());
		}
		return r;
	}

	/**
	 * 把list map中map对象的key全部转为小写形式
	 * 
	 * @param listmap
	 * @return
	 */
	public static List<Map<String, Object>> listKeyToLower(
			List<Map<String, Object>> listmap) {
		List<Map<String, Object>> r = new ArrayList<Map<String, Object>>();
		if (listmap == null || listmap.size() == 0)
			return r;
		for (Map<String, Object> map : listmap) {
			r.add(keyToLower(map));
		}
		return r;
	}
}