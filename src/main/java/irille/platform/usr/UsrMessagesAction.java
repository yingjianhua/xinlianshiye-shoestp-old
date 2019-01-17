package irille.platform.usr;

import irille.action.MgtAction;
import irille.pub.svr.LoginUserMsg;
import irille.shop.usr.UsrMessages;
import irille.shop.usr.UsrMessagesDAO;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class UsrMessagesAction extends MgtAction<UsrMessages> {
    @Override
    public Class beanClazz() {
        return UsrMessages.class;
    }

    public UsrMessages getBean() {
        return _bean;
    }

    public void setBean(UsrMessages bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private Integer sender;//查询发件人
    @Getter
    @Setter
    private Integer recipient;//查询收件人
    @Getter
    @Setter
    private String stationLetter;//站内信
    @Getter
    @Setter
    private String title;//标题
    @Getter
    @Setter
    private String content;//内容
    @Getter
    @Setter
    private Integer id;//站内信id


    /**
     * @Description: 查询站内信列表
     * *@date 2019/1/16 11:50
     * *@anthor zjl
     */
    public void getMessagesList() throws IOException {
        write(UsrMessagesDAO.getMessagesList(sender, recipient, getStart(), getLimit()));
    }

    /**
     * @Description: 获取发件人列表和收件人列表
     * *@date 2019/1/16 15:50
     * *@anthor zjl
     */
    public void getSenderAndRecipients() throws IOException {
        write(UsrMessagesDAO.getSenderAndRecipients());
    }

    /**
     * @Description: 删除多个站内信
     * *@date 2019/1/17 09:19
     * @anthor zjl
     */
    public void deletes() throws IOException {
        if (id != null) {
            UsrMessagesDAO.deletes dl = new UsrMessagesDAO.deletes();
            dl.setBKey(id);
            dl.commit();
        } else {
            String[] list = stationLetter.split(",");
            for (String s : list) {
                UsrMessagesDAO.deletes dl = new UsrMessagesDAO.deletes();
                dl.setBKey(Integer.valueOf(s));
                dl.commit();
            }
        }
        write();
    }

    /**
     * @Description: 获取所有采购商列表
     * *@date 2019/1/17 11:00
     * @anthor zjl
     */
    public void getPurchases() throws IOException {
        write(UsrMessagesDAO.getPurchases(getStart(), getLimit()));
    }

    /**
     * @Description: 新增站内信(发布系统消息)
     * *@date 2019/1/17 11:39
     * @anthor zjl
     */
    public void ins() throws IOException {
        UsrMessagesDAO.ins(recipient, title, content);
        write();
    }
    /**
     * @Description: 站内信回复
     * *@date 2019/1/17 14:51
     * @anthor zjl
     */
    public void reply() throws IOException {
        UsrMessagesDAO.reply reply = new UsrMessagesDAO.reply();
        reply.setB(getBean());
        reply.commit();
        write();
    }
}
