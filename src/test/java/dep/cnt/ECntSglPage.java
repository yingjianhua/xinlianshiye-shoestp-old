package dep.cnt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.cnt.CntSglPage;

public class ECntSglPage extends CntSglPage {

    public static void main(String[] args) {
        new ECntSglPage().crtExt().crtFiles();

    }


    public EMCrt crtExt() {
        VFlds vflds = new VFlds(TB);
        VFlds searchVflds = new VFlds(T.TITLE);
        EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
        ext.getVfldsForm().del(T.CREATE_BY);
        ext.getVfldsForm().del(T.CREATE_TIME);
        ext.newExts().init();
        return ext;
    }


}
