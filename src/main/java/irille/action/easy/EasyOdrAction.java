package irille.action.easy;

import irille.shop.easy.EasyOdr;
import irille.action.MgtAction;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 20:04
 */
public class EasyOdrAction extends MgtAction<EasyOdr> {
    @Override
    public Class beanClazz() {
        return EasyOdr.class;
    }
}
