package irille.sellerAction.svs;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.Entity.SVS.SVSInfo;
import irille.action.seller.SellerAction;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetBaseScoreUtils;
import irille.sellerAction.svs.inf.ISVSInfoAction;
import irille.shop.usr.Usr;
import lombok.Data;

@Data
public class SVSInfoAction extends SellerAction<SVSInfo> implements ISVSInfoAction {
  @Inject SVSInfoService service;
  @Inject ObjectMapper om;

  private String search;
  private String capacity;
  private String factory;
  private String quality;
  private String team;
  private String exhibition;
  private String partner;

  /**
   * 提交认证信息
   *
   * @author GS
   */
  @Override
  public void application() throws Exception {

    if (getSupplier() == null) throw new WebMessageException(ReturnCode.failure, "用户未登录,无法提交认证信息");
    if (getSupplier().gtStatus() != Usr.OStatus.APPR)
      throw new WebMessageException(ReturnCode.failure, "商家未审核,无法提交认证信息");
    if (search.trim() == ""
        || "".equals(search.trim())
        || null == search.trim()
        || "{}".equals(search.trim()))
      throw new WebMessageException(ReturnCode.failure, "研发能力信息不能为空");
    if (capacity.trim() == ""
        || "".equals(capacity.trim())
        || null == capacity.trim()
        || "{}".equals(capacity.trim()))
      throw new WebMessageException(ReturnCode.failure, "生产能力信息不能为空");
    if (factory.trim() == ""
        || "".equals(factory.trim())
        || null == factory.trim()
        || "{}".equals(factory.trim()))
      throw new WebMessageException(ReturnCode.failure, "工厂模式信息不能为空");
    if (quality.trim() == ""
        || "".equals(quality.trim())
        || null == quality.trim()
        || "{}".equals(quality.trim()))
      throw new WebMessageException(ReturnCode.failure, "产品质量信息不能为空");
    if (team.trim() == ""
        || "".equals(team.trim())
        || null == team.trim()
        || "{}".equals(team.trim()))
      throw new WebMessageException(ReturnCode.failure, "外贸团队信息不能为空");
    if (exhibition.trim() == ""
        || "".equals(exhibition.trim())
        || null == exhibition.trim()
        || "{}".equals(exhibition.trim()))
      throw new WebMessageException(ReturnCode.failure, "展会信息不能为空");
    if (partner.trim() == ""
        || "".equals(partner.trim())
        || null == partner.trim()
        || "{}".equals(partner.trim()))
      throw new WebMessageException(ReturnCode.failure, "合作商信息不能为空");

    SVSInfo svs = new SVSInfo();
    Map<String, Integer> map = GetBaseScoreUtils.countROS(search, capacity, factory);
    int score =
        GetBaseScoreUtils.getBaseScore(
            search, capacity, factory, quality, team, exhibition, partner);
    if (score < 30) throw new WebMessageException(ReturnCode.failure, "未满足银牌基础分值,无法提交认证");

    if (score >= 30 && score <= 59) svs.stGrade(SVSGradeType.SILVER);
    if (score >= 60) svs.stGrade(SVSGradeType.GOLD);
    svs.stStatus(SVSAuthenticationStatus.ToBeAudited);
    svs.setBaseScore(score);
    svs.setApplicationTime(new Date());
    svs.setResearch(search);
    svs.setProductionCapacity(capacity);
    svs.setRealFactory(factory);
    svs.setProductQuality(quality);
    svs.setForeignTradeTeam(team);
    svs.setExhibitionAttended(exhibition);
    svs.setPartner(partner);
    svs.stSupplier(getSupplier());
    svs.setDynamicScore(0);
    svs.setApplicationCount(1);
    if (null != map.get("capacityBase")) svs.setCapacityBase(map.get("capacityBase"));
    if (null != map.get("researchBase")) svs.setResearchBase(map.get("researchBase"));
    if (null != map.get("factoryBase")) svs.setFactoryBase(map.get("factoryBase"));
    write(service.application(svs));
    ;
  }

  /**
   * 更新用户SVS用户信息
   *
   * @author GS
   */
  @Override
  public void updAutInfo() throws Exception {
    if (search.trim() == ""
        || "".equals(search.trim())
        || null == search.trim()
        || "{}".equals(search.trim()))
      throw new WebMessageException(ReturnCode.failure, "研发能力信息不能为空");
    if (capacity.trim() == ""
        || "".equals(capacity.trim())
        || null == capacity.trim()
        || "{}".equals(capacity.trim()))
      throw new WebMessageException(ReturnCode.failure, "生产能力信息不能为空");
    if (factory.trim() == ""
        || "".equals(factory.trim())
        || null == factory.trim()
        || "{}".equals(factory.trim()))
      throw new WebMessageException(ReturnCode.failure, "工厂模式信息不能为空");
    if (quality.trim() == ""
        || "".equals(quality.trim())
        || null == quality.trim()
        || "{}".equals(quality.trim()))
      throw new WebMessageException(ReturnCode.failure, "产品质量信息不能为空");
    if (team.trim() == ""
        || "".equals(team.trim())
        || null == team.trim()
        || "{}".equals(team.trim()))
      throw new WebMessageException(ReturnCode.failure, "外贸团队信息不能为空");
    if (exhibition.trim() == ""
        || "".equals(exhibition.trim())
        || null == exhibition.trim()
        || "{}".equals(exhibition.trim()))
      throw new WebMessageException(ReturnCode.failure, "展会信息不能为空");
    if (partner.trim() == ""
        || "".equals(partner.trim())
        || null == partner.trim()
        || "{}".equals(partner.trim()))
      throw new WebMessageException(ReturnCode.failure, "合作商信息不能为空");

    if (getSupplier() != null)
      write(
          service.updSVSInfo(
              getSupplier().getPkey(),
              search,
              capacity,
              factory,
              quality,
              team,
              exhibition,
              partner));
    else throw new WebMessageException(ReturnCode.failure, "用户未登录");
  }

  /**
   * 获取用户SVS认证信息
   *
   * @author GS
   */
  @Override
  public void getAutInfo() throws Exception {
    if (getSupplier() != null) write(service.getSVSInfo(getSupplier().getPkey()));
    else throw new WebMessageException(ReturnCode.failure, "用户未登录");
  };
}
