package cn.com.shop.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.kit.StringKit;

/**
 * 获取全局的ContextPath供freemarker使用
* @ClassName: ContextInterceptor 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:27:46 
*
 */
public class ContextInterceptor implements Interceptor {
	private String contextPathKey = "ctx";
	private String namespaceKey = "name_space";

	public ContextInterceptor() {
	}

	public ContextInterceptor(String base) {
		if (StringKit.isBlank(base)) {
			throw new IllegalArgumentException(
					"contextPathKey can not be blank.");
		}
		this.contextPathKey = base;
	}

	public ContextInterceptor(String base, String namespace) {
		if (!StringKit.notBlank(base, namespace)) {
			throw new IllegalArgumentException(
					"contextPathKey can not be blank.");
		}
		this.contextPathKey = base;
		this.namespaceKey = namespace;
	}

	public void intercept(ActionInvocation ai) {
		ai.invoke();
		HttpServletRequest request = ai.getController().getRequest();
		request.setAttribute(contextPathKey, request.getContextPath());
		request.setAttribute(namespaceKey, ai.getControllerKey());
	}
}
