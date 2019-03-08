package irille.Dao.SVS.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import irille.Dao.SVS.SVSAuthenticationService;
import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.pm.PM;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetBaseScoreUtils;

import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.SVS.SVSAuthenticationView;
import irille.view.SVS.SVSInfoView;

import java.util.Date;


public class SVSAuthenticationServiceImpl implements SVSAuthenticationService {
    @Inject
    ObjectMapper om;
    @Inject
    IPMMessageService pm;
    //获取认证信息
    @Override
    public SVSAuthenticationView getAutInfo(Integer pkey) throws Exception {
        SVSInfo info = SVSAuthenticationDao.querySVS(pkey);
        SVSAuthenticationView view = CreateView(info);
        System.out.println(info.toString());
        return view;
    }

    private SVSAuthenticationView CreateView(SVSInfo info) throws Exception {
        SVSAuthenticationView view = new SVSAuthenticationView();
        view.setPkey(info.getPkey());
        view.setGrade(info.getGrade());
        view.setStatus(info.getStatus());
        view.setFailureReasons(info.getFailureReasons());
        view.setAuthenticationTime(info.getAuthenticationTime());
        view.setCount(info.getApplicationCount());
        view.setSupplier(info.gtSupplier().getName());
        view.setCapacity(om.readValue(info.getProductionCapacity(), SVSInfoView.productionCapacity.class));
        view.setExhibition(om.readValue(info.getExhibitionAttended(), SVSInfoView.exhibitionAttended.class));
        view.setFactory(om.readValue(info.getRealFactory(), SVSInfoView.realFactory.class));
        view.setQuality(om.readValue(info.getProductQuality(), SVSInfoView.productQuality.class));
        view.setResearch(om.readValue(info.getResearch(), SVSInfoView.research.class));
        view.setTeam(om.readValue(info.getForeignTradeTeam(), SVSInfoView.tradeTeam.class));
        view.setPart(om.readValue(info.getPartner(), SVSInfoView.partner.class));
        return view;
    }

    //平台进行认证
    @Override
    public void Authentication(UsrSupplier supplier,Integer status, Integer grade, String reasons, Integer pkey) throws Exception {
        SVSInfo svs = SVSAuthenticationDao.querySVS(pkey);
        int score = GetBaseScoreUtils.getBaseScore(svs.getResearch(), svs.getProductionCapacity(), svs.getRealFactory(), svs.getProductQuality(), svs.getForeignTradeTeam(), svs.getExhibitionAttended(), svs.getPartner());
        if (score < 30) {
            throw new WebMessageException(ReturnCode.failure, "未满足银牌基础分值,无法提交认证");
        }
        SVSAuthenticationDao.updateSVS(status, grade, reasons, pkey,score);
        if(status==SVSAuthenticationStatus.SUCCESS.getLine().getKey()){
            svs.setAuthenticationTime(new Date());
            svs.stStatus(SVSAuthenticationStatus.SUCCESS);
        }else if(status==SVSAuthenticationStatus.FAIL.getLine().getKey()){
            svs.stStatus(SVSAuthenticationStatus.FAIL);
        }
        pm.send(PM.OTempType.SVS_APPR_NOTICE,supplier, null, svs);

    }
}
