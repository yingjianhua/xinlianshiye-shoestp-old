package irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Convert;


import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.IConvertBeanFactory;
import irille.pub.util.SetBeans.SetBean.ConvertibleBeanFactorys.Tools;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: HelloBox passxml@gmail.com
 * Date: 2018/8/4
 * Time: 16:08
 */
public class StringToListConvert implements IConvertBeanFactory<String, List> {


    @Override
    public String getName() {
        return Tools.getName(String.class, List.class);
    }

    @Override
    public Class<List> getType() {
        return List.class;
    }

    @Override
    public List convert(String source) {
        return Arrays.asList(source.split(",")).stream().filter(s -> {
            if (s.length() > 1) return true;
            return false;
        }).collect(Collectors.toList());
    }
}
