package irille.Service.Manage.O2O;

import com.google.inject.ImplementedBy;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Service.Manage.O2O.Imp.O2OActivityServiceImpl;
import irille.view.O2O.O2OProductView;
import irille.view.O2O.PdtSearchView;
import irille.view.Page;
import irille.view.O2O.O2OActivityView;

/**
 * O2O活动业务层
 *
 * @author Jianhua Ying
 */
@ImplementedBy(O2OActivityServiceImpl.class)
public interface O2OActivityService {

    /**
     * <p>活动列表
     * <p>搜索条件
     * <ul>
     * <li>开始时间
     * <li>截止时间
     * <li>活动名称
     * <li>活动状态
     */
    Page<O2OActivityView> list(Integer start, Integer limit, O2OActivityView condition);

    /**
     * <p>取消活动
     */
    void cancel(Integer pkey);

    /**
     * <p>发布活动
     */
    void deploy(O2OActivityView view);

    /**
     * 报名列表
     */
    Page<O2OProductView> enrollList(PdtSearchView search, Integer start, Integer limit,Integer type);

    /**
     * 审核
     */
    void appr(Integer id, String reason, O2O_ProductStatus status);

    /**
     * 私人展厅商品审核
     */
    void privatefindAppr(Integer id, String reason, O2O_PrivateExpoPdtStatus status);

    /**
     * 处理上下架
     */
    void lowerAndUpper(Integer id, String reason, O2O_ProductStatus status);

    /**
     * 私人订购会商品处理上下架
     */
    void privateLowerAndUpper(Integer id, String reason, O2O_PrivateExpoPdtStatus status);

    O2OActivityView load(Integer pkey);

    Page priveteList(int start, int limit, Integer status, Integer verify_status, String cat, String supName);
}
