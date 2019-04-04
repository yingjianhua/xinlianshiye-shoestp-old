package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;

import java.util.regex.Pattern;

import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

/** Created by IntelliJ IDEA. User: HelloBox passxml@gmail.com Date: 2018/8/4 Time: 16:08 */
public class StringTolong_Convert implements IConvertBeanFactory {

  private final Pattern pattern = Pattern.compile("[0-9]{1,}");

  @Override
  public Object convert(Object s) {
    String var = String.valueOf(s);
    if (var == null) {
      return null;
    }
    if (var.length() < 1) {
      return null;
    }
    if (pattern.matcher(var).matches()) {
      Long result = Long.parseLong(var);
      return result;
    }
    return null;
  }

  @Override
  public String getName() {
    return Tools.getName(String.class, long.class);
  }

  @Override
  public Class<Long> getType() {
    return Long.class;
  }
}
