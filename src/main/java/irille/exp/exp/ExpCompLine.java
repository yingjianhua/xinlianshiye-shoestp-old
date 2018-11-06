package irille.exp.exp;

import irille.pub.Log;
import irille.pub.bean.BeanLong;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

import java.math.BigDecimal;
import java.util.Date;

public class ExpCompLine extends BeanLong<ExpCompLine>{
	private static final Log LOG = new Log(ExpCompLine.class);
	public static final Tb TB = new Tb(ExpCompLine.class, "测试明细", "明细").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtLongPkey()), 
		MAIN(ExpComp.fldOutKey()),
		AMT(SYS.AMT),
		DATE_TIME(SYS.DATE_TIME__NULL),
		ROW_VERSION(SYS.ROW_VERSION),
		// (FLDS.),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
//		 public static final Index IDX_MAIN_EM = TB.addIndex("mainEm", true,MAIN);
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

	static { // 在此可以加一些对FLD进行特殊设定的代码
		T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
	}

	// @formatter:on

	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Long _pkey;	// 编号  LONG
  private Long _main;	// 复合界面 <表主键:ExpComp>  LONG
  private BigDecimal _amt;	// 金额  DEC(16,2)
  private Date _dateTime;	// 日期时间  TIME<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public ExpCompLine init(){
		super.init();
    _main=null;	// 复合界面 <表主键:ExpComp>  LONG
    _amt=ZERO;	// 金额  DEC(16,2)
    _dateTime=null;	// 日期时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Long getPkey(){
    return _pkey;
  }
  public void setPkey(Long pkey){
    _pkey=pkey;
  }
  public Long getMain(){
    return _main;
  }
  public void setMain(Long main){
    _main=main;
  }
  public ExpComp gtMain(){
    if(getMain()==null)
      return null;
    return (ExpComp)get(ExpComp.class,getMain());
  }
  public void stMain(ExpComp main){
    if(main==null)
      setMain(null);
    else
      setMain(main.getPkey());
  }
  public BigDecimal getAmt(){
    return _amt;
  }
  public void setAmt(BigDecimal amt){
    _amt=amt;
  }
  public Date getDateTime(){
    return _dateTime;
  }
  public void setDateTime(Date dateTime){
    _dateTime=dateTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
