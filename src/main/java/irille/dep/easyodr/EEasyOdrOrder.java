package irille.dep.easyodr;

import irille.shop.easy.EasyOdr;
import irille.shop.easy.EasyOdrline;
import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.view.VFlds;

public class EEasyOdrOrder extends EasyOdr {
    public static void main(String[] args) {
        new EEasyOdrOrder().crtExt().crtFiles();
        //页面删除小计
    }

    public EMCrt crtExt() {
        EasyOdrline.TB.getCode();
        VFlds[] vflds = new VFlds[]{new VFlds(TB)};
        VFlds[] mflds = new VFlds[]{new VFlds(T.ORDER_NUM)};
        VFlds[] searchVflds = new VFlds[]{new VFlds(T.ORDER_NUM)};
        EMCrtComp ext = new EMCrtComp(TB, vflds, mflds, searchVflds,
                new VFlds[]{new VFlds(EasyOdrline.T.ORDER_ID)});
        VFlds vfl = ext.getVfldsForm();
        vfl.del(T.ORDER_NUM);//删除订单号
        vfl.del(T.TIME);//订单时间
        vfl.del(T.COUNYPD);//订单总价
        ext.newExts().init();
        return ext;
    }
}
