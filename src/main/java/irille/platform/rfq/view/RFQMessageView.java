package irille.platform.rfq.view;

import irille.sellerAction.rfq.view.RFQConsultMessageView;
import irille.view.BaseView;
import lombok.Data;

import java.util.List;

@Data
public class RFQMessageView implements BaseView {
    private Integer pkey;//RFQConsultRelation的PKEY
    private String supplierName;//供应商名称
    private String user;//采购商名称
    private List<RFQConsultMessageView> messages;//聊天记录详细信息
}
