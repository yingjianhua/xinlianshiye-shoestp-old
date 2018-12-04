package irille.view.Manage.OdrMeeting.Sale;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductView {
    private String name;
    private String image;
    private Integer id;
    private Integer count;
    private Integer personCount;
    private BigDecimal price;
    private BigDecimal totalAmt;
    private Map<String,List<SpecView>> views;
}
