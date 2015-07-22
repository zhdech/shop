/**   
* @Title: ImportShopTest.java 
* @Package cn.com.shop 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhdech
* @date 2014年9月7日 下午10:04:07 
* @version V1.0   
*/
package cn.com.shop;

import cn.com.shop.common.IConstants;
import cn.com.shop.common.util.IPropertyUtil;
import cn.com.shop.common.util.PropertyManager;
import cn.com.shop.web.goods.Goods;
import cn.com.shop.web.login.LoginUser;
import cn.com.shop.web.order.Order;
import cn.com.shop.web.orderinfo.OrderInfo;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/** 
 * @ClassName: ImportShopTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhdech
 * @date 2014年9月7日 下午10:04:07 
 *  
 */
public class ImportShopTest{

	public static void main(String[] args) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=GBK&zeroDateTimeBehavior=convertToNull", "root", "123456");
		cp.start();
		IConstants.jdbcMap.put("DB_TYPE", PropertyManager.getProperty("DB_TYPE"));
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		arp.start();
		new IPropertyUtil();
		arp.addMapping("t_user","id", LoginUser.class);
		arp.addMapping("t_orderlist","orderid", Order.class);
		arp.addMapping("t_orderinfo","id", OrderInfo.class);
		arp.addMapping("t_goods","goodsid", Goods.class);
		arp.refresh();
//		readXml(null);
	}
}
