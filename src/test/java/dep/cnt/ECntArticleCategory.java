package dep.cnt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.view.VFlds;
import irille.shop.cnt.CntArticleCategory;

public class ECntArticleCategory extends CntArticleCategory {

    public static void main(String[] args) {
        new ECntArticleCategory().crtExt().crtFiles();

    }

    public EMCrt crtExt() {
        CntArticleCategory.TB.getCode();
        VFlds[] vflds = new VFlds[]{new VFlds(TB)};
        VFlds[] mflds = new VFlds[]{new VFlds(T.NAME, T.ENABLED)};
        VFlds[] searchVflds = new VFlds[]{new VFlds(T.NAME, T.ENABLED)};
        VFlds flds = new VFlds(T.ROOTID);

        VFlds[] vFlds = new VFlds[]{flds};
//        flds.del();
//        flds.del(CntPageTypeLine.T.CREATE_TIME);
        EMCrt ext = new EMCrtComp(TB, vflds, mflds, searchVflds,
                vFlds
        );
        ext.getVfldsForm().del(T.CREATE_BY);
        ext.getVfldsForm().del(T.CREATE_TIME);
        ext.newExts().init();
        return ext;
    }

}
