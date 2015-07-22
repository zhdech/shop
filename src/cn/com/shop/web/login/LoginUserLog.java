package cn.com.shop.web.login;

import cn.com.shop.common.IPageUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/**
 * 记录帐号登录时的日志
* @ClassName: LoginUserLog 
* @author zhdech
* @date 2015年5月5日 下午8:47:42 
*
 */
@SuppressWarnings("serial")
public class LoginUserLog extends Model<LoginUserLog>{

	public static final LoginUserLog dao = new LoginUserLog();

	public LoginUserLog getUserLogByName(String name) {
		return dao.findFirst("select id,name,mobile,username,status,address,logintime from t_user_log where name=? ", name);
	}
	
	public LoginUserLog getUserById(String id){
		return dao.findById(id);
	}
	
	/**
     * 
    * @Title: getMasterList 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<LoginUserLog> getUserList(String condition){
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select id,name,password,mobile,username,qq,status,manager,address  ","from t_user where status<>9 "+ condition);
    }
    
}
