package irille.shop.cnt;

import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Cnt_Install extends InstallBase {
  public static final Cnt_Install INST = new Cnt_Install();

  @Override
  public void initMenu(SysMenuDAO m) {
    m.proc(CntAd.TB, 110, null);
    m.proc(CntLink.TB, 120, null);
    SysMenu cntPage = m.procParent("文章管理", "cnt", 130, null);
    m.proc(CntArticle.TB, 140, cntPage);
    m.proc(CntArticleCategory.TB, 150, cntPage);
    SysMenu cntPageType = m.procParent("单页管理", "cnt", 160, null);
    m.proc(CntSglPage.TB, 170, cntPageType);
    m.proc(CntSglPageCategory.TB, 180, cntPageType);
    m.proc(CntMagazine.TB, 240, cntPageType);
  }
}
