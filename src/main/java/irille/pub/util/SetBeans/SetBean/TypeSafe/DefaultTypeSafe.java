package irille.pub.util.SetBeans.SetBean.TypeSafe;

import java.util.concurrent.ConcurrentHashMap;

import irille.pub.util.SetBeans.SetBean.Beans.TypeSafeResult;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert.*;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

/** Created by IntelliJ IDEA. User: HelloBox passxml@gmail.com Date: 2018/8/4 Time: 10:33 */
public class DefaultTypeSafe implements ITypeSafe {
  private ConcurrentHashMap<String, IConvertBeanFactory> map;

  public IConvertBeanFactory getConvertFactorys(String name) {
    return map.get(name);
  }

  public DefaultTypeSafe() {
    map = new ConcurrentHashMap<>();
    addConvertFactorys(new StringToLongConvert());
    addConvertFactorys(new StringTolong_Convert());
    addConvertFactorys(new StringToListConvert());
    addConvertFactorys(new TimestampToDateConvert());
    addConvertFactorys(new TimestampToStringConvert());
    addConvertFactorys(new DateoDateConvert());
    addConvertFactorys(new DateToStringConvert());
    addConvertFactorys(new SqlDateToStringConvert());
    addConvertFactorys(new LongToIntegerConvert());
    addConvertFactorys(new BigDecimalToDoubleConvert());
    addConvertFactorys(new BigDecimalToIntegerConvert());
    addConvertFactorys(new ByteToIntegerConvert());
  }

  @Override
  public void addConvertFactorys(IConvertBeanFactory absConvertBeanFactory) {
    map.put(absConvertBeanFactory.getName(), absConvertBeanFactory);
  }

  @Override
  public TypeSafeResult run(Object getValue, Class<?> setType) {
    Class getValueClass = getValue.getClass();
    TypeSafeResult result = new TypeSafeResult();
    if (getValueClass == setType) {
      result.setSetType(getValueClass);
      result.setSetValue(getValue);
    } else {
      Class isType = baseType(getValueClass, setType);
      if (isType != null) {
        result.setSetType(isType);
        result.setSetValue(getValue);
        return result;
      } else {
        String key = Tools.getName(getValueClass, setType);
        IConvertBeanFactory iConvertBeanFactory = map.get(key);

        if (iConvertBeanFactory != null) {
          result.setSetValue(iConvertBeanFactory.convert(getValue));
          result.setSetType(iConvertBeanFactory.getType());
          return result;
        } else {
          result.setSetType(getValueClass);
          result.setSetValue(getValue);
          return result;
        }
      }
    }
    return result;
  }

  private Class baseType(Class getType, Class setType) {
    if (setType == long.class && getType == Long.class) {
      return long.class;
    }
    if (setType == int.class && getType == Integer.class) {
      return int.class;
    }
    if (setType == char.class && getType == Character.class) {
      return char.class;
    }
    if (setType == double.class && getType == Double.class) {
      return double.class;
    }
    if (setType == boolean.class && getType == Boolean.class) {
      return boolean.class;
    }
    if (setType == float.class && getType == Float.class) {
      return float.class;
    }
    if (setType == short.class && getType == Short.class) {
      return short.class;
    }
    if (setType == byte.class && getType == Byte.class) {
      return byte.class;
    }
    return null;
  }
}
