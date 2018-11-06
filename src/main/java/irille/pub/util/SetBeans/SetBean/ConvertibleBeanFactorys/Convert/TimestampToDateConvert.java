package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;


import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: HelloBox passxml@gmail.com
 * Date: 2018/8/11
 * Time: 9:34
 */
public class TimestampToDateConvert implements IConvertBeanFactory<Timestamp, Date> {
    @Override
    public String getName() {
        return Tools.getName(Timestamp.class, Date.class);
    }

    @Override
    public Class<Date> getType() {
        return Date.class;
    }

    @Override
    public Date convert(Timestamp source) {
        return Date.from(source.toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant());
    }
}
