package cn.com.shop.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.com.shop.common.IConstants;
import cn.com.shop.web.goods.Goods;
import cn.com.shop.web.login.LoginUser;
import cn.com.shop.web.order.Order;
import cn.com.shop.web.orderinfo.OrderInfo;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
/**
 * 操作xml公用类
* @ClassName: ExcelUtil 
* @author zhdech
* @date 2014年9月7日 下午9:11:26 
*
 */
public class ExcelUtil  {
	
	/**
	 * 读取xml
	* @Title: readXml 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void readXml(String filePathName){
		try {
//			InputStream is = new FileInputStream("D:\\临时文件\\报价单.xls");
			InputStream is = new FileInputStream(filePathName);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 循环工作表Sheet
	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	        	HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	        	if (hssfSheet == null) {
	                continue;
	            }
	        	// 循环行Row
	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	            	HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                if (hssfRow == null) {
	                    continue;
	                }
	                HSSFCell brandHC = hssfRow.getCell(0);
	                if (brandHC == null) {
	                    continue;
	                }
	                String brand = getValue(brandHC);
	                
	                HSSFCell modelHC = hssfRow.getCell(1);
	                if (modelHC == null) {
	                    continue;
	                }
	                String model = getValue(modelHC);
	                
	                HSSFCell colorHC = hssfRow.getCell(2);
	                if (colorHC == null) {
	                    continue;
	                }
	                String color = getValue(colorHC);
	                
	                HSSFCell pirceHC = hssfRow.getCell(3);
	                if (pirceHC == null) {
	                    continue;
	                }
	                String pirce = getValue(pirceHC);
	                
	                Goods goods = new Goods();
	                goods.set("brand", brand);
	                goods.set("model", model);
	                goods.set("color", color);
	                goods.set("pirce", pirce);
	                if(pirce==null || "0".equals(pirce))
	                	goods.set("stock", 0);
	                else
	                	goods.set("stock", 10);
	                
	                Goods goods1 = goods.getGoodsByBrandMC(brand,model,color);
	                if(goods1==null){
	                	goods.save();
	                }else{
	                	goods1.set("pirce", pirce);
	                	goods1.update();
	                }
	                	
	            }
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

	public static void main(String[] args) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://127.0.0.1:3306/shop?characterEncoding=GBK&zeroDateTimeBehavior=convertToNull", "root", "123456");
		cp.start();
		IConstants.jdbcMap.put("DB_TYPE", PropertyManager.getProperty("DB_TYPE"));
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		arp.start();
		new IPropertyUtil();
		arp.addMapping("t_user","id", LoginUser.class);
		arp.addMapping("t_orderlist","orderid", Order.class);
		arp.addMapping("t_orderinfo","id", OrderInfo.class);
		arp.addMapping("t_goods","goodsid", Goods.class);
		arp.refresh();
		readXml(null);
	}
}
