package com.xinlianshiye.shoestp.plat.dao.pm.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xinlianshiye.shoestp.plat.dao.pm.IPMTemplateDao;

import irille.Entity.pm.PMTemplate;
import irille.pub.bean.Query;
import irille.pub.tb.IEnumFld;
import irille.pub.util.BatchUtils;

public class PMTemplateDaoImp implements IPMTemplateDao {

  private List<String> sqls = new ArrayList<>();

  @Override
  public List<PMTemplate> list() {
    return Query.selectFrom(PMTemplate.class).queryList();
  }

  @Override
  public void add(List<PMTemplate> addBeans) {
    List<IEnumFld> fields =
        Arrays.asList(
            PMTemplate.T.RCVR_TYPE,
            PMTemplate.T.TYPE,
            PMTemplate.T.TITLE,
            PMTemplate.T.EMAIL_STATUS,
            PMTemplate.T.PM_STATUS,
            PMTemplate.T.ROW_VERSION);
    BatchUtils.batchIns(PMTemplate.class, fields, addBeans);
  }

  @Override
  public void del(List<PMTemplate> delBeans) {
    List<IEnumFld> fields = Arrays.asList(PMTemplate.T.PKEY);
    BatchUtils.batchDel(PMTemplate.class, fields, delBeans);
  }

  @Override
  public PMTemplate load(Integer pkey) {
    return Query.SELECT(PMTemplate.class, pkey);
  }

  public void addSql(String sql) {
    sqls.add(sql);
  }
}
