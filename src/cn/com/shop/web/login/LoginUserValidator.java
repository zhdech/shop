package cn.com.shop.web.login;


import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginUserValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("loginUser.name", "nameMsg", "请输入登录名");
		validateRequiredString("loginUser.password","passMsg","请输入密码");
		validateRequiredString("loginUser.username", "userNameMsg", "请输入姓名");
		validateRequiredString("loginUser.mobile","mobileMsg","请输入手机号");
		validateRequiredString("loginUser.address", "addMsg", "请输入发货地址");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepModel(LoginUser.class);
		if(getActionKey().equals("/loginUser/save"))
		{
			c.render("/page/manager/user/user_add.html");
		}
	}

}
