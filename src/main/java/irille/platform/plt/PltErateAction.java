package irille.platform.plt;

import irille.action.ActionBase;
import irille.action.MgtAction;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PltErateAction  extends MgtAction<PltErate> {
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
    //修改货币
    public void Upd() throws IOException {
        PltErateDAO.Upd bf=new PltErateDAO.Upd();
        bf.setB(getBean());
        bf.commit();
        write();
    }
    //插入货币
    public void  inspltErate(){
        PltErateDAO.InsInit ins = new PltErateDAO.InsInit();
        List<PltErate> list = new ArrayList<>();
        PltErate pe= getBean();
        list.add(PltErateDAO.InsInit.build(pe.getLogo(), pe.getCurName(), pe.getSymbol(), pe.getEnabled()==1?true:false,
                pe.getSiteCur()==1?true:false,pe.getRate(), pe.getSupCur()==1?true:false,pe.getNowrate()));
        ins.setList(list);
        ins.commit();
    }
}
