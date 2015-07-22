package cn.com.shop.common.security.interceptor;

import cn.com.shop.common.config.AuthenticatedFreeMarkerMethod;
import cn.com.shop.common.config.HasAnyRolesFreeMarkerMethod;
import cn.com.shop.common.config.HasRoleFreeMarkerMethod;
import cn.com.shop.common.security.HasPermissionFreeMarkerMethod;
import cn.com.shop.common.util.IFieldUtil;
import cn.com.shop.common.util.IPropertyUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class FreeMarkerInterceptor implements Interceptor{

	@Override
	public void intercept(ActionInvocation ai) {
		 Controller c = ai.getController();
         c.setAttr("hasRole", new HasRoleFreeMarkerMethod());
         c.setAttr("hasAnyRoles", new HasAnyRolesFreeMarkerMethod());
         c.setAttr("hasPermission", new HasPermissionFreeMarkerMethod());
         c.setAttr("isAuthenticated", new AuthenticatedFreeMarkerMethod());
         
         //自定义国际化获取
         c.setAttr("langMap", IPropertyUtil.langMap);
         //自定义字典获取
         c.setAttr("codeMap", IPropertyUtil.codeMap);
         //获取标准字段的中文释义（json格式字符串）
         c.setAttr("fieldJson", IFieldUtil.fieldJson);
         
         // 执行正常逻辑
         ai.invoke();

	}

}
