package com.xinlianshiye.shoestp.plat.view.pm;

import irille.view.BaseView;
import lombok.Data;

@Data
public class TemplateView implements BaseView {
  private Integer pkey;
  private String type; // 消息类型
  private Integer typeId;
  private Byte mailEnable; // 邮箱启用
  private Byte pmEnable; // 站内信启用
  private String rec; // 接收人类型(字符串)
  private String mailContent; // 邮箱模板内容
  private String pmContent; // 站内信模板内容
  private Integer recType; // 接收人类型
  private String mailTitle; // 邮件标题
}
