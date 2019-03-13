package irille.shop.cnt;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class CntAdLine extends BeanInt<CntAdLine> {
  private static final Log LOG = new Log(CntAdLine.class);

  public static final Tb TB = new Tb(CntAdLine.class, "广告明细", "明细").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {
    PKEY(TB.crtIntPkey()),
    MAIN(CntAd.fldOutKey()),
    NAME(SYS.NAME__100_NULL, "广告名称"),
    BRIEF(SYS.STR__200_NULL, "广告简介"),
    IMAGE(SYS.IMG__200_NULL, "广告图片"),
    URL(SYS.STR__200, "广告链接"),
    SORT(SYS.INT, "排序"),
    MAIN_IMG(SYS.YN, "是否主图"),
    ENABLED(SYS.ENABLED),
    ROW_VERSION(SYS.ROW_VERSION),
  // (FLDS.),
  // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
  // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
  ;

    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
    T.PKEY.getFld().getTb().lockAllFlds(); // 加锁所有字段,不可以修改
  }

  // @formatter:on

  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  // 实例变量定义-----------------------------------------
  private Integer _pkey; // 编号  INT
  private Long _main; // 广告管理 <表主键:CntAd>  LONG
  private String _name; // 广告名称  STR(100)<null>
  private String _brief; // 广告简介  STR(200)<null>
  private String _image; // 广告图片  STR(200)<null>
  private String _url; // 广告链接  STR(200)
  private Integer _sort; // 排序  INT
  private Byte _mainImg; // 是否主图 <OYn>  BYTE
  // YES:1,是
  // NO:0,否
  private Byte _enabled; // 启用标志 <OEnabled>  BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private Short _rowVersion; // 版本  SHORT

  @Override
  public CntAdLine init() {
    super.init();
    _main = null; // 广告管理 <表主键:CntAd>  LONG
    _name = null; // 广告名称  STR(100)
    _brief = null; // 广告简介  STR(200)
    _image = null; // 广告图片  STR(200)
    _url = null; // 广告链接  STR(200)
    _sort = 0; // 排序  INT
    _mainImg = OYn.DEFAULT.getLine().getKey(); // 是否主图 <OYn>  BYTE
    _enabled = OEnabled.DEFAULT.getLine().getKey(); // 启用标志 <OEnabled>  BYTE
    _rowVersion = 0; // 版本  SHORT
    return this;
  }

  // 方法----------------------------------------------
  public Integer getPkey() {
    return _pkey;
  }

  public void setPkey(Integer pkey) {
    _pkey = pkey;
  }

  public Long getMain() {
    return _main;
  }

  public void setMain(Long main) {
    _main = main;
  }

  public CntAd gtMain() {
    if (getMain() == null) return null;
    return (CntAd) get(CntAd.class, getMain());
  }

  public void stMain(CntAd main) {
    if (main == null) setMain(null);
    else setMain(main.getPkey());
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public String getBrief() {
    return _brief;
  }

  public void setBrief(String brief) {
    _brief = brief;
  }

  public String getImage() {
    return _image;
  }

  public void setImage(String image) {
    _image = image;
  }

  public String getUrl() {
    return _url;
  }

  public void setUrl(String url) {
    _url = url;
  }

  public Integer getSort() {
    return _sort;
  }

  public void setSort(Integer sort) {
    _sort = sort;
  }

  public Byte getMainImg() {
    return _mainImg;
  }

  public void setMainImg(Byte mainImg) {
    _mainImg = mainImg;
  }

  public Boolean gtMainImg() {
    return byteToBoolean(_mainImg);
  }

  public void stMainImg(Boolean mainImg) {
    _mainImg = booleanToByte(mainImg);
  }

  public Byte getEnabled() {
    return _enabled;
  }

  public void setEnabled(Byte enabled) {
    _enabled = enabled;
  }

  public Boolean gtEnabled() {
    return byteToBoolean(_enabled);
  }

  public void stEnabled(Boolean enabled) {
    _enabled = booleanToByte(enabled);
  }

  public Short getRowVersion() {
    return _rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    _rowVersion = rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
