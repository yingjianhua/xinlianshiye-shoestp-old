package irille.platform.cnt;

import irille.action.ActionBase;
import irille.shop.cnt.CntMagazine;
import irille.shop.cnt.CntMagazineDAO;
import lombok.Data;

import java.io.IOException;

@Data
public class CntMagazineAction extends ActionBase<CntMagazine> {
    @Override
    public Class beanClazz() {
        return CntMagazine.class;
    }
    public CntMagazine getBean() {
        return _bean;
    }

    public void setBean(CntMagazine bean) {
        this._bean = bean;
    }
    private String name;//查询杂志名称
    public void list() throws IOException {
        write( CntMagazineDAO.listCM(name,getStart(),getLimit()));
    }
    private  String pkeys;
    //删除多个杂志
    public void deletes(){
        String[] list= pkeys.split(",");
        for(String  st :list){
            CntMagazineDAO.del dl=new CntMagazineDAO.del();
            dl.setBKey(Integer.valueOf(st));
            dl.commit();
        }
    }
    public void  inscm(){
        CntMagazineDAO.Ins dl=new CntMagazineDAO.Ins();
        dl.setB(getBean());
        dl.commit();
    }
}
