package cn.com.shop.common.util;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 
* @ClassName: FieldModel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 上午10:30:09 
*
 */
@SuppressWarnings("serial")
public class FieldModel extends Model<FieldModel>{

	public static final FieldModel dao = new FieldModel();
	
	public List<FieldModel> getFieldList()
	{
		return dao.find("select field_name,field_desc from T_sys_standard_field where 1=1");
	}
}
