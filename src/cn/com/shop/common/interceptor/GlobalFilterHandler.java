package cn.com.shop.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfinal.handler.Handler;
import com.jfinal.i18n.I18N;

/**
 * 
* @ClassName: GlobalFilterHandler 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:27:58 
*
 */
public class GlobalFilterHandler extends Handler{

	/**
	 * 默认返回到指定的登录首页
	 */
	private final String redirecturl = "/login.jsp";
	/**
	 * 不被拦截的请求及扩展名
	 */
	//private final String exclude = "login;login.html;.css;.js";
	String[] arrayExcludeStr = new String[]{"_login","login","login.jsp",".css",".js",".eot",".svg",".ttf",".woff"};
	/**
	 * boolean常量，用作是非判断
	 */
	private boolean bn = false;

	/**
	 * 方法名：handle
	 * 描述  ：继承的函数名，用于判断session存储用户的登录状态
	 *
	 * @param target
	 * @param request
	 * @param response
	 * @param isHandled
	 */
	public void handle(String target, HttpServletRequest request,HttpServletResponse response, boolean[] isHandled) {
		long start = System.currentTimeMillis();
		
		HttpSession httpSession = request.getSession();
		 String url = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());   
		 System.out.println("url-----"+url);
		 
		 if(url.equals("/"))
		 {
			 bn = true;
		 }else
		 {
			 for(String str:arrayExcludeStr)
			 {
				 if(url.indexOf(str) > 0 && url.lastIndexOf(".jsp") < 1)
				 {
					 bn = true;
					 break;
				 }
			 }
		 }
		 
		 if(!bn)
		 {
	        if(httpSession != null && httpSession.getAttribute(I18N.getText("vf_common_websys_session_user")) != null){
	        	nextHandler.handle(target, request, response, isHandled);
	        }else{
	            System.out.println("当前帐号还 没有 登录，访问收到限制");
	            try {
					response.sendRedirect(request.getContextPath()+redirecturl);
					nextHandler.handle(target, request, response, isHandled);
				} catch (IOException e) {
					//e.printStackTrace();
				}
	            
	        }
		 }else
		 {
			 nextHandler.handle(target, request, response, isHandled);
		 }
		 bn = false;
		 long end = System.currentTimeMillis();
		
		 System.out.println("rending time:" + (end - start) + "ms");
	}

	
}
