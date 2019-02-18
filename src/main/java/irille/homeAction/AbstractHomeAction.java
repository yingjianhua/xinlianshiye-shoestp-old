package irille.homeAction;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import irille.Filter.svr.ItpSessionmsg;
import irille.action.BaseAction;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.verify.RandomImageServlet;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;

/**
 * 商城端数据请求action抽象类
 * @author Jianhua Ying
 *
 */
@Getter
@Setter
public abstract class AbstractHomeAction extends BaseAction implements RequestAware, SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	
	protected Integer start = 0;
	protected Integer limit = 10;

	/**
	 * 获取文件上传路径的 项目相对路径
	 * @return
	 */
	public static String getUploadPath() {
		return getUploadPath(false);
	}
	/**
	 * 获取文件上传路径，绝对路径或相对路径
	 * @param real
	 * @return
	 */
	public static String getUploadPath(boolean real) {
		String path = ServletActionContext.getServletContext().getInitParameter("uploadPath");
		if(Str.isEmpty(path))
			path = "uploads";
		if(path.indexOf(":")>0)
			return path;
		if(real) {
			return ServletActionContext.getServletContext().getRealPath("/") + path;
		} else {
			return path;
		}
	}
	// 取下载路径--有绝对路径的情况
	public String getDownPath() {
		return getUploadPath(true);
	}

	 // 取Session中的验证码
    public String verifyCode() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession ssn = request.getSession(false);
        if (ssn == null)
            return null;
        return (String) ssn.getAttribute(RandomImageServlet.RANDOM_LOGIN_KEY);
    }

//---------------------------------------------上传文件功能--------------------------------------
  	private String fileFileName = "";
  	private File file;

//  	public void upload() throws IOException {
//  		if(getPurchase() == null) {
//  			writeTimeout();
//  		} else {
//  			write(ImageUpload.upload(beanClazz(), fileFileName, file));
//  		}
//  	}

  	public String getFileFileName() {
  		return fileFileName;
  	}

  	public void setFileFileName(String fileFileName) {
  		this.fileFileName = fileFileName;
  	}

  	public File getFile() {
  		return file;
  	}

  	public void setFile(File file) {
  		this.file = file;
  	}
//---------------------------------------------上传文件功能--------------------------------------

  	public static final void setUser(UserView user) {
  		ItpSessionmsg.getSessionmsg().setUser(user);
  	}
  	public static final UserView getUser() {
  		return ItpSessionmsg.getSessionmsg().getUser();
  	}
  	public static final void setPurchase(UsrPurchase purchase) {
  		ItpSessionmsg.getSessionmsg().setPurchase(purchase);
  	}
  	public static final  UsrPurchase getPurchase() {
  		return ItpSessionmsg.getSessionmsg().getPurchase();
  	}
  	public static final PltErate curCurrency() {
  		return BeanBase.load(PltErate.class, ItpSessionmsg.getSessionmsg().getCurrency());
  	}
  	public static final void curCurrency(PltErate currency) {
  		ItpSessionmsg.getSessionmsg().setCurrency(currency.getPkey());
  	}
	public static final  Language curLanguage() {
		return ItpSessionmsg.getSessionmsg().getLang();
	}
	public static final  void setCurLanguage(Language lang) {
		ItpSessionmsg.getSessionmsg().setLang(lang);
	}
	public static final boolean isMobile() {
		return ItpSessionmsg.getSessionmsg().getIsMobile();
	}
	public static final void setMobile(boolean isMobile) {
		ItpSessionmsg.getSessionmsg().setIsMobile(isMobile);
	}

}
