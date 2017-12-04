package util;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exception.SysException;


/**
 *
 * @ClassName SerialUtil
 * @Description Copyright (c) 2014 by NS Soft.
 * @author xuliguo
 * @date 2015年8月12日 下午5:55:40
 * @version V1.0
 * 
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SerialUtil {

	private static final Logger logger = Logger.getLogger(SerialUtil.class);

	public static Map JsonToMap(JSONObject object) throws SysException {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = null;
			try {
				value = object.get(key);
				if (value instanceof JSONArray) {
					value = JsonToList((JSONArray) value);
				} else if (value instanceof JSONObject) {
					value = JsonToMap((JSONObject) value);
				}
				map.put(key, value);
			} catch (JSONException e) {
				logger.error(e);
				throw new SysException("Json转换成Map出错.");
			}
		}
		return map;
	}

	public static List JsonToList(JSONArray array) throws SysException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = null;
			try {
				value = array.get(i);
				if (value instanceof JSONArray) {
					value = JsonToList((JSONArray) value);
				} else if (value instanceof JSONObject) {
					value = JsonToMap((JSONObject) value);
				}
				list.add(value);
			} catch (JSONException e) {
				logger.error(e);
				throw new SysException("Json转换成List出错.");
			}
		}
		//logger.info(list);
		return list;
	}

	
	
}

