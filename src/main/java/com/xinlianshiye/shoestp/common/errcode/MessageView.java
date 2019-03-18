package com.xinlianshiye.shoestp.common.errcode;

import irille.view.BaseView;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/3/13 Time: 16:52 */
public class MessageView implements BaseView {
  private Integer ret;
  private String msg;

  public MessageView(Integer ret, String msg) {
    this.ret = ret;
    this.msg = msg;
  }

  public MessageView(Integer ret) {
    this.ret = ret;
  }

  public Integer getRet() {
    return ret;
  }

  public void setRet(Integer ret) {
    this.ret = ret;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
