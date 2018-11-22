package irille.action;

import irille.pub.bean.BeanMain;
import irille.pub.util.GenericsUtils;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

public abstract class BeanAction<T extends BeanMain> extends BaseAction {

    private static final long serialVersionUID = -3099350026313996838L;

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
        if (ServletActionContext.getRequest().getHeader("Content-Type").indexOf("application/json") != -1) {
            int contentLength = ServletActionContext.getRequest().getContentLength();
            if (contentLength < 0) {
                return null;
            }
            byte buffer[] = new byte[contentLength];
            for (int i = 0; i < contentLength; ) {
                int len = ServletActionContext.getRequest().getInputStream().read(buffer, i, contentLength - i);
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
