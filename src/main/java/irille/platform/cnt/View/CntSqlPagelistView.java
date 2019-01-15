package irille.platform.cnt.View;
import irille.view.BaseView;
import lombok.Data;

//  平台单页 列表
@Data
public class CntSqlPagelistView implements BaseView {
    //pkey 标题 分类  简述 详情 启用  排序
    private  Integer id;
    private  String title;
    private  Integer pageType;
    private  String brief;
    private  String descrip;
    private  byte enabled;
    private  Integer sort;

}
