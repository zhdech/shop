package cn.com.shop.common;

/**
 * 
* @ClassName: IPageUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:30:43 
*
 */
public class IPageUtil {

	public static int pageNumber = 1;
	public static int pageSize = 200;
	
	public static void setPagePara(String para0,String para1)
	{
		if(para0!=null)
		{
			pageNumber = Integer.valueOf(para0);
		}else
		{
			pageNumber = 1;
		}
		
		if(para1!=null)
		{
			pageSize = Integer.valueOf(para1);
		}else
		{
			pageSize = 200;
		}
	}
	
	
}
