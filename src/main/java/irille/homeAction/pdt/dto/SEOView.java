package irille.homeAction.pdt.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class SEOView implements BaseView {
  @JsonSerialize(using = I18NFieldSerializer.class)
  private String title;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String keyWord;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String description;
}
