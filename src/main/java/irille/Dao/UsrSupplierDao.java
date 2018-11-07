package irille.Dao;

import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierRole;
import irille.view.usr.UsrshopSettingView;
import irille.view.usr.shopSettingView;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/5
 * Time: 16:36
 */
public class UsrSupplierDao {

    /**
     * @Description: 获取用户信息
     * @date 2018/11/5 17:30
     * @author lijie@shoestp.cn
     */
    public Map<String, Object> getInfoById(int i) {
        SQL sql = new SQL();
        return Query.sql(sql.SELECT(
                UsrSupplier.T.PKEY,
                UsrSupplier.T.NAME,
                UsrSupplier.T.LOGO
        ).SELECT(
                UsrSupplierRole.T.NAME.getFld().getTb().getCode()+"."+UsrSupplierRole.T.NAME.getFld().getCodeSqlField() + " as  role_name"
        ).LEFT_JOIN(UsrSupplierRole.class, UsrSupplier.T.ROLE, UsrSupplierRole.T.PKEY)
                .FROM(UsrSupplier.class)).queryMap();

    }
    public UsrSupplier getUsrShopSetting(int i) {
    	return  BeanBase.load(UsrSupplier.class, i);
	}
}
