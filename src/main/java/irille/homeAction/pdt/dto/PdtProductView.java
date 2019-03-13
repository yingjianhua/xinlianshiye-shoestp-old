package irille.homeAction.pdt.dto;

import irille.pub.idu.IduPage;
import irille.shop.pdt.Pdt;

/** 用于内部方法传值 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/8/7 Time: 9:12 */
public class PdtProductView {
  private IduPage page;
  private String[] flds;
  private boolean order;
  private int category;
  private String spec;
  private long supplierId;
  private String keyword;
  // 根据哪个字段查询,返回只有这个结果集
  private PdtProductView.onlyFld onlyFld;

  private Pdt.OProductType productType;

  public Pdt.OProductType getProductType() {
    return productType;
  }

  public PdtProductView setProductType(Pdt.OProductType productType) {
    this.productType = productType;
    return this;
  }

  public String[] getFlds() {
    return flds;
  }

  public PdtProductView setFlds(String[] flds) {
    this.flds = flds;
    return this;
  }

  public boolean isOrder() {
    return order;
  }

  public PdtProductView setOrder(boolean order) {
    this.order = order;
    return this;
  }

  public int getCategory() {
    if (this.category == 0) return -1;
    return category;
  }

  public PdtProductView setCategory(int category) {
    this.category = category;
    return this;
  }

  public String getSpec() {
    return spec;
  }

  public PdtProductView setSpec(String spec) {
    this.spec = spec;
    return this;
  }

  public long getSupplierId() {
    if (this.supplierId == 0) return -1;
    return supplierId;
  }

  public PdtProductView setSupplierId(long supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public String getKeyword() {
    return keyword;
  }

  public PdtProductView setKeyword(String keyword) {
    this.keyword = keyword;
    return this;
  }

  public PdtProductView.onlyFld getOnlyFld() {
    return onlyFld;
  }

  public PdtProductView setOnlyFld(PdtProductView.onlyFld onlyFld) {
    this.onlyFld = onlyFld;
    return this;
  }

  public IduPage getPage() {
    return page;
  }

  public PdtProductView setPage(IduPage page) {
    this.page = page;
    return this;
  }

  public enum onlyFld {
    Hot,
    New;
  }
}
