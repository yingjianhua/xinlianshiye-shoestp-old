package irille.Dao.EO;

import irille.shop.easy.EasyOdr;
import irille.shop.easy.EasyOdr.T;
import irille.shop.easy.EasyOdrline;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.shop.pdt.PdtSpec;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrPurchaseLine;
import irille.view.EO.easyodrView;
import irille.view.EO.eolineView;
import irille.view.Page;
import irille.view.odr.OrderSearchView;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EasyOdrDao {
    private static final LogMessage LOG = new LogMessage(EasyOdrDao.class);
    @Inject
    private EasyOdrDao.Ins ins;

    @Inject
    private  EasyOdrLineDao.Ins lins;
    /**
    *@Description: 商家端显示订购会商品列表
     * @parameter :  1.开始查询条数 2.查询几条 3.产品名称 4.开始时间 5.商家id
    *@date 2018/12/5 15:59
    *@anthor wilson zhang
    */
    public Page sellerlist(Integer start, Integer limit, OrderSearchView osv, Integer supplierid ){
        if(null!=start){ start = 0; }
        if(null!=limit){ start = 0; }
        SQL sql= new SQL(){{
                     SELECT(T.PKEY,T.TIME,T.ORDER_NUM,T.ADDRESS,T.COUNYPD,T.NAME,T.PHONE,T.PURCHASE)
                    .FROM(EasyOdr.class);
                     if(null !=osv) {
                         System.out.println(osv.getBeginTime());
                         if (null != osv.getBeginTime() && null != osv.getEndTime()) {
                             WHERE(T.TIME, ">?", osv.getBeginTime());
                             WHERE(T.TIME, "<?", osv.getEndTime());
                         }
                         if (null != osv.getNumber()) {
                             WHERE(T.ORDER_NUM, "like '%"+osv.getNumber()+"%'");
                         }
                     }
                    if (null != supplierid) {
                        WHERE(T.SUPPLIER, "=?", supplierid);
                    }
        }};
        Integer count= Query.sql(sql).queryCount();
        List<easyodrView> eslist=  Query.sql(sql).queryMaps().stream().map(bean ->new easyodrView(){{
            setId((Integer)bean.get(T.PKEY.getFld().getCodeSqlField()));
            setTime((Date)bean.get(T.TIME.getFld().getCodeSqlField()));
            setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
            setPhone((String)bean.get(T.PHONE.getFld().getCodeSqlField()));
            UsrPurchase up=BeanBase.load(UsrPurchase.class,(Integer)bean.get(T.PURCHASE.getFld().getCodeSqlField()));
            setEmail(up.getEmail());
            setOdrnum((String)bean.get(T.ORDER_NUM.getFld().getCodeSqlField()));
            setAddress((String) bean.get(T.ADDRESS));
            setCount((Integer)bean.get(T.COUNYPD.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());

        return  new Page(eslist,start,limit,count);
    }


    /**
     * @Description: 购物车生成订单
     * @date 2018/12/5 13:44
     * @anthor wilson zhang
     */
    public void generate0rder(Integer getPurchaseLineid, Integer Purchaseid, List<easyodrView> list)  throws  Exception{
        UsrPurchaseLine address = null;
        try {
            address = BeanBase.load(UsrPurchaseLine.class, getPurchaseLineid);
        } catch (Exp e) {
            throw LOG.errTran("addressfrom%Please_Select_The_Shipping_Address", "请选择收货地址");
        }

        for (int j = 0; j <list.size() ; j++) {


                    EasyOdr eo = new EasyOdr();
                    eo.setPurchase(Purchaseid);
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
                    //添加新订单
                    ins.setB(eo).commit();
                    //添加订单号
                    Integer odrnum= buildOrderNum( ins.getB());
                    Integer counti=0;
                    List<eolineView> elvlist= list.get(j).getList();
                    for (int i = 0; i < elvlist.size(); i++) {
                        EasyOdrline eol=new EasyOdrline();
                        eol.setOrderId(odrnum);
                        eol.setSpec(Integer.valueOf(elvlist.get(i).getId()));
                        PdtSpec ps=BeanBase.load(PdtSpec.class,elvlist.get(i).getId());
                        if(eo.getSupplier()==null){
                            eo.setSupplier(ps.gtProduct().gtSupplier().getPkey());
                        }
                        eol.setIamge(ps.getPics());
                        eol.setProductname(ps.gtProduct().getName());
                        eol.setColor(ps.gtColor().getName());
                        eol.setSize(ps.gtSize().getName());
                        eol.setNum(Integer.valueOf(elvlist.get(i).getNum()));
                        counti+=Integer.valueOf(elvlist.get(i).getNum());
                        eol.setRemarks(list.get(j).getRemarks());
                        try{
                            eol.ins();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        System.out.println(getPurchaseLineid);
                        System.out.println(ps.getPkey());
                        SQL sql=new SQL(){{
                                    DELETE_FROM(UsrCart.class)
                                    .WHERE(UsrCart.T.PURCHASE,"=?",Purchaseid)
                                    .WHERE(UsrCart.T.SPEC,"=?",ps.getPkey());
                        }};
                        Query.sql(sql).executeUpdate();
                    }
                    if(null !=counti){
                        ins.getB().setCounypd(counti);
                        ins.getB().upd();
                    }
        }
    }
    private Integer buildOrderNum(EasyOdr order) {
        //设置订单号
        String timeStamp = Env.getSystemTime().getTime() + "";
        String pkey = String.valueOf(order.getPkey());
        String orderid = timeStamp.substring(0, timeStamp.length() - pkey.length()) + pkey;
        order.setOrderNum(orderid);
        order.upd();
        return order.getPkey();
    }

    /**
     * @Description: 生成订单管理
     * @date 2018/12/5 13:44
            * @anthor wilson zhang
     */
    public static class Ins extends IduIns<Ins, EasyOdr> {
        @Override
        public void before() {
            getB().setTime(Env.getTranBeginTime());
            getB().setCounypd(0);
            super.before();
        }
    }


}
