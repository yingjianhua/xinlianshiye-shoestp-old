package irille.platform.cnt;

import irille.action.ActionBase;
import irille.platform.cnt.View.CntSqlPagelistView;
import irille.pub.bean.BeanBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.cnt.CntSglPage;
import irille.shop.cnt.CntSglPageDAO;

import java.io.IOException;

public class CntSglPageAction extends ActionBase<CntSglPage> {
    @Override
    public Class beanClazz() {
        return CntSglPage.class;
    }
    public CntSglPage getBean() {
        return _bean;
    }

    public void setBean(CntSglPage bean) {
        this._bean = bean;
    }
    /**
    *@Description:  平台单页列表显示
    *@date 2019/1/14 16:17
    *@anthor wilson zhang
    */
    public void listview() throws IOException {
        write(CntSglPageDAO.listview());
    }
    /**
     *@Description:  平台单页列表插入
     *@date 2019/1/14 16:17
     *@anthor wilson zhang
     */
    public void inssgl() throws  IOException{
        CntSglPageDAO.Inssgl cp=new CntSglPageDAO.Inssgl();
        LoginUserMsg loginUserMsg = (LoginUserMsg) this.session.get(LOGIN);
        System.out.println(getBean());
        getBean().setCreateBy(loginUserMsg.get_user().getPkey());
        cp.setB(getBean());
        cp.commit();
        write();
    }

    /**
     *@Description:  平台单页列表修改
     *@date 2019/1/14 16:17
     *@anthor wilson zhang
     */
    public void Updsql() throws  IOException{
        CntSglPageDAO.Updsql cp=new CntSglPageDAO.Updsql();
        LoginUserMsg loginUserMsg = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreateBy(loginUserMsg.get_user().getPkey());
        cp.setB(getBean());
        cp.commit();
        write();
    }
    /**
     *@Description:  平台单页列表删除
     *@date 2019/1/14 16:17
     *@anthor wilson zhang
     */
    public void delsql() throws  IOException{
        CntSglPageDAO.Del cp=new CntSglPageDAO.Del();
        cp.setBKey(getBean().getPkey());
        cp.commit();
        write();
    }
    public void loadsql() throws  IOException{
        CntSglPage cp=BeanBase.load(CntSglPage.class,getBean().getPkey());
        CntSqlPagelistView cv=new CntSqlPagelistView();
        cv.setId(cp.getPkey());
        cv.setTitle(cp.getTitle());
        cv.setPageType(cp.getPageType());
        cv.setBrief(cp.getBrief());
        cv.setDescrip(cp.getDescrip());
        cv.setEnabled(cp.getEnabled());
        cv.setSort(Integer.valueOf((short) cp.getSort()));
       write(cv);
    }

}
