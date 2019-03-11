package irille.pub.dynamicScore;

import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.SVS.SVSInfo;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.Env;
import irille.pub.util.BatchUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import text.SVSNewestPdt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SVSNewestPdtAction implements ISVSNewestPdtAction, Job {
    private SVSNewestPdtService svsNewestPdtService = new SVSNewestPdtServiceImpl();

    @Override
    public Integer getTotalScore(Integer supId) {
        return new Double(getPdtFixedFraction(supId) + getNewPdtFraction(supId) + getPrivatePdtFraction(supId) + getO2OPdtFraction(supId) + getInqFraction(supId) + getRFQQuoteFraction(supId)).intValue();
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
    public List<Integer> getSVSInfoSupIds() {
        return svsNewestPdtService.getSVSInfoSupIds();
    }

    @Override
    public List<SVSInfo> getSVSInfos(String supIds) {
        return Query.sql(svsNewestPdtService.getSVSInfos(supIds)).queryList(SVSInfo.class);
    }


    @Override
    public void updSVSGrade(String supIds) {
        List<SVSInfo> infos = getSVSInfos(getSVSInfoSupIds());
        infos.forEach(info -> {
            if (info.getBaseScore() >= 30 && info.getBaseScore() < 59) {

            } else if (info.getBaseScore() >= 60) {

            }else{

            }
//            info.setDynamicScore(getTotalScore(info.getSupplier()));
        });
    }

    @Override
    /**
     * 金牌以上的商家15分钟计算动态分
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("====SVS修改动态分====");
        O2O_Product.TB.getCodeSqlTb();
        O2O_PrivateExpoPdt.TB.getCodeSqlTb();
        SVSNewestPdt.TB.getCodeSqlTb();
        Env.INST.initTran(null, null);
        List<SVSInfo> infos = getSVSInfos(getSupDiamondsAndGold());
        infos.forEach(info -> {
            info.setDynamicScore(getTotalScore(info.getSupplier()));
        });
        BatchUtils.batchUpd(SVSInfo.class, Arrays.asList(SVSInfo.T.DYNAMIC_SCORE), Arrays.asList(SVSInfo.T.PKEY), infos);
        System.out.println("====SVS修改动态分END====");
    }

    @Override
    public List<SVSInfo> getSVSInfos(List<Integer> supIds) {
        return getSVSInfos(supIds.stream().map(id -> {
            return String.valueOf(id);
        }).collect(Collectors.joining(",")));
    }
}
