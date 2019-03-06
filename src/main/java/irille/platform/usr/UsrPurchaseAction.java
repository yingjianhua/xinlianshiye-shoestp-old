package irille.platform.usr;

import irille.action.MgtAction;
import irille.core.sys.Sys;
import irille.pub.bean.sql.MconditionsView;
import irille.shop.plt.Plt;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrPurchaseDAO;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class UsrPurchaseAction  extends MgtAction<UsrPurchase> {
    @Override
    public Class beanClazz() {
        return UsrPurchase.class;
    }
    public UsrPurchase getBean() {
        return _bean;
    }

    public void setBean(UsrPurchase bean) {
        this._bean = bean;
    }
    @Setter
    @Getter
    private List<MconditionsView> mv;
    public  void listMd() throws IOException {
        write(UsrPurchaseDAO.listUsrPurchaseListViews(mv,getStart(),getLimit()));
    }
    // 平台性别列表
    public void loadsex() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        for (Sys.OSex o : Sys.OSex.values()) {
            JSONObject lineJsona = new JSONObject();
            lineJsona.put("name", o.getLine().getName());
            lineJsona.put("pkey", o.getLine().getKey());
            ja.put(lineJsona);
        }
        json.put("STORE_ROOT", ja);
        writerOrExport(json);
    }
    @Setter
    @Getter
    //搜索字段
    private String fldvalue;
    //搜索字段内容
    @Setter
    @Getter
    private String condition;

    /**
     * 查询采购商列表
     * @author lingjian
     * @date 2019/1/24 14:30
     * @throws Exception
     */
    public void list() throws Exception {
        write(UsrPurchaseDAO.listpurselect(fldvalue,condition,getStart(), getLimit()));
    }
}
