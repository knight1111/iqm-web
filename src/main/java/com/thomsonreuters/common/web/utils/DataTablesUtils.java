package com.thomsonreuters.common.web.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class DataTablesUtils {

	public static Map<String, String> paramStringToMap(String rsContent)
			throws Exception {
		JSONArray arry = JSONArray.fromObject(StringEscapeUtils.unescapeHtml4(rsContent));
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			String k = null;
			String v = null;
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = jsonObject.get(key).toString();
				if (key != null && key.equals("name")) {
					k = value;
				} else if (key.equals("value")) {
					v = value;
				}
			}
			map.put(k, v);
		}
		return map;
	}

	private static Object invokeMethod(String className, String methodName,
			Object[] args) throws Exception {

		Class ownerClass = Class.forName(className);
		Object owner = ownerClass.newInstance();
		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(owner, args);
	}
	//
	// public static <T> Map<String, Object> result(String aoData,
	// Class serviceClass, String methodName) throws Exception {
	//
	// Map<String, Object> dataMap = new HashMap<String, Object>();
	//
	// Map<String, String> aoMap = DataTablesUtils.paramStringToMap(aoData);
	//
	// String sEcho = "0";
	// String iCurrentPage = "1";
	// String iDisplayLength = "10";
	// String sSearch = null;
	// int count = 0;
	// int initEcho = 0;
	//
	// if (aoMap.size() > 0) {
	// sEcho = aoMap.get("sEcho");
	// iCurrentPage = aoMap.get("iCurrentPage");
	// iDisplayLength = aoMap.get("iDisplayLength");
	// sSearch = aoMap.get("sSearch");
	// }
	//
	// PageHelper.startPage(Integer.valueOf(iCurrentPage),
	// Integer.valueOf(iDisplayLength));
	// PageInfo<T> page = new PageInfo<T>(resultList);
	// count = (int) page.getTotal();
	// initEcho = Integer.parseInt(sEcho) + 1;
	//
	// dataMap.put("sEcho", initEcho);
	// dataMap.put("iTotalRecords", count);
	// dataMap.put("iTotalDisplayRecords", count);
	// dataMap.put("aaData", page.getList());
	//
	// return dataMap;
	// }

}
