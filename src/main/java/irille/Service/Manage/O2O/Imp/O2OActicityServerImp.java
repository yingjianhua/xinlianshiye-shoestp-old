package irille.Service.Manage.O2O.Imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.plat.service.pm.imp.PMMessageServiceImp;

import irille.Dao.PdtProductDao;
import irille.Dao.O2O.O2OActivityDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.pm.PM.OTempType;
import irille.Service.Manage.O2O.IO2OActicityServer;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.pub.Log;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.Idu;
import irille.pub.svr.DbPool;
import irille.pub.util.BatchUtils;
import irille.pub.validate.Regular;
import irille.pub.validate.ValidRegex;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrSupplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class O2OActicityServerImp implements IO2OActicityServer, Job {

  private static final Log LOG = new Log(O2OActicityServerImp.class);

  private static O2OActivityDao o2OActivityDao;

  private static O2OProductDao o2OProductDao;

  private static PdtProductDao pdtProductDao;

  private static IO2OPdtServer o2OPdtServer;

  private static IPMMessageService messageService;

  static {
    if (pdtProductDao == null) pdtProductDao = new PdtProductDao();
    if (o2OActivityDao == null) o2OActivityDao = new O2OActivityDao();
    if (o2OProductDao == null) o2OProductDao = new O2OProductDao();
    if (o2OPdtServer == null) o2OPdtServer = new O2OPdtServerImp();
    if (messageService == null) messageService = new PMMessageServiceImp();
  }

  /** 参与活动 */
  @Override
  public void enroll(UsrSupplier supplier, Integer activity, String name, String tel, String pdts) {
    if (null == name || (null != name && "".equals(name.trim()))) {
      throw new WebMessageException(ReturnCode.failure, "请输入名称");
    }
    if (null == tel || (null != tel) && "".equals(tel.trim())) {
      throw new WebMessageException(ReturnCode.failure, "请输入电话号码");
    }
    if (!ValidRegex.regMarch(Regular.REGULAR_TEL, tel)) {
      throw new WebMessageException(ReturnCode.failure, "请输入格式正确的电话号码");
    }
    O2O_Activity activityEntity = o2OActivityDao.getActivityInfoById(activity);
    if (null == activityEntity) {
      throw LOG.err("noActivity", "活动不存在");
    }
    if (activityEntity.getEndDate().before(new Date())) {
      throw new WebMessageException(ReturnCode.failure, "活动已结束,无法报名参加");
    }
    boolean flag = false;
    O2O_JoinInfo joinInfo =
        O2O_JoinInfo.chkUniqueActivity_supplier(false, activity, supplier.getPkey());
    if (null == joinInfo) {
      flag = true;
      joinInfo = new O2O_JoinInfo();
      joinInfo.setActivity(activity);
      joinInfo.setSupplier(supplier.getPkey());
    }
    joinInfo.setName(name);
    joinInfo.setTel(tel);
    if (flag) {
      joinInfo.ins();
    } else {
      joinInfo.upd();
    }
    List<String> listAll = new ArrayList<>();
    if (null != activityEntity.getActivityCat()) {
      List<Integer> cats =
          Arrays.asList(activityEntity.getActivityCat().split(",")).stream()
              .map(
                  str -> {
                    return Integer.valueOf(str);
                  })
              .collect(Collectors.toList());

      cats.forEach(
          cat -> {
            listAll.addAll(
                pdtProductDao.getCatsNodeByCatId(cat).stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList()));
          });
    }
    List<O2O_Product> insO2oPdts = new ArrayList<O2O_Product>();
    List<O2O_Product> updO2oPdts = new ArrayList<O2O_Product>();
    for (PdtProduct pdt : pdtProductDao.findAllByPkeys(pdts)) {
      if (listAll.size() > 0) {
        if (listAll.indexOf(String.valueOf(pdt.getCategory())) == -1) {
          throw new WebMessageException(ReturnCode.failure, "商品分类错误");
        }
      }
      O2O_Product o2oPdt =
          O2O_Product.chkUniqueProduct_id_join_info_id(false, pdt.getPkey(), joinInfo.getPkey());
      if (o2oPdt != null) {
        if (!o2oPdt.getVerifyStatus().equals(O2O_ProductStatus.Failed.getLine().getKey())) {
          continue;
        } else if (o2oPdt.getVerifyStatus().equals(O2O_ProductStatus.Failed.getLine().getKey())) {
          o2oPdt.setVerifyStatus(O2O_ProductStatus._DEFAULT.getLine().getKey());
        }
      } else {
        o2oPdt = new O2O_Product();
        //                if(pdt.getProductType().equals(Pdt.OProductType.O2O.getLine().getKey())){
        //                	o2oPdt.setVerifyStatus(O2O_ProductStatus.PASS.getLine().getKey());
        //                }else{
        o2oPdt.setVerifyStatus(O2O_ProductStatus._DEFAULT.getLine().getKey());
        //                }
      }
      o2oPdt.setStatus(O2O_ProductStatus.ON.getLine().getKey());
      o2oPdt.setActivityId(activityEntity.getPkey());
      o2oPdt.setPrice(pdt.getCurPrice());
      o2oPdt.setMinOq(pdt.getMinOq());
      o2oPdt.setProductId(pdt.getPkey());
      o2oPdt.setUpdatedTime(new Date());
      if (null == o2oPdt.getPkey()) {
        insO2oPdts.add(o2oPdt);
      } else {
        updO2oPdts.add(o2oPdt);
      }
    }
    if (insO2oPdts.size() > 0) {
      Idu.insLine(joinInfo, insO2oPdts, O2O_Product.T.JOIN_INFO_ID.getFld());
    }
    if (updO2oPdts.size() > 0) {
      BatchUtils.batchUpd(
          O2O_Product.class,
          Arrays.asList(O2O_Product.T.VERIFY_STATUS),
          Arrays.asList(O2O_Product.T.PKEY),
          updO2oPdts);
      //      Idu.updLine(joinInfo, updO2oPdts, O2O_Product.T.JOIN_INFO_ID.getFld());
    }
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    log.info("<<<======开始更新O2O活动状态======>>>");
    List<O2O_Activity> activities = o2OActivityDao.findAllByStatusExceptEnd();
    Map<Integer, List<O2O_Product>> map = new HashMap<Integer, List<O2O_Product>>();
    if (activities != null && activities.size() > 0) {
      String actPkeys =
          activities.stream()
              .map(
                  a -> {
                    return String.valueOf(a.getPkey());
                  })
              .collect(Collectors.joining(","));

      List<O2O_Product> products = o2OProductDao.findAllByActivityIn(actPkeys);
      for (O2O_Product o : products) {
        O2O_Activity activity = o.gtActivityId();
        if (map.containsKey(activity.getPkey())) {
          map.get(activity.getPkey()).add(o);
        } else {
          List<O2O_Product> oprod = new ArrayList<O2O_Product>();
          oprod.add(o);
          map.put(activity.getPkey(), oprod);
        }
      }
    }
    List<O2O_Activity> acts = new ArrayList<O2O_Activity>();
    List<PdtProduct> prods = new ArrayList<PdtProduct>();

    Date now = new Date();
    if (null != activities) {
      for (O2O_Activity a : activities) {
        if (a.getEndDate().before(now)
            && (!a.getStatus().equals(O2O_ActivityStatus.END.getLine().getKey())
                || !a.getStatus().equals(O2O_ActivityStatus.CLOSE.getLine().getKey()))) {
          // 结束时间早于现在,活动结束
          a.setStatus(O2O_ActivityStatus.END.getLine().getKey());
          List<O2O_Product> prods2 = map.get(a.getPkey());
          if (prods2 != null) {
            for (O2O_Product p : prods2) {
              // 活动结束,变为普通商品
              PdtProduct product = p.gtProductId();
              if (o2OPdtServer.judgeO2o(product) != null
                  && o2OPdtServer.judgeO2o(product).equals(false)) {
                // 是(O2O商品-普通商品)
                product.setProductType(Pdt.OProductType.GENERAL.getLine().getKey());
                prods.add(product);
              }
            }
            acts.add(a);
          }
        }
        if (a.getStartDate().before(now)
            && a.getEndDate().after(now)
            && !a.getStatus().equals(O2O_ActivityStatus.ACTIVITY.getLine().getKey())) {
          // 开始时间早于现在,结束时间晚于现在,活动进行中
          a.setStatus(O2O_ActivityStatus.ACTIVITY.getLine().getKey());
          acts.add(a);
        }
        if (a.getStartDate().after(now)
            && !a.getStatus().equals(O2O_ActivityStatus.TOBEGIN.getLine().getKey())) {
          // 开始时间晚于现在,活动未开始
          a.setStatus(O2O_ActivityStatus.TOBEGIN.getLine().getKey());
          acts.add(a);
        }
      }
    }
    if (acts.size() > 0) {
      o2OActivityDao.upd(acts);
      for (O2O_Activity act : acts) {
        messageService.send(OTempType.O2O_ACTIVITY_NOTICE, null, null, act, act.gtAddress());
      }
      try {
        DbPool.getInstance().getConn().commit();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (prods.size() > 0) {
      pdtProductDao.upd(prods);
    }

    log.info("<<<======更新O2O活动状态结束======>>>");
  }
}
