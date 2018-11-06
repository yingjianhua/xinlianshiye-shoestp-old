package irille.dep.cnt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.cnt.CntArticle;

public class ECntArticle extends CntArticle {

    public static void main(String[] args) {
        new ECntArticle().crtExt().crtFiles();

    }


    public EMCrt crtExt() {
        VFlds vflds = new VFlds(TB);
        VFlds searchVflds = new VFlds(T.TITLE,T.KEYWORD);
        EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
        ext.getVfldsForm().del(T.CREATE_BY);
        ext.getVfldsForm().del(T.CREATE_TIME);
        ext.getVfldsList().del(T.IMAGES);
        ext.newExts().init();
        return ext;
    }
}
