package cn.com.shop.web.orderinfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.shop.common.IPageUtil;
import cn.com.shop.common.security.CryptUtil;
import cn.com.shop.common.util.SequenceUtil;
import cn.com.shop.web.goods.Goods;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class OrderInfo extends Model<OrderInfo>{

	private Logger logger = Logger.getLogger(OrderInfo.class);
	
	public static final OrderInfo dao = new OrderInfo();
	
    
    /**
     * 
    * @Title: getOrderInfoList 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<OrderInfo> getOrderInfoList(String condition){
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select id,orderid,goodsid,amount,number,loginname,status ","from t_orderinfo where 1=1 "+ condition);
    }
    
    /**
     * 
    * @Title: getOrderInfoByName 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    
    public List<OrderInfo> getOrderInfoByName(String loginName,String goodsId) {
    	String where = "";
    	if(StringUtils.isNotBlank(loginName)){
    		where += " and loginname='"+loginName+"'";
    	}else if(StringUtils.isNotBlank(goodsId)){
    		where += " and goodsid="+goodsId;
    	}
    	return dao.find("select id,orderid,goodsid,amount,number,loginname from t_orderinfo where 1=1 and status=0 "+where);
//		return dao.findFirst("select orderid,goodsid,amount,number,loginname from t_orderinfo where 1=1 and loginname=? and status=0 ", loginName);
	}
    
    
    /**
     * 
    * @Title: getOrderInfoByPara 
    * @Description: 根据订单名称 或 建单人手机号查询。 
    * @param @param masterName
    * @param @param mobile
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<OrderInfo> getOrderInfoByPara(String loginName){
    	List<Object> param=new ArrayList<Object>();
    	StringBuffer whereStr =new StringBuffer(" where 1=1 ");
    	if(loginName != null && !loginName.equals("")){
    		if(StringUtils.isNotBlank(loginName)){
    			whereStr.append(" and loginname like ?");
    			param.add("%"+loginName+"%");   //注意这行的参数书写方式，对于jfinal来讲，like查询必须使用这个方式
    		}
    	}
    	
    	logger.info("执行主帐号的分页查询");
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select id,orderid,goodsid,amount,number,loginname ","from t_orderinfo " + whereStr,param.toArray());
    }
    
	public OrderInfo getMaterById(long id){
		return dao.findById(id);
	}

	
	public boolean save(int orderId,String goodsId,String pirce,String goodNum,String loginName) {
		this.set("orderid", orderId);
		this.set("goodsid", goodsId);
		this.set("amount", pirce);
		this.set("number", goodNum);
		this.set("loginname", loginName);
		this.set("id", SequenceUtil.getLogSeq());
		return super.save();
	}
	
	
    public boolean update(OrderInfo orderInfo,String goodsNum) {
    	try {
    		int number = Integer.parseInt(orderInfo.get("number")+"")+Integer.parseInt(goodsNum);
    		orderInfo.set("number", number);
    		orderInfo.set("id", orderInfo.getNumber("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return orderInfo.update();
	}
    
    /**
     * 
    * @Title: getOrderInfoList 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<OrderInfo> getOrderInfoList(){
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select goodsid,brand,model,color,pirce,stock,remark  ","from t_orderinfo where 1=1 ");
    }
    
    /**
	 * 更新购物车商品状态为已下单
	* @Title: update 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public boolean updateShopStatus(String loginName){
		List<OrderInfo> orderInfoList = new OrderInfo().getOrderInfoByName(loginName,"");
		boolean bn = false;
		if(orderInfoList!=null){
			for (int i = 0; i < orderInfoList.size(); i++) {
				OrderInfo orderInfo = orderInfoList.get(i);
				orderInfo.set("status", 1);
				bn = orderInfo.update();
			}
		}
		return bn;
	}
}
