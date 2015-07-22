package cn.com.shop.web.orderinfo;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class OrderInfoValidator extends Validator{

	protected void validate(Controller c) {
		validateRequiredString("master.name", "nameMsg", "请输入主帐号名称");
		validateRequiredString("master.password","passMsg","请输入密码");
	}

	protected void handleError(Controller c) {
		c.keepModel(OrderInfo.class);
		if(getActionKey().equals("/master/save"))
		{
			c.render("/page/manager/accmanager/master/master_add.html");
		}else if(getActionKey().equals("/master/update"))
		{
			c.render("/page/manager/accmanager/master/master_update.html");
		}
	}

}
