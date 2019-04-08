package irille.view.usr;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsultMessageView {
  private String content;

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
  private Date sendTime;

  private boolean P2S;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public boolean isP2S() {
    return P2S;
  }

  public void setP2S(boolean p2s) {
    P2S = p2s;
  }
}
