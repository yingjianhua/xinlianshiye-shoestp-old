package irille.action.cnt;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.pub.inf.IExtName;
import irille.shop.cnt.CntSglPageCategory;
import irille.shop.plt.PltConfigDAO;

public class CntSglPageCategoryAction extends MgtAction<CntSglPageCategory> {
    public CntSglPageCategory getBean() {
        return _bean;
    }

    public void setBean(CntSglPageCategory bean) {
        this._bean = bean;
    }

    @Override
    public Class beanClazz() {
        return CntSglPageCategory.class;
    }

	/**
	 * 对应EXT上的外键下拉框组件 在store加载调用
	 */
	public void getComboTrigger() throws Exception {
		String where = crtQueryAll();
		if (tb().chk("enabled")) {
			where += " AND enabled = 1";
		}
		if (!Str.isEmpty(getSarg1()))
			where += " AND (" + getSarg1() + ")";
		where += orderBy();
		IduPage page = newPage();
		page.setStart(getStart());
		page.setLimit(0); // 取所有数据，下拉框不分页
		page.setWhere(where);
		page.commit();
		List<CntSglPageCategory> list = page.getList();
		JSONArray ja = new JSONArray();
		JSONObject lineJson = null;
		boolean isinf = false;
		if (list.size() > 0 && list.get(0) instanceof IExtName)
			isinf = true;
		for (CntSglPageCategory line : list) {
			lineJson = new JSONObject();
			// 注意不论主键是否为STRING,都转成字符串
			// 前EXT组件初始化时,数字也是以字符形式判断其值
			lineJson.put("value", line.getPkey().toString());
			if (isinf)
				lineJson.put("text", line.getPageName(PltConfigDAO.manageLanguage()));
			else
				lineJson.put("text", line.getPkey());
			ja.put(lineJson);
		}
		JSONObject json = new JSONObject();
		json.put(STORE_ROOT, ja);
		ServletActionContext.getResponse().getWriter().print(json.toString());
	}
}
