package irille.platform.plt;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.action.ActionBase;
import irille.action.MgtAction;
import irille.platform.plt.View.CountryView;
import irille.pub.util.upload.ImageUpload;
import irille.shop.plt.*;
import irille.view.plt.ImageView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class PltCountryAction extends MgtAction<PltCountry> {
    @Override
    public Class beanClazz() {
        return PltCountry.class;
    }

    public PltCountry getBean() {
        return _bean;
    }

    public void setBean(PltCountry bean) {
        this._bean = bean;
    }


    //删除国家
    public void del() throws Exception {
        PltCountryDAO.Del del = new PltCountryDAO.Del();
        del.setB(getBean());
        del.commit();
        writeSuccess(getBean());
    }

    //修改国家是否启用
    public void countryenable() throws Exception {
        PltCountryDAO.enable ph = new PltCountryDAO.enable();
        ph.setB(getBean());
        ph.commit();
        write();
    }

    //修改热门国家是否启用
    public void countryhot() throws Exception {
        PltCountryDAO.Hot ph = new PltCountryDAO.Hot();
        ph.setB(getBean());
        ph.commit();
        write();
    }

    @Getter
    @Setter
    private String countryName;
    @Getter
    @Setter
    private String shortName;
    @Getter
    @Setter
    private String zone;
    @Getter
    @Setter
    private CountryView countryView;

    //获取国家列表  参数 国家名称
    public void List() throws Exception {
        write(PltCountryDAO.list(countryName,shortName,zone,getStart(), getLimit()));
    }

    /**
     * 修改一个国家的信息
     *
     * @Date 219/1/14 10:19
     * @author zjl
     */
    public void updCountry() throws IOException {
        PltCountryDAO.updCountry(countryView);
        write();
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
