package dep.usr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrSubscribe;

public class EUsrSubscribe extends UsrSubscribe {
    public static void main(String[] args) {
        new EUsrSubscribe().crtExt().crtFiles();
    }

    public EMCrt crtExt() {
        VFlds vflds = new VFlds(TB);
        VFlds searchVflds = new VFlds(T.EMAIL);//搜索工具栏设置
        EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
        ext.getVfldsList().get(T.EMAIL).setWidthList(300);
        ext.newExts().init();
        return ext;
    }

}
