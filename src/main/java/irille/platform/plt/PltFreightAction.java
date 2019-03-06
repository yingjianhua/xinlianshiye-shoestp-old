package irille.platform.plt;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Dao.PltFreightDao;
import irille.action.MgtAction;
import irille.platform.plt.View.DeliveryAreaView;
import irille.platform.plt.View.FreightManagementView;
import irille.pub.svr.LoginUserMsg;
import irille.pub.util.upload.ImageUpload;
import irille.shop.cnt.CntMagazine;
import irille.shop.plt.Plt;
import irille.shop.plt.PltFreight;
import irille.shop.plt.PltFreightLine;
import irille.shop.plt.PltFreightLineDAO;
import irille.view.Page;
import irille.view.plt.ImageView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class PltFreightAction extends MgtAction<PltFreight> {
    @Override
    public Class beanClazz() {
        return PltFreight.class;
    }


    public PltFreight getBean() {
        return _bean;
    }

    public void setBean(PltFreight bean) {
        this._bean = bean;
    }
    @Inject
    private PltFreightDao pltFreightDAO;
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private FreightManagementView freightManagementView;
    @Getter
    @Setter
    private DeliveryAreaView deliveryAreaView;
    @Getter
    @Setter
    private PltFreightLine beanline;
    /**
     * @Description: (平台)获取运费管理列表
     * *@date 2019/1/3 16:33
     * *@anthor zjl
     */
    @Setter
    @Getter
    private String selectcompany;
    public void getShippingList()  throws IOException {
        write(pltFreightDAO.getShippingList(selectcompany,getStart(), getLimit()));
    }

    /**
     * @Description: (平台)获取配送区域列表
     * *@date 2019/1/3 16:49
     * *@anthor zjl
     */
    public void getDeliveryAreaList() throws IOException {
        write(pltFreightDAO.getDeliveryAreaList(id));
    }

    /**
     * @Description: (平台)新增运费管理
     * *@date 2019/1/4 9:56
     * *@anthor zjl
     */
    public void insShipping() throws  IOException{
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        freightManagementView.setCreatedby(lu.get_user().getPkey());
        pltFreightDAO.insShipping(freightManagementView);
        write();
    }

    /**
     * @Description: (平台)新增配送区域
     * *@date 2019/1/4 10:16
     * *@anthor zjl
     */
    public void insDeliveryArea() throws  IOException{
        pltFreightDAO.insDeliveryArea(beanline);
        write();
    }

    /**
     * @Description: (平台)获取单个运费管理
     * *@date 2019/1/4 11:03
     * *@anthor zjl
     */
    public void getShipping() throws  IOException {
        write( pltFreightDAO.getShipping(id));
    }

    /**
     * @Description: (平台)修改单个运费管理
     * *@date 2019/1/4 11:26
     * *@anthor zjl
     */
    public void updShipping() throws  IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        freightManagementView.setCreatedby(lu.get_user().getPkey());
        pltFreightDAO.updShipping(freightManagementView);
        write();
    }

    /**
     * @Description: (平台)获取单个配送区域
     * *@date 2019/1/4 11:52
     * *@anthor zjl
     */
    public void getDeliveryArea() throws  IOException {
        write(pltFreightDAO.getDeliveryArea(id));
    }

    /**
     * @Description: (平台)更新单个配送区域
     * *@date 2019/1/4 13:54
     * *@anthor zjl
     */
    public void updDeliveryArea() throws  IOException {
        pltFreightDAO.updDeliveryArea(beanline);
        write();
    }
    /**
    *@Description:  删除平台快递公司
    *@date 2019/1/16 16:52
    *@anthor wilson zhang
    */
    public void delcompany() throws Exception{
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        pltFreightDAO.delFreight(id,lu.get_user().getPkey());
        write();
    }
    /**
     *@Description:  删除平台快递公司区间
     *@date 2019/1/16 16:52
     *@anthor wilson zhang
     */
    public void delsection() throws Exception{
        PltFreightLineDAO.delsection(id);
        write();
    }

    // 平台运费管理 计算方式
    public void loadstate() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        for (Plt.WeightType o : Plt.WeightType.values()) {
            JSONObject lineJsona = new JSONObject();
            lineJsona.put("name", o.getLine().getName());
            lineJsona.put("pkey", o.getLine().getKey());
            ja.put(lineJsona);
        }
        json.put("STORE_ROOT", ja);
        writerOrExport(json);
    }

    @Getter
    @Setter
    private String fileFileName = "";
    @Getter
    @Setter
    private File file;
    /**
     * 上传图片
     *
     * @Date 219/1/14 15:13
     * @author zjl
     */
    public void upload() throws Exception {
        if (getLoginSys() == null) {
            JSONObject json = new JSONObject();
            json.put("success", false);
            json.put("msg", "登录超时,请重新登录");
            writerOrExport(json);
        } else {
            ImageView view = ImageUpload.upload(beanClazz(), fileFileName, file);
            JSONObject json = new JSONObject();
            json.put("ret", 1);
            json.put("result", new JSONObject(new ObjectMapper().writeValueAsString(view)));
            json.put("success", true);
            writerOrExport(json);
        }
    }
}
