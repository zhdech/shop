package cn.com.shop.web.login;

import cn.com.shop.common.IPageUtil;
import cn.com.shop.common.security.rights.BaseController;
import cn.com.shop.common.util.CryptUtil;
import cn.com.shop.common.util.IPropertyUtil;
import cn.com.shop.common.util.IStringUtil;
import cn.com.shop.common.util.SearchParaUtil;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

public class LoginUserController extends  BaseController{

	public void getUserList(){
		
		//设置条件查询的参数存储 -- 直接拷贝使用
		SearchParaUtil.setAttr(this);
		
		//分页信息，仅供有分页查询的页面使用-- 直接拷贝使用
      	IPageUtil.setPagePara(getPara(0), getPara(1));
		
		//列头信息--使用国际化配置，
        setAttr("headMap", IStringUtil.getJsonToMap(IPropertyUtil.langMap.get("lang.webmanager.user.tableHeader")));
        
        //获取分页对象，page对象中包含list列表结果集
        Page<LoginUser> page = getModel(LoginUser.class).getUserList(this.getWhereSql());//Page<Master> page = getModel(Master.class).getMasterListByPara(masterName);
		
		//列表数据
		setAttr("pageObj", page);
		
		render("/page/manager/user/user_list.html");
	}
	
	public void gotoLoginUserInfo(){
		render("/page/login/loginUserInfo.html");
	}
	
	public void gotoUpdate(){
		setAttr("userInfo", getModel(LoginUser.class).getUserById(getPara()));
		render("/page/manager/user/user_update.html");
	}
	
	public void delete(){
		boolean bn = getModel(LoginUser.class).delete(getParaToInt(),9);
		
		if(bn){
			renderText("OPT_SUCCESS");
		} else {
			renderText("OPT_ERROR");
		}
	}
	
	/**
	 * 锁定
	* @Title: updateLock 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateLock(){
		boolean bn = getModel(LoginUser.class).delete(getParaToInt(),1);
		
		if(bn){
			renderText("OPT_SUCCESS");
		} else {
			renderText("OPT_ERROR");
		}
	}
	
	/**
	 * 解锁
	* @Title: updateUnLock 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateUnLock(){
		boolean bn = getModel(LoginUser.class).delete(getParaToInt(),0);
		
		if(bn){
			renderText("OPT_SUCCESS");
		} else {
			renderText("OPT_ERROR");
		}
	}
	
	/**
	 * 解锁
	* @Title: updateUnLock 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void update(){
		String id = getPara("userInfo.id");
		String pwd = getPara("userInfo.password");
		LoginUser model = getModel(LoginUser.class);
		try {
			LoginUser tempLogin = model.getUserById(id);
    		if(!tempLogin.getStr("password").equals(pwd)){
    			model.set("password", CryptUtil.encrypt(getPara("userInfo.password")));
    		}
    		model.set("id", id);
    		model.set("mobile", getPara("userInfo.mobile"));
    		model.set("username", getPara("userInfo.username"));
    		model.set("qq", getPara("userInfo.qq"));
    		model.set("manager", getPara("userInfo.manager"));
    		model.set("address", getPara("userInfo.address"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean bn = model.update();
		if(bn){
			renderText("OPT_SUCCESS");
		} else {
			renderText("OPT_ERROR");
		}
	}
	
	public void gotoAdd(){
		render("/page/manager/user/user_add.html");
	}
	
	@Before(LoginUserValidator.class)
	public void save(){
		LoginUser loginUser = getModel(LoginUser.class);
		loginUser.set("password", CryptUtil.encrypt(getPara("loginUser.password")));
		boolean bn = loginUser.save();
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
}
