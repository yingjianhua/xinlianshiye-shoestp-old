package irille.platform.plt;

import irille.Dao.PltConfigDao;
import irille.action.MgtAction;
import irille.action.usr.UsrAccessAction;
import irille.platform.plt.View.LanguageView;
import irille.pub.bean.Bean;
import irille.pub.tb.FldLanguage;
import irille.shop.plt.PltConfig;
import irille.shop.usr.UsrSupplierRoleDAO;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PltConfigAction extends MgtAction<PltConfig> {
    @Override
    public Class beanClazz() {
        return null;
    }

    @Inject
    private PltConfigDao pltConfigDao;
    @Getter
    @Setter
    private Map<Integer, Map<String, String>> map;
    public String _lines;
    public String _type;
    private String _jyType;

    public String getJyType() {
        return _jyType;
    }

    public void setJyType(String _jyType) {
        this._jyType = _jyType;
    }

    public String getLines() {
        return _lines;
    }

    public void setLines(String _lines) {
        this._lines = _lines;
    }

    public String getType() {
        return _type;
    }

    public void setType(String _type) {
        this._type = _type;
    }

    /**
     * 获取所有语言
     * Date 2019/01/08 10:03
     * zjl
     */
    public void getLanguages() throws IOException {
        write(pltConfigDao.getLanguages());
    }

    /**
     * 获取默认语言
     * Date 2019/01/08 11:43
     * zjl
     */
    public void getCurrentLanguage() throws IOException {
        write(pltConfigDao.getCurrentLanguage());
    }

    /**
     * (平台)获取基础设置-语言设置
     * Date 2019/1/3 14:21
     *
     * @return zjl
     */
    public Map<Integer, Map<String, String>> getLanguageList() {
        return pltConfigDao.getLanguageList();
    }

    /**
     * (平台)基础设置-语言设置(修改语言)
     * Date 2019/1/3 14:21
     *
     * @return zjl
     */
    public void updLanguage() {
        for (Integer integer : map.keySet()) {
            PltConfig pltConfig = Bean.load(PltConfig.class, PltConfig.T.PKEY + "=" + integer);
            for (String s : map.get(integer).keySet()) {
                pltConfig.setValue(map.get(integer).get(s));
            }
            pltConfig.upd();
        }
    }

    /**
     * (平台)获取权限列表
     * Date 2019/1/3 14:21
     *
     * @return zjl
     * @throws IOException
     * @throws JSONException
     */
    public void getPermissionList() throws IOException, JSONException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().print(UsrSupplierRoleDAO.ProvCtrl.crtRes((Integer) getPkey(), UsrAccessAction.MODULES.get(getJyType())).toString());
    }

    /**
     * 修改权限
     * Date 2019/1/3 14:21
     *
     * @return zjl
     */
    public void updPermission() throws IOException, JSONException {
        UsrSupplierRoleDAO.UpdCtrl act = new UsrSupplierRoleDAO.UpdCtrl();
        act.setBKey(getPkey());
        act.setLines(getLines() == null ? "" : getLines());
        act.setType(UsrAccessAction.MODULES.get(getType()));
        act.commit();
        writeSuccess();
    }
}
