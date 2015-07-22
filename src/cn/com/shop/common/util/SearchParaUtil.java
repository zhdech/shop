package cn.com.shop.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jfinal.core.Controller;

public class SearchParaUtil {
	
	 public static Controller setAttr(Controller c) 
	 {
	     Map<String, String[]> map = c.getParaMap();
	     Set<Entry<String, String[]>> tempset = map.entrySet();
	     Iterator<Entry<String, String[]>> it = tempset.iterator();
	     
	     while(it.hasNext()) {
	    	 Entry<String, String[]> en = it.next();
	    	 c.setAttr((String)en.getKey(), en.getValue()[0]);
	     }
	     
	     return c;
     }
	 
}
