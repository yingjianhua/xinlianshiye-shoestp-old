package irille.platform.usr;

import irille.action.ActionBase;
import irille.shop.usr.UsrConsult;
import irille.shop.usr.UsrConsultDAO;
import lombok.Data;

import java.io.IOException;


/**
 * @author lingjian
 * @date 2019/1/23 13:44
 * @Version 1.0
 */
@Data
public class UsrConsultAction extends ActionBase<UsrConsult> {

    @Override
    public Class beanClazz() {
        return UsrConsult.class;
    }
    public UsrConsult getBean() { return _bean; }
    public void setBean(UsrConsult bean) {
        this._bean = bean;
    }

    private String name;
    private String title;
    private String content;

    /**
     * 查询询盘列表
     * @author lingjian
     * @date 2019/1/22 13:36
     * @throws Exception
     */
    public void list() throws Exception {
        write(UsrConsultDAO.listview(name,title,content,getStart(), getLimit()));
    }

    /**
     * 删除单条询盘记录
     * @author lingjian
     * @date 2019/1/22 13:38
     * @throws IOException
     */
    public void delete() throws IOException {
        UsrConsultDAO.remove remove = new UsrConsultDAO.remove();
        remove.setBKey(getBean().getPkey());
        remove.commit();
        write();
    }
}
