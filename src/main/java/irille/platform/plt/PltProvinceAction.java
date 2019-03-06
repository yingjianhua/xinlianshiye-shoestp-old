package irille.platform.plt;

import irille.action.MgtAction;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.plt.PltProvince;
import irille.shop.plt.PltProvinceDAO;
import irille.view.plt.ProvinceView;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;

public class PltProvinceAction extends MgtAction<PltProvince> {
    @Override
    public Class beanClazz() {
        return PltProvince.class;
    }

    public PltProvince getBean() {
        return _bean;
    }

    public void setBean(PltProvince bean) {
        this._bean = bean;
    }

    @Inject
    private PltProvinceDAO pltProvinceDAO;
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private ProvinceView provinceView;

    /**
     * 获取省份列表(对一个国家)
     *
     * @Date 219/1/14 16:45
     * @author zjl
     */
    public void getProvinceList() throws IOException {
        write(pltProvinceDAO.listByCountry(id));
    }

    /**
     * 新增省份
     *
     * @Date 219/1/14 16:45
     * @author zjl
     */
    public void insProvince() throws IOException {
        PltProvince province = new PltProvince();
        province.setMain(provinceView.getId());
        province.setName(provinceView.getName());
        province.setShortName(provinceView.getShortName());
        translateUtil.autoTranslate(province);
        province.ins();
        write();
    }
}
