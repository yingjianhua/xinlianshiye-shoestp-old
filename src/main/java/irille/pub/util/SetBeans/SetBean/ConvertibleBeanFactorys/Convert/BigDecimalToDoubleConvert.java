package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;

import java.math.BigDecimal;

import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

/** Created by IntelliJ IDEA. User: HelloBox passxml@gmail.com Date: 2018/8/11 Time: 9:34 */
public class BigDecimalToDoubleConvert implements IConvertBeanFactory<BigDecimal, Double> {
  @Override
  public String getName() {
    return Tools.getName(BigDecimal.class, Double.class);
  }

  @Override
  public Class<Double> getType() {
    return Double.class;
  }

  @Override
  public Double convert(BigDecimal source) {
    return source.doubleValue();
  }
}
