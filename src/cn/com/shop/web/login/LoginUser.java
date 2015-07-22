package cn.com.shop.web.login;

import cn.com.shop.common.IPageUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class LoginUser extends Model<LoginUser>{

	public static final LoginUser dao = new LoginUser();

	public LoginUser getMasterByName(String masterName) {
		return dao.findFirst("select id,name,password,mobile,username,qq,status,manager,address from t_user where name=? and status=0", masterName);
	}
	
	public LoginUser getUserById(String id){
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
    public Page<LoginUser> getUserList(String condition){
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select id,name,password,mobile,username,qq,status,manager,address  ","from t_user where status<>9 "+ condition);
    }
    
    public boolean delete(int id,int status){
    	LoginUser user = dao.findById(id);
    	user.set("status", status);
    	return user.update();
    }

    public boolean update(String id,String pwd) {
//    	LoginUser tempLogin = null;
//		//密码加密处理
//    	try {
//    		tempLogin = getUserById(id);
//    		if(!tempLogin.getStr("password").equals(pwd)){
//    			this.set("password", CryptUtil.encrypt(this.getStr("password")));
//    		}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    	
		return super.update();
	}
}
