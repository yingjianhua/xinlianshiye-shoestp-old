package irille.Entity.pm;

import java.util.Date;

import irille.Entity.pm.PM.OMessageType;
import irille.Entity.pm.PM.ORCVRType;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class PMRead extends BeanInt<PMRead> {
public static final Tb TB = new Tb(PMRead.class, "站内信阅读关联").setAutoIncrement().addActIUDL();
	
	public enum T implements IEnumFld {
		PKEY(TB.crtIntPkey()),
		READ_MESSAGE(PMMessage.fldOutKey()),//阅读消息
		READER(SYS.INT, "阅读人"),//阅读人
		READ_TIME(SYS.DATE_TIME__NULL, "阅读时间"),
		ROW_VERSION(SYS.ROW_VERSION),
		 //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

//        public static final Index IDX_CODE = TB.addIndex("code", true, T.);
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
        return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
    }

    public static Fld fldOutKey(String code, String name) {
        return Tb.crtOutKey(TB, code, name);
    }


    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _readMessage;	// 站内信 <表主键:PMMessage>  INT
  private Integer _reader;	// 阅读人  INT
  private Date _readTime;	// 阅读时间  TIME<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PMRead init(){
		super.init();
    _readMessage=null;	// 站内信 <表主键:PMMessage>  INT
    _reader=0;	// 阅读人  INT
    _readTime=null;	// 阅读时间  TIME
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
  public Integer getReadMessage(){
    return _readMessage;
  }
  public void setReadMessage(Integer readMessage){
    _readMessage=readMessage;
  }
  public PMMessage gtReadMessage(){
    if(getReadMessage()==null)
      return null;
    return (PMMessage)get(PMMessage.class,getReadMessage());
  }
  public void stReadMessage(PMMessage readMessage){
    if(readMessage==null)
      setReadMessage(null);
    else
      setReadMessage(readMessage.getPkey());
  }
  public Integer getReader(){
    return _reader;
  }
  public void setReader(Integer reader){
    _reader=reader;
  }
  public Date getReadTime(){
    return _readTime;
  }
  public void setReadTime(Date readTime){
    _readTime=readTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
