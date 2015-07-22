package cn.com.shop.common.config;



import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class TableClassModel extends Model<TableClassModel>{
	
	public static final TableClassModel dao = new TableClassModel();
	
	public List<TableClassModel> getTableClassList() {
    	List<TableClassModel> list = new ArrayList<TableClassModel>();
		list = dao.find("select moduleid,tablename,classname from T_module_orm where 1=1");
		return list;
	}
	
}
