package cn.com.shop.web.login;


import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("j_username", "j_usernameMsg", "登录帐号不能为空！");
		validateRequiredString("j_password", "j_passwordMsg", "登录密码不能为空！");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepPara("j_username");
		c.keepPara("j_password");
		c.render("/page/login/login.html");
	}

}
