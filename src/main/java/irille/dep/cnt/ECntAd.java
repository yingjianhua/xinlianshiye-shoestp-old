package irille.dep.cnt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.view.VFlds;
import irille.shop.cnt.CntAd;
import irille.shop.cnt.CntAdLine;

public class ECntAd extends CntAd {

    public static void main(String[] args) {
        new ECntAd().crtCnt().crtFiles();
    }


    public EMCrt crtCnt() {
        CntAdLine.TB.getCode();
        VFlds[] vflds = new VFlds[]{new VFlds(TB)};
        VFlds[] mflds = new VFlds[]{new VFlds(T.SIGNAGE, T.AD_POSITION)};
        VFlds[] searchVflds = new VFlds[]{new VFlds(T.SIGNAGE, T.AD_POSITION)};
        EMCrt ext = new EMCrtComp(TB, vflds, mflds, searchVflds, new VFlds[]{new VFlds(CntAdLine.T.MAIN)});
        VFlds vfl = ext.getVfldsForm();

//		vfl.get(T.PAGENAME ).setReadOnly("!this.insFlag");
//		vfl.get(T.ADPOSITION).setReadOnly("!this.insFlag");
//		vfl.get(T.PICCOUNT).setReadOnly("!this.insFlag");
        vfl.get(T.WIDTH).setReadOnly("!this.insFlag");
        vfl.get(T.HEIGHT).setReadOnly("!this.insFlag");
//		vfl.get(T.NUMBER).setReadOnly("!this.insFlag");
        vfl.get(T.CREATED_BY).setReadOnly("!this.insFlag");
        vfl.get(T.CREATED_TIME).setReadOnly("!this.insFlag");
//        ext.getVfldsForm().del(T.WIDTH);
//        ext.getVfldsForm().del(T.HEIGHT);
        ext.getVfldsForm().get(T.WIDTH).setNullTrue();
        ext.getVfldsForm().get(T.HEIGHT).setNullTrue();
        ext.getVfldsForm().del(T.CREATED_BY);
        ext.getVfldsForm().del(T.CREATED_TIME);
        ext.getVfldsForm().del(T.ENABLED);

        ext.newExts().init();
        return ext;
    }

    //@formatter:off

    /** Begin onTodo ********

     *** End onTodo *********/
    /** Begin onUndo ********

     *** End onUndo *********/
    //@formatter:on


}
