package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;

import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/18
 * Time: 14:17
 */
public class LongToIntegerConvert implements IConvertBeanFactory<Long, Integer> {
    @Override
    public String getName() {
        return Tools.getName(Long.class, Integer.class);
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }

    @Override
    public Integer convert(Long source) {
        return source.intValue();
    }

}
