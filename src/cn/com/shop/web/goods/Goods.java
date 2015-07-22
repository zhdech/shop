package cn.com.shop.web.goods;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.shop.common.IPageUtil;
import cn.com.shop.common.util.SequenceUtil;
import cn.com.shop.web.login.LoginUser;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Goods extends Model<Goods>{

	private Logger logger = Logger.getLogger(Goods.class);
	
	public static final Goods dao = new Goods();
	
	/**
	 * 
	* @Title: getMasterList 
	* @Description: 查询所有的商品
	* @param @return    设定文件 
	* @return List<Order>    返回类型 
	* @throws
	 */
    public List<Goods> getGoodsList() {
    	List<Goods> list = new ArrayList<Goods>();
		try {
			list = dao.find("select goodsid,brand,model,color,pirce,stock,remark from t_goods");
		} catch (Exception e) {
			logger.error("获取商品列表查询出现异常", e);
		}
		return list;
	}
    
    /**
     * 
    * @Title: getMasterList 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<Goods> getGoodsListByPage(String condition){
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select goodsid,brand,model,color,pirce,stock,remark ",
    			" from t_goods where 1=1 "+ condition +" group by brand model");
    }
    
    /**
     * 
    * @Title: getMasterList 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public List<Goods> getGoodsList(String condition){
    	String sql = "select goodsid,brand,model,color,pirce,stock,remark from t_goods where 1=1 "+ condition +" group by brand,model";
    	return dao.find(sql);
    }
    
    /**
     * 
    * @Title: getMasterList 
    * @Description: 根据查询 条件进行查询 订单 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Order>    返回类型 
    * @throws
     */
    public Page<Goods> getShopsList(String condition,LoginUser user){
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select o.orderid orderid,o.goodsid goodsid,o.number number,"
    			+ "o.id id,g.brand brand,g.model model,g.color color,g.pirce pirce,concat(g.brand,'_',g.model,'_',g.color) good,"
    			+ "o.number*g.pirce totalnum ","from t_orderinfo o left join t_goods g on g.goodsid = o.goodsid where o.status=0 "
    			+" and o.loginname='"+ user.getStr("name")+"' "
    			+ condition+" order by g.brand");
    }
    
    /**
     * 获取订单信息
    * @Title: getOrderList 
    * @param @param condition
    * @param @return    设定文件 
    * @return Page<Goods>    返回类型 
    * @throws
     */
    public List<Goods> getOrderList(String condition){
    	String sql = "select o.orderid orderid,o.goodsid goodsid,o.number number,"
    			+ "o.id id,g.brand brand,g.model model,g.color color,g.pirce pirce,concat(g.brand,'_',g.model,'_',g.color) good,"
    			+ "o.number*g.pirce totalnum from t_orderinfo o left join t_goods g on g.goodsid = o.goodsid where o.status=1 "
    			+ condition+" order by g.brand";
    	return dao.find(sql);
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
    public Page<Goods> getGoodsListByPara(String orderName,String mobile){
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
    	return dao.paginate(IPageUtil.pageNumber, IPageUtil.pageSize, "select goodsid,brand,model,color,pirce,stock,remark ","from t_goods " + whereStr,param.toArray());
    }
    


    /**
     * 通过商品型号得到所有的型 号列表
    * @Title: getGoodsByModel 
    * @param @param model
    * @param @return    设定文件 
    * @return Goods    返回类型 
    * @throws
     */
	public Goods getGoodsByModel(String model) {
		return dao.findFirst("select goodsid,brand,model,color,pirce,stock,remark from t_goods where model=?", model);
	}
	
	/**
     * 通过品牌，商品型号，颜色 得到所有的型 号列表
    * @Title: getGoodsByModel 
    * @param @param model
    * @param @return    设定文件 
    * @return Goods    返回类型 
    * @throws
     */
	public Goods getGoodsByBrandMC(String brand,String model,String color) {
		return dao.findFirst("select goodsid,brand,model,color,pirce,stock,remark from t_goods where brand=? and "
				+ " model=? and color=?",brand, model,color);
	}
	
	public Goods getGoodsById(long id)
	{
		return dao.findById(id);
	}

	
	public boolean save() {
		//设置ID
//		this.set("goodsid", SequenceUtil.getSequence());
		
		return super.save();
	}
	
	/**
	 * 更新操作
	 */
    public boolean update() {
		return super.update();
	}
    
}
