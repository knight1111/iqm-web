package com.thomsonreuters.common.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;

import com.google.common.collect.Maps;
import com.thomsonreuters.common.utils.PropertiesLoader;
import com.thomsonreuters.common.utils.StringUtils;

public class GlobalConstants {

	private static GlobalConstants global = new GlobalConstants();
	
	public static GlobalConstants getInstance() {
		return global;
	}

	/**
	 * Global property
	 */
	private static Map<String, String> map = Maps.newHashMap();

	/**
	 * Load property file
	 */
	private static PropertiesLoader loader = new PropertiesLoader(
			"app.properties");

	/**
	 * Show/Hide
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * Yes/No
	 */
	public static final String YES = "1";
	public static final String NO = "0";

	/**
	 * True/False
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";

	/**
	 * get Property
	 * 
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	/**
	 * get AdminPath
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}

	/**
	 * get FrontPath
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}

	/**
	 * get UrlSuffix
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}

	/**
	 * get Constants
	 * 
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return GlobalConstants.class.getField(field).get(null);
		} catch (Exception e) {
			// Do nothing
		}
		return null;
	}

	/**
	 * get ProjectPath
	 * 
	 * @return
	 */
	public static String getProjectPath() {
		String projectPath = GlobalConstants.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)) {
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null) {
				while (true) {
					File f = new File(file.getPath() + File.separator + "src"
							+ File.separator + "main");
					if (f == null || f.exists()) {
						break;
					}
					if (file.getParentFile() != null) {
						file = file.getParentFile();
					} else {
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
	}

}
