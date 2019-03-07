package irille.Dao.SVS.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
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

public class SVSAuthenticationDao {
    @Inject
    ObjectMapper om;
    //查看认证信息
    public static SVSAuthenticationView querySVS(Integer pkey) {
        SQL sql = new SQL(){{
            SELECT(SVSInfo.class).FROM(SVSInfo.class).WHERE(SVSInfo.T.PKEY," =? ",pkey);
        }};
        SVSInfo sVSInfo = Query.sql(sql).query(SVSInfo.class);
        System.out.println(sVSInfo.getSupplier());
       SQL sq = new SQL(){{
           SELECT(UsrSupplier.class).FROM(UsrSupplier.class).WHERE(UsrSupplier.T.PKEY," =? ",sVSInfo.getSupplier());
       }};
        UsrSupplier usrSupplier = Query.sql(sq).query(UsrSupplier.class);
        SVSAuthenticationView svsAuthenticationView=new SVSAuthenticationView();
        svsAuthenticationView.setExhibitionAttended(sVSInfo.getExhibitionAttended());
        svsAuthenticationView.setForeingTradeTeam(sVSInfo.getForeignTradeTeam());
        svsAuthenticationView.setPkey(sVSInfo.getPkey());
        svsAuthenticationView.setPratner(sVSInfo.getPartner());
        svsAuthenticationView.setProductiveCapacity(sVSInfo.getProductionCapacity());
        svsAuthenticationView.setRealFactory(sVSInfo.getRealFactory());
        svsAuthenticationView.setForeingTradeTeam(sVSInfo.getForeignTradeTeam());
        svsAuthenticationView.setResearch(sVSInfo.getResearch());
        svsAuthenticationView.setStatus(sVSInfo.getStatus());
        svsAuthenticationView.setSupplier(usrSupplier.getName());
        return svsAuthenticationView;
    }

    //进行认证
    public static void updateSVS(Integer status, Integer grade, String reasons, Integer pkey) {
        SVSInfo svsInfo = BeanBase.load(SVSInfo.class, pkey);
        svsInfo.setStatus((byte) status.intValue());
        if(status==1){
            svsInfo.setAuthenticationTime(Env.getSystemTime());
        }
        svsInfo.setGrade((byte) grade.intValue());
        svsInfo.setFailureReasons(reasons);
        svsInfo.upd();
    }
}
