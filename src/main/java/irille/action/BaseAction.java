package irille.action;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

import irille.view.BaseView;
import irille.view.ResultView;
import org.apache.struts2.ServletActionContext;

public abstract class BaseAction extends ActionSupport {

  private static final long serialVersionUID = 7212211735448905796L;

  public static final byte RET_CODE_SUCCESS = 1; // 成功
  public static final byte RET_CODE_FAiLURE = 0; // 失败
  public static final byte RET_CODE_TIMEOUT = -1; // 失败
  private static final String DEFAULT_CONTENT_TYPE = "application/json;charset=utf-8";

  private static final ObjectMapper oMapper =
      new ObjectMapper().setSerializationInclusion(Include.NON_NULL);


  /**
   * 登录超时或者未登录
   *
   * @throws IOException
   */
  public static void writeTimeout() throws IOException {
    writeAsJson(RET_CODE_TIMEOUT, null, null);
  }

  public static void writeErr(int code, String msg) throws IOException {
    writeAsJson(code, msg, null);
  }

  public static void writeErr(String msg) throws IOException {
    writeAsJson(RET_CODE_FAiLURE, msg, null);
  }

  public static void write() throws IOException {
    writeAsJson(RET_CODE_SUCCESS, null, null);
  }

  public static <T extends BaseView> void write(T view) throws IOException {
    writeAsJson(RET_CODE_SUCCESS, null, view);
  }

  public static void write(com.xinlianshiye.shoestp.common.errcode.MessageView view)
      throws IOException {
    writeAsJson(view.getRet(), view.getMsg(), null);
  }

  public static <T extends BaseView> void write(Collection<T> list) throws IOException {
    writeAsJson(RET_CODE_SUCCESS, null, list);
  }

  public static <T extends BaseView> void write(irille.view.Page<T> page) throws IOException {
    writeAsJson(RET_CODE_SUCCESS, null, page);
  }

  private static void writeAsJson(int ret, String msg, Object o) throws IOException {
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType(DEFAULT_CONTENT_TYPE);
    ResultView result = new ResultView();
    result.setRet(ret);
    result.setMsg(msg);
    result.setResult(o);
    response.getWriter().print(oMapper.writeValueAsString(result));
  }

  public String getJsonBody() throws IOException {
    if (ServletActionContext.getRequest().getHeader("Content-Type").indexOf("application/json")
        != -1) {
      int contentLength = ServletActionContext.getRequest().getContentLength();
      if (contentLength < 0) {
        return null;
      }
      byte buffer[] = new byte[contentLength];
      for (int i = 0; i < contentLength; ) {
        int len =
            ServletActionContext.getRequest().getInputStream().read(buffer, i, contentLength - i);
        if (len == -1) {
          break;
        }
        i += len;
      }
      return new String(buffer, "utf-8");
    }
    return null;
  }
}
