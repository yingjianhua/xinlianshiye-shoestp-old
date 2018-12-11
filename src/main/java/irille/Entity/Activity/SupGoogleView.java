package irille.Entity.Activity;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;

import java.util.Date;

/**
 * Pk大赛 商家谷歌数据视图 保存表
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 11:21
 */
public class SupGoogleView extends BeanInt<SupGoogleView> {
    public static final Tb TB = new Tb(SupGoogleView.class, "商家谷歌数据视图保存").setAutoIncrement();

    public enum T implements IEnumFld {
        PKEY(TB.crtIntPkey()),
        SUPID(UsrSupplier.fldOutKey()),
        VIEWID(SYS.STR__20),
        CREATED_TIME(SYS.CREATED_DATE_TIME),
        ROW_VERSION(SYS.ROW_VERSION),
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

        private Fld _fld;
        public static final Tb.Index IDX_SUP_ID = TB.addIndex("sup_id", true, SUPID);

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
    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _supid;	// 供应商 <表主键:UsrSupplier>  INT
  private String _viewid;	// 字符20  STR(20)
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public SupGoogleView init(){
		super.init();
    _supid=null;	// 供应商 <表主键:UsrSupplier>  INT
    _viewid=null;	// 字符20  STR(20)
    _createdTime=Env.getTranBeginTime();	// 建档时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static SupGoogleView loadUniqueSup_id(boolean lockFlag,Integer supid) {
    return (SupGoogleView)loadUnique(T.IDX_SUP_ID,lockFlag,supid);
  }
  public static SupGoogleView chkUniqueSup_id(boolean lockFlag,Integer supid) {
    return (SupGoogleView)chkUnique(T.IDX_SUP_ID,lockFlag,supid);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getSupid(){
    return _supid;
  }
  public void setSupid(Integer supid){
    _supid=supid;
  }
  public UsrSupplier gtSupid(){
    if(getSupid()==null)
      return null;
    return (UsrSupplier)get(UsrSupplier.class,getSupid());
  }
  public void stSupid(UsrSupplier supid){
    if(supid==null)
      setSupid(null);
    else
      setSupid(supid.getPkey());
  }
  public String getViewid(){
    return _viewid;
  }
  public void setViewid(String viewid){
    _viewid=viewid;
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
