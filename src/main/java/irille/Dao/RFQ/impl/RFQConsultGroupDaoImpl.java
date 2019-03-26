package irille.Dao.RFQ.impl;

import java.util.List;

import irille.Dao.RFQ.RFQConsultGroupDao;
import irille.Entity.RFQ.RFQConsultGroup;
import irille.pub.bean.Query;

/**
 * 询盘分组持久层
 *
 * @author Jianhua Ying
 */
public class RFQConsultGroupDaoImpl implements RFQConsultGroupDao {

  @Override
  public void save(RFQConsultGroup bean) {
    if (bean.getPkey() == null) {
      bean.ins();
    } else {
      bean.upd();
    }
  }

  @Override
  public Integer countBySupplier_PkeyName(Integer supplierId, String name) {
    return Query.SELECT(RFQConsultGroup.class)
        .WHERE(RFQConsultGroup.T.SUPPLIER, "=?", supplierId)
        .WHERE(RFQConsultGroup.T.NAME, "=?", name)
        .queryCount();
  }

  @Override
  public RFQConsultGroup findByPkeySupplier_Pkey(Integer pkey, Integer supplierId) {
    return Query.SELECT(RFQConsultGroup.class)
        .WHERE(RFQConsultGroup.T.PKEY, "=?", pkey)
        .WHERE(RFQConsultGroup.T.SUPPLIER, "=?", supplierId)
        .query();
  }

  @Override
  public void delete(Integer pkey) {
    Query.SELECT(RFQConsultGroup.class, pkey).del();
  }

  @Override
  public List<RFQConsultGroup> findAllBySupplier_Pkey(Integer supplierPkey) {
    return Query.SELECT(RFQConsultGroup.class)
        .WHERE(RFQConsultGroup.T.SUPPLIER, "=?", supplierPkey)
        .queryList();
  }
}
