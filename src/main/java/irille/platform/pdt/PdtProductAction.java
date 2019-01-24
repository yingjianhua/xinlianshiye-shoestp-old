package irille.platform.pdt;

import com.fasterxml.jackson.core.JsonProcessingException;
import irille.action.MgtAction;
import irille.action.pdt.inf.IPdtProductAction;
import irille.pub.Str;
import irille.pub.bean.Query;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.AppConfig;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class PdtProductAction extends MgtAction<PdtProduct> implements IPdtProductAction {

	public PdtProduct getBean() {
		return _bean;
	}

	public void setBean(PdtProduct bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PdtProduct.class;
	}

	private Boolean verify;

	@Override
	public void verify() throws Exception {
		PdtProduct bean = PdtProductDAO.verify(verify, (Integer)getPkey());
		bean.setPicture(AppConfig.image_base_url+bean.getPicture());
		writeSuccess(bean);
	}

    /**
    *@Description:  产品管理页面 审核 为启用停用   停用会级联销售状态一起下架
    *@date 2019/1/24 13:47
    *@anthor wilson zhang
    */
    public void isverify() throws Exception {
        PdtProduct bean = PdtProductDAO.verify(verify, (Integer)getPkey());
        write();
    }
	@Override
	public void list() throws Exception {
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		// 目前过滤器的搜索，是肯定会带初始条件的
		String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
		IduPage page = newPage();
		page.setStart(getStart());
		page.setLimit(getLimit());
		page.setWhere(where);
		page.commit();
		List<PdtProduct> list = page.getList();
		JSONObject lineJson = null;
		for (PdtProduct line : list) {
			if(line.getNormAttr() != null && !line.getNormAttr().trim().equals("")){
				List<String> s = Query.
						SELECT(Language.zh_CN, PdtAttrLine.T.NAME)
						.FROM(PdtAttrLine.class)
						.WHERE(PdtAttrLine.T.PKEY, "in ("+line.getNormAttr()+")")
						.queryList(String.class);
				String normalAttr = s.stream()
						.collect(Collectors.joining(","));
				line.setNormAttr(normalAttr);
			}
			lineJson = crtJsonByBean(line);
			ja.put(lineJson);
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
	}

	@Override
	public void onSale(boolean onSale, int pkey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void publish() {
		// TODO Auto-generated method stub

	}

	public Boolean getVerify() {
		return verify;
	}
	public void setVerify(Boolean verify) {
		this.verify = verify;
	}
    //搜索字段
    private String fldvalue;
    //搜索字段内容
    private String condition;

    /**
     * 查询采购商列表
     * @author lingjian
     * @date 2019/1/24 14:30
     * @throws Exception
     */
    public void list() throws Exception {
        write(PdtProductDAO.listproduct(fldvalue,condition,getStart(), getLimit()));
    }
}