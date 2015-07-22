package cn.com.shop.common.security.rights;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
/**
 * 模块数据权限控制器
 */
public class ModuleDataControl {
	
	
	@SuppressWarnings("unused")
	private String module;
	
	//dataid， datavalue
	private Map<String, List<String>>  datacontrolMap = new HashMap<String, List<String>> ();
	
	public ModuleDataControl(String module) {
		this.module = module;
	}
	
	/**
	 * 此方法用来判断当前访问模块是否在允许的数据权限范围内.
	 * @return true or false 
	 */
	public boolean isAllow(Controller c) { 
		boolean flag = true;
		for(Map.Entry<String, List<String>> entry : datacontrolMap.entrySet()) {
			String str = c.getPara(entry.getKey());
			if(str != null && !str.trim().equals("")){
				List<String> list = Arrays.asList(str.split(","));
				if(!entry.getValue().containsAll(list)){
					return false;
				}
			}
		}
		return flag;
	}
	
	
	public Map<String, List<String>> getMap() {
		return this.datacontrolMap;
	}
	
	
	
	/**
	 * 获取指定参数名对应的数据权限值,多个值之间以","分割.
	 * @param para 参数名
	 * @return 参数值; 当此模块不具有para为名的参数值时,返回空字符串.
	 */
	public String getPara(String para) {
		String str = "";
		List<String> list = datacontrolMap.get(para);
		if(list != null) {
			for(int i=0;  i<list.size(); i++) {
				String s = list.get(i);
				if(i == list.size()-1) {
					str = str + s;
				} else {
					str = str + s +",";
				}
			}
		}
		
	    return str;
	}
	
	/**
	 * 
	 * @param dataid 数据权限点
	 * @param dataValue  数据权限点的值List
	 */
	public void setDataControl(String dataid, List<String> dataValue) {
		this.datacontrolMap.put(dataid, dataValue);
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(String key : datacontrolMap.keySet()){
			sb.append("["+key +":" + datacontrolMap.get(key)+"],");
		}
		return sb.substring(0, sb.length()-1).toString();
	}
}
