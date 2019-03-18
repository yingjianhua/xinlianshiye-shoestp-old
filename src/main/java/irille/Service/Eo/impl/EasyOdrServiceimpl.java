package irille.Service.Eo.impl;

import java.util.List;

import javax.inject.Inject;

import irille.Dao.EO.EasyOdrDao;
import irille.Service.Eo.EasyOdrService;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.bean.BeanBase;
import irille.pub.svr.Env;
import irille.shop.easy.EasyOdr;
import irille.shop.easy.EasyOdrline;
import irille.shop.pdt.PdtSpec;
import irille.shop.usr.UsrPurchaseLine;
import irille.view.Easy.EasyodrView;
import irille.view.Easy.EolineView;
import org.json.JSONObject;

public class EasyOdrServiceimpl implements EasyOdrService {
  private static final LogMessage LOG = new LogMessage(EasyOdrDao.class);

  @Inject private EasyOdrDao easyOdrDao;

  @Override
  public void generateOrder(Integer getPurchaseLineid, Integer purchaseid, List<EasyodrView> list)
      throws Exception {
    UsrPurchaseLine address = null;
    try {
      address = BeanBase.load(UsrPurchaseLine.class, getPurchaseLineid);
    } catch (Exp e) {
      throw LOG.errTran("addressfrom%Please_Select_The_Shipping_Address", "请选择收货地址");
    }
    for (int j = 0; j < list.size(); j++) {
      EasyOdr eo = new EasyOdr();
      eo.setPurchase(purchaseid);
      eo.setName(address.getName());
      eo.setPhone(address.getPhonenumber());
      eo.setSupplier(list.get(j).getSupplierid());
      JSONObject ja = new JSONObject();
      ja.put("countryid", address.getCountry());
      ja.put("regionid", address.getRegion());
      ja.put("city", address.getCity());
      ja.put("address", address.getAddress());
      eo.setAddress(ja.toString());
      eo.setTime(Env.getTranBeginTime());
      eo.setCounypd(0);
      // 添加订单号
      Integer odrnum = easyOdrDao.buildOrderNum(easyOdrDao.addNewOrder(eo));
      Integer count = 0;
      List<EolineView> elvlist = list.get(j).getList();
      for (int i = 0; i < elvlist.size(); i++) {
        EasyOdrline eol = new EasyOdrline();
        eol.setOrderId(odrnum);
        eol.setSpec(Integer.valueOf(elvlist.get(i).getId()));
        PdtSpec ps = BeanBase.load(PdtSpec.class, elvlist.get(i).getId());
        if (eo.getSupplier() == null) {
          eo.setSupplier(ps.gtProduct().gtSupplier().getPkey());
        }
        eol.setIamge(ps.getPics());
        eol.setProductname(ps.gtProduct().getName());
        eol.setColor(ps.gtColor().getName());
        eol.setSize(ps.gtSize().getName());
        eol.setNum(Integer.valueOf(elvlist.get(i).getNum()));
        count += Integer.valueOf(elvlist.get(i).getNum());
        eol.setRemarks(list.get(j).getRemarks());
        try {
          eol.ins();
        } catch (Exception e) {
          e.printStackTrace();
        }
        easyOdrDao.removaCat(purchaseid, ps.getPkey());
      }
      if (null != count) {
        easyOdrDao.updateCountry(count);
      }
    }
  }

  /**
   * @Description: 老订单转换为新订单数据
   *
   * @date 2018/12/14 10:25
   * @author lijie@shoestp.cn
   */
  public void oldorderToEasyOrder() {}
}
