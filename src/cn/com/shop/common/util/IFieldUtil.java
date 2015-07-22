package cn.com.shop.common.util;

import java.util.List;


/**
 * 
* @ClassName: IFieldUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:30:22 
*
 */
public class IFieldUtil {
	public static String fieldJson = "";  
	
	public IFieldUtil()
	{
		loadFieldToJson();
	}
	
	private void loadFieldToJson()
	{
		List<FieldModel> list = FieldModel.dao.getFieldList();
		if(list != null && list.size() > 0)
		{
			fieldJson = "[{";
			for(int i=0;i<list.size();i++)
			{
				if(i==0)
				{
					fieldJson = fieldJson + "'" + list.get(i).getStr("field_name").toUpperCase() + "':'" + list.get(i).getStr("field_desc")+"'";
				}else
				{
					fieldJson = fieldJson + ",'" + list.get(i).getStr("field_name").toUpperCase() + "':'" + list.get(i).getStr("field_desc")+"'";
				}
			}
			fieldJson = fieldJson + "}]";
		}
		
	}
	
	
}
