package cn.com.shop.common.security.interceptor;

import java.util.concurrent.ConcurrentHashMap;


/**
 * 
* @ClassName: UserSessionContext 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:29:25 
*
 */
public class UserSessionContext {

private static ConcurrentHashMap<String, String> userSessionMap = new ConcurrentHashMap<String, String>();
	
	public static void addSession(String masterName, String userSessionId){
		
		userSessionMap.put(masterName, userSessionId);
	}
	
	public static void delSession(String masterName){
		
		userSessionMap.remove(masterName);
	}
	
	public static boolean isSessionExist(String masterName){
		
		return userSessionMap.containsKey(masterName);
	}
	
	public static String getUserSessionId(String masterName){
		
		return userSessionMap.get(masterName);
	}
	
}
