package irille.view.usr;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.view.BaseView;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/29
 * Time: 14:23
 */
public class UsrMessageView implements BaseView {
    @SetBean(OriginalField = "pkey")
    private int id;
    private String title;
    private String content;
    private String reply;
    private byte status;
    private String sendTime;
    private String readTime;
    private String replyTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setStatus(Integer status) {
        this.status = (byte) Byte.valueOf(String.valueOf(status));
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
