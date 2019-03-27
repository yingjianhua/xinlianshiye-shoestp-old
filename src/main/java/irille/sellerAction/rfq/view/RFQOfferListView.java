package irille.sellerAction.rfq.view;

import java.util.List;
import java.util.Map;

import irille.view.Page;
import irille.view.Manage.RFQ.RFQManageMyQuoteListBody;

public class RFQOfferListView extends Page<RFQManageMyQuoteListBody> {
  private Map<Integer, Object> countries;

  public RFQOfferListView(
      List<RFQManageMyQuoteListBody> items, int start, int limit, int totalCount, Map<Integer, Object> countries) {
    super(items, start, limit, totalCount);
    this.countries = countries;
  }

  public Map<Integer, Object> getCountries() {
    return countries;
  }

  public void setCountries(Map<Integer, Object> countries) {
    this.countries = countries;
  }
}
