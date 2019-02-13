package irille.platform.plt;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.action.ActionBase;
import irille.action.MgtAction;
import irille.core.sys.Sys;
import irille.pub.bean.BeanBase;
import irille.pub.svr.LoginUserMsg;
import irille.pub.util.upload.ImageUpload;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.view.plt.ImageView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PltErateAction  extends MgtAction<PltErate> {
    @Inject
    private PltErateDAO pltErateDAO;
    @Override
    public Class beanClazz() {
        return PltErate.class;
    }
    public PltErate getBean() {
        return _bean;
    }

    public void setBean(PltErate bean) {
        this._bean = bean;
    }
    //    //汇率 列表
    public void  list() throws  IOException{
        write(PltErateDAO.list());
    }
    //设置网站默认货币
    public void Defcur() throws IOException {
        PltErateDAO.Defcur bf=new PltErateDAO.Defcur();
        bf.setB(getBean());
        bf.commit();
        write();
    }
    //设置商家默认货币
    public void Bcdefcur() throws IOException {
        PltErateDAO.Bcdefcur bf=new PltErateDAO.Bcdefcur();
        bf.setB(getBean());
        bf.commit();
        write();
    }
    //设置货币是否启用
    public void enabled() throws IOException {
        PltErateDAO.enabled bf=new PltErateDAO.enabled();
        bf.setB(getBean());
        bf.commit();
        write();
    }
    //修改货币对象
    public void updprate() throws IOException {
        PltErateDAO.Upderate bf=new PltErateDAO.Upderate();
        bf.setB(getBean());
        bf.commit();
        write();
    }


    //插入货币
    public void  inspltErate() throws  IOException{
        PltErateDAO.InsInit ins = new PltErateDAO.InsInit();
        List<PltErate> list = new ArrayList<>();
        PltErate pe= getBean();
        pe.setSiteCur(Sys.OYn.NO.getLine().getKey());
        pe.setSupCur(Sys.OYn.NO.getLine().getKey());
        pe.setNowrate(pe.getRate().divide(PltErateDAO.getrate(), 4, BigDecimal.ROUND_UP));
        LoginUserMsg lu=(LoginUserMsg)this.session.get(LOGIN);
        list.add(PltErateDAO.InsInit.build(pe.getLogo(), pe.getCurName(), pe.getSymbol(), pe.getEnabled()==1?true:false,
                pe.getSiteCur()==1?true:false,pe.getRate(), pe.getSupCur()==1?true:false,pe.getNowrate(),lu.get_user().getPkey()));
        ins.setList(list);
        ins.commit();
        write();
    }

    @Getter
    @Setter
    private String fileFileName = "";
    @Getter
    @Setter
    private File file;
    public void upload() throws Exception {
        if(getLoginSys() == null) {
            JSONObject json = new JSONObject();
            json.put("success", false);
            json.put("msg", "登录超时,请重新登录");
            writerOrExport(json);
        } else {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(file.getAbsolutePath()));
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if(width != 42 && height != 30)
                throw LOG.err("wrongSize","仅限上传42*30PX的图片!");
            ImageView view = ImageUpload.upload(beanClazz(), fileFileName, file);
            JSONObject json = new JSONObject();
            json.put("ret", 1);
            json.put("result", new JSONObject(new ObjectMapper().writeValueAsString(view)));
            json.put("success", true);
            writerOrExport(json);
        }
    }
    /**
     * 获取已开启的所有货币
     * @Date 2019/01/11 16:21
     * @author zjl
     */
    public void getCurrencyList() throws IOException {
        write(pltErateDAO.getCurrencyList());
    }
}
