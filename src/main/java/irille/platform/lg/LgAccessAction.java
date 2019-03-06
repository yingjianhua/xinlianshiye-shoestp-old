package irille.platform.lg;

import irille.action.ActionBase;
import irille.shop.lg.LgAccess;
import irille.shop.lg.LgAccessDAO;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class LgAccessAction extends ActionBase<LgAccess> {
    @Override
    public Class beanClazz() {
        return LgAccess.class;
    }

    public LgAccess getBean() {
        return _bean;
    }

    public void setBean(LgAccess bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String user;//用户
    @Getter
    @Setter
    private String loginName;//登录名
    @Getter
    @Setter
    private String requestUrl;//请求地址
    /**
     * @Description: 获取访问日志
     * *@date 2019/1/21 10:39
     * *@anthor zjl
     */
    public void getAccessList() throws IOException {
       write(LgAccessDAO.getAccessList(getStart(),getLimit(),user,loginName,requestUrl));
    }

    /**
     * @Description: 统计访问日志
     * *@date 2019/1/21 11:55
     * *@anthor zjl
     */
    public void getStatisticsList() throws IOException {
       write(LgAccessDAO.getStatisticsList(id));
    }
}
