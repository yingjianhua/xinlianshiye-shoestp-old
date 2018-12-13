package irille.Dao.EO;


import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.easy.EasyOdrline;
import irille.view.Easy.EolineView;

import java.util.List;
import java.util.stream.Collectors;

public class EasyOdrLineDao {
    /**
     * @Description: 生成订单规格
     * @date 2018/12/5 13:44
     * @anthor wilson zhang
     */
    public static class Ins extends IduIns<Ins, EasyOdrline> {
        @Override
        public void before() {
            super.before();
        }
    }

    public List<EolineView> getListOdrLine(Integer eastOdrId, FldLanguage.Language language) {
        SQL sql = new SQL() {{
            SELECT(EasyOdrline.class);
            FROM(EasyOdrline.class)
                    .WHERE(EasyOdrline.T.ORDER_ID, "=?", eastOdrId);
        }};
        List<EolineView> list = Query.sql(sql).queryMaps().stream().map(bean -> new EolineView() {{
            setImage((String) bean.get(EasyOdrline.T.IAMGE.getFld().getCodeSqlField()));
            setProductname(translateUtil.getLanguage(bean.get(EasyOdrline.T.PRODUCTNAME.getFld().getCodeSqlField()), language));
            setColor(translateUtil.getLanguage(bean.get(EasyOdrline.T.COLOR.getFld().getCodeSqlField()), language));
            setSize(translateUtil.getLanguage(bean.get(EasyOdrline.T.SIZE.getFld().getCodeSqlField()), language));
            setNum(String.valueOf(bean.get(EasyOdrline.T.NUM.getFld().getCodeSqlField())));
            setRemarks((String) bean.get(EasyOdrline.T.REMARKS.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return list;
    }
}
