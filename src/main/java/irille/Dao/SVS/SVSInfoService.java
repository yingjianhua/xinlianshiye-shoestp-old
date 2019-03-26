package irille.Dao.SVS;

import com.google.inject.ImplementedBy;

import irille.Dao.SVS.impl.SVSInfoServiceImpl;
import irille.Entity.SVS.SVSInfo;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.SVS.SVSDetailedInfoView;
import irille.view.SVS.SVSInfoListView;

@ImplementedBy(SVSInfoServiceImpl.class)
public interface SVSInfoService {

  /**
   * 申请认证SVS
   *
   * @author GS
   */
  public SVSDetailedInfoView application(SVSInfo svs) throws Exception;

  public SVSDetailedInfoView getSVSInfo(Integer supplierId) throws Exception;

  /**
   * 商家端修改认证信息
   *
   * @param supplierId
   * @param res
   * @param capacity
   * @param factory
   * @param quality
   * @param team
   * @param exhibition
   * @param part
   * @return
   * @throws Exception
   * @author GS
   */
  public SVSDetailedInfoView updSVSInfo(
      Integer supplierId,
      String res,
      String capacity,
      String factory,
      String quality,
      String team,
      String exhibition,
      String part)
      throws Exception;

  /**
   * 平台端修改商家认证信息
   *
   * @param supplierId
   * @param res
   * @param capacity
   * @param factory
   * @param quality
   * @param team
   * @param exhibition
   * @param part
   * @return
   * @throws Exception
   */
  public SVSDetailedInfoView adminUpdSVSInfo(
      UsrSupplier supplier,
      String res,
      String capacity,
      String factory,
      String quality,
      String team,
      String exhibition,
      String part)
      throws Exception;
  
  public Page<SVSInfoListView>  findSVSInfoList(Integer start,Integer limit,String shopName,Byte status,Byte shopStatus,Byte grade);
}
