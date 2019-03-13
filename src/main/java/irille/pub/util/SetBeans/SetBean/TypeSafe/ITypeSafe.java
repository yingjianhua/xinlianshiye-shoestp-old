package irille.pub.util.SetBeans.SetBean.TypeSafe;

import irille.pub.util.SetBeans.SetBean.Beans.TypeSafeResult;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;

/** Created by IntelliJ IDEA. User: HelloBox passxml@gmail.com Date: 2018/8/4 Time: 10:32 */
public interface ITypeSafe {
  TypeSafeResult run(Object getValue, Class<?> type);

  void addConvertFactorys(IConvertBeanFactory iConvertBeanFactory);
}
