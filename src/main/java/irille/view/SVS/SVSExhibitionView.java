package irille.view.SVS;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SVSExhibitionView implements BaseView {
  private String name; // 展会名称
  private String exhibitionTime; // 参加展会时间
  private Integer countryId; // 展会举办国家编号
  private String exhibitionDetails; // 展会详情
  private String exhibitionPicture; // 展会图片
}
