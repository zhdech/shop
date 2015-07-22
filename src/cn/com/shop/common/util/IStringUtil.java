package cn.com.shop.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class IStringUtil {

	/**
	 * 方法名：getJsonToMap
	 * 描述  ：将json对象转化成数组
	 *
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, String> getJsonToMap(String jsonStr)
	{
		Map<String, String> map = new LinkedHashMap<String, String>(); 
		String repStr = jsonStr.replace("{", "").replace("}", "").trim(); 
        String[] arrayStr = repStr.split(","); 
        for (int i = 0; i < arrayStr.length; i++) { 
            String[] stringArray = arrayStr[i].split(":"); 
            map.put(stringArray[0], stringArray[1]); 
        }
        return map;
	}
	

	public static boolean notBlank(String str){
		return str != null && !"".equals(str.trim());
	}
	
	public static boolean isBlank(String str){
		return str == null || "".equals(str.trim());
	}
	
}
