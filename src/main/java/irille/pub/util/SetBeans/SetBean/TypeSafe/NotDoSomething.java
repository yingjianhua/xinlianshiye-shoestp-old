package irille.pub.util.SetBeans.SetBean.TypeSafe;

import irille.pub.util.SetBeans.SetBean.Beans.TypeSafeResult;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;

/** Created by IntelliJ IDEA. User: HelloBox passxml@gmail.com Date: 2018/8/4 Time: 10:43 */
public class NotDoSomething implements ITypeSafe {
  @Override
  public TypeSafeResult run(Object getValue, Class<?> type) {
    Class getValueClass = getValue.getClass();
    TypeSafeResult result = new TypeSafeResult();
    result.setSetValue(getValue);
    result.setSetType(getValueClass);
    return result;
  }

  @Override
  public void addConvertFactorys(IConvertBeanFactory iConvertBeanFactory) {}
}
