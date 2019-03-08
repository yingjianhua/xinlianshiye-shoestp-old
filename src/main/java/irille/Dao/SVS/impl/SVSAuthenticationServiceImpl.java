package irille.Dao.SVS.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import irille.Dao.SVS.SVSAuthenticationService;
import irille.Entity.SVS.SVSInfo;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetBaseScoreUtils;

import irille.view.SVS.SVSAuthenticationView;
import irille.view.SVS.SVSInfoView;


public class SVSAuthenticationServiceImpl implements SVSAuthenticationService {
    @Inject
    ObjectMapper om;

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


    @Override
    public void Authentication(Integer status, Integer grade, String reasons, Integer pkey) throws Exception {
        SVSInfo info = SVSAuthenticationDao.querySVS(pkey);
        int score = GetBaseScoreUtils.getBaseScore(info.getResearch(), info.getProductionCapacity(), info.getRealFactory(), info.getProductQuality(), info.getForeignTradeTeam(), info.getExhibitionAttended(), info.getPartner());
        System.out.println(score);
        if (score < 50) {
            throw new WebMessageException(ReturnCode.failure, "未满足银牌基础分值,无法提交认证");
        }
        SVSAuthenticationDao.updateSVS(status, grade, reasons, pkey);
    }
}
