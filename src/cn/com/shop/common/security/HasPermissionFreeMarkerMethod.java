package cn.com.shop.common.security;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@SuppressWarnings("deprecation")
public class HasPermissionFreeMarkerMethod implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		if (null == list || 1 != list.size()) {
			throw new TemplateModelException("Wrong arguments: only one argument is allowed");
		}
		String permission = (String) list.get(0);

		boolean bn = false;
        bn = permission != null && permission.length() > 0;
		return bn;
	}
	
}
