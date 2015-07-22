package cn.com.shop.web.orderinfo;


import cn.com.shop.common.IPageUtil;
import cn.com.shop.common.security.rights.BaseController;
import cn.com.shop.common.util.IPropertyUtil;
import cn.com.shop.common.util.IStringUtil;
import cn.com.shop.common.util.SearchParaUtil;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

public class OrderInfoController extends BaseController{

	
	public void index(){
		
	}
	
	public void getOrderList(){
		
		//设置条件查询的参数存储 -- 直接拷贝使用
		SearchParaUtil.setAttr(this);
		
		//分页信息，仅供有分页查询的页面使用-- 直接拷贝使用
      	IPageUtil.setPagePara(getPara(0), getPara(1));
		
		//列头信息--使用国际化配置，
        setAttr("headMap", IStringUtil.getJsonToMap(IPropertyUtil.langMap.get("lang.webmanager.ordermanager.maste.tableHeader")));
        
        //获取分页对象，page对象中包含list列表结果集
        Page<OrderInfo> page = getModel(OrderInfo.class).getOrderInfoList(this.getWhereSql());
        //Page<Master> page = getModel(Master.class).getMasterListByPara(masterName);
		
		//列表数据
		setAttr("pageObj", page);
		
		render("/page/manager/ordermanager/order/order_list.html");
	}
	
	public void gotoUpdate(){
		setAttr("master", getModel(OrderInfo.class).getMaterById(Long.valueOf(getPara())));
		render("/page/manager/accmanager/master/master_update.html");
	}
	
	@Before(OrderInfoValidator.class)
	public void update(){
		boolean bn = getModel(OrderInfo.class).update();
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
		
	}
	
	public void save(){
		boolean bn = getModel(OrderInfo.class).save();
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	
	public void gotoSave(){
		render("/page/manager/accmanager/master/master_add.html");
	}
	
	public void delete(){
		boolean bn = getModel(OrderInfo.class).deleteById(Long.valueOf(getPara()));
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	public void getMasterDetail(){
		setAttr("master", getModel(OrderInfo.class).getMaterById(Long.valueOf(getPara())));
		render("/page/manager/accmanager/master/master_detail.html");
	}
	
}
