package irille.view.plt;

import irille.view.BaseView;

public class ConfigView implements BaseView {

  private String manageLanguage; // 平台端使用的语言

  public String getManageLanguage() {
    return manageLanguage;
  }

  public void setManageLanguage(String manageLanguage) {
    this.manageLanguage = manageLanguage;
  }
}
