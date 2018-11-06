package irille.shop.cnt;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OEnabled;
import irille.core.sys.SysSeq;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanLong;
import irille.pub.bean.ISeq;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.pub.tb.*;
import irille.shop.pdt.PdtCat;

import java.util.Date;

public class CntAd extends BeanLong<CntAd> implements ISeq {
    public static final Tb TB = new Tb(CntAd.class, "广告管理").setAutoIncrement().addActIUDL();

    public enum T implements IEnumFld {
        PKEY(TB.crtLongPkey()),
        SIGNAGE(Tb.crt(OSIGNAGE.DEFAULT)),
        AD_POSITION(Tb.crt(OAdLocation.DEFAULT)),
        CATEGORY(PdtCat.fldOutKey().setNull()),  //仅当广告位置为 板块广告的时候有效
        HEIGHT(SYS.INT, "高"),
        WIDTH(SYS.INT, "宽"),
        DISPLAY_TYPE(Tb.crt(OAdDisplayType.DEFAULT)),
        LANG(SYS.STR__10, "语言标识"),
        //        LANG(PltConfig.fldOutKey()),
        ENABLED(SYS.ENABLED),
        CREATED_BY(SYS.CREATED_BY),
        CREATED_TIME(SYS.CREATED_DATE_TIME),
        ROW_VERSION(SYS.ROW_VERSION),
        //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
        //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
        //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<

//        public static final Index IDX_NUMBER = TB.addIndex("number", true, PKEY);
//        public static final Index IDX_ADPOSITION = TB.addIndex("adposition", true, AD_POSITION);

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


    @Override
    public void initSeq(SysSeq s) {
        s.setPkey(gtTable().getPkey());
        s.stOrgFlag(false);
        s.stType(Sys.OType.NONE);
    }

    //@formatter:on

    //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Long _pkey;	// 编号  LONG
  private Byte _signage;	// 系统标识 <OSIGNAGE>  BYTE
	// ANY:0,所有平台
	// PC:1,PC
	// IOS:2,IOS
	// ANDROID:3,ANDROID
	// OTHER:4,其他
  private Byte _adPosition;	// 展示位置 <OAdLocation>  BYTE
	// INDEX_SWIPER:0,首页轮播图
	// INDEX_RIGHT_TOP:5,首页左侧上部
	// INDEX_RIGHT_BOTTOM:6,首页左侧下部
	// INDEX_BANNER_MIDDLE:10,首页中间
	// PDT_CAT:15,产品分类广告
	// SUPPLIER_HEADED:20,供应商列表头部
  private Integer _category;	// 产品类目 <表主键:PdtCat>  INT<null>
  private Integer _height;	// 高  INT
  private Integer _width;	// 宽  INT
  private Byte _displayType;	// 展示类型 <OAdDisplayType>  BYTE
	// ONE:0,渐显
	// TWO:1,上滚动
	// THREE:2,下滚动
  private String _lang;	// 语言标识  STR(10)
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private Integer _createdBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public CntAd init(){
		super.init();
    _signage=OSIGNAGE.DEFAULT.getLine().getKey();	// 系统标识 <OSIGNAGE>  BYTE
    _adPosition=OAdLocation.DEFAULT.getLine().getKey();	// 展示位置 <OAdLocation>  BYTE
    _category=null;	// 产品类目 <表主键:PdtCat>  INT
    _height=0;	// 高  INT
    _width=0;	// 宽  INT
    _displayType=OAdDisplayType.DEFAULT.getLine().getKey();	// 展示类型 <OAdDisplayType>  BYTE
    _lang=null;	// 语言标识  STR(10)
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _createdBy=Idu.getUser().getPkey();	// 建档员 <表主键:SysUser>  INT
    _createdTime=Env.getTranBeginTime();	// 建档时间  TIME
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
  public Byte getSignage(){
    return _signage;
  }
  public void setSignage(Byte signage){
    _signage=signage;
  }
  public OSIGNAGE gtSignage(){
    return (OSIGNAGE)(OSIGNAGE.ANY.getLine().get(_signage));
  }
  public void stSignage(OSIGNAGE signage){
    _signage=signage.getLine().getKey();
  }
  public Byte getAdPosition(){
    return _adPosition;
  }
  public void setAdPosition(Byte adPosition){
    _adPosition=adPosition;
  }
  public OAdLocation gtAdPosition(){
    return (OAdLocation)(OAdLocation.INDEX_SWIPER.getLine().get(_adPosition));
  }
  public void stAdPosition(OAdLocation adPosition){
    _adPosition=adPosition.getLine().getKey();
  }
  public Integer getCategory(){
    return _category;
  }
  public void setCategory(Integer category){
    _category=category;
  }
  public PdtCat gtCategory(){
    if(getCategory()==null)
      return null;
    return (PdtCat)get(PdtCat.class,getCategory());
  }
  public void stCategory(PdtCat category){
    if(category==null)
      setCategory(null);
    else
      setCategory(category.getPkey());
  }
  public Integer getHeight(){
    return _height;
  }
  public void setHeight(Integer height){
    _height=height;
  }
  public Integer getWidth(){
    return _width;
  }
  public void setWidth(Integer width){
    _width=width;
  }
  public Byte getDisplayType(){
    return _displayType;
  }
  public void setDisplayType(Byte displayType){
    _displayType=displayType;
  }
  public OAdDisplayType gtDisplayType(){
    return (OAdDisplayType)(OAdDisplayType.ONE.getLine().get(_displayType));
  }
  public void stDisplayType(OAdDisplayType displayType){
    _displayType=displayType.getLine().getKey();
  }
  public String getLang(){
    return _lang;
  }
  public void setLang(String lang){
    _lang=lang;
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
  public Integer getCreatedBy(){
    return _createdBy;
  }
  public void setCreatedBy(Integer createdBy){
    _createdBy=createdBy;
  }
  public SysUser gtCreatedBy(){
    if(getCreatedBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getCreatedBy());
  }
  public void stCreatedBy(SysUser createdBy){
    if(createdBy==null)
      setCreatedBy(null);
    else
      setCreatedBy(createdBy.getPkey());
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

    //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

    public enum OAdLocation implements IEnumOpt {
        //需定义
        INDEX_SWIPER(0, "首页轮播图"), INDEX_RIGHT_TOP(5, "首页左侧上部"), INDEX_RIGHT_BOTTOM(6, "首页左侧下部"), INDEX_BANNER_MIDDLE(10, "首页中间"), PDT_CAT(15, "产品分类广告"),
        SUPPLIER_HEADED(20, "供应商列表头部");
        public static final String NAME = "展示位置";
        public static final OAdLocation DEFAULT = INDEX_SWIPER; // 定义缺省值;

        private EnumLine _line;

        OAdLocation(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            return _line;
        }
    }

    public enum OAdDisplayType implements IEnumOpt {
        ONE(0, "渐显"), TWO(1, "上滚动"), THREE(2, "下滚动");
        public static final String NAME = "展示类型";
        public static final OAdDisplayType DEFAULT = ONE; // 定义缺省值;

        private EnumLine _line;

        OAdDisplayType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            return _line;
        }
    }

    public enum OSIGNAGE implements IEnumOpt {
        ANY(0, "移动端"), PC(1, "PC端"), IOS(2, "IOS"), ANDROID(3, "ANDROID"), OTHER(4, "其他");
        public static final String NAME = "系统标识";
        public static final OSIGNAGE DEFAULT = ANY; // 定义缺省值;

        private EnumLine _line;

        OSIGNAGE(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            return _line;
        }
    }
}
