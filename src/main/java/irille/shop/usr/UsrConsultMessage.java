package irille.shop.usr;

import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

import java.util.Date;

public class UsrConsultMessage extends BeanInt<UsrConsultMessage>{
	public static final Tb TB = new Tb(UsrConsultMessage.class, "询盘留言").setAutoIncrement().addActIUDL();
	public  enum T implements  IEnumFld{//@formatter:off
		PKEY(TB.crtIntPkey()),
		CONTENT(SYS.TEXT__20000_NULL,"内容"),//留言内容
		SEND_TIME(SYS.TIME, "留言时间"), //留言时间
		RELATION(UsrConsultRelation.fldOutKey("relation", "询盘关联")),
		P_2_S(SYS.YN, "是采购商留言"),//true:采购商给供应商留言，false:供应商给采购商留言
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
		//@formatter:on
	  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _content;	// 内容  TEXT(200)<null>
  private Date _sendTime;	// 留言时间  TIME
  private Integer _relation;	// 询盘关联 <表主键:UsrConsultRelation>  INT
  private Byte _p2S;	// 是采购商留言 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public UsrConsultMessage init(){
		super.init();
    _content=null;	// 内容  TEXT(200)
    _sendTime=null;	// 留言时间  TIME
    _relation=null;	// 询盘关联 <表主键:UsrConsultRelation>  INT
    _p2S=OYn.DEFAULT.getLine().getKey();	// 是采购商留言 <OYn>  BYTE
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
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public Date getSendTime(){
    return _sendTime;
  }
  public void setSendTime(Date sendTime){
    _sendTime=sendTime;
  }
  public Integer getRelation(){
    return _relation;
  }
  public void setRelation(Integer relation){
    _relation=relation;
  }
  public UsrConsultRelation gtRelation(){
    if(getRelation()==null)
      return null;
    return (UsrConsultRelation)get(UsrConsultRelation.class,getRelation());
  }
  public void stRelation(UsrConsultRelation relation){
    if(relation==null)
      setRelation(null);
    else
      setRelation(relation.getPkey());
  }
  public Byte getP2S(){
    return _p2S;
  }
  public void setP2S(Byte p2S){
    _p2S=p2S;
  }
  public Boolean gtP2S(){
    return byteToBoolean(_p2S);
  }
  public void stP2S(Boolean p2S){
    _p2S=booleanToByte(p2S);
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	   //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<  

}
