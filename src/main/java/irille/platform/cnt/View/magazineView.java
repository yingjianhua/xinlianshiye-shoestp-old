package irille.platform.cnt.View;

import irille.view.BaseView;
import lombok.Data;
import sun.dc.pr.PRError;

import java.util.Date;
//  平台杂志对象
@Data
public class magazineView implements BaseView {
    private Integer id;
    private  String name; //名称
    private  String specialPictures; //图片
    private Date time;//发布时间
    private Integer cycle;//周期
    private String  content;//内容
    private String url;//路径
    private String createdby;//建档员
    private Date createdtime;//建档时间
}
