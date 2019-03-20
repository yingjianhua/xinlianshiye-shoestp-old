package irille.shop.pdt;

import java.util.List;
import java.util.stream.Collectors;

import irille.action.dataimport.util.StringUtil;
import irille.core.sys.Sys.OYn;
import irille.pub.LogMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.svr.Env;
import irille.pub.util.Censor.SensitiveWord;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.view.Page;
import irille.view.pdt.PdtDescribeModuleView;

public class PdtDescribeModuleDAo {

  public static final LogMessage LOG = new LogMessage(PdtDescribeModuleDAo.class);

  public static Page getList(Integer supplier, Integer start, Integer limit) {
    SQL sql = new SQL();
    sql.SELECT(PdtDescribeModule.class);
    sql.FROM(PdtDescribeModule.class);
    sql.AND();
    sql.WHERE(PdtDescribeModule.T.SUPPLIER, " =? ", supplier);
    sql.orWhere(PdtDescribeModule.T.DEFAULT_MODULE, " =? ", OYn.YES.getLine().getKey());
    sql.AND();
    sql.WHERE(PdtDescribeModule.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    Integer count = Query.sql(sql).queryCount();
    List<PdtDescribeModule> list =
        Query.sql(sql.LIMIT(start, limit)).queryList(PdtDescribeModule.class);
    List<PdtDescribeModuleView> listView =
        list.stream()
            .map(
                bean ->
                    new PdtDescribeModuleView() {
                      {
                        setId(bean.getPkey());
                        setName(bean.getName());
                        setRemark(bean.getRemark());
                        setCreateTime(bean.getCreateTime());
                        setUpdateTime(bean.getUpdateTime());
                        setDefaultModule((int) bean.getDefaultModule());
                        setDescription(bean.getDescription());
                      }
                    })
            .collect(Collectors.toList());
    return new Page(listView, start, limit, count);
  }

  public static PdtDescribeModuleView getInfo(Integer supplier, Integer dmPkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtDescribeModule.class);
    sql.FROM(PdtDescribeModule.class);
    sql.WHERE(PdtDescribeModule.T.PKEY, " =? ", dmPkey);
    sql.WHERE(PdtDescribeModule.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    PdtDescribeModule bean = Query.sql(sql).query(PdtDescribeModule.class);
    if (bean == null) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    }
    if (bean.getSupplier() != null && !bean.getSupplier().equals(supplier)) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    }
    PdtDescribeModuleView dmView = new PdtDescribeModuleView();
    dmView.setId(bean.getPkey());
    dmView.setName(bean.getName());
    dmView.setRemark(bean.getRemark());
    dmView.setCreateTime(bean.getCreateTime());
    dmView.setUpdateTime(bean.getUpdateTime());
    dmView.setDefaultModule((int) bean.getDefaultModule());
    dmView.setDescription(bean.getDescription());
    return dmView;
  }

  public static void del(Integer supplier, Integer dmPkey) {
    PdtDescribeModule bean = verify(supplier, dmPkey);
    bean.setDeleted(OYn.YES.getLine().getKey());
    bean.upd();
  }

  public static void add(PdtDescribeModule dm, Integer supplier) {
    checkLenth(dm);
    Integer queryCount =
        Query.SELECT(PdtDescribeModule.class)
            .FROM(PdtDescribeModule.class)
            .WHERE(PdtDescribeModule.T.SUPPLIER, " =? ", supplier)
            .WHERE(PdtDescribeModule.T.DELETED, " =? ", OYn.NO.getLine().getKey())
            .queryCount();
    if (queryCount >= 10) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "您已添加了10条,无法继续添加");
    }

    dm.setSupplier(supplier);
    dm.setCreateTime(Env.getSystemTime());
    dm.setUpdateTime(Env.getSystemTime());
    dm.setDefaultModule(OYn.NO.getLine().getKey());
    dm.setDeleted(OYn.NO.getLine().getKey());
    dm.ins();
  }

  public static void upd(PdtDescribeModule dm, Integer supplier) {
    checkLenth(dm);
    PdtDescribeModule bean = verify(supplier, dm.getPkey());
    bean.setName(dm.getName());
    bean.setRemark(dm.getRemark());
    bean.setDescription(dm.getDescription());
    bean.setUpdateTime(Env.getSystemTime());
    bean.upd();
  }

  public static PdtDescribeModule verify(Integer supplier, Integer dmPkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtDescribeModule.class);
    sql.FROM(PdtDescribeModule.class);
    sql.WHERE(PdtDescribeModule.T.PKEY, " =? ", dmPkey);
    sql.WHERE(PdtDescribeModule.T.SUPPLIER, " =? ", supplier);
    sql.WHERE(PdtDescribeModule.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    PdtDescribeModule bean = Query.sql(sql).query(PdtDescribeModule.class);
    if (bean == null) throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    return bean;
  }

  public static Page platGetList(Integer start, Integer limit) {
    SQL sql = new SQL();
    sql.SELECT(PdtDescribeModule.class);
    sql.FROM(PdtDescribeModule.class);
    sql.WHERE(PdtDescribeModule.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    Integer count = Query.sql(sql).queryCount();
    List<PdtDescribeModule> list =
        Query.sql(sql.LIMIT(start, limit)).queryList(PdtDescribeModule.class);
    List<PdtDescribeModuleView> listView =
        list.stream()
            .map(
                bean ->
                    new PdtDescribeModuleView() {
                      {
                        setId(bean.getPkey());
                        setName(bean.getName());
                        setRemark(bean.getRemark());
                        setCreateTime(bean.getCreateTime());
                        setUpdateTime(bean.getUpdateTime());
                        setDefaultModule((int) bean.getDefaultModule());
                        setDescription(bean.getDescription());
                        setSup(bean.getSupplier());
                      }
                    })
            .collect(Collectors.toList());
    return new Page(listView, start, limit, count);
  }

  public static PdtDescribeModuleView platGetInfo(Integer dmPkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtDescribeModule.class);
    sql.FROM(PdtDescribeModule.class);
    sql.WHERE(PdtDescribeModule.T.PKEY, " =? ", dmPkey);
    sql.WHERE(PdtDescribeModule.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    PdtDescribeModule bean = Query.sql(sql).query(PdtDescribeModule.class);
    if (bean == null) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    }
    PdtDescribeModuleView dmView = new PdtDescribeModuleView();
    dmView.setId(bean.getPkey());
    dmView.setName(bean.getName());
    dmView.setRemark(bean.getRemark());
    dmView.setCreateTime(bean.getCreateTime());
    dmView.setUpdateTime(bean.getUpdateTime());
    dmView.setDefaultModule((int) bean.getDefaultModule());
    dmView.setDescription(bean.getDescription());
    return dmView;
  }

  public static void platUpd(PdtDescribeModule dm) {
    checkLenth(dm);
    PdtDescribeModule bean = Query.SELECT(PdtDescribeModule.class, dm.getPkey());
    if (bean == null) throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    bean.setName(dm.getName());
    bean.setRemark(dm.getRemark());
    bean.setDescription(dm.getDescription());
    bean.setUpdateTime(Env.getSystemTime());
    if (bean.getDefaultModule() == OYn.YES.getLine().getKey()) translateUtil.autoTranslate(bean);
    bean.upd();
  }

  public static void platAdd(PdtDescribeModule dm) {
    checkLenth(dm);
    dm.setCreateTime(Env.getSystemTime());
    dm.setUpdateTime(Env.getSystemTime());
    dm.setDefaultModule(OYn.YES.getLine().getKey());
    dm.setDeleted(OYn.NO.getLine().getKey());
    translateUtil.autoTranslate(dm);
    dm.ins();
  }

  public static void platDel(Integer dmpkey) {
    PdtDescribeModule bean = Query.SELECT(PdtDescribeModule.class, dmpkey);
    if (bean == null) throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    bean.setDeleted(OYn.YES.getLine().getKey());
    bean.upd();
  }

  public static void checkLenth(PdtDescribeModule dm) {
    if (dm.getName().length() > PdtDescribeModule.T.NAME.getFld().getLen())
      throw new WebMessageException(ReturnCode.service_wrong_data, "超出名称长度");
    if (StringUtil.hasValue(dm.getRemark())) {
      if (dm.getRemark().length() > PdtDescribeModule.T.REMARK.getFld().getLen())
        throw new WebMessageException(ReturnCode.service_wrong_data, "超出备注长度");
    }
    // 敏感词校验
    SensitiveWord.filterInfo(dm.getName());
  }
}
