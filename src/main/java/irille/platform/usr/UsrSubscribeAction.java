package irille.platform.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrSubscribe;
import irille.shop.usr.UsrSubscribeDAO;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class UsrSubscribeAction extends MgtAction<UsrSubscribe> {
    @Override
    public Class beanClazz() {
        return UsrSubscribe.class;
    }

    public UsrSubscribe getBean() {
        return _bean;
    }

    public void setBean(UsrSubscribe bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String ids;
    /**
     * @Description: 获取订阅列表
     * *@date 2019/1/21 14:05
     * *@anthor zjl
     */
    public void getSubscribeList() throws IOException {
        write(UsrSubscribeDAO.getSubscribeList(getStart(),getLimit(),email));
    }
    /**
     * @Description: 删除多个订阅以及单个
     * *@date 2019/1/21 14:14
     * *@anthor zjl
     */
    public void deletes() throws IOException {
        UsrSubscribeDAO.deletes dl = new UsrSubscribeDAO.deletes();
        if(id!=null){
            dl.setBKey(id);
            dl.commit();
        }else{
            String [] list = ids.split(",");
            for (String s : list) {
                dl.setBKey(Integer.valueOf(s));
                dl.commit();
            }
        }
        write();
    }
    /**
     * @Description: 新增订阅
     * *@date 2019/1/21 14:21
     * *@anthor zjl
     */
    public void ins() throws IOException {
        UsrSubscribeDAO.ins(email);
        write();
    }
    /**
     * @Description: 修改订阅
     * *@date 2019/1/21 14:22
     * *@anthor zjl
     */
    public void upd() throws IOException {
        UsrSubscribeDAO.upd upd = new UsrSubscribeDAO.upd();
        upd.setB(getBean());
        upd.commit();
        write();
    }
}
