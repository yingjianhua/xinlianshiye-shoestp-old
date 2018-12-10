package irille.Entity.Pk;

import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/3
 * Time: 10:51
 */
public class PkCompetitionData extends BeanInt<PkCompetitionData> {
    public static final Tb TB = new Tb(PkCompetitionData.class, "PK大赛数据统计").setAutoIncrement().addActList();

    public enum T implements IEnumFld {
        PKEY(TB.crtIntPkey()),
        SUPID(UsrSupplier.fldOutKey()),
        PE(SYS.INT, "曝光量"),
        TRAFFICVOLUME(SYS.INT, "点击量"),
        INQUIRY(SYS.INT, "询盘量"),
        CREATEDTIME(SYS.CREATED_DATE_TIME, "生成时间"),
        ROW_VERSION(SYS.ROW_VERSION),
        ;
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<

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

    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _supid;	// 供应商 <表主键:UsrSupplier>  INT
  private Integer _pe;	// 曝光量  INT
  private Integer _trafficvolume;	// 点击量  INT
  private Integer _inquiry;	// 询盘量  INT
  private Date _createdtime;	// 生成时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PkCompetitionData init(){
		super.init();
    _supid=null;	// 供应商 <表主键:UsrSupplier>  INT
    _pe=0;	// 曝光量  INT
    _trafficvolume=0;	// 点击量  INT
    _inquiry=0;	// 询盘量  INT
    _createdtime=Env.getTranBeginTime();	// 生成时间  TIME
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
  public Integer getPe(){
    return _pe;
  }
  public void setPe(Integer pe){
    _pe=pe;
  }
  public Integer getTrafficvolume(){
    return _trafficvolume;
  }
  public void setTrafficvolume(Integer trafficvolume){
    _trafficvolume=trafficvolume;
  }
  public Integer getInquiry(){
    return _inquiry;
  }
  public void setInquiry(Integer inquiry){
    _inquiry=inquiry;
  }
  public Date getCreatedtime(){
    return _createdtime;
  }
  public void setCreatedtime(Date createdtime){
    _createdtime=createdtime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
    // @formatter:off
}