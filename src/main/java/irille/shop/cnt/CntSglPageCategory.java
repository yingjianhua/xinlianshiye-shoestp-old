package irille.shop.cnt;

import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
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
public class CntSglPageCategory extends BeanInt<CntSglPageCategory> implements IExtName { // implements ICmbRpRpt{
    /**
     * setAutoIncrement 设置自增
     * addActIUDL 增加IUDL 按钮
     * addActEnabled  增加启用按钮
     */
    public static final Tb TB = new Tb(CntSglPageCategory.class, "单页类型").setAutoIncrement().addActIUDL();

    @Override
    public String getExtName() {
        return getPageName();
    }

    //表结构定义
    public enum T implements IEnumFld {//@formatter:off
        PKEY(TB.crtIntPkey()),   //字段字段字段
        //        CODE(SYS.CODE__100),
        PAGE_NAME(SYS.MUILTI_LANGUAGE, "名称"),  //多国语言
        TAG(SYS.STR__40_NULL, "关键词"),
        PAGE_TYPE_TEXT(SYS.TEXT__200_NULL, "简述"),
        //ENABLED(SYS.ENABLED),
//        LANG(SYS.STR__8, "语言标识"),
        CREATE_BY(SYS.CREATED_BY),
        CREATE_TIME(SYS.CREATED_DATE_TIME),
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

    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _pageName;	// 名称  JSONOBJECT
  private String _tag;	// 关键词  STR(40)<null>
  private String _pageTypeText;	// 简述  TEXT(200)<null>
  private Integer _createBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public CntSglPageCategory init(){
		super.init();
    _pageName=null;	// 名称  JSONOBJECT
    _tag=null;	// 关键词  STR(40)
    _pageTypeText=null;	// 简述  TEXT(200)
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
  public String getPageName(){
    return _pageName;
  }
  public void setPageName(String pageName){
    _pageName=pageName;
  }
  public JSONObject gtPageName() throws JSONException {
    return getPageName()==null?new JSONObject():new JSONObject(getPageName());
  }
  public void stPageName(JSONObject pageName){
    setPageName(pageName==null?null:pageName.toString());
  }
  public String getPageName(FldLanguage.Language l) throws JSONException {
    return gtPageName().has(l.name())?gtPageName().getString(l.name()):"";
  }
  public void setPageName(String pageName, FldLanguage.Language l) throws JSONException {
    stPageName(gtPageName().put(l.name(), pageName));
  }
  public String getTag(){
    return _tag;
  }
  public void setTag(String tag){
    _tag=tag;
  }
  public String getPageTypeText(){
    return _pageTypeText;
  }
  public void setPageTypeText(String pageTypeText){
    _pageTypeText=pageTypeText;
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
