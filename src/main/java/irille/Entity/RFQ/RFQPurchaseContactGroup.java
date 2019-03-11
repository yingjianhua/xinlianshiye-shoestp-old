package irille.Entity.RFQ;

import java.util.Date;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrPurchase;

public class RFQPurchaseContactGroup extends BeanInt<RFQPurchaseContactGroup> {
	
	private static final long serialVersionUID = 2724809311094704042L;
	
	public static final Tb<?> TB = new Tb<>(RFQPurchaseContactGroup.class, "采购商联系人分组").setAutoIncrement();

    public enum T implements IEnumFld {
        PKEY(Tb.crtIntPkey()),
        NAME(Sys.T.STR__100),//分组名称
        PURCHASE(UsrPurchase.fldOutKey()),//采购商
        CREATED_TIME(Sys.T.CREATED_DATE),//建立时间
        ROW_VERSION(Sys.T.ROW_VERSION),
        // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
        private Fld<?> _fld;

        private T(Class<?> clazz, String name, boolean... isnull) {
            _fld = TB.addOutKey(clazz, this, name, isnull);
        }

        private T(IEnumFld fld, boolean... isnull) {
            this(fld, null, isnull);
        }

        private T(IEnumFld fld, String name, boolean... null1) {
            _fld = TB.add(fld, this, name, null1);
        }

        private T(IEnumFld fld, String name, int strLen) {
            _fld = TB.add(fld, this, name, strLen);
        }

        private T(Fld<?> fld) {
            _fld = TB.add(fld, this);
        }

        public Fld<?> getFld() {
            return _fld;
        }
    }

    static { // 在此可以加一些对FLD进行特殊设定的代码
        T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
    }

    public static Fld<?> fldOutKey() {
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
    }

    public static Fld<?> fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name).setType(null);
    }

    // @formatter:on
    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 字符100  STR(100)
  private Integer _purchase;	// 采购商 <表主键:UsrPurchase>  INT
  private Date _createdTime;	// 建档日期  DATE
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public RFQPurchaseContactGroup init(){
		super.init();
    _name=null;	// 字符100  STR(100)
    _purchase=null;	// 采购商 <表主键:UsrPurchase>  INT
    _createdTime=Env.getWorkDate();	// 建档日期  DATE
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
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
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

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
