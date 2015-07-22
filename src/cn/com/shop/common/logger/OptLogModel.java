package cn.com.shop.common.logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.shop.common.util.IDateUtil;
import cn.com.shop.common.util.SequenceUtil;

import com.jfinal.plugin.activerecord.Model;
/**
 * 操作日志记录类
* @ClassName: OptLogModel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:27:12 
*
 */
@SuppressWarnings("serial")
public class OptLogModel extends Model<OptLogModel>{

	/**
	 * MODEL类实例
	 */
	public static final OptLogModel dao = new OptLogModel();
	
	/**
	 * 方法名：optLogAdd
	 * 描述  ：基本操作日志增加
	 *
	 * @param optid    操作对象ID
	 * @param moduleid 模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel 日志级别
	 * @param model    入库前查询出对应的model对象
	 * @return
	 */
	public boolean optLogAdd(long optid,String moduleid,String module_optid,String optlevel,Model model)
	{
		//String content = setAddContent(reqMap);
		setValues(optid,"add",moduleid,module_optid,optlevel,model.toJson());
		
		return super.save();
	}
	
	/**
	 * 方法名：optLogUpdate
	 * 描述  ：基本操作日志修改
	 *
	 * @param optid    操作对象ID
	 * @param moduleid 模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel 日志级别
	 * @param reqMap   页面表单提交数据map集合
	 * @param model    入库前查询出对应的model对象
	 * @return
	 */
	public boolean optLogUpdate(long optid,String moduleid,String module_optid,String optlevel,Map reqMap,Model model)
	{
		String content = setUpdateContent(reqMap,model);
		if(!content.equals(""))
		{
			setValues(optid,"update",moduleid,module_optid,optlevel,content);
			return super.save();
		}
		
		return false;
	}
	
	/**
	 * 方法名：optLogDel
	 * 描述  ：基本操作日志删除
	 *
	 * @param optid    操作对象ID
	 * @param moduleid 模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel 日志级别
	 * @param model    入库前查询出对应的model对象
	 * @return
	 */
	public boolean optLogDel(long optid,String moduleid,String module_optid,String optlevel,Model model)
	{
		setValues(optid,"del",moduleid,module_optid,optlevel,model.toJson());
		return super.save();
	}
	
	/**
	 * 方法名：getLogListByPara
	 * 描述  ：根据操作对象的id，查询出该对象的所有记录
	 *
	 * @param objId
	 * @return
	 */
	public List<OptLogModel> getLogListByPara(long objId)
	{
		return dao.find("select id,optid,opttype,moduleid,module_optid,optlevel,operator,srcip,sessionid,opttime,content from T_LOG_BASE_MODIFY where optid=? and (opttype='add' or opttype='update' or opttype='del') order by opttime asc ",objId);
	}
	
	/**
	 * 方法名：getAuthLogListByPara
	 * 描述  ：根据操作对象的id，查询出该对象的所有的授权操作记录
	 *
	 * @param objId
	 * @return
	 */
	public List<OptLogModel> getAuthLogListByPara(long objId)
	{
		return dao.find("select id,optid,opttype,moduleid,module_optid,optlevel,operator,srcip,sessionid,opttime,content from T_LOG_BASE_MODIFY where optid=? and (opttype='authAdd' or opttype='authDel') order by opttime asc ",objId);
	}
	
	
	/**
	 * 方法名：logBaseModifySave
	 * 描述  ： 基础数据的修改记录保存（全参数模式）
	 *
	 * @param optid        操作对象ID
	 * @param opttype      操作类型
	 * @param moduleid     模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel     日志级别
	 * @param operator     操作人
	 * @param srcip        操作的IP
	 * @param sessionId    sessionId
	 * @param content      操作内容，json格式，例如：[{MOBILE:[{oldVal:'',newVal:'13110101010'}],POSITION:[{oldVal:'',newVal:'123'}]}]
	 * @return
	 */
	public boolean logBaseModifySave(long optid,String opttype,String moduleid,String module_optid,String optlevel,String operator,String srcip,String sessionId,String content)
	{
		setAllValues(optid,opttype,moduleid,module_optid,optlevel,operator,srcip,sessionId,content);
		return super.save();
	}
	
