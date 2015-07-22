package cn.com.shop.web.goods;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class GoodsValidator extends Validator{

	protected void validate(Controller c) {
		validateRequiredString("goods.brand", "brandMsg", "请输入商品品牌名称");
		validateRequiredString("goods.model","modelMsg","请输入商品型号");
		validateRequiredString("goods.color", "colorMsg", "请输入商品颜色");
		validateRequiredString("goods.pirce","pirceMsg","请输入售价");
	}

	protected void handleError(Controller c) {
		c.keepModel(Goods.class);
		if(getActionKey().equals("/goods/save"))
		{
			c.render("/page/manager/goods/goods_add.html");
		}else if(getActionKey().equals("/master/update"))
		{
			c.render("/page/manager/goods/goods_update.html");
		}
	}

}
