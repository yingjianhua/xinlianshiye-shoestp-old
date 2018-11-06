package irille.homeAction.pdt.dto;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.view.BaseView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/10
 * Time: 11:16
 */
public class PdtCommentView implements BaseView {
    @SetBean(OriginalField = "pkey")
    private int id;
    private String name;
    private String usrMemberLevelName;
    private String usrMemberLevelIco;
    private String comment;
    private List<String> images;
    private int product_satisfaction;
    @SetBean(OriginalField = "comment_time")
    private String commenttime;

    private int useful_number;
    private int unuseful_number;
    private String reply;


    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"usrMemberLevelName\":\"" + usrMemberLevelName + '\"' +
                ", \"usrMemberLevelIco\":\"" + usrMemberLevelIco + '\"' +
                ", \"comment\":\"" + comment + '\"' +
                ", \"images\":" + images +
                ", \"product_satisfaction\":" + product_satisfaction +
                ", \"commenttime\":\"" + commenttime + '\"' +
                ", \"useful_number\":" + useful_number +
                ", \"unuseful_number\":" + unuseful_number +
                ", \"reply\":\"" + reply + '\"' +
                "} " + super.toString();
    }

    public int getId() {
        return id;
    }

    public PdtCommentView setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        StringBuffer stringBuffer = new StringBuffer();
        if (name != null)
            for (int i = 0; i < name.length(); i++) {
                if (i < name.length() / 5) {
                    stringBuffer.append(name.charAt(i));
                } else {
                    stringBuffer.append("*");
                }
            }
        return stringBuffer.toString();
    }


    public PdtCommentView setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsrMemberLevelName() {
        return usrMemberLevelName;
    }

    public PdtCommentView setUsrMemberLevelName(String usrMemberLevelName) {
        this.usrMemberLevelName = usrMemberLevelName;
        return this;
    }

    public String getUsrMemberLevelIco() {
        return usrMemberLevelIco;
    }

    public PdtCommentView setUsrMemberLevelIco(String usrMemberLevelIco) {
        this.usrMemberLevelIco = usrMemberLevelIco;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PdtCommentView setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public PdtCommentView setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public int getProduct_satisfaction() {
        return product_satisfaction;
    }

    public PdtCommentView setProduct_satisfaction(int product_satisfaction) {
        this.product_satisfaction = product_satisfaction;
        return this;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public PdtCommentView setCommenttime(String commenttime) {
        this.commenttime = commenttime;
        return this;
    }

    public int getUseful_number() {
        return useful_number;
    }

    public PdtCommentView setUseful_number(int useful_number) {
        this.useful_number = useful_number;
        return this;
    }

    public int getUnuseful_number() {
        return unuseful_number;
    }

    public PdtCommentView setUnuseful_number(int unuseful_number) {
        this.unuseful_number = unuseful_number;
        return this;
    }

    public String getReply() {
        return reply;
    }

    public PdtCommentView setReply(String reply) {
        this.reply = reply;
        return this;
    }

}
