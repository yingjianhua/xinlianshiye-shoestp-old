package dep.cnt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.cnt.CntSglPageCategory;

public class ECntSglPageCategory extends CntSglPageCategory {

    public static void main(String[] args) {
        new ECntSglPageCategory().crtExt().crtFiles();

    }


    public EMCrt crtExt() {
        VFlds vflds = new VFlds(TB);
        VFlds searchVflds = new VFlds(T.PAGE_NAME);
        EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
        ext.getVfldsForm().del(T.PAGE_TYPE_TEXT);
        ext.getVfldsForm().del(T.TAG);
        ext.getVfldsForm().del(T.CREATE_BY);
        ext.getVfldsForm().del(T.CREATE_TIME);
        ext.newExts().init();
        return ext;
    }


}
