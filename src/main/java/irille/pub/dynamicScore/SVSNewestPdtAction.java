package irille.pub.dynamicScore;

import irille.Entity.SVS.SVSInfo;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class SVSNewestPdtAction implements ISVSNewestPdtAction, Job {
    private SVSNewestPdtService svsNewestPdtService = new SVSNewestPdtServiceImpl();

    @Override
    public Integer getTotalScore(Integer supId) {
        return new Double(getPrivatePdtFraction(supId) + getPdtFixedFraction(supId) + getNewPdtFraction(supId) + getO2OPdtFraction(supId) + getInqFraction(supId) + getRFQQuoteFraction(supId)).intValue();
    }

    @Override
    public Integer quantity(SQL sql) {
        return Query.sql(sql).queryCount();
    }

    @Override
    public Integer getPdtFixedFraction(Integer supId) {
        return quantity(svsNewestPdtService.getPdts(supId)) * 6;
    }

    @Override
    public Integer getNewPdtFraction(Integer supId) {
        return quantity(svsNewestPdtService.getNewPdtFraction(supId)) * 6;
    }

    @Override
    public Double getPrivatePdtFraction(Integer supId) {
        return Double.valueOf(quantity(svsNewestPdtService.getPrivatePdt(supId)) * 0.1);
    }

    @Override
    public Integer getO2OPdtFraction(Integer supId) {
        return quantity(svsNewestPdtService.getO2OPdt(supId)) * 1;
    }

    @Override
    public Integer getInqFraction(Integer supId) {
        return svsNewestPdtService.getInqReply(supId) * 60;
    }

    @Override
    public Integer getRFQQuoteFraction(Integer supId) {
        return quantity(svsNewestPdtService.getRFQQuote(supId)) * 60;
    }

    @Override
    public List<Integer> getSupDiamondsAndGold() {
        return svsNewestPdtService.getSupDiamondsAndGold();
    }

    @Override
    public SVSInfo getSVSInfo(Integer supId) {
        return Query.sql(svsNewestPdtService.getSVSInfo(supId)).query(SVSInfo.class);
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("=========================");
        for (Integer integer : getSupDiamondsAndGold()) {
            SVSInfo svsInfo = getSVSInfo(integer);
            svsInfo.setDynamicScore(getTotalScore(integer));
            svsInfo.upd();
        }
    }
}
