package irille.Service.Manage.O2O.Imp;

import irille.Dao.O2O.O2OActivityDao;
import irille.Dao.PdtProductDao;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_Product;
import irille.Service.Manage.O2O.IO2OActicityServer;
import irille.pub.Log;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.Idu;
import irille.pub.util.GetValue;
import irille.sellerAction.o2o.inf.IO2oActivityAction;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrSupplier;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class O2OActicityServerImp implements IO2OActicityServer{

    private static final Log LOG = new Log(O2OActicityServerImp.class);

    @Inject
    private O2OActivityDao o2OActivityDao;

    @Inject
    private PdtProductDao pdtProductDao;

    /**
     *  参与活动
     */
    @Override
    public void enroll(UsrSupplier supplier,Integer activity, String name, String tel, String pdts) {
        O2O_Activity activityEntity = o2OActivityDao.getActivityInfoById(activity);
        if(null == activityEntity)
            throw LOG.err("noActivity","活动不存在");
        if(activityEntity.getEndDate().before(new Date())){
        	throw new WebMessageException(ReturnCode.failure, "活动已结束,无法报名参加");
        }
        boolean flag = false;
        O2O_JoinInfo joinInfo = O2O_JoinInfo.chkUniqueActivity_supplier(false,activity,supplier.getPkey());
        if(null == joinInfo){
            flag = true;
            joinInfo = new O2O_JoinInfo();
            joinInfo.setActivity(activity);
            joinInfo.setSupplier(supplier.getPkey());
        }
        joinInfo.setName(name);
        joinInfo.setTel(tel);
        if(flag){
            joinInfo.ins();
        }else{
            joinInfo.upd();
        }
        List<String> listAll = new ArrayList<>();
        if(null != activityEntity.getActivityCat()){
          List<Integer> cats = Arrays.asList(activityEntity.getActivityCat().split(",")).stream().map(str->{
              return Integer.valueOf(str);
          }).collect(Collectors.toList());

          cats.forEach(cat->{
              listAll.addAll(pdtProductDao.getCatsNodeByCatId(cat).stream().map(String::valueOf).collect(Collectors.toList()));
          });
        }
        List<O2O_Product> insO2oPdts = new ArrayList<O2O_Product>();
        List<O2O_Product> updO2oPdts = new ArrayList<O2O_Product>();
        for(PdtProduct pdt:pdtProductDao.findAllByPkeys(pdts)){
            if(listAll.size() > 0){
                if(listAll.indexOf(String.valueOf(pdt.getCategory())) == -1){
                    throw new WebMessageException(ReturnCode.failure,"商品分类错误");
                }
            }
            O2O_Product o2oPdt = O2O_Product.chkUniqueProduct_id_join_info_id(false,pdt.getPkey(),joinInfo.getPkey());
            if(o2oPdt != null){
                if(!o2oPdt.getVerifyStatus().equals(O2O_ProductStatus.Failed.getLine().getKey())){
                    continue;
                }else if(o2oPdt.getVerifyStatus().equals(O2O_ProductStatus.Failed.getLine().getKey())){
                	o2oPdt.setVerifyStatus(O2O_ProductStatus._DEFAULT.getLine().getKey());
                }
            }else{
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
            if(null == o2oPdt.getPkey()){
                insO2oPdts.add(o2oPdt);
            }else{
                updO2oPdts.add(o2oPdt);
            }
        }
        if(insO2oPdts.size()>0){
            Idu.insLine(joinInfo,insO2oPdts,O2O_Product.T.JOIN_INFO_ID.getFld());
        }
        if(updO2oPdts.size()>0) {
            Idu.updLine(joinInfo, updO2oPdts, O2O_Product.T.JOIN_INFO_ID.getFld());
        }
    }


}
