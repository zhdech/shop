package cn.com.shop.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: IConstants 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:30:38 
*
 */
public class IConstants {

	/**
	 * 
	 */
	public static final String GLOBAL_COMMON_WEBROOT = "/";
	
	public static Map<String,String> jdbcMap = new HashMap<String,String>();
	
	/**
	 * 
	 */
	public static final String GLOBAL_COMMON_SESSION_USER = "SESSION_USER";
	public static final String GLOBAL_COMMON_SESSION_ID = "SESSION_ID";
	
	/**
	 * 
	 */
	public static final String GLOBAL_MYSQL_SEQUENCE = "select nextval('Q_BASE')";
	public static final String GLOBAL_MYSQL_LOGSEQ = "select nextval('Q_BASE')";
	public static final String GLOBAL_MQSQL_SCHEDULEDSEQ = "select nextval('SCHEDULEDID')";
	
	public static final String GLOBAL_ORACLE_SEQUENCE = "select Q_BASE.Nextval from dual";
	public static final String GLOBAL_ORACLE_LOGSEQ = "select Q_LOG.Nextval from dual";
	public static final String GLOBAL_ORACLE_SCHEDULEDSEQ = "select SCHEDULEDID.Nextval from dual";
	/**
	 * 转向另一个web平台的配置地址
	 */
	public static final String GOTO_WEBMANAGER_URL = "GOTO_WEBMANAGER_URL";
	public static final String GOTO_WEBPORTAL_URL = "GOTO_WEBPORTAL_URL";
	
	
	/**
	 * 模块数据权限Session常量
	 */
	public static final String SESSION_USER_DATA_CONTROL ="userDataControl";
}
