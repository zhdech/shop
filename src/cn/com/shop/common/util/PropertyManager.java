package cn.com.shop.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

/**   
 * 属性文件管理工具类
 */
public class PropertyManager {

    private static PropertyManager manager = null;
    private static Object managerLock = new Object();
    private static String propsName = "/jdbc.properties";

    /**   
     * 获得属性文件管理工具实例
     * @return PropertyManager 返回一个实例，如果实例不存在则创建一个
     */
    public static PropertyManager getInstance() {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        return manager;
    }
    /**   
     * 根据属性名称取得属性值
     * @param name String 属性名称
     * @return String 返回属性值
     */
    public static String getProperty(String name) {
        return getProperty(name, null);
    }
    /**   
     * 根据属性名称取得属性值,没有值时返回默认值
     * @param name String 属性名称
     * @param defaultValue String 默认值
     * @return String 返回属性值
     */
    public static String getProperty(String name, String defaultValue) {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        return manager.getProp(name,defaultValue);
    }
    /**   
     * 设置属性值
     * @param name String 属性名称
     * @param value String 属性值
     */
    public static void setProperty(String name, String value) {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        manager.setProp(name, value);
    }

    /**   
     * 删除属性值
     * @param name String 属性名称
      */
    public static void deleteProperty(String name) {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        manager.deleteProp(name);
    }

    /**   
     * 枚举属性
     * @return Enumeration 返回Enumeration集合
     */
    public static Enumeration propertyNames() {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        return manager.propNames();
    }

    /**   
     * 判断属性文件是否可读
    * @return boolean 返回true或false
     */
    public static boolean propertyFileIsReadable() {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        return manager.propFileIsReadable();
    }

    /**   
     * 判断属性文件是否可写
     * @return boolean 返回true或false
     */
    public static boolean propertyFileIsWritable() {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        return manager.propFileIsWritable();
    }

    /**   
     * 判断属性文件是否存在
     * @return boolean 返回true或false
     */
    public static boolean propertyFileExists() {
        if (manager == null) {
            synchronized(managerLock) {
                if (manager == null) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        return manager.propFileExists();
    }
    
    /**   
     * 保存属性内容到属性文件中
     * @param properties Properties 属性集合
     */
   public static void savePropsToFile(Properties properties) {
        OutputStream out = null;
        try {
        	String filename = PropertyManager.class.getResource("/").getPath();
        	filename = filename.substring(1, filename.length()-1);
        	filename = filename + propsName;
            out = new FileOutputStream(filename);
            properties.store(out, "properties -- " + (new java.util.Date()));
        }
        catch (Exception ioe) {
             ioe.printStackTrace();
        }
        finally {
            try {
               out.close();
            } catch (Exception e) { }
        }
    }
   
    private Properties properties = null;
    private Object propertiesLock = new Object();
    private String resourceURI;

    /**
     * 创建新的属性管理器
     */
    private PropertyManager(String resourceURI) {
        this.resourceURI = resourceURI;
    }
    /**
     * 取得属性值,无值时返回默认值
     * @param name String 属性名称
     * @param defaultValue String 属性名称
     * @return String 属性值
     */
    protected String getProp(String name,String defaultValue) {
          if (properties == null) {
            synchronized(propertiesLock) {
                if (properties == null) {
                    loadProps();
                }
            }
        }
        String property = properties.getProperty(name);
        if (property == null) {
            return defaultValue;
        }
        else {
            return property.trim();
        }
    }
    /**
     * 取得属性值
     * @param name String 属性名称
     * @return String 属性值
     */
    protected String getProp(String name) {
          return getProp(name,null);
    }
    /**
     * 设置属性值
     * @param name String 属性名称
     * @param value String 属性值
     */
    protected void setProp(String name, String value) {
         synchronized (propertiesLock) {
             if (properties == null) {
                loadProps();
            }
            properties.setProperty(name, value);
            saveProps();
        }
    }
    /**
     * 删除属性值
     * @param name String 属性名称
     */
    protected void deleteProp(String name) {
        synchronized (propertiesLock) {
             if (properties == null) {
                loadProps();
            }
            properties.remove(name);
            saveProps();
        }
    }
    /**
     * 枚举属性
     * @return Enumeration 返回Enumeration型属性
     */
    protected Enumeration propNames() {
        if (properties == null) {
            synchronized(propertiesLock) {
                //Need an additional check
                if (properties == null) {
                    loadProps();
                }
            }
        }
        return properties.propertyNames();
    }

    /**
     * 加载属性
     */
    private void loadProps() {
        properties = new Properties();
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream(resourceURI);
            properties.load(in);
        }
        catch (Exception e) {
            System.err.println("Error reading Application properties in PropertyManager.loadProps() " + e);
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (Exception e) { }
        }
    }

    /**
     * 保存属性
     */
    private void saveProps() {
        String path = properties.getProperty("path").trim();
        OutputStream out = null;
        try {
            out = new FileOutputStream(path);
            properties.store(out, "properties -- " + (new java.util.Date()));
        }
        catch (Exception ioe) {
             ioe.printStackTrace();
        }
        finally {
            try {
               out.close();
            } catch (Exception e) { }
        }
    }
    /**
     * 属性文件是否可读
     * @return boolean 返回true或false
     */
    public boolean propFileIsReadable() {
        try {
            InputStream in = getClass().getResourceAsStream(resourceURI);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 属性文件是否存在
     * @return boolean 返回true或false
     */
    public boolean propFileExists() {
        String path = getProp("path");
        File file = new File(path);
        if (file.isFile()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 属性文件是否可写
     * @return boolean 返回true或false
     */
    public boolean propFileIsWritable() {
        String path = getProp("path");
        File file = new File(path);
        if (file.isFile()) {
            if (file.canWrite()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    /*
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(PropertyManager.class.getClassLoader().getResource(""));
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(PropertyManager.class.getResource(""));
        System.out.println(PropertyManager.class.getResource("/").getPath());
        System.out.println(new File("").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
    }
    */
}
