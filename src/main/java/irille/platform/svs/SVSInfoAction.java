package irille.platform.svs;

import java.io.IOException;

import javax.inject.Inject;

import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.SVSInfo;
import irille.action.ActionBase;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrSupplier;
import lombok.Data;

@Data
public class SVSInfoAction extends ActionBase<SVSInfo> {

  @Inject SVSInfoService service;
  // 用户编号
  private Integer supplierId;
  private String search;
  private String capacity;
  private String factory;
  private String quality;
  private String team;
  private String exhibition;
  private String partner;
  private String shopName;
  private Byte status;
  private Byte grade;
  private Byte shopStatus;

  // 修改对应认证信息
  public void updAutInfo() throws Exception {
    if (supplierId == null) throw new WebMessageException(ReturnCode.failure, "用户编号不能为空");
    if (null == search.trim()
        || search.trim() == ""
        || "".equals(search.trim())
        || "{}".equals(search.trim()))
      throw new WebMessageException(ReturnCode.failure, "研发能力信息不能为空");
    if (null == capacity.trim()
        || capacity.trim() == ""
        || "".equals(capacity.trim())
        || "{}".equals(capacity.trim()))
      throw new WebMessageException(ReturnCode.failure, "生产能力信息不能为空");
    if (null == factory.trim()
        || factory.trim() == ""
        || "".equals(factory.trim())
        || "{}".equals(factory.trim()))
      throw new WebMessageException(ReturnCode.failure, "工厂模式信息不能为空");
    if (null == quality.trim()
        || quality.trim() == ""
        || "".equals(quality.trim())
        || "{}".equals(quality.trim()))
      throw new WebMessageException(ReturnCode.failure, "产品质量信息不能为空");
    if (null == team.trim()
        || team.trim() == ""
        || "".equals(team.trim())
        || "{}".equals(team.trim()))
      throw new WebMessageException(ReturnCode.failure, "外贸团队信息不能为空");
    if (null == exhibition.trim()
        || exhibition.trim() == ""
        || "".equals(exhibition.trim())
        || "{}".equals(exhibition.trim()))
      throw new WebMessageException(ReturnCode.failure, "展会信息不能为空");
    if (null == partner.trim()
        || partner.trim() == ""
        || "".equals(partner.trim())
        || "{}".equals(partner.trim()))
      throw new WebMessageException(ReturnCode.failure, "合作商信息不能为空");
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, supplierId);
    if (supplier != null)
      write(
          service.adminUpdSVSInfo(
              supplier, search, capacity, factory, quality, team, exhibition, partner));
    else throw new WebMessageException(ReturnCode.failure, "用户不存在");
  };

  // 获取对应用户SVS信息
  void getAutInfo() {};

  // 获取SVS认证信息列表
  public void findAutInf() throws Exception {
    System.out.println(status);
    if (getLimit() == 0) setLimit(5);
    write(service.findSVSInfoList(getStart(), getLimit(), shopName, status, shopStatus, grade));
  }

  @Override
  public Class beanClazz() {
    // TODO Auto-generated method stub
    return SVSInfo.class;
  }
}
