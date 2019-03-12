package irille.shop.usr;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class UsrAnnex extends BeanInt<UsrAnnex>{
	public static final Tb TB = new Tb(UsrAnnex.class,"供应商的附件表").addActIUDL().setAutoIncrement();

	public enum T implements IEnumFld{
		PKEY(Tb.crtIntPkey()),
		SUPPLIER(UsrSupplier.fldOutKey()), // 供应商外键
        CERT_PHOTO_Name(SYS.STR__200_NULL, "供应商表营业执照图片对应的文件名"),
        ID_CARD_FRONT_PHOTO_Name(SYS.STR__200_NULL, "供应商表法人身份证正面图片文件名"),
        CONTACTS_ID_CARD_FRONT_PHOTO_Name(SYS.STR__200_NULL, "供应商表运营负责人身份证正面图片文件名"),
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
        public static final Tb.Index IDX_SUPPLIER = TB.addIndex("supplier",true, T.SUPPLIER);
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
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}

	//@formatter:on
	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _supplier;	// 供应商 <表主键:UsrSupplier>  INT
  private String _certPhotoName;	// 供应商表营业执照图片对应的文件名  STR(200)<null>
  private String _idCardFrontPhotoName;	// 供应商表法人身份证正面图片文件名  STR(200)<null>
  private String _contactsIdCardFrontPhotoName;	// 供应商表运营负责人身份证正面图片文件名  STR(200)<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public UsrAnnex init(){
		super.init();
    _supplier=null;	// 供应商 <表主键:UsrSupplier>  INT
    _certPhotoName=null;	// 供应商表营业执照图片对应的文件名  STR(200)
    _idCardFrontPhotoName=null;	// 供应商表法人身份证正面图片文件名  STR(200)
    _contactsIdCardFrontPhotoName=null;	// 供应商表运营负责人身份证正面图片文件名  STR(200)
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static UsrAnnex loadUniqueSupplier(boolean lockFlag,Integer supplier) {
    return (UsrAnnex)loadUnique(T.IDX_SUPPLIER,lockFlag,supplier);
  }
  public static UsrAnnex chkUniqueSupplier(boolean lockFlag,Integer supplier) {
    return (UsrAnnex)chkUnique(T.IDX_SUPPLIER,lockFlag,supplier);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getSupplier(){
    return _supplier;
  }
  public void setSupplier(Integer supplier){
    _supplier=supplier;
  }
  public UsrSupplier gtSupplier(){
    if(getSupplier()==null)
      return null;
    return (UsrSupplier)get(UsrSupplier.class,getSupplier());
  }
  public void stSupplier(UsrSupplier supplier){
    if(supplier==null)
      setSupplier(null);
    else
      setSupplier(supplier.getPkey());
  }
  public String getCertPhotoName(){
    return _certPhotoName;
  }
  public void setCertPhotoName(String certPhotoName){
    _certPhotoName=certPhotoName;
  }
  public String getIdCardFrontPhotoName(){
    return _idCardFrontPhotoName;
  }
  public void setIdCardFrontPhotoName(String idCardFrontPhotoName){
    _idCardFrontPhotoName=idCardFrontPhotoName;
  }
  public String getContactsIdCardFrontPhotoName(){
    return _contactsIdCardFrontPhotoName;
  }
  public void setContactsIdCardFrontPhotoName(String contactsIdCardFrontPhotoName){
    _contactsIdCardFrontPhotoName=contactsIdCardFrontPhotoName;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
