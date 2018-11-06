package irille.action;

import irille.pub.bean.BeanMain;
import irille.pub.util.GenericsUtils;

public abstract class BeanAction<T extends BeanMain> extends BaseAction {
	
	private static final long serialVersionUID = -3099350026313996838L;

	@SuppressWarnings("unchecked")
	public BeanAction() {
		beanClass = (Class<T>)GenericsUtils.getSuperClassGenricType(getClass());
	}
	private Class<T> beanClass;
	
	protected T _bean = null;
	
	public final Class<T> beanClazz(){
		return beanClass;
	}
	public final T getBean() {
		return _bean;
	}
	public final void setBean(T bean) {
		this._bean = bean;
	}
	
}
