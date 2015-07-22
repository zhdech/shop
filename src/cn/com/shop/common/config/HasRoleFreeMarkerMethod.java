package cn.com.shop.common.config;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@SuppressWarnings("deprecation")
public class HasRoleFreeMarkerMethod implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		if (null == list || 1 != list.size()) {
			throw new TemplateModelException("Wrong arguments: only one argument is allowed");
		}
		String roleName = (String) list.get(0);
		
		boolean bn = roleName != null && roleName.length() > 0;

		return bn;
	}

}
