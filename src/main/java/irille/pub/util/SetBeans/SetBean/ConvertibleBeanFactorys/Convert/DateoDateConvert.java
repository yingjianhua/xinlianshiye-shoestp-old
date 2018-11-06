package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;


import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: HelloBox passxml@gmail.com
 * Date: 2018/8/11
 * Time: 9:34
 */
public class DateoDateConvert implements IConvertBeanFactory<java.sql.Date, Date> {
    @Override
    public String getName() {
        return Tools.getName(java.sql.Date.class, Date.class);
    }

    @Override
    public Class<Date> getType() {
        return Date.class;
    }

    @Override
    public Date convert(java.sql.Date source) {
        return new Date(source.getTime());
    }
}
