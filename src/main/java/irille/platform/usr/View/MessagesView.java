package irille.platform.usr.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class MessagesView implements BaseView {
    private Integer id;//站内信id
    private String sender;//发件人
    private String recipient;//收件人
    private String title;//标题
    private String content;//内容
    private String reply;//回复
    private Integer status;//阅读状态
    private Integer type;//消息类别
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date sendTime;//发送时间
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date readTime;//阅读时间
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date replyTime;//回复时间
}
