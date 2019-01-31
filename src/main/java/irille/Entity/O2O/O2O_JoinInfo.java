package irille.Entity.O2O;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 * O2O产品
 */
public class O2O_JoinInfo extends BeanInt<O2O_JoinInfo> {

    public static final Tb TB = new Tb(O2O_JoinInfo.class, "O2O负责人信息").setAutoIncrement().addActIUDL();

    public enum T implements IEnumFld {
        PKEY(TB.crtIntPkey()),
        NAME(SYS.STR__100_NULL, "负责人信息"),
        Tel(SYS.STR__100_NULL, "电话"),
        ACTIVITY_ID(O2O_Activity.fldOutKey()),
        SUPPLIER_ID(UsrSupplier.fldOutKey()),
        ROW_VERSION(SYS.ROW_VERSION),
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

        private Fld _fld;

        private T(Class clazz, String name, boolean... isnull) {
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

        private T(Fld fld) {
            _fld = TB.add(fld, this);
        }

        public Fld getFld() {
            return _fld;
        }

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

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 负责人信息  STR(100)<null>
  private String _tel;	// 电话  STR(100)<null>
  private Integer _activityId;	// O2O活动信息 <表主键:O2O_Activity>  INT
  private Integer _supplierId;	// 供应商 <表主键:UsrSupplier>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public O2O_JoinInfo init(){
		super.init();
    _name=null;	// 负责人信息  STR(100)
    _tel=null;	// 电话  STR(100)
    _activityId=null;	// O2O活动信息 <表主键:O2O_Activity>  INT
    _supplierId=null;	// 供应商 <表主键:UsrSupplier>  INT
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
  public String getTel(){
    return _tel;
  }
  public void setTel(String tel){
    _tel=tel;
  }
  public Integer getActivityId(){
    return _activityId;
  }
  public void setActivityId(Integer activityId){
    _activityId=activityId;
  }
  public O2O_Activity gtActivityId(){
    if(getActivityId()==null)
      return null;
    return (O2O_Activity)get(O2O_Activity.class,getActivityId());
  }
  public void stActivityId(O2O_Activity activityId){
    if(activityId==null)
      setActivityId(null);
    else
      setActivityId(activityId.getPkey());
  }
  public Integer getSupplierId(){
    return _supplierId;
  }
  public void setSupplierId(Integer supplierId){
    _supplierId=supplierId;
  }
  public UsrSupplier gtSupplierId(){
    if(getSupplierId()==null)
      return null;
    return (UsrSupplier)get(UsrSupplier.class,getSupplierId());
  }
  public void stSupplierId(UsrSupplier supplierId){
    if(supplierId==null)
      setSupplierId(null);
    else
      setSupplierId(supplierId.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
