package irille.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.pub.bean.BeanMain;
import irille.pub.util.GenericsUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import javax.inject.Inject;
import java.io.*;

public abstract class BeanAction<T extends BeanMain> extends BaseAction {

    private static final long serialVersionUID = -3099350026313996838L;

    @Inject
    protected ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public BeanAction() {
        beanClass = (Class<T>) GenericsUtils.getSuperClassGenricType(getClass());
    }

    private Class<T> beanClass;

    protected T _bean = null;

    public final Class<T> beanClazz() {
        return beanClass;
    }

    public final T getBean() {
        return _bean;
    }

    public final void setBean(T bean) {
        this._bean = bean;
    }

    public String getJsonBody() throws IOException {
        if (ServletActionContext.getRequest().getHeader("Content-Type").indexOf("application/json")
                != -1) {
            return IOUtils.toString(ServletActionContext.getRequest().getInputStream(), "utf-8");
        }
        return null;
    }

    public String getParams(String name) {
        return ServletActionContext.getRequest().getParameter(name);
    }

    protected void sendOutPuiStreanm(
            ByteArrayOutputStream byteArrayOutputStream, String downloadFileName) {
        ServletActionContext.getResponse()
                .setHeader("Content-disposition", "filename=" + downloadFileName);
        ServletActionContext.getResponse().setContentType("text/plain");
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ServletActionContext.getResponse()
                    .setHeader("Content-Length", String.valueOf(inputStream.available()));
            OutputStream outputStream =
                    new BufferedOutputStream(ServletActionContext.getResponse().getOutputStream());
            byte[] bytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(bytes, 0, 1024)) != -1) {
                outputStream.write(bytes, 0, bytesRead);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
