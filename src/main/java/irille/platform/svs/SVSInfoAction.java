package irille.platform.svs;

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

  // 修改对应认证信息
  public void updAutInfo() throws Exception {
    if (supplierId == null) throw new WebMessageException(ReturnCode.failure, "用户编号不能为空");

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
  void findAutInf() {}

  @Override
  public Class beanClazz() {
    // TODO Auto-generated method stub
    return SVSInfo.class;
  }
}
