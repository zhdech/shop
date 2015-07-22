package cn.com.shop.web.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.shop.common.IPageUtil;
import cn.com.shop.common.security.CryptUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Order extends Model<Order>{

	private Logger logger = Logger.getLogger(Order.class);
	
	public static final Order dao = new Order();
	
    
    /**
     * 
    * @Title: getMasterList 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<Order> getOrderList(String condition){
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select orderid,ordername,createname,createmobile,createtime,orderstatus,totalnum,totalamount ","from t_orderlist where orderstatus!=9 "+ condition+" order by orderstatus");
    }
    
    
    /**
     * 
    * @Title: getMasterListByPara 
    * @Description: 根据订单名称 或 建单人手机号查询。 
    * @param @param masterName
    * @param @param mobile
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<Order> getMasterListByPara(String orderName,String mobile){
    	List<Object> param=new ArrayList<Object>();
    	StringBuffer whereStr =new StringBuffer(" where 1=1 ");
    	if(orderName != null && !orderName.equals("")){
    		if(StringUtils.isNotBlank(orderName)){
    			whereStr.append(" and ordername like ?");
    			param.add("%"+orderName+"%");   //注意这行的参数书写方式，对于jfinal来讲，like查询必须使用这个方式
    		}
    		if(StringUtils.isNotBlank(mobile)){
    			whereStr.append(" and createmobile like ? ");
    			param.add("%"+mobile+"%");
    		}
    	}
    	
    	logger.info("执行主帐号的分页查询");
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select orderid,ordername,createname,createmobile,createtime,orderstatus,totalnum,totalamount ","from t_orderlist " + whereStr,param.toArray());
    }
    


	public Order getOrderStatus() {
		return dao.findFirst("select * from t_orderlist where orderstatus=0");
	}
	
	public Order getOrderById(long id)
	{
		return dao.findById(id);
	}

	
	public boolean save() {
		return super.save();
	}
	
	
    public boolean update() {
		return super.update();
	}
}