	/**
	 * 方法名：roleAuthoriseLogAdd
	 * 描述  ：角色授权，添加授权操作
	 *
	 * @param optid    操作对象ID
	 * @param moduleid 模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel 日志级别
	 * @param names
	 * @return
	 */
	public boolean roleAuthoriseLogAdd(long optid,String moduleid,String module_optid,String optlevel,String[] names)
	{
		String content = setRoleAuthoriseContent("authAdd",names);
		if(!content.equals(""))
		{
			setValues(optid,"authAdd",moduleid,module_optid,optlevel,content);
			return super.save();
		}
		return false;
	}
	
	
	/**
	 * 方法名：roleAuthoriseLogDel
	 * 描述  ：角色授权，删除授权操作
	 *
	 * @param optid    操作对象ID
	 * @param moduleid 模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel 日志级别
	 * @param names
	 * @return
	 */
	public boolean roleAuthoriseLogDel(long optid,String moduleid,String module_optid,String optlevel,String[] names)
	{
		String content = setRoleAuthoriseContent("authDel",names);
		if(!content.equals(""))
		{
			setValues(optid,"authDel",moduleid,module_optid,optlevel,content);
			return super.save();
		}
		return false;
	}
	
	
	/**
	 * 方法名：masSlaveAuthLogAdd
	 * 描述  ：主从帐号授权，添加授权操作
	 *
	 * @param optid    操作对象ID
	 * @param moduleid 模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel 日志级别
	 * @param conMap<String,String[]>   此map的key建议是slaveid ，value的存值是数组，例如：{"11","22","33","44"}  依次是  资源、从帐号、有效时间、结束时间
	 * @return
	 */
	public boolean masSlaveAuthLogAdd(long optid,String moduleid,String module_optid,String optlevel,Map<String,String[]> conMap)
	{
		String content = setMasSlaveAuthContent("authAdd",conMap);
		if(!content.equals(""))
		{
			setValues(optid,"authAdd",moduleid,module_optid,optlevel,content);
			return super.save();
		}
		
		return false;
	}
	
	
	/**
	 * 方法名：masSlaveAuthLogDel
	 * 描述  ：主从帐号授权，删除授权操作
	 *
	 * @param optid    操作对象ID
	 * @param moduleid 模块ID
	 * @param module_optid 权限点ID
	 * @param optlevel 日志级别
	 * @param conMap<String,String[]>   此map的key建议是slaveid ，value的存值是数组，例如：{"11","22","33","44"}  依次是  资源、从帐号、有效时间、结束时间
	 * @return
	 */
	public boolean masSlaveAuthLogDel(long optid,String moduleid,String module_optid,String optlevel,Map<String,String[]> conMap)
	{
		String content = setMasSlaveAuthContent("authDel",conMap);
		if(!content.equals(""))
		{
			setValues(optid,"authDel",moduleid,module_optid,optlevel,content);
			return super.save();
		}
		
		return false;
	}
	
	
	
	/**
	 * 方法名：setValues
	 * 描述  ：设置各个参数的值，供add/update/del方法调用使用
	 *
	 * @param optid
	 * @param opttype
	 * @param moduleid
	 * @param module_optid
	 * @param optlevel
	 * @param content
	 * @return
	 */
	private OptLogModel setValues(long optid,String opttype,String moduleid,String module_optid,String optlevel,String content)
	{
		this.set("id", SequenceUtil.getLogSeq());
		this.set("optid", optid);
		this.set("opttype", opttype);
		this.set("moduleid", moduleid);
		this.set("module_optid", module_optid);
		this.set("optlevel", optlevel);
		this.set("opttime", IDateUtil.getTimestamp());
		this.set("content", content);
		
		return this;
	}
	
	/**
	 * 方法名：setAllValues
	 * 描述  ：设置所有的参数
	 *
	 * @param optid
	 * @param opttype
	 * @param moduleid
	 * @param module_optid
	 * @param optlevel
	 * @param operator
	 * @param srcip
	 * @param sessionId
	 * @param content
	 * @return
	 */
	private OptLogModel setAllValues(long optid,String opttype,String moduleid,String module_optid,String optlevel,String operator,String srcip,String sessionId,String content)
	{
		this.set("id", SequenceUtil.getLogSeq());
		this.set("optid", optid);
		this.set("opttype", opttype);
		this.set("moduleid", moduleid);
		this.set("module_optid", module_optid);
		this.set("optlevel", optlevel);
		this.set("operator", operator);
		this.set("srcip", srcip);
		this.set("sessionid", sessionId);
		this.set("opttime", IDateUtil.getTimestamp());
		this.set("content", content);
		
		return this;
	}
	
	/**
	 * 方法名：setAddContent
	 * 描述  ：设置添加操作的操作内容
	 *
	 * @param reqMap
	 * @return
	 */
	private String setAddContent(Map reqMap)
	{
		StringBuffer sbuf = new StringBuffer();
		Set reqSet = reqMap.keySet();
        Iterator it = reqSet.iterator();
	    while(it.hasNext()) {
	    	 String ikey = (String) it.next();
			 sbuf.append(ikey);
			 sbuf.append("=");
			 sbuf.append(reqMap.get(ikey)==null?"":reqMap.get(ikey));
			 sbuf.append(",");
	     }
	     //System.out.println("-------=========>>>" + sbuf.toString());
		
		return sbuf.toString();
	}
	
