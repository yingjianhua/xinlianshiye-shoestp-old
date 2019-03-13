package irille.shop.cnt;

import irille.core.sys.ConfPackage;

public class Cnt_ConfPackage extends ConfPackage {
  public static final Cnt_ConfPackage INST = new Cnt_ConfPackage();

  @Override
  public void initPacks() {
    _packsFlag = true;
    installSys();
    add(Cnt.class, 9500);
  }
}
