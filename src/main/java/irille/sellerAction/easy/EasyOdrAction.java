package irille.sellerAction.easy;

import com.google.inject.Inject;
import irille.Dao.EO.EasyOdrDao;
import irille.sellerAction.SellerAction;
import irille.sellerAction.easy.inf.IEasyOdrAction;
import irille.shop.easy.EasyOdr;
import irille.view.Easy.EasyodrView;
import irille.view.Page;
import irille.view.odr.OrderSearchView;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Date;

public class EasyOdrAction extends SellerAction<EasyOdr> implements IEasyOdrAction {


    @Getter
    @Setter
    private OrderSearchView search;
    @Inject
    private EasyOdrDao easyOdrDao;

    @Getter
    @Setter
    private Integer eastOdrId;
    @Getter
    @Setter
    private String eastOdrIds;
    @Getter
    @Setter
    private String path;

    public void newOrderlist() throws IOException {
        Page<EasyodrView> page = easyOdrDao.sellerlist(getStart(), getLimit(), search, getSupplier().getPkey());
        write(page);
    }


    /**
     * @Description: 导出单个订单表格
     * @date 2018/12/10 16:59
     * @anthor wilson zhang
     */
    public void oneexport() throws Exception {
        sendOutPuiStreanm(easyOdrDao.oneexport(eastOdrId, getSupplier().getPkey()), new Date() + ".xlsx");
    }

    /**
     * @Description: 导出多个订单表格
     * @date 2018/12/10 16:59
     * @anthor wilson zhang
     */
    public void manyexports() {
        try {
            sendOutPuiStreanm(easyOdrDao.manyexport(eastOdrIds, getSupplier().getPkey()), new Date() + ".zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导出当前供应商的全部订单表格
    public void manyexport() {
        String list = null;
        if (search != null) {
            list = easyOdrDao.allOdr(getSupplier().getPkey(), search.getBeginTime(), search.getEndTime());
        } else {
            list = easyOdrDao.allOdr(getSupplier().getPkey(), null, null);
        }
        sendOutPuiStreanm(easyOdrDao.manyexport(list, getSupplier().getPkey()), new Date() + ".zip");
    }
}
