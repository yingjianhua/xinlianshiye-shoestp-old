/** */
package irille.temporary.entity;

import java.util.Date;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

/** @author liyichao */
public class PageHits extends BeanInt<PageHits> {
  public static final Tb<?> TB = new Tb(PageHits.class, "页面点击量").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    PAGE(SYS.STR__100, "页面名称"),
    ELEMENT(SYS.STR__100, "元素名称"),
    HITS(SYS.LONG, "点击量"),
    CREATED_TIME(SYS.DATE, "创建日期"),
    ROW_VERSION(SYS.ROW_VERSION),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;
    public static final Index IDX_PAGE_ELEMENT_CREATED_TIME =
        TB.addIndex("page_element_created_time", true, PAGE, ELEMENT, CREATED_TIME);
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    private Fld<?> _fld;

    private T(Class<?> clazz, String name, boolean... isnull) {
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

    private T(Fld<?> fld) {
      _fld = TB.add(fld, this);
    }

    public Fld<?> getFld() {
      return _fld;
    }
  }

  static { // 在此可以加一些对FLD进行特殊设定的代码
    T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _page;	// 页面名称  STR(100)
  private String _element;	// 元素名称  STR(100)
  private Long _hits;	// 点击量  LONG
  private Date _createdTime;	// 创建日期  DATE
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PageHits init(){
		super.init();
    _page=null;	// 页面名称  STR(100)
    _element=null;	// 元素名称  STR(100)
    _hits=(long)0;	// 点击量  LONG
    _createdTime=null;	// 创建日期  DATE
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static PageHits loadUniquePage_element_created_time(boolean lockFlag,String page,String element,Date createdTime) {
    return (PageHits)loadUnique(T.IDX_PAGE_ELEMENT_CREATED_TIME,lockFlag,page,element,createdTime);
  }
  public static PageHits chkUniquePage_element_created_time(boolean lockFlag,String page,String element,Date createdTime) {
    return (PageHits)chkUnique(T.IDX_PAGE_ELEMENT_CREATED_TIME,lockFlag,page,element,createdTime);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getPage(){
    return _page;
  }
  public void setPage(String page){
    _page=page;
  }
  public String getElement(){
    return _element;
  }
  public void setElement(String element){
    _element=element;
  }
  public Long getHits(){
    return _hits;
  }
  public void setHits(Long hits){
    _hits=hits;
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

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
