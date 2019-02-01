package irille.platform.o2o;

import java.io.IOException;

import javax.inject.Inject;

import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Service.Manage.O2O.O2OActivityService;
import irille.action.ActionBase;
import irille.view.O2O.O2OActivityView;
import irille.view.O2O.PdtSearchView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;

@Getter
@Setter
public class O2OActivityAction extends ActionBase<O2O_Activity> implements IO2OActivityAction {

    private static final long serialVersionUID = 1L;

    @Inject
    private O2OActivityService o2OActivityService;

    private O2OActivityView activity;

    @Setter
    @Getter
    private PdtSearchView search;
    @Setter
    private String reason;

    @Setter
    private Integer id;
    @Override
    public Class<O2O_Activity> beanClazz() {
        return O2O_Activity.class;
    }

    @Override
    public void list() throws IOException {
        write(o2OActivityService.list(getStart(), getLimit(), activity));
    }

    @Override
    public void cancel() throws IOException, JSONException {
        o2OActivityService.cancel(id);
        writeSuccess();
    }

    /**
     * 发布或编辑活动
     */
    @Override
    public void deploy() throws IOException, JSONException {
        o2OActivityService.deploy(activity);
        writeSuccess();
    }

    @Override
    public void enrollList() throws IOException {
        write(o2OActivityService.enrollList(search,getStart(),getLimit()));
    }

    /**
     * 审核通过
     */
    public void pass() throws IOException, JSONException {
        o2OActivityService.appr(id,null, O2O_ProductStatus.PASS);
        writeSuccess();
    }

    /**
     * 审核拒绝
     */
    public void refuse() throws IOException, JSONException {
        o2OActivityService.appr(id,reason,O2O_ProductStatus.Failed);
        writeSuccess();
    }

    /**
     * 确认下架
     */
    public void lower() throws IOException, JSONException {
        o2OActivityService.lowerAndUpper(id,null,O2O_ProductStatus.OFF);
        writeSuccess();
    }

    /**
     * 拒绝下架
     */
    public void upper() throws IOException, JSONException {
        o2OActivityService.lowerAndUpper(id,reason,O2O_ProductStatus.ON);
        writeSuccess();
    }

    public void load() throws IOException{
        write(o2OActivityService.load(id));
    }


}