	/**
	 * 方法名：setUpdateContent
	 * 描述  ：比较修改前和修改后的数据差异，将差异数据进行拼装储存成修改内容
	 *       格式参考：字段名=（修改前的值 ， 修改后的值）；
	 *       例如：  [{MOBILE:[{oldVal:'',newVal:'13110101010'}],POSITION:[{oldVal:'',newVal:'123'}]}]
	 *
	 * @param reqMap
	 * @param model
	 * @return
	 */
	private String setUpdateContent(Map reqMap,Model model)
	{
		
		StringBuffer sbuf = new StringBuffer();
		Set reqSet = reqMap.keySet();
        Iterator it = reqSet.iterator();
        sbuf.append("[{");
	    while(it.hasNext()) {
	    	 String ikey = (String) it.next();
	    	 if(!(reqMap.get(ikey)==null?"":reqMap.get(ikey)).equals(model.get(ikey)==null?"":model.get(ikey)))
	    	 {
	    		 sbuf.append(ikey);
	    		 sbuf.append(":[{oldVal:'");
	    		 sbuf.append(model.get(ikey)==null?"":model.get(ikey));
	    		 sbuf.append("',newVal:'");
	    		 sbuf.append(reqMap.get(ikey)==null?"":reqMap.get(ikey));
	    		 sbuf.append("'}],");
	    	 }
	     }
	    String jsonStr = sbuf.toString();
	    if(jsonStr.length()>2)
	    {
	    	jsonStr = jsonStr.substring(0,jsonStr.length()-1) + "}]";
	    }else
	    {
	    	jsonStr = "";
	    }
	   
		return jsonStr;
	}
	
	/**
	 * 方法名：setRoleAuthoriseContent
	 * 描述  ：
	 *
	 * @param optType  操作类型，分别是：authAdd、authDel
	 * @param names      授权的name数组
	 * @return   返回json格式数据 ，如：[{authAdd:[{master:11}],authAdd:[{master:22}],authAdd:[{master:33}],authAdd:[{master:44}]}]
	 */
	private String setRoleAuthoriseContent(String optType,String[] names)
	{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("[{");
		if(names.length > 0)
		{
			for(int i=0;i<names.length;i++)
			{
				String optTypeStr = optType + i;
				sbuf.append(optTypeStr);
				sbuf.append(":");
				sbuf.append("[{");
				sbuf.append("master:");
				sbuf.append(names[i]);
				sbuf.append("}],");
				optTypeStr="";
			}
		}
		String jsonStr = sbuf.toString();
		if(jsonStr.length()>2)
	    {
	    	jsonStr = jsonStr.substring(0,jsonStr.length()-1) + "}]";
	    }else
	    {
	    	jsonStr = "";
	    }
		return jsonStr;
	}
	
	
	/**
	 * 方法名：setMasSlaveAuthContent
	 * 描述  ：
	 *
	 * @param map  此map的key建议使用从帐号的ID，value的存储方式是4参数数组格式，参数分别是resname（资源名称）、slavename（从帐号名称）、validtime（有效时间，可为空）、invalidtime（失效时间，可为空）
	 * @return       返回json格式数据 ，如：[{authAdd:[{res:11,slave:22,validtime:33,invalidtime:44}],authAdd:[{res:111,slave:222,validtime:333,invalidtime:444}],authAdd:[{res:1111,slave:2222,validtime:3333,invalidtime:4444}]}]
	 */
	private String setMasSlaveAuthContent(String optType,Map<String,String[]> map)
	{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("[{");
		if(map.size()>0)
		{
				Set tempSet = map.keySet();
		        Iterator it = tempSet.iterator();
			    while(it.hasNext()) {
			    	String ikey = (String) it.next();
			    	String[] arrStr = (String[])map.get(ikey);
			    	String optTypeStr = optType + ikey;
			    	sbuf.append(optTypeStr);
			    	sbuf.append(":");
		    		sbuf.append("[{res:");
		    		sbuf.append(arrStr[0]);
		    		sbuf.append(",slave:");
		    		sbuf.append(arrStr[1]);
		    		sbuf.append(",validtime:");
		    		sbuf.append(arrStr[2]==null?"''":arrStr[2]);
		    		sbuf.append(",invalidtime:");
		    		sbuf.append(arrStr[3]==null?"''":arrStr[3]);
		    		sbuf.append("}],");
		    		optTypeStr="";
			    }
		}
		String jsonStr = sbuf.toString();
		if(jsonStr.length()>2)
	    {
	    	jsonStr = jsonStr.substring(0,jsonStr.length()-1) + "}]";
	    }else
	    {
	    	jsonStr = "";
	    }
		return jsonStr;
	}
	
	public static void main(String[] args) {
		OptLogModel om = new OptLogModel();
		String[] str = {"11","22","33","44"};
		System.out.println(om.setRoleAuthoriseContent("authAdd",str));
		System.out.println(om.setRoleAuthoriseContent("authDel",str));
		
		Map<String,String[]> map = new HashMap<String,String[]>();
		map.put("111", str);
		map.put("222", str);
		map.put("333", str);
		
		
		System.out.println(om.setMasSlaveAuthContent("authAdd",map));
	}
	
}
