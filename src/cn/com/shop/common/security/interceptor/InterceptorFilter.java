package cn.com.shop.common.security.interceptor;


import javax.servlet.http.HttpSession;

import cn.com.shop.web.login.LoginUser;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * 
* @ClassName: InterceptorFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:28:58 
*
 */
public class InterceptorFilter implements Interceptor{


	@Override
	public void intercept(ActionInvocation ai) {
		HttpSession hs = ai.getController().getSession(false);
		if(hs!=null){
			Object masterObj = hs.getAttribute("master");
			if(masterObj==null){
				boolean bn = false;
				if((ai.getActionKey().equals("/") && ai.getMethodName().endsWith("index")) || 
						(ai.getActionKey().equals("/loginManager") && ai.getMethodName().endsWith("loginManager")) ||
						(ai.getActionKey().equals("/login") && ai.getMethodName().endsWith("login")||
						(ai.getActionKey().equals("/_login") && ai.getMethodName().endsWith("_login")))){
					bn = true;
				}
				if(bn){
					ai.invoke();
				}else{
					ai.getController().setAttr("errorInfo","当前行为的帐号没有登录或登录超时，请登录后操作！");
					ai.getController().redirect("/");
				}
			}else{
				ai.invoke();
			}
		}else{
			ai.invoke();
		}
	}
	
}
