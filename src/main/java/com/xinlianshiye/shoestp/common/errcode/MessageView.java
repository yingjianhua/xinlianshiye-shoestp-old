package com.xinlianshiye.shoestp.common.errcode;

import irille.pub.exception.ReturnCode;
import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/3/13 Time: 16:52 */
@Data
public class MessageView implements BaseView {
  private Integer ret;
  private String msg;
  private ReturnCode returnCode;

  public MessageView(Integer ret, String msg) {
    this.ret = ret;
    this.msg = msg;
  }

  public MessageView(Integer ret) {
    this.ret = ret;
  }

  public MessageView(ReturnCode returnCode, String msg) {
    this.msg = msg;
    this.ret = returnCode.getCode();
    this.returnCode = returnCode;
  }
}
