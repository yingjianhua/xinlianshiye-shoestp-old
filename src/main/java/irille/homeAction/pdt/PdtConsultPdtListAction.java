package irille.homeAction.pdt;

import irille.homeAction.HomeAction;
import irille.homeAction.pdt.inf.IPdtConsultPdtListAction;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.shop.pdt.PdtConsultPdtList;
import irille.shop.pdt.PdtConsultPdtListDAO;
import irille.view.pdt.ConsultProductView;

import java.io.IOException;

public class PdtConsultPdtListAction extends HomeAction<PdtConsultPdtList> implements IPdtConsultPdtListAction {

    private static final long serialVersionUID = -3155157653498079851L;

    private Integer product;

    /**
     * 将产品添加到询盘产品列表中
     *
     * @throws Exception
     * @author yingjianhua
     */
    @Override
    @NeedLogin
    public void add() throws IOException {
    	ConsultProductView view = PdtConsultPdtListDAO.add(product, getPurchase().getPkey());
        write(view);
    }

    /**
     * 获取当前登录采购商的询盘产品列表的数据
     *
     * @author yingjianhua
     */

    /**
     * 询盘产品列表的过滤条件
     * 可以为空
     */
    private Integer supplierId;

    @NeedLogin
    public void list() throws Exception {
        write(PdtConsultPdtListDAO.listViewByPurchase(getPurchase().getPkey(), supplierId, curLanguage()));
    }

    /**
     * 删除询盘产品列表中的产品
     *
     * @throws Exception
     * @author yingjianhua
     */
    public void delete() throws Exception {
        if (getPurchase() == null)
            writeErr(-1, "timeout");
        else if (!PdtConsultPdtListDAO.isOwner((Integer) getId(), getPurchase().getPkey()))
            writeErr("wrong owner");
        else {
            PdtConsultPdtListDAO.delete((Integer) getId());
            write();
        }
    }

    private String ids;

    /**
     * 批量删除询盘产品列表中的产品
     *
     * @throws Exception
     * @author yingjianhua
     */
    public void deletes() throws Exception {
        if (getPurchase() == null) {
            writeErr(-1, "timeout");
        } else {
            for (String id : ids.split(",")) {
                if (!PdtConsultPdtListDAO.isOwner(Integer.valueOf(id), getPurchase().getPkey())) {
                    writeErr("wrong owner");
                    return;
                }
            }
            for (String id : ids.split(",")) {
                PdtConsultPdtListDAO.delete(Integer.valueOf(id));
            }
            write();
        }
    }


    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId==-1?null:supplierId;
	}

}
