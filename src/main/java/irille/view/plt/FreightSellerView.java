package irille.view.plt;

import irille.view.BaseView;

public class FreightSellerView implements BaseView {
  private int id;
  private String company;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }
}
