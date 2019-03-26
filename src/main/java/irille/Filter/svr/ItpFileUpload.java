package irille.Filter.svr;

import org.apache.struts2.interceptor.FileUploadInterceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.ConfigurationException;

import irille.pub.Log;

public class ItpFileUpload extends FileUploadInterceptor {

  private static final long serialVersionUID = 1L;

  private static final Log LOG = new Log(ItpFileUpload.class);

  public String intercept(ActionInvocation invocation) throws Exception {

    String result = null;
    try {
      result = super.intercept(invocation);
    } catch (ConfigurationException e) {
        throw LOG.err("upload", "上传出错，请检查文件类型与大小");
    }
    return result;
  }
}
