package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;

import java.math.BigDecimal;

import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

/** Created by IntelliJ IDEA. User: HelloBox passxml@gmail.com Date: 2018/8/11 Time: 9:34 */
public class ByteToIntegerConvert implements IConvertBeanFactory<Byte, Integer> {
  @Override
  public String getName() {
    return Tools.getName(Byte.class, Integer.class);
  }

  @Override
  public Class<Integer> getType() {
    return Integer.class;
  }

  @Override
  public Integer convert(Byte source) {
    return source.intValue();
  }
}
