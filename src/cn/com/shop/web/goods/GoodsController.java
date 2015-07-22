package cn.com.shop.web.goods;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import cn.com.shop.common.IPageUtil;
import cn.com.shop.common.security.rights.BaseController;
import cn.com.shop.common.util.ExcelUtil;
import cn.com.shop.common.util.IPropertyUtil;
import cn.com.shop.common.util.IStringUtil;
import cn.com.shop.common.util.SearchParaUtil;
import cn.com.shop.common.util.SequenceUtil;
import cn.com.shop.web.login.LoginUser;
import cn.com.shop.web.orderinfo.OrderInfo;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

public class GoodsController extends BaseController{

	
	public void index(){
		
	}
	
	public void getGoodsList(){
		
		//设置条件查询的参数存储 -- 直接拷贝使用
		SearchParaUtil.setAttr(this);
		
		//分页信息，仅供有分页查询的页面使用-- 直接拷贝使用
//      	IPageUtil.setPagePara(getPara(0), getPara(1));
		
		//列头信息--使用国际化配置，
        setAttr("headMap", IStringUtil.getJsonToMap(IPropertyUtil.langMap.get("lang.webmanager.goods.tableHeader")));
        
        //获取分页对象，page对象中包含list列表结果集
        List<Goods> page = getModel(Goods.class).getGoodsList(this.getWhereSql());//Page<Master> page = getModel(Master.class).getMasterListByPara(masterName);
		
		//列表数据
		setAttr("pageObj", page);
		
		render("/page/manager/goods/goods_list.html");
	}
	
	
	public void gotoUpdate(){
		setAttr("goods", getModel(Goods.class).getGoodsById(Long.valueOf(getPara())));
		render("/page/manager/goods/goods_update.html");
	}
	
	public void update(){
		String id = getPara("goods.goodsid");
		String pirce = getPara("goods.pirce");
		String stock = getPara("goods.stock");
		String remark = getPara("goods.remark");
		Goods goods = new Goods();
		goods.set("goodsid", id);
		goods.set("pirce", pirce);
		goods.set("stock", stock);
		goods.set("remark", remark);
		boolean bn = goods.update();
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	public void gotoSave(){
		render("/page/manager/goods/goods_add.html");
	}
	
	@Before(GoodsValidator.class)
	public void save(){
		boolean bn = getModel(Goods.class).save();
		if(bn){
			renderText("OPT_SUCCESS");
		} else {
			renderText("OPT_ERROR");
		}
	}
	
	public void getGoodsDetail(){
		setAttr("master", getModel(Goods.class).getGoodsById(Long.valueOf(getPara())));
		render("/page/manager/goods/goods_detail.html");
	}
	
	/**
	 * 删除购物车商品
	* @Title: deleteShop 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteGoods(){
		boolean bn = getModel(Goods.class).deleteById(Long.valueOf(getPara()));
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	/**
	 * 查询购物车商品
	* @Title: getShopList 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getShopList(){
		LoginUser loginUser = (LoginUser)getSession().getAttribute("master");
		//设置条件查询的参数存储 -- 直接拷贝使用
		SearchParaUtil.setAttr(this);
		
		//分页信息，仅供有分页查询的页面使用-- 直接拷贝使用
      	IPageUtil.setPagePara(getPara(0), getPara(1));
		
		//列头信息--使用国际化配置，
        setAttr("headMap", IStringUtil.getJsonToMap(IPropertyUtil.langMap.get("lang.webmanager.shop.tableHeader")));
        
        //获取分页对象，page对象中包含list列表结果集
        Page<Goods> page = getModel(Goods.class).getShopsList(this.getWhereSql(),loginUser);//Page<Master> page = getModel(Master.class).getMasterListByPara(masterName);
		
		//列表数据
		setAttr("pageObj", page);
		
		render("/page/manager/shop/shop_list.html");
	}
	
	/**
	 * 添加购物车
	* @Title: save 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void saveShop(){
		LoginUser loginUser = (LoginUser)getSession().getAttribute("master");
		String loginName = loginUser.getStr("name");
		List<OrderInfo> orderInfoList = new OrderInfo().getOrderInfoByName(loginName,"");
		int orderId = 0;
		String pirce = getPara("pirce");
		String goodsId = getPara("goodsId");
		String goodNum = getPara("goodNum");
		boolean isUpdate = false;
		boolean bn = false;
		if(orderInfoList!=null){
			for (int i = 0; i < orderInfoList.size(); i++) {
				OrderInfo orderInfo = orderInfoList.get(i);
				if(orderInfo!=null){
					String y_goodsid = orderInfo.get("goodsid")+"";
					if(y_goodsid.equals(goodsId)){
						bn = getModel(OrderInfo.class).update(orderInfo,goodNum);
						isUpdate = true;
						break;
					}
					orderId = Integer.parseInt(orderInfo.get("orderid")+"");
				}
				
			}
			setAttr("shoppingsum", orderInfoList.size());
			setAttr("dechong", "dechong");
		}
		if(orderId == 0)
			orderId = SequenceUtil.getLogSeq();
		if(!isUpdate){
			bn = getModel(OrderInfo.class).save(orderId,goodsId,pirce,goodNum,loginName);
		}
		
		if(bn){
			renderText("OPT_SUCCESS");
		} else {
			renderText("OPT_ERROR");
		}
	}
	
	/**
	 * 更新购物车商品
	* @Title: update 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateShop(){
		String id = getPara("goodsId");
		String goodNum = getPara("goodNum");
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.set("id", id);
		orderInfo.set("number", goodNum);
		boolean bn = orderInfo.update();
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	
	/**
	 * 删除购物车商品
	* @Title: deleteShop 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteShop(){
		boolean bn = getModel(OrderInfo.class).deleteById(Long.valueOf(getPara()));
		if(bn)
		{
			renderText("OPT_SUCCESS");
		}else
		{
			renderText("OPT_ERROR");
		}
	}
	
	/**
	 * 转向导入模板页面
	* @Title: getImportGoods 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void gotoImportGoods(){
		render("/page/manager/goods/goods_import.html");
	}
	
	/**
	 * 根据模板文件导入数据
	* @Title: importGoods 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void importGoods(){
		UploadFile uploadFile = getFile("fileName");
		File file = uploadFile.getFile();
		ExcelUtil.readXml(file.getPath());
//			BufferedReader br = new BufferedReader(new FileReader(uploadFile.getFile()));
//			for (int i = 0; i < 5; i++) {
//				String pwd = br.readLine();
//				System.out.println(pwd);
//		render("/page/manager/goods/goods_import.html");
		renderText("OPT_SUCCESS");
	}
}
