package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;


import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: HelloBox passxml@gmail.com
 * Date: 2018/8/11
 * Time: 9:34
 */
public class SqlDateToStringConvert implements IConvertBeanFactory<java.sql.Date, String> {
    @Override
    public String getName() {
        return Tools.getName(java.sql.Date.class, String.class);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    public String convert(java.sql.Date source) {
        return SimpleDateFormat.getDateInstance().format(new Date(source.getTime()));
    }
}
