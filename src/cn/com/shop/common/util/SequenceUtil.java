package cn.com.shop.common.util;

import cn.com.shop.common.IConstants;

import com.jfinal.plugin.activerecord.Db;

public class SequenceUtil  {

	public static int getSequence()
	{
		if((IConstants.jdbcMap.get("DB_TYPE")).equals("mysql"))
		{
			return Db.queryInt(IConstants.GLOBAL_MYSQL_SEQUENCE).intValue();
		}else
		{
			return Db.queryNumber(IConstants.GLOBAL_ORACLE_SEQUENCE).intValue();
		}
	}
	
	
	public static int getLogSeq()
	{
		if((IConstants.jdbcMap.get("DB_TYPE")).equals("mysql"))
		{
			return Db.queryInt(IConstants.GLOBAL_MYSQL_LOGSEQ).intValue();
		}else
		{
			return Db.queryNumber(IConstants.GLOBAL_ORACLE_LOGSEQ).intValue();
		}
	}
	
	public static int getScheduleSeq() {
		if ((IConstants.jdbcMap.get("DB_TYPE")).equals("mysql")) {
			return Db.queryInt(IConstants.GLOBAL_MQSQL_SCHEDULEDSEQ).intValue();
		} else {
			return Db.queryNumber(IConstants.GLOBAL_ORACLE_SCHEDULEDSEQ)
					.intValue();
		}
	}
}
