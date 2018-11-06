package irille.shop.usr;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtCat;
import irille.shop.plt.PltConfigDAO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 店铺分类 供应商自定义的产品分类， 商家端定义的产品类目
 *
 * @author yingjianhua
 */
public class UsrProductCategory extends BeanInt<UsrProductCategory> implements IExtName {
	public static final Tb TB = new Tb(UsrProductCategory.class, "店铺-产品类目").setAutoIncrement().addActList();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()),
		NAME(SYS.MUILTI_LANGUAGE, "标题"), // 标题  多国语言
		CATEGORY_UP(UsrProductCategory.fldOutKey("category", "隶属分类").setNull()),
		SUPPLIER(UsrSupplier.fldOutKey("supplier", "所属供应商")),//所属供应商
		ENABLED(SYS.ENABLED), //上架整个分类产品
		//SEO字段
		SEO_TITLE_EN(SYS.STR__200_NULL, "标题"), //标题
		SEO_KEYWORD_EN(SYS.STR__200_NULL, "关键词"), //关键词
		SEO_DESCRIPTION_EN(SYS.STR__200_NULL, "简述"), //简述

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
		T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
	}

	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}

	@Override
	public String getExtName() {
		String name = null;
		try {
			name = getName(PltConfigDAO.manageLanguage());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return name;
	}

	// @formatter:on

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 标题  JSONOBJECT
  private Integer _categoryUp;	// 隶属分类 <表主键:UsrProductCategory>  INT<null>
  private Integer _supplier;	// 所属供应商 <表主键:UsrSupplier>  INT
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private String _seoTitleEn;	// 标题  STR(200)<null>
  private String _seoKeywordEn;	// 关键词  STR(200)<null>
  private String _seoDescriptionEn;	// 简述  STR(200)<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public UsrProductCategory init(){
		super.init();
    _name=null;	// 标题  JSONOBJECT
    _categoryUp=null;	// 隶属分类 <表主键:UsrProductCategory>  INT
    _supplier=null;	// 所属供应商 <表主键:UsrSupplier>  INT
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _seoTitleEn=null;	// 标题  STR(200)
    _seoKeywordEn=null;	// 关键词  STR(200)
    _seoDescriptionEn=null;	// 简述  STR(200)
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
  public UsrProductCategory gtCategoryUp(){
    if(getCategoryUp()==null)
      return null;
    return (UsrProductCategory)get(UsrProductCategory.class,getCategoryUp());
  }
  public void stCategoryUp(UsrProductCategory categoryUp){
    if(categoryUp==null)
      setCategoryUp(null);
    else
      setCategoryUp(categoryUp.getPkey());
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
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
