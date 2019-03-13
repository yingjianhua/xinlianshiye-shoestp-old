package irille.view.Manage.OdrMeeting;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

/** 订购会列表（参与） */
@Data
public class OdrMeetingParticipatelistView implements BaseView {
  private Integer id; // pkey
  private String images; // 缩略图

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String name; // 订购会名称

  private String exhibition; // 展厅

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String country; // 国家

  private String suppliername; // 供应商
  private Integer state; // 状态

  @JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
  private Date starttime; // 开始时间

  @JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
  private Date endtime; // 结束时间
}
