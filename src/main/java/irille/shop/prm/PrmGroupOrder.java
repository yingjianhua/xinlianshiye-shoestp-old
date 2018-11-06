package irille.shop.prm;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 采购商订购商品时生成的总订单信息
 * @author liyichao
 *
 */
public class PrmGroupOrder extends BeanInt<PrmGroupOrder>{
	public static final Tb TB = new Tb(PrmGroupOrder.class,"团购订单").setAutoIncrement().addActIUDL();
	
	public enum T implements IEnumFld{
		PKEY(TB.crtIntPkey()),
		ORDER_NUM(SYS.STR__100_NULL,"订单号"), 
		PURCHASE(UsrPurchase.fldOutKey()), //采购商
		ACTIVITY(PrmGroupPurchase.fldOutKey()),		 //订单相应联合采购
		COUNT(SYS.INT,"订购数量"),		 
		AMT_TOTAL(SYS.PRICE,"订购总价"),
		CURRENCY(PltErate.fldOutKey("currency", "货币")),
		CREATED_TIME(SYS.CREATED_DATE_TIME),
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		
		private Fld _fld;
		private T(Class clazz,String name,boolean... isnull) 
			{_fld=TB.addOutKey(clazz,this,name,isnull);	}
		private T(IEnumFld fld,boolean... isnull) { this(fld,null,isnull); } 
		private T(IEnumFld fld, String name,boolean... null1) {
			_fld=TB.add(fld,this,name,null1);}
		private T(IEnumFld fld, String name,int strLen) {
			_fld=TB.add(fld,this,name,strLen);}
		private T(Fld fld) {_fld=TB.add(fld,this); }
		public Fld getFld(){return _fld;}
		
	}
	static { //在此可以加一些对FLD进行特殊设定的代码
		T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
	}
	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}
	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}
	//@formatter:on

		//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _orderNum;	// 订单号  STR(100)<null>
  private Integer _purchase;	// 采购商 <表主键:UsrPurchase>  INT
  private Integer _activity;	// 联合采购 <表主键:PrmGroupPurchase>  INT
  private Integer _count;	// 订购数量  INT
  private BigDecimal _amtTotal;	// 订购总价  DEC(14,4)
  private Integer _currency;	// 货币 <表主键:PltErate>  INT
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PrmGroupOrder init(){
		super.init();
    _orderNum=null;	// 订单号  STR(100)
    _purchase=null;	// 采购商 <表主键:UsrPurchase>  INT
    _activity=null;	// 联合采购 <表主键:PrmGroupPurchase>  INT
    _count=0;	// 订购数量  INT
    _amtTotal=ZERO;	// 订购总价  DEC(14,4)
    _currency=null;	// 货币 <表主键:PltErate>  INT
    _createdTime=Env.getTranBeginTime();	// 建档时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getOrderNum(){
    return _orderNum;
  }
  public void setOrderNum(String orderNum){
    _orderNum=orderNum;
  }
  public Integer getPurchase(){
    return _purchase;
  }
  public void setPurchase(Integer purchase){
    _purchase=purchase;
  }
  public UsrPurchase gtPurchase(){
    if(getPurchase()==null)
      return null;
    return (UsrPurchase)get(UsrPurchase.class,getPurchase());
  }
  public void stPurchase(UsrPurchase purchase){
    if(purchase==null)
      setPurchase(null);
    else
      setPurchase(purchase.getPkey());
  }
  public Integer getActivity(){
    return _activity;
  }
  public void setActivity(Integer activity){
    _activity=activity;
  }
  public PrmGroupPurchase gtActivity(){
    if(getActivity()==null)
      return null;
    return (PrmGroupPurchase)get(PrmGroupPurchase.class,getActivity());
  }
  public void stActivity(PrmGroupPurchase activity){
    if(activity==null)
      setActivity(null);
    else
      setActivity(activity.getPkey());
  }
  public Integer getCount(){
    return _count;
  }
  public void setCount(Integer count){
    _count=count;
  }
  public BigDecimal getAmtTotal(){
    return _amtTotal;
  }
  public void setAmtTotal(BigDecimal amtTotal){
    _amtTotal=amtTotal;
  }
  public Integer getCurrency(){
    return _currency;
  }
  public void setCurrency(Integer currency){
    _currency=currency;
  }
  public PltErate gtCurrency(){
    if(getCurrency()==null)
      return null;
    return (PltErate)get(PltErate.class,getCurrency());
  }
  public void stCurrency(PltErate currency){
    if(currency==null)
      setCurrency(null);
    else
      setCurrency(currency.getPkey());
  }
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

			//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
