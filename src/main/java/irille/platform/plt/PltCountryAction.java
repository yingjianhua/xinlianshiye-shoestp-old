package irille.platform.plt;

import irille.action.ActionBase;
import irille.action.MgtAction;
import irille.shop.plt.*;
import lombok.Getter;
import lombok.Setter;

public class PltCountryAction  extends MgtAction<PltCountry> {
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
    public  void  countryenable() throws Exception{
        PltCountryDAO.enable ph=new PltCountryDAO.enable();
        ph.getB();
        ph.commit();
        write();
    }
    //修改国家是否启用
    public  void  countryhot() throws Exception{
        PltCountryDAO.Hot ph=new PltCountryDAO.Hot();
        ph.getB();
        ph.commit();
        write();
    }
    @Getter
    @Setter
    private  String Countryname;
    //获取国家列表  参数 国家名称
    public void List() throws  Exception{
        write(PltCountryDAO.list(Countryname,getStart(),getLimit()));
    }
}
