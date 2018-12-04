package irille.Dao.Activity.Romania;

import irille.Entity.Pk.PkCompetitionData;
import irille.pub.bean.query.BeanQuery;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrConsult;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/3
 * Time: 11:24
 */
public class PkCompetitionDataDao {


    public List<PkCompetitionData> getPkCompetitionData(Date startTime, Date endTime) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PkCompetitionData.class
        ).FROM(PkCompetitionData.class).WHERE(
                PkCompetitionData.T.CREATEDTIME, ">=?", startTime
        ).WHERE(
                PkCompetitionData.T.CREATEDTIME, "<?", endTime
        );
        return query.queryList(PkCompetitionData.class);
    }


    public Integer getInquiry(Date startDate, Date endDate, int supid) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                UsrConsult.T.PKEY
        ).FROM(UsrConsult.class).LEFT_JOIN(
                PdtProduct.class, PdtProduct.T.PKEY, UsrConsult.T.PKEY
        )
                .WHERE(PdtProduct.T.SUPPLIER, "=?", supid)
                .WHERE(UsrConsult.T.CREATE_TIME, ">=?", startDate)
                .WHERE(UsrConsult.T.CREATE_TIME, "<?", endDate)
        ;
        return query.queryCount();
    }
}
