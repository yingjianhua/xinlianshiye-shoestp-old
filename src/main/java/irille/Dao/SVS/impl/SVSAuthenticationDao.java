package irille.Dao.SVS.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.SVSInfo;
import irille.platform.pdt.View.pdtCatView.PdtCatView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.Env;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrSupplier;
import irille.view.SVS.SVSAuthenticationView;

import java.util.HashMap;
import java.util.Map;

public class SVSAuthenticationDao {
    @Inject
    ObjectMapper om;
    //查看认证信息
    public  static SVSInfo querySVS(Integer pkey) {
        SQL sql = new SQL(){{
            SELECT(SVSInfo.class).FROM(SVSInfo.class).WHERE(SVSInfo.T.PKEY," =? ",pkey);
        }};
        SVSInfo sVSInfo = Query.sql(sql).query(SVSInfo.class);
        return sVSInfo;
    }

    //进行认证
    public static void updateSVS(Integer status, Integer grade, String reasons, Integer pkey,Integer score) {
        SVSInfo svsInfo = BeanBase.load(SVSInfo.class, pkey);
        svsInfo.setStatus((byte) status.intValue());
        if(status==SVSAuthenticationStatus.SUCCESS.getLine().getKey()){
            svsInfo.setBaseScore(score);
            svsInfo.setAuthenticationTime(Env.getSystemTime());
        }
        if(grade!=null){
            svsInfo.setGrade((byte) grade.intValue());
        }
        svsInfo.setFailureReasons(reasons);
        svsInfo.upd();
    }
}
