package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;


import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: HelloBox passxml@gmail.com
 * Date: 2018/8/4
 * Time: 16:08
 */
public class StringToLongConvert implements IConvertBeanFactory<String, Long> {

    private final Pattern pattern = Pattern.compile("[0-9]{1,}");

    @Override
    public Long convert(String var) {
        if (var == null) {
            return null;
        }
        if (var.length() < 1) {
            return null;
        }
        if (pattern.matcher(var).matches()) {
            Long result =
                    Long.parseLong(var);
            return result;
        }
        return null;
    }


    @Override
    public String getName() {
        return Tools.getName(String.class, Long.class);
    }

    @Override
    public Class<Long> getType() {
        return Long.class;
    }


}