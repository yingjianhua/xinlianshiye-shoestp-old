package irille.dep.omt;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;

public class EOrderMeeting extends OrderMeeting {
  public static void main(String[] args) {
    new EOrderMeeting().crtExt().crtFiles();
  }

  public EMCrt crtExt() {
    VFlds[] vflds = new VFlds[] {new VFlds(TB)};
    VFlds[] mflds = new VFlds[] {new VFlds(T.NAME, T.STATUS)};
    VFlds[] searchVflds = new VFlds[] {new VFlds(T.NAME, T.STATUS)};
    EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
    ext.newExts().init();
    return ext;
  }
}
