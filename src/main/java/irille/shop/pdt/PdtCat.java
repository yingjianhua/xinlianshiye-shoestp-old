package irille.shop.pdt;

import java.util.Date;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 产品类目
 *
 * @author yingjianhua
 */
public class PdtCat extends BeanInt<PdtCat> implements IExtName {
  public static final Tb TB =
      new Tb(PdtCat.class, "产品类目").setAutoIncrement().addActIUDL().addActEnabled();

  public enum T implements IEnumFld { // @formatter:off
    PKEY(TB.crtIntPkey()),
    NAME(SYS.MUILTI_LANGUAGE, "标题"), // 标题  多国语言
    CATEGORY_UP(PdtCat.fldOutKey("category", "隶属分类").setNull()),
    ENABLED(SYS.ENABLED), // 上架整个分类产品
    // 3.1.1
    PRODUCT_IMAGE(SYS.STR__200_NULL, "产品分类图片"),
    DISPLAY(SYS.NY, "首页显示"),

    // SEO字段
    SEO_TITLE_EN(SYS.STR__200_NULL, "标题"), // 标题
    SEO_KEYWORD_EN(SYS.STR__200_NULL, "关键词"), // 关键词
    SEO_DESCRIPTION_EN(SYS.STR__200_NULL, "简述"), // 简述
    DELETED(SYS.NY, "是否删除"),
    CREATE_BY(SYS.CREATED_BY),
    CREATE_TIME(SYS.CREATED_DATE_TIME),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
    T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  @Override
  public String getExtName() {
    return getName();
  }

  // @formatter:on

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 标题  JSONOBJECT
  private Integer _categoryUp;	// 隶属分类 <表主键:PdtCat>  INT<null>
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private String _productImage;	// 产品分类图片  STR(200)<null>
  private Byte _display;	// 首页显示 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _seoTitleEn;	// 标题  STR(200)<null>
  private String _seoKeywordEn;	// 关键词  STR(200)<null>
  private String _seoDescriptionEn;	// 简述  STR(200)<null>
  private Byte _deleted;	// 是否删除 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _createBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PdtCat init(){
		super.init();
    _name=null;	// 标题  JSONOBJECT
    _categoryUp=null;	// 隶属分类 <表主键:PdtCat>  INT
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _productImage=null;	// 产品分类图片  STR(200)
    _display=OYn.DEFAULT.getLine().getKey();	// 首页显示 <OYn>  BYTE
    _seoTitleEn=null;	// 标题  STR(200)
    _seoKeywordEn=null;	// 关键词  STR(200)
    _seoDescriptionEn=null;	// 简述  STR(200)
    _deleted=OYn.DEFAULT.getLine().getKey();	// 是否删除 <OYn>  BYTE
    _createBy=Idu.getUser().getPkey();	// 建档员 <表主键:SysUser>  INT
    _createTime=Env.getTranBeginTime();	// 建档时间  TIME
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
  public JSONObject gtName() throws JSONException {
    return getName()==null?new JSONObject():new JSONObject(getName());
  }
  public void stName(JSONObject name){
    setName(name==null?null:name.toString());
  }
  public String getName(FldLanguage.Language l) throws JSONException {
    return gtName().has(l.name())?gtName().getString(l.name()):"";
  }
  public void setName(String name, FldLanguage.Language l) throws JSONException {
    stName(gtName().put(l.name(), name));
  }
  public Integer getCategoryUp(){
    return _categoryUp;
  }
  public void setCategoryUp(Integer categoryUp){
    _categoryUp=categoryUp;
  }
  public PdtCat gtCategoryUp(){
    if(getCategoryUp()==null)
      return null;
    return (PdtCat)get(PdtCat.class,getCategoryUp());
  }
  public void stCategoryUp(PdtCat categoryUp){
    if(categoryUp==null)
      setCategoryUp(null);
    else
      setCategoryUp(categoryUp.getPkey());
  }
  public Byte getEnabled(){
    return _enabled;
  }
  public void setEnabled(Byte enabled){
    _enabled=enabled;
  }
  public Boolean gtEnabled(){
    return byteToBoolean(_enabled);
  }
  public void stEnabled(Boolean enabled){
    _enabled=booleanToByte(enabled);
  }
  public String getProductImage(){
    return _productImage;
  }
  public void setProductImage(String productImage){
    _productImage=productImage;
  }
  public Byte getDisplay(){
    return _display;
  }
  public void setDisplay(Byte display){
    _display=display;
  }
  public Boolean gtDisplay(){
    return byteToBoolean(_display);
  }
  public void stDisplay(Boolean display){
    _display=booleanToByte(display);
  }
  public String getSeoTitleEn(){
    return _seoTitleEn;
  }
  public void setSeoTitleEn(String seoTitleEn){
    _seoTitleEn=seoTitleEn;
  }
  public String getSeoKeywordEn(){
    return _seoKeywordEn;
  }
  public void setSeoKeywordEn(String seoKeywordEn){
    _seoKeywordEn=seoKeywordEn;
  }
  public String getSeoDescriptionEn(){
    return _seoDescriptionEn;
  }
  public void setSeoDescriptionEn(String seoDescriptionEn){
    _seoDescriptionEn=seoDescriptionEn;
  }
  public Byte getDeleted(){
    return _deleted;
  }
  public void setDeleted(Byte deleted){
    _deleted=deleted;
  }
  public Boolean gtDeleted(){
    return byteToBoolean(_deleted);
  }
  public void stDeleted(Boolean deleted){
    _deleted=booleanToByte(deleted);
  }
  public Integer getCreateBy(){
    return _createBy;
  }
  public void setCreateBy(Integer createBy){
    _createBy=createBy;
  }
  public SysUser gtCreateBy(){
    if(getCreateBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getCreateBy());
  }
  public void stCreateBy(SysUser createBy){
    if(createBy==null)
      setCreateBy(null);
    else
      setCreateBy(createBy.getPkey());
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
