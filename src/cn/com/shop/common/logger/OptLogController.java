package cn.com.shop.common.logger;

import java.util.List;

import com.jfinal.core.Controller;


/**
 * 
* @ClassName: OptLogController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:26:36 
*
 */
public class OptLogController extends Controller{

	/**
	 * 方法名：getObjOptLog
	 * 描述  ：通过操作对象的id编号获取操作日志记录
	 *
	 */
	public void getObjOptLog()
	{
		OptLogModel logOptDao = new OptLogModel();
		List<OptLogModel> logList = logOptDao.getLogListByPara(Long.valueOf(getPara("optId")));
		renderJson(logList);
	}
	
	
	/**
	 * 方法名：getObjAuthOptLog
	 * 描述  ：通过操作对象的id编号获取授权操作日志记录
	 *
	 */
	public void getObjAuthOptLog()
	{
		OptLogModel logOptDao = new OptLogModel();
		List<OptLogModel> logList = logOptDao.getAuthLogListByPara(Long.valueOf(getPara("optId")));
		renderJson(logList);
	}
	
	public void getOptLog()
	{
		
	}
}
