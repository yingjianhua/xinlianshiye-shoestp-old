package irille.view.pub;

import java.util.List;

import irille.view.Page;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/2/1 Time: 11:45 */
@Data
public class PageSearch extends Page {
  private List breadcrumbnav;

  public PageSearch(
      int totalCount, int totalPage, int currentPage, int start, int limit, List items) {
    super(totalCount, totalPage, currentPage, start, limit, items);
  }

  public PageSearch(List items, int start, int limit, int totalCount) {
    super(items, start, limit, totalCount);
  }

  public PageSearch(Page searchPdtByQuery) {
    super(
        searchPdtByQuery.getTotalCount(),
        searchPdtByQuery.getTotalPage(),
        searchPdtByQuery.getCurrentPage(),
        searchPdtByQuery.getStart(),
        searchPdtByQuery.getLimit(),
        searchPdtByQuery.getItems());
  }
}
