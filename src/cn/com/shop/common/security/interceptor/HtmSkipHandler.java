package cn.com.shop.common.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;


/**
 * 
* @ClassName: HtmSkipHandler 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:28:50 
*
 */
public class HtmSkipHandler extends Handler{

	
	/**  方法名：handle
	 * 描述  ：
	 *
	 * @param target
	 * @param request
	 * @param response
	 * @param isHandled 
	 */
	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		int index = target.lastIndexOf(".html");
		if(index != -1)
		{
			target = target.substring(0, index);
		}
		nextHandler.handle(target, request, response, isHandled);
	}

}
