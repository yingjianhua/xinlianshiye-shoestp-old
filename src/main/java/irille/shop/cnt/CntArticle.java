package irille.shop.cnt;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 单据关联关系表，包括源--目的，主--明细，关联单据在此定义
 *
 * @author surface1
 */
public class CntArticle extends BeanInt<CntArticle> { // implements ICmbRpRpt{
    public static final Tb TB = new Tb(CntArticle.class, "文章管理").setAutoIncrement().addActIUDL().addActEnabled();


    //表结构定义
    public enum T implements IEnumFld {//@formatter:off
        PKEY(TB.crtIntPkey()),   //字段字段字段
        TITLE(SYS.MUILTI_LANGUAGE, "标题"), // 多国语言
        ARTICLE_CATEGORY(CntArticleCategory.fldOutKey(), "分类"),
        URL(SYS.STR__50_NULL, "URL"),
        REWRITE_URL(SYS.STR__20_NULL, "自定义URL地址"),
        IMAGES(SYS.IMG__400_NULL, "图片"),
        DATE(SYS.DATE__NULL, "日期"),
        TAG(SYS.NAME__100_NULL, "TAG"),
        KEYWORD(SYS.NAME__100_NULL, "关键词"),
        INTRO(SYS.MUILTI_LANGUAGE_NULL, "简述"), // 多国语言
        DETAIL(SYS.MUILTI_LANGUAGE_NULL, "详细描述"), // 多国语言
        ENABLED(SYS.ENABLED, "显示"),
        CREATE_BY(SYS.CREATED_BY),
        CREATE_TIME(SYS.CREATED_DATE_TIME),
        ROW_VERSION(SYS.ROW_VERSION),;

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

        private T(Fld fld, String name) {
            _fld = TB.add(fld, this, name);
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
  private String _title;	// 标题  JSONOBJECT
  private Integer _articleCategory;	// 分类 <表主键:CntArticleCategory>  INT
  private String _url;	// URL  STR(50)<null>
  private String _rewriteUrl;	// 自定义URL地址  STR(20)<null>
  private String _images;	// 图片  STR(400)<null>
  private Date _date;	// 日期  DATE<null>
  private String _tag;	// TAG  STR(100)<null>
  private String _keyword;	// 关键词  STR(100)<null>
  private String _intro;	// 简述  JSONOBJECT<null>
  private String _detail;	// 详细描述  JSONOBJECT<null>
  private Byte _enabled;	// 显示 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private Integer _createBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public CntArticle init(){
		super.init();
    _title=null;	// 标题  JSONOBJECT
    _articleCategory=null;	// 分类 <表主键:CntArticleCategory>  INT
    _url=null;	// URL  STR(50)
    _rewriteUrl=null;	// 自定义URL地址  STR(20)
    _images=null;	// 图片  STR(400)
    _date=null;	// 日期  DATE
    _tag=null;	// TAG  STR(100)
    _keyword=null;	// 关键词  STR(100)
    _intro=null;	// 简述  JSONOBJECT
    _detail=null;	// 详细描述  JSONOBJECT
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 显示 <OEnabled>  BYTE
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
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public JSONObject gtTitle() throws JSONException {
    return getTitle()==null?new JSONObject():new JSONObject(getTitle());
  }
  public void stTitle(JSONObject title){
    setTitle(title==null?null:title.toString());
  }
  public String getTitle(FldLanguage.Language l) throws JSONException {
    return gtTitle().has(l.name())?gtTitle().getString(l.name()):"";
  }
  public void setTitle(String title, FldLanguage.Language l) throws JSONException {
    stTitle(gtTitle().put(l.name(), title));
  }
  public Integer getArticleCategory(){
    return _articleCategory;
  }
  public void setArticleCategory(Integer articleCategory){
    _articleCategory=articleCategory;
  }
  public CntArticleCategory gtArticleCategory(){
    if(getArticleCategory()==null)
      return null;
    return (CntArticleCategory)get(CntArticleCategory.class,getArticleCategory());
  }
  public void stArticleCategory(CntArticleCategory articleCategory){
    if(articleCategory==null)
      setArticleCategory(null);
    else
      setArticleCategory(articleCategory.getPkey());
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
  }
  public String getRewriteUrl(){
    return _rewriteUrl;
  }
  public void setRewriteUrl(String rewriteUrl){
    _rewriteUrl=rewriteUrl;
  }
  public String getImages(){
    return _images;
  }
  public void setImages(String images){
    _images=images;
  }
  public Date getDate(){
    return _date;
  }
  public void setDate(Date date){
    _date=date;
  }
  public String getTag(){
    return _tag;
  }
  public void setTag(String tag){
    _tag=tag;
  }
  public String getKeyword(){
    return _keyword;
  }
  public void setKeyword(String keyword){
    _keyword=keyword;
  }
  public String getIntro(){
    return _intro;
  }
  public void setIntro(String intro){
    _intro=intro;
  }
  public JSONObject gtIntro() throws JSONException {
    return getIntro()==null?new JSONObject():new JSONObject(getIntro());
  }
  public void stIntro(JSONObject intro){
    setIntro(intro==null?null:intro.toString());
  }
  public String getIntro(FldLanguage.Language l) throws JSONException {
    return gtIntro().has(l.name())?gtIntro().getString(l.name()):"";
  }
  public void setIntro(String intro, FldLanguage.Language l) throws JSONException {
    stIntro(gtIntro().put(l.name(), intro));
  }
  public String getDetail(){
    return _detail;
  }
  public void setDetail(String detail){
    _detail=detail;
  }
  public JSONObject gtDetail() throws JSONException {
    return getDetail()==null?new JSONObject():new JSONObject(getDetail());
  }
  public void stDetail(JSONObject detail){
    setDetail(detail==null?null:detail.toString());
  }
  public String getDetail(FldLanguage.Language l) throws JSONException {
    return gtDetail().has(l.name())?gtDetail().getString(l.name()):"";
  }
  public void setDetail(String detail, FldLanguage.Language l) throws JSONException {
    stDetail(gtDetail().put(l.name(), detail));
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

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
