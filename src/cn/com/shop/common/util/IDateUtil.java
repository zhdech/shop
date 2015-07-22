package cn.com.shop.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
* @ClassName: IDateUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:30:15 
*
 */
public class IDateUtil {

	public IDateUtil() {

	}
	/**
     * 时间格式 yyyy-MM-dd
     */
    public static final String DATE_FORMAT_SIGN_YMD = "yyyy-MM-dd";
    /**
     * 时间格式 yyyyMMdd
     */
    public static final String DATE_FORMAT_YMD = "yyyyMMdd";
    /**
     * 时间格式 yyyyMMddHHmmss
     */
    public static final String DATE_FORMAT_YMDHMS = "yyyyMMddHHmmss";
    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_SIGN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式 yyyyMMddHHmmssSSS
     */
    public static final String DATE_FORMAT_YMD_MILLISECOND = "yyyyMMddHHmmssSSS";
    /**
     * yyyy年MM月dd日
     */
    public static final String DATE_FORMAT_SIGN_YMD_CHINESE = "yyyy年MM月dd日";
    
    /**
     * 方法名：getCurrentDate
     * 描述  ：获取当前日期  yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentDate() {
    	try {
			Date date = new Date();
			SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_FORMAT_SIGN_YMD);
			String daStr = simpledateformat.format(date);
			return daStr;
		} catch (Exception e) {
			return "";
		}
	}
    
    /**
     * 方法名：getCurrentTime
     * 描述  ：获取当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTime() {
		try {
			Date date = new Date();
			SimpleDateFormat simpledateformat = new SimpleDateFormat(DATE_FORMAT_SIGN_YMDHMS);
			String daStr = simpledateformat.format(date);
			return daStr;
		} catch (Exception e) {
			return "";
		}
	}
    
    public static Timestamp getTimestamp()
    {
    	return new Timestamp(new Date().getTime());
    }
    
    public static void main(String[] args) {
    	//System.out.println(getCurrentTime());
    	getTimestamp();
	}

}
