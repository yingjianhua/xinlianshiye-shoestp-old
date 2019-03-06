package irille.Entity.SVS;

import java.math.BigDecimal;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.usr.UsrSupplier;

public class SVSInfo  extends BeanInt<SVSInfo>{
	
	public static void main(String[] args) {
		System.out.println(TB.getCode());
		System.out.println(SVSInfo.T.PKEY);
		System.out.println(TB.getFlds().length);
	}
	
	public static final Tb<?> TB = new Tb<>(SVSInfo.class, "SVS认证").setAutoIncrement().addActIUDL();

    public enum T implements IEnumFld {
        PKEY(Tb.crtIntPkey()),
        SUPPLIER(UsrSupplier.fldOutKey()),
		RESEARCH(Sys.T.JSON,"研发能力"), 
		PRODUCTION_CAPACITY(Sys.T.JSON,"生产能力"),
		REAL_FACTORY(Sys.T.JSON,"真实工厂"),
		PRODUCT_QUALITY(Sys.T.JSON,"产品质量"),
		FOREIGN_TRADE_TEAM(Sys.T.JSON,"外贸团队"),
		EXHIBITION_ATTENDED(Sys.T.JSON,"参加过的展会"),
		PARTNER(Sys.T.JSON,"合作商"),
		BASE_SCORE(Sys.T.INT,"基础分"),
		DYNAMIC_SCORE(Sys.T.INT,"动态分"),
		STATUS(Tb.crt(SVSAuthenticationStatus.DEFAULT)),
		GRADE(Tb.crt(SVSGradeType.DEFAULT)),
		APPLICATION_TIME(Sys.T.DATE_TIME,"申请认证时间"),
		AUTHENTICATION_TIME(Sys.T.DATE_TIME__NULL,"认证时间"),
		FAILURE_REASONS(Sys.T.STR__200_NULL,"认证失败原因"),
	    ROW_VERSION(Sys.T.ROW_VERSION),
        // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
        // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
        ;
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
        T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
    }

    // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _supplier;	// 供应商 <表主键:UsrSupplier>  INT
  private String _research;	// 研发能力  JSONOBJECT
  private String _productionCapacity;	// 生产能力  JSONOBJECT
  private String _realFactory;	// 真实工厂  JSONOBJECT
  private String _productQuality;	// 产品质量  JSONOBJECT
  private String _foreignTradeTeam;	// 外贸团队  JSONOBJECT
  private String _exhibitionAttended;	// 参加过的展会  JSONOBJECT
  private String _partner;	// 合作商  JSONOBJECT
  private Integer _baseScore;	// 基础分  INT
  private Integer _dynamicScore;	// 动态分  INT
  private Byte _status;	// 认证结果类型 <SVSAuthenticationStatus>  BYTE
	// SUCCESS:1,认证成功
	// FAIL:2,认证失败
	// ToBeAudited:0,待审核
	// NoApplication:-1,未申请
  private Byte _grade;	// SVS商家等级类型 <SVSGradeType>  BYTE
	// SILVER:1,银
	// GOLD:2,金
	// DIAMONDS:3,钻石
	// NotAvailable:0,暂无等级
  private Date _applicationTime;	// 申请认证时间  TIME
  private Date _authenticationTime;	// 认证时间  TIME<null>
  private String _failureReasons;	// 认证失败原因  STR(200)<null>
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public SVSInfo init(){
		super.init();
    _supplier=null;	// 供应商 <表主键:UsrSupplier>  INT
    _research=null;	// 研发能力  JSONOBJECT
    _productionCapacity=null;	// 生产能力  JSONOBJECT
    _realFactory=null;	// 真实工厂  JSONOBJECT
    _productQuality=null;	// 产品质量  JSONOBJECT
    _foreignTradeTeam=null;	// 外贸团队  JSONOBJECT
    _exhibitionAttended=null;	// 参加过的展会  JSONOBJECT
    _partner=null;	// 合作商  JSONOBJECT
    _baseScore=0;	// 基础分  INT
    _dynamicScore=0;	// 动态分  INT
    _status=SVSAuthenticationStatus.DEFAULT.getLine().getKey();	// 认证结果类型 <SVSAuthenticationStatus>  BYTE
    _grade=SVSGradeType.DEFAULT.getLine().getKey();	// SVS商家等级类型 <SVSGradeType>  BYTE
    _applicationTime=Env.getTranBeginTime();	// 申请认证时间  TIME
    _authenticationTime=null;	// 认证时间  TIME
    _failureReasons=null;	// 认证失败原因  STR(200)
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
  public String getResearch(){
    return _research;
  }
  public void setResearch(String research){
    _research=research;
  }
  public JSONObject gtResearch() throws JSONException {
    return getResearch()==null?new JSONObject():new JSONObject(getResearch());
  }
  public void stResearch(JSONObject research){
    setResearch(research==null?null:research.toString());
  }
  public String getProductionCapacity(){
    return _productionCapacity;
  }
  public void setProductionCapacity(String productionCapacity){
    _productionCapacity=productionCapacity;
  }
  public JSONObject gtProductionCapacity() throws JSONException {
    return getProductionCapacity()==null?new JSONObject():new JSONObject(getProductionCapacity());
  }
  public void stProductionCapacity(JSONObject productionCapacity){
    setProductionCapacity(productionCapacity==null?null:productionCapacity.toString());
  }
  public String getRealFactory(){
    return _realFactory;
  }
  public void setRealFactory(String realFactory){
    _realFactory=realFactory;
  }
  public JSONObject gtRealFactory() throws JSONException {
    return getRealFactory()==null?new JSONObject():new JSONObject(getRealFactory());
  }
  public void stRealFactory(JSONObject realFactory){
    setRealFactory(realFactory==null?null:realFactory.toString());
  }
  public String getProductQuality(){
    return _productQuality;
  }
  public void setProductQuality(String productQuality){
    _productQuality=productQuality;
  }
  public JSONObject gtProductQuality() throws JSONException {
    return getProductQuality()==null?new JSONObject():new JSONObject(getProductQuality());
  }
  public void stProductQuality(JSONObject productQuality){
    setProductQuality(productQuality==null?null:productQuality.toString());
  }
  public String getForeignTradeTeam(){
    return _foreignTradeTeam;
  }
  public void setForeignTradeTeam(String foreignTradeTeam){
    _foreignTradeTeam=foreignTradeTeam;
  }
  public JSONObject gtForeignTradeTeam() throws JSONException {
    return getForeignTradeTeam()==null?new JSONObject():new JSONObject(getForeignTradeTeam());
  }
  public void stForeignTradeTeam(JSONObject foreignTradeTeam){
    setForeignTradeTeam(foreignTradeTeam==null?null:foreignTradeTeam.toString());
  }
  public String getExhibitionAttended(){
    return _exhibitionAttended;
  }
  public void setExhibitionAttended(String exhibitionAttended){
    _exhibitionAttended=exhibitionAttended;
  }
  public JSONObject gtExhibitionAttended() throws JSONException {
    return getExhibitionAttended()==null?new JSONObject():new JSONObject(getExhibitionAttended());
  }
  public void stExhibitionAttended(JSONObject exhibitionAttended){
    setExhibitionAttended(exhibitionAttended==null?null:exhibitionAttended.toString());
  }
  public String getPartner(){
    return _partner;
  }
  public void setPartner(String partner){
    _partner=partner;
  }
  public JSONObject gtPartner() throws JSONException {
    return getPartner()==null?new JSONObject():new JSONObject(getPartner());
  }
  public void stPartner(JSONObject partner){
    setPartner(partner==null?null:partner.toString());
  }
  public Integer getBaseScore(){
    return _baseScore;
  }
  public void setBaseScore(Integer baseScore){
    _baseScore=baseScore;
  }
  public Integer getDynamicScore(){
    return _dynamicScore;
  }
  public void setDynamicScore(Integer dynamicScore){
    _dynamicScore=dynamicScore;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public SVSAuthenticationStatus gtStatus(){
    return (SVSAuthenticationStatus)(SVSAuthenticationStatus.NoApplication.getLine().get(_status));
  }
  public void stStatus(SVSAuthenticationStatus status){
    _status=status.getLine().getKey();
  }
  public Byte getGrade(){
    return _grade;
  }
  public void setGrade(Byte grade){
    _grade=grade;
  }
  public SVSGradeType gtGrade(){
    return (SVSGradeType)(SVSGradeType.NotAvailable.getLine().get(_grade));
  }
  public void stGrade(SVSGradeType grade){
    _grade=grade.getLine().getKey();
  }
  public Date getApplicationTime(){
    return _applicationTime;
  }
  public void setApplicationTime(Date applicationTime){
    _applicationTime=applicationTime;
  }
  public Date getAuthenticationTime(){
    return _authenticationTime;
  }
  public void setAuthenticationTime(Date authenticationTime){
    _authenticationTime=authenticationTime;
  }
  public String getFailureReasons(){
    return _failureReasons;
  }
  public void setFailureReasons(String failureReasons){
    _failureReasons=failureReasons;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

    // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
