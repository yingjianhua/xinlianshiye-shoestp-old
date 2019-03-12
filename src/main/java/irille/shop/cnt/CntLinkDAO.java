package irille.shop.cnt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.shop.plt.Plt.ErrMsgs;

public class CntLinkDAO {
  public static final Log LOG = new Log(CntLinkDAO.class);

  public static class Ins extends IduIns<Ins, CntLink> {
    /*
     * 自动生成修改时间,建党员,建档时间
     * @see irille.pub.idu.Idu#before()
     */
    @Override
    public void before() {
      getB().setUpdatedTime(Env.getSystemTime()); // 修改时间
      getB().setCreatedTime(Env.getSystemTime()); // 建党时间
      getB().setCreatedBy(Idu.getUser().getPkey()); // 建党员
      getB().setUpdatedBy(Idu.getUser().getPkey()); // 更新员
      super.before();
    }

    /*
     * 验证数值的正确性
     * @see irille.pub.idu.IduIns#valid()
     */
    @Override
    public void valid() {
      FormValid().validNotEmpty(CntLink.T.NAME, CntLink.T.UPDATED_TIME);

      Validate validDate = new Validate(getB());
      Validate.validNumber(
          new Integer[] {getB().getSort(), getB().getCreatedBy()},
          CntLink.T.SORT.getFld().getName(),
          CntLink.T.CREATED_BY.getFld().getName());
      Validate.validUniqueErr(
          new Object[] {CntLink.chkUniqueName(false, getB().getName())},
          CntLink.T.NAME.getFld().getName());
      // 验证数值不能为null
      Validate.validNotNull(
          new Object[] {getB().getUpdatedTime(), getB().getCreatedTime()},
          CntLink.T.UPDATED_TIME.getFld().getName(),
          ErrMsgs.notNull,
          CntLink.T.CREATED_TIME.getFld().getName());
      super.valid();
    }
  }

  public static class Upd extends IduUpd<Upd, CntLink> {
    /*
     * 验证数值的正确性
     * @see irille.pub.idu.IduIns#valid()
     */
    @Override
    public void valid() {
      super.valid();
      FormValid().validNotEmpty(CntLink.T.NAME, CntLink.T.UPDATED_TIME);

      Validate validDate = new Validate(getB());
      Validate.validNumber(
          new Integer[] {getB().getSort(), getB().getCreatedBy()},
          CntLink.T.SORT.getFld().getName(),
          CntLink.T.CREATED_BY.getFld().getName());
      // 验证数值不能为null
      Validate.validNotNull(
          new Object[] {getB().getUpdatedTime(), getB().getCreatedTime()},
          CntLink.T.UPDATED_TIME.getFld().getName(),
          ErrMsgs.notNull,
          CntLink.T.CREATED_TIME.getFld().getName());
    }

    /*
     * 自动生成修改时间,定义不修改的字段
     * @see irille.pub.idu.Idu#before()
     */
    @Override
    public void before() {
      CntLink dbBean = loadThisBeanAndLock();
      getB().setUpdatedTime(Env.getSystemTime()); // 自动生成修改时间
      getB().setUpdatedBy(Idu.getUser().getPkey()); // 更新员
      PropertyUtils.copyPropertiesWithout(
          dbBean, getB(), CntLink.T.CREATED_BY, CntLink.T.CREATED_TIME);
      setB(dbBean);
    }
  }
}
