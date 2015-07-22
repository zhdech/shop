package cn.com.shop.common.config;


import cn.com.shop.common.IConstants;
import cn.com.shop.common.interceptor.ContextInterceptor;
import cn.com.shop.common.security.interceptor.FreeMarkerInterceptor;
import cn.com.shop.common.security.interceptor.HtmSkipHandler;
import cn.com.shop.common.security.interceptor.InterceptorFilter;
import cn.com.shop.common.util.IPropertyUtil;
import cn.com.shop.web.goods.Goods;
import cn.com.shop.web.login.LoginUser;
import cn.com.shop.web.login.LoginUserLog;
import cn.com.shop.web.order.Order;
import cn.com.shop.web.orderinfo.OrderInfo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
/**
 * 
* @ClassName: BasicConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:27:20 
*
 */
public class BasicConfig extends JFinalConfig{
	
	private ActiveRecordPlugin arp = null;
	
	/**
	 * 供Shiro插件使用。
	 */
	Routes routes = new Routes(){public void config() {}};;
	

	@Override
	public void configConstant(Constants constants) {
		// 加载少量必要配置，随后可用getProperty(...)获取值
		loadPropertyFile("classes/jdbc.properties");				
		constants.setDevMode(getPropertyToBoolean("DevMode", false));
		

		//错误页面转向配置
		constants.setErrorView(401, "/page/common/error.html");
		constants.setErrorView(403, "/page/common/error.html");
		constants.setErrorView(404, "/page/common/error.html");
		
		constants.setErrorView(501, "/page/common/error.html");
		constants.setErrorView(505, "/page/common/error.html");
	}

	@Override
	public void configHandler(Handlers handler) {
		handler.add(new ContextPathHandler("path"));
		//handler.add(new UrlSkipHandler(".+\\.\\w{1,4}", true));
		handler.add(new HtmSkipHandler());
	}

	@Override
	public void configInterceptor(Interceptors interCeptor) {
		interCeptor.add(new SessionInViewInterceptor());
		interCeptor.add(new ContextInterceptor());
		interCeptor.add(new FreeMarkerInterceptor());
		interCeptor.add(new InterceptorFilter());
	}

	@Override
	public void configPlugin(Plugins plugin) {
		
		IConstants.jdbcMap.put("DB_TYPE", getProperty("DB_TYPE"));
		if(getProperty("DB_TYPE").equals("mysql"))
		{
			//mysql数据库
			// 配置C3p0数据库连接池插件
			C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("DB_URL").trim(), getProperty("DB_USER").trim(), getProperty("DB_PASSWORD").trim());
			c3p0Plugin.setDriverClass(getProperty("DB_DRIVER").trim());
			plugin.add(c3p0Plugin);
			
			// 配置ActiveRecord插件
			arp = new ActiveRecordPlugin(c3p0Plugin);
			plugin.add(arp);
		}else{
			//oracle数据库
			//配置C3p0数据库连接池插件
			C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("DB_URL").trim(), getProperty("DB_USER").trim(), getProperty("DB_PASSWORD").trim());
			c3p0Plugin.setDriverClass(getProperty("DB_DRIVER").trim());
			plugin.add(c3p0Plugin);
			
			// 配置ActiveRecord插件
			arp = new ActiveRecordPlugin(c3p0Plugin);
			plugin.add(arp);
			
			//配置oracle数据库方言
			arp.setDialect(new OracleDialect());
			//配置属性名（字段名）大小写不敏感的容器工厂
			arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		}
		
		//加载数据表名 与 class的映射
//		arp.addMapping("T_MODULE_ORM", TableClassModel.class);
		
		//加载Shiro插件
		plugin.add(new ShiroPlugin(routes));
		
	}
	
//	public void afterJFinalStart(){
//		new IPropertyUtil();
//		//web启动时 ，动态从数据库中加载表名和class类的对应关系
//		List<TableClassModel> list = TableClassModel.dao.getTableClassList();
//		for(int i=0;i<list.size();i++){
//			TableClassModel tableClassModel = list.get(i);
//			try {
//				arp.addMapping(tableClassModel.getStr("tablename"), Class.forName(tableClassModel.getStr("classname")));
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		//重新加载
//		arp.refresh();
//		
//		new IFieldUtil();
//	}
	public void afterJFinalStart(){
		new IPropertyUtil();
		arp.addMapping("t_user","id", LoginUser.class);
		arp.addMapping("t_orderlist","orderid", Order.class);
		arp.addMapping("t_orderinfo","id", OrderInfo.class);
		arp.addMapping("t_goods","goodsid", Goods.class);
		arp.addMapping("t_user_log","id", LoginUserLog.class);
		//重新加载
		arp.refresh();
	}

	/**
	 * 配置处理器
	 */
	public void configRoute(Routes route) {
		 this.routes = route;
		
//		 route.add("/optlog",OptLogController.class);
//		 route.add("/login",LoginController.class);
		 
		 //路由自动扫描配置
		 AutoBindRoutes abs = new AutoBindRoutes();
		 abs.config(route);
		
	}
	
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5); 
	}

}
