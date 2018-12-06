package irille.action.easy;

import irille.shop.easy.EasyOdrline;
import irille.action.MgtAction;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 20:04
 */
public class EasyOdrlineAction extends MgtAction<EasyOdrline> {
    @Override
    public Class beanClazz() {
        return EasyOdrline.class;
    }
}
