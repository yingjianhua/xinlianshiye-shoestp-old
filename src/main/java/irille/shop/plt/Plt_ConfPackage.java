package irille.shop.plt;

import irille.core.sys.ConfPackage;
import irille.shop.cnt.Cnt;
import irille.shop.easy.Easy;
import irille.shop.lg.Lg;
import irille.shop.odr.Odr;
import irille.shop.pdt.Pdt;
import irille.shop.prm.Prm;
import irille.shop.pub.Pub;
import irille.shop.usr.Usr;

public class Plt_ConfPackage extends ConfPackage {

    public static final Plt_ConfPackage INST = new Plt_ConfPackage();

    @Override
    public void initPacks() {
        _packsFlag = true;
        installSys();
        add(Usr.class, 1000);
        add(Plt.class, 1100);
        add(Pdt.class, 1200);
        add(Odr.class, 1300);
        add(Cnt.class, 1400);
        add(Lg.class, 1700);
        add(Pub.class, 8400);
        add(Prm.class, 8600);
        add(Easy.class, 8700);
    }

}
