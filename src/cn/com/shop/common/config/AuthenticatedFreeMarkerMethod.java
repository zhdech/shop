package cn.com.shop.common.config;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@SuppressWarnings("deprecation")
public class AuthenticatedFreeMarkerMethod implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		if (null == list || 1 != list.size()) {
			throw new TemplateModelException("Wrong arguments: only one argument is allowed");
		}
		String authenticated = (String) list.get(0);

		return true;
	}
}
