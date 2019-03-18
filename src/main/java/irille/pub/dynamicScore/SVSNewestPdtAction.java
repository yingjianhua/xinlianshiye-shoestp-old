package irille.pub.dynamicScore;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.SVS.SVSNewestPdt;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.Env;
import irille.pub.util.BatchUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SVSNewestPdtAction implements ISVSNewestPdtAction {
  private SVSNewestPdtService svsNewestPdtService = new SVSNewestPdtServiceImpl();

  @Override
  public Integer getTotalScore(Integer supId) {
    return new Double(
            getPdtFixedFraction(supId)
                + getNewPdtFraction(supId)
                + getPrivatePdtFraction(supId)
                + getO2OPdtFraction(supId)
                + getInqFraction(supId)
                + getRFQQuoteFraction(supId))
        .intValue();
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
  public List<SVSInfo> getSVSInfos(List<Integer> supIds) {
    return getSVSInfos(
        supIds.stream()
            .map(
                id -> {
                  return String.valueOf(id);
                })
            .collect(Collectors.joining(",")));
  }

  @Override
  public void updSVSGrade() {
    List<SVSInfo> infos = getSVSInfos(getSVSInfoSupIds());
    // 使用方法引用简化lambda
    Function<SVSInfo, Integer> extractIdWay2 = SVSInfo::getDynamicScore;
    List<SVSInfo> infosSortByScore =
        infos.stream()
            .sorted(Comparator.comparing(extractIdWay2).reversed())
            .collect(Collectors.toList());
    SVSInfo svsMin =
        infosSortByScore.get(Double.valueOf(Math.floor(infosSortByScore.size() * 0.25)).intValue());
    infos.forEach(
        info -> {
          info.setDynamicScore(getTotalScore(info.getSupplier()));
          if (info.getBaseScore() >= 30 && info.getBaseScore() < 59) {
            info.setGrade(SVSGradeType.SILVER.getLine().getKey());
          } else if (info.getBaseScore() >= 60 && info.getBaseScore() < 75) {
            info.setGrade(SVSGradeType.GOLD.getLine().getKey());
          } else {
            if (info.getDynamicScore() > svsMin.getDynamicScore()) {
              info.setGrade(SVSGradeType.DIAMONDS.getLine().getKey());
            } else {
              info.setGrade(SVSGradeType.GOLD.getLine().getKey());
            }
          }
        });
    BatchUtils.batchUpd(
        SVSInfo.class,
        Arrays.asList(SVSInfo.T.DYNAMIC_SCORE, SVSInfo.T.GRADE),
        Arrays.asList(SVSInfo.T.PKEY),
        infos);
  }

  public static class updSVSFraction implements Job {
    @Override
    /** 金牌以上的商家15分钟计算动态分 */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      System.out.println("====SVS修改动态分====");
      O2O_Product.TB.getCodeSqlTb();
      O2O_PrivateExpoPdt.TB.getCodeSqlTb();
      SVSNewestPdt.TB.getCodeSqlTb();
      Env.INST.initTran(null, null);
      SVSNewestPdtAction svsNewestPdtAction = new SVSNewestPdtAction();

      List<SVSInfo> infos =
          svsNewestPdtAction.getSVSInfos(svsNewestPdtAction.getSupDiamondsAndGold());
      infos.forEach(
          info -> {
            info.setDynamicScore(svsNewestPdtAction.getTotalScore(info.getSupplier()));
          });
      BatchUtils.batchUpd(
          SVSInfo.class,
          Arrays.asList(SVSInfo.T.DYNAMIC_SCORE),
          Arrays.asList(SVSInfo.T.PKEY),
          infos);
      System.out.println("====SVS修改动态分END====");
    }
  }

  public static class updSVSGrade implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      O2O_Product.TB.getCodeSqlTb();
      O2O_PrivateExpoPdt.TB.getCodeSqlTb();
      SVSNewestPdt.TB.getCodeSqlTb();
      Env.INST.initTran(null, null);
      System.out.println("====SVS修改等级====");
      SVSNewestPdtAction svsNewestPdtAction = new SVSNewestPdtAction();
      svsNewestPdtAction.updSVSGrade();
      System.out.println("====SVS修改等级END====");
    }
  }
}
