package irille.pub.util;

import irille.pub.util.SetBeans.SetBean.Beans.TypeSafeResult;
import irille.pub.util.SetBeans.SetBean.TypeSafe.DefaultTypeSafe;
import irille.pub.util.SetBeans.SetBean.TypeSafe.ITypeSafe;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/22
 * Time: 19:05
 */
public class GetValue1 {

    private static ITypeSafe typeSafe = new DefaultTypeSafe();

    public static <T> T get(Map map, String key, Class<T> tClass,T defaultValue) {
        Object o = map.get(key);
        if (o==null)
            return defaultValue;
        if (tClass.isAssignableFrom(o.getClass())) {
            return (T) map.get(key);
        } else {
            TypeSafeResult typeSafeResult = typeSafe.run(o, tClass);
            return (T) typeSafeResult.getSetValue();
        }
    }
}
