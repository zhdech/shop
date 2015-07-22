package cn.com.shop.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class IPropertyUtil {

	public static Map<String, String> langMap = new HashMap<String, String>();  
	public static Map<String, Map<String,String>> codeMap = new HashMap<String, Map<String,String>>(); 
	public static Map<String, String> sysMap = new HashMap<String, String>();
	private Properties langProperties = null;
	private Properties codeProperties = null;
	private Properties sysProperties = null;
    private String langFilePath = "/web_lang_zh_CN.properties";  
    private String codeFilePath = "/web_code_zh_CN.properties";
    private String sysFilePath = "/system.properties";
	
	private Properties manager = null; 
      
    public IPropertyUtil() {  
        if(langMap.size() == 0 && codeMap.size() == 0)
        {
        	loadLangFileProps();
        	loadCodeFileProps();
        	loadSysFileProps();
        	propertyToMap();
        	
        	
        }
    }  
    
    public IPropertyUtil(String[] filePath) {  
        this.langFilePath = filePath[0];  
        this.codeFilePath = filePath[1];
        if(langMap.size() == 0 && codeMap.size() == 0)
        {
        	loadLangFileProps();
        	loadCodeFileProps();
        	propertyToMap();
        }
    }  
    
    
    /**
     * 方法名：loadLangFileProps
     * 描述  ：加载国际化文件
     *
     */
    private void loadLangFileProps() {
    	langProperties = new Properties();
        InputStream in = null;
        try {
        	System.out.println(getClass());
            in = getClass().getResourceAsStream(this.langFilePath);
        	//in = new FileInputStream(this.langFilePath);
        	langProperties.load(in);
        }
        catch (Exception e) {
            System.err.println("Error reading Application properties in PropertyUtil.loadLangFileProps() " + e);
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (Exception e) { }
        }
    }
    
    /**
     * 方法名：loadCodeFileProps
     * 描述  ：加载code值，每个codekey对应的value必须是json对象
     *
     */
    private void loadCodeFileProps() {
    	codeProperties = new Properties();
        InputStream in = null;
        try {
        	System.out.println(getClass());
            in = getClass().getResourceAsStream(this.codeFilePath);
        	//in = new FileInputStream(this.codeFilePath);
        	codeProperties.load(in);
        }
        catch (Exception e) {
            System.err.println("Error reading Application properties in PropertyUtil.loadCodeFileProps() " + e);
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (Exception e) { }
        }
    }
    
    
    /**
     * 方法名：loadSysFileProps
     * 描述  ：加载web系统的相关配置
     *
     */
    private void loadSysFileProps() {
    	sysProperties = new Properties();
        InputStream in = null;
        try {
        	System.out.println(getClass());
            in = getClass().getResourceAsStream(this.sysFilePath);
        	//in = new FileInputStream(this.langFilePath);
            sysProperties.load(in);
        }
        catch (Exception e) {
            System.err.println("Error reading Application properties in PropertyUtil.loadSysFileProps() " + e);
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (Exception e) { }
        }
    }
    
    
    private void propertyToMap()
    {
    	//将国际化信息转化至map中
    	langMap = (Map) langProperties;
    	
    	//将code值中的json转化成map
    	Map<String, String> tempMap = new HashMap<String, String>(); 
    	tempMap = (Map) codeProperties;
    	
    	String[] arrayKeys = tempMap.keySet().toString().replace("[","").replace("]", "").split(",");
    	String value = "";
    	for (int i = 0; i < arrayKeys.length; i++) {
    		value = tempMap.get(arrayKeys[i].trim());
    		Map<String, String> valueMap = IStringUtil.getJsonToMap(value);
    		codeMap.put(arrayKeys[i].trim(), valueMap);
		}
    	
    	//将获取的web系统配置存放进map
    	sysMap = (Map) sysProperties;
    	
    }
    
    public static void main(String[] args) {
    	new IPropertyUtil();
	}

	private IPropertyUtil(String props){
		manager = new Properties();
		try {
			manager.load(getClass().getResourceAsStream(props));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**   
     * 获得属性文件管理工具实例
     * @return PropertyManager 返回一个实例，如果实例不存在则创建一个
     */
    public static IPropertyUtil getInstance(String propsName) {
       return new IPropertyUtil(propsName);
    }
    /**   
     * 根据属性名称取得属性值
     * @param name String 属性名称
     * @return String 返回属性值
     */
    public String getProperty(String name) {
        return getProperty(name, null);
    }
    /**   
     * 根据属性名称取得属性值,没有值时返回默认值
     * @param name String 属性名称
     * @param defaultValue String 默认值
     * @return String 返回属性值
     */
    public String getProperty(String name, String defaultValue) {
        return manager.getProperty(name,defaultValue);
    }
    
}
