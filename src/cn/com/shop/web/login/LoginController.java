package cn.com.shop.web.login;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.com.shop.common.IConstants;
import cn.com.shop.common.security.CryptUtil;
import cn.com.shop.common.util.IDateUtil;
import cn.com.shop.common.util.IPropertyUtil;
import cn.com.shop.common.util.IStringUtil;
import cn.com.shop.common.util.SearchParaUtil;
import cn.com.shop.web.goods.Goods;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
/**
 * 
* @ClassName: LoginController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhdech
* @date 2014年8月3日 下午8:24:22 
*
 */
public class LoginController extends Controller{
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	private LoginUser masterHandle = new LoginUser();
	
	private String toLoginPage = "/page/login/login.html";
	private String toErrorPage = "/page/common/error.html";

	/**
	 * 方法名：index
	 * 描述  ：默认跳转函数，此处直接转向登录页面
	 *
	 */
	public void index(){
		render(toLoginPage);
	}
	
	public void _login() throws IOException{
		logger.info("过去验证码");
		HttpServletResponse response = getResponse();
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);

		// 指定生成的响应是图片
		response.setContentType("image/jpeg");
		int width = 70;
		int height = 30;

		// 创建BufferedImage类的对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 创建Graphics类的对象
		Graphics g = image.getGraphics();
		// 实例化一个Random对象
		Random random = new Random();
		// 通过Font构造字体
		Font mFont = new Font("华文宋体", Font.BOLD, 25);
		// 改变图形的当前颜色为随机生成的颜色
		g.setColor(Color.cyan);
		// 绘制一个填色矩形
		g.fillRect(0, 0, width, height);
		// 画一条折线
		// 设置当前颜色为预定义颜色中的深灰色
		g.setColor(Color.DARK_GRAY);

		g.setFont(mFont);
		// 生成并输出随机的验证文字
		String sRand = "";
		int itmp = 0;
		for (int i = 0; i < 4; i++) {
			if ((random.nextInt(2)+1)%2==0) {
				itmp = random.nextInt(26) + 65; // 生成A~Z的字母
			} else {
				itmp = random.nextInt(10) + 48; // 生成0~9的数字
			}
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			g.drawString(String.valueOf(ctmp), 16 * i+5, 25);
		}
		
		// 将生成的验证码保存到Session中
		getSession().setAttribute("randCheckCode", sRand);
		logger.info("生成的验证码: " + sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		renderNull();
		logger.info("获取验证码结束");
	}
	@Before(LoginValidator.class)
	public void login(){
		String userName = getPara("j_username").trim();
		String passWord = getPara("j_password").trim();
		logger.info("userName:"+userName+" password:"+passWord);
		if(userName==null){
			setAttr("errorInfo","帐号不能为空，请重新输入...");
			slaveLog(userName, userName, userName, userName, "帐号不能为空，请重新输入...");
			render(toLoginPage);
		}else{
			String jCode = getPara("j_code").trim();
			if(!jCode.equalsIgnoreCase((String) getSession().getAttribute("randCheckCode"))){
				setAttr("errorInfo","验证码输入有误....");
				slaveLog(userName, userName, userName, userName, "验证码输入有误....");
				render(toLoginPage);
			}else{
				getSession().removeAttribute("randCheckCode");
				LoginUser master = masterHandle.getMasterByName(userName);
				
				if(master!=null && CryptUtil.encrypt(passWord).equals(master.getStr("password"))){
					if(master != null)
					{
						setAttr("userName", userName);
						//临时测试的跳转webmanager地址 http://127.0.0.1:8080/FourA
						setAttr("wAddress", IPropertyUtil.sysMap.get(IConstants.GOTO_WEBMANAGER_URL));
						getSession().setAttribute("master", master);
						
						//设置条件查询的参数存储 -- 直接拷贝使用
						SearchParaUtil.setAttr(this);
						
						//分页信息，仅供有分页查询的页面使用-- 直接拷贝使用
//			      	IPageUtil.setPagePara(getPara(0), getPara(1));
						
						//列头信息--使用国际化配置，
						setAttr("headMap", IStringUtil.getJsonToMap(IPropertyUtil.langMap.get("lang.webmanager.goods.tableHeader")));
						
						//获取分页对象，page对象中包含list列表结果集
						List<Goods> page = getModel(Goods.class).getGoodsList("");//Page<Master> page = getModel(Master.class).getMasterListByPara(masterName);
						
						//列表数据
						setAttr("pageObj", page);
						
						slaveLog(userName, master.getStr("mobile"), master.getStr("username"), master.getStr("address"), "登陆成功");
						
						//从session中获取当前登录的帐号信息
						render("/page/manager/main/index_main.html");
					}
				}else{
					setAttr("messageInfo","帐号或密码错误，请重新输入...");
					slaveLog(userName, userName, userName, userName, "帐号或密码错误，请重新输入...");
					render(toLoginPage);
				}
			}
		}
	}
	
	public void slaveLog(String name,String mobile,String userName,String address,String status){
		LoginUserLog log = getModel(LoginUserLog.class);
		log.set("name",name);
		log.set("mobile",mobile);
		log.set("username",userName);
		log.set("address",address);
		log.set("status",status);
		log.set("logintime", IDateUtil.getCurrentTime());
		log.save();
	}
	
	/**
	 * 方法名：logout
	 * 描述  ：退出登录页面
	 *
	 */
	public void logout()
	{
		render(toLoginPage);
	}
	
	
	public void gotoWebManager()
	{
		//从session中获取当前登录的帐号信息
		render("/page/manager/main/index_main.html");
	}
	
	public void gotoWebPortal()
	{
		render("/page/portal/main/index_main.html");
	}
	
}
