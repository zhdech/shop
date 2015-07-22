package cn.com.shop.common.security.rights;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.jfinal.core.Controller;
/**
 * 将原有获取模块名称的方法(从url中截取模块名称的方法不严谨)舍弃,代之以从当前请求的Controller类名中截取. <br>
 * 这要求所有的controller(控制器)的命名必须遵循两个原则: <br>
 * 		1. 单词Controller首字母大写,且语法正确.<br>
 * 		2. 模块名在数据数据中以小写形式出现.<br>
* @ClassName: BaseController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:29:40 
*
 */
public class BaseController extends Controller{	
	
	
	private Map<String, Object> getBaseParaMap() {
		
		Map tempMap = new HashMap();
		Map formMap = (Map)super.getParaMap();
		
		if(formMap.size()>0)
		{
			Set formSet = formMap.keySet();
			Iterator it = formSet.iterator();
			while(it.hasNext()) {
		    	String enKey = (String) it.next();
		    	String enVal = ((String[])formMap.get(enKey))[0];
		    	if(enVal!=null && !enVal.equals("") && !enVal.equals("-1"))
		    	{
		    		tempMap.put(enKey,enVal);
		    	}else
		    	{
		    		continue;
		    	}
		    	
		    }
		}
		return tempMap;
	}
	
	
	public String getWhereSql()
	{
		StringBuffer sbuf = new StringBuffer();
		Map<String,Object> map = getBaseParaMap();
		Set tempSet = map.keySet();
		Iterator it = tempSet.iterator();
		while(it.hasNext()) 
		{
			String enKey = (String)it.next();
			sbuf.append(" and ");
			sbuf.append(enKey);
			if(map.get(enKey).toString().indexOf(",")>0)
			{
				sbuf.append(" in ");
				sbuf.append("('" + map.get(enKey).toString().replace("[", "").replace("]", "").replace(",", "','") + "')");
			}else
			{
				sbuf.append(" like ");
				sbuf.append("'%" + map.get(enKey).toString() + "%'");
			}
		}
		
		return sbuf.toString();
	}
	
	
	
	
	/**
	 * 获取当前请求的模块名称
	 */
	private String getModule() {
		return this.getClass().getSimpleName().replace("Controller", "").toLowerCase();
	}
}
