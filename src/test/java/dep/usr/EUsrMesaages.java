package dep.usr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrMessages;

public class EUsrMesaages extends UsrMessages {
    public static void main(String[] args) {
        new EUsrMesaages().crtExt().crtFiles();
    }

    public EMCrt crtExt() {
        VFlds[] vflds = new VFlds[]{new VFlds(TB)};
        VFlds[] searchVflds = new VFlds[]{new VFlds(T.SENDUSER,T.RECIVER,T.STATUS,T.TYPE)};
        EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
        ext.getVfldsList().get(T.STATUS).setWidthList(138);
        ext.getVfldsForm().del(T.SEND_TIME);
        ext.getVfldsForm().del(T.STATUS);
        ext.getVfldsForm().del(T.SENDUSER);
        ext.getVfldsForm().del(T.REPLY_TIME);
        ext.getVfldsForm().del(T.READ_TIME);
        ext.getVfldsForm().del(T.TYPE);
        ext.newExts().init();
        return ext;
    }

    /** renderer: this.getNames() **/
    /**
     * getNames() {
        return function (v) {
            if (v == -1) {
                return "系统管理员"
            } else {
                Ext.Ajax.request({
                    async: false,
                    url: base_path + '/usr_UsrMessages_getName?userId=' + v,
                    success: function (response, options) {
                        v = response.responseText
                    }
                });
            }
            return v
        };
    },
     */
}
