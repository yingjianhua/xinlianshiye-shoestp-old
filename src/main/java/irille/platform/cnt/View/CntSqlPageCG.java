package irille.platform.cnt.View;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

//  平台单页分类  下拉列表
@Data
public class CntSqlPageCG implements BaseView {
    private  Integer id;
    @JsonSerialize(using = I18NFieldSerializer.class)
    private  String name;
}
