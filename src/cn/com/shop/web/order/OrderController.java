package cn.com.shop.web.order;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.com.shop.common.IPageUtil;
import cn.com.shop.common.security.rights.BaseController;
import cn.com.shop.common.util.IPropertyUtil;
import cn.com.shop.common.util.IStringUtil;
import cn.com.shop.common.util.SearchParaUtil;
import cn.com.shop.web.goods.Goods;
import cn.com.shop.web.login.LoginUser;
import cn.com.shop.web.orderinfo.OrderInfo;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

public class OrderController extends BaseController{

	
	public void index(){
		
	}
	
	public void getOrderList(){
		
		//设置条件查询的参数存储 -- 直接拷贝使用
		SearchParaUtil.setAttr(this);
		
		//分页信息，仅供有分页查询的页面使用-- 直接拷贝使用
      	IPageUtil.setPagePara(getPara(0), getPara(1));
		
		//列头信息--使用国际化配置，
//        setAttr("headMap", IStringUtil.getJsonToMap(IPropertyUtil.langMap.get("lang.webmanager.ordermanager.maste.tableHeader")));
        
        //获取分页对象，page对象中包含list列表结果集
        Page<Order> page = getModel(Order.class).getOrderList(this.getWhereSql());//Page<Master> page = getModel(Master.class).getMasterListByPara(masterName);
		
		//列表数据
		setAttr("pageObj", page);
		
		render("/page/manager/ordermanager/order/order_list.html");
	}
	
	
	public void gotoUpdate(){
		setAttr("master", getModel(Order.class).getOrderById(Long.valueOf(getPara())));
		render("/page/manager/accmanager/master/master_update.html");
	}
	
	@Before(OrderValidator.class)
	public void update(){
		boolean bn = getModel(Order.class).update();
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
		
	}
	
	public void save(){
		LoginUser loginUser = (LoginUser)getSession().getAttribute("master");
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		String milliseconds = simpledateformat.format(new Date());
		String loginName = loginUser.getStr("name");
		String mobile = loginUser.getStr("mobile");
		String goodNum = getPara("goodNum");
		String orderId = getPara("orderId");
		String total = getPara("total");
		Order order = new Order();
		order.set("orderid", orderId);
		order.set("ordername", loginName+milliseconds);
		order.set("createname", loginName);
		order.set("createmobile", mobile);
		order.set("totalnum", goodNum);
		order.set("totalamount", total);
		boolean bn = false;
		try {
			bn = order.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bn){
			new OrderInfo().updateShopStatus(loginName);
			renderText("OPT_SUCCESS");
		}else{
			renderText("OPT_ERROR");
		}
	}
	
	
	public void gotoSave(){
		render("/page/manager/accmanager/master/master_add.html");
	}
	
	public void delete(){
		boolean bn = getModel(Order.class).deleteById(Long.valueOf(getPara()));
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	public void getOrderDetail(){
		setAttr("headMap", IStringUtil.getJsonToMap(IPropertyUtil.langMap.get("lang.webmanager.shop.tableHeader")));
		//得到订单的信息
		Order orderList = getModel(Order.class).getOrderById(Long.valueOf(getPara()));
		setAttr("orderList", orderList);
		//得到收货人帐号
		String loginName = orderList.getStr("createname");
		//得到购买的商品列表
		String where = " and o.loginname='"+loginName+"' and o.orderid = "+Long.valueOf(getPara());
		List<Goods> goodsList = getModel(Goods.class).getOrderList(where);
		setAttr("goodsList", goodsList);
		//得到收货人的详细信息
		LoginUser user = getModel(LoginUser.class).getMasterByName(loginName);
		setAttr("user", user);
		render("/page/manager/ordermanager/order/order_detail.html");
	}
	
	/**
	 * 更新订单状态。更新 为发货状态
	* @Title: updateOrder 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateOrder(){
		long orderId = Long.valueOf(getPara());
		Order order = getModel(Order.class).getOrderById(orderId);
//		order.set("orderid", orderId);
		order.set("orderstatus", 1);
		boolean bn = order.update();
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	public void queryOrderStatus(){
		Order order = getModel(Order.class).getOrderStatus();
		if(order!=null){
			renderText("true");
		}else{
			renderText("false");
		}
	}
}
