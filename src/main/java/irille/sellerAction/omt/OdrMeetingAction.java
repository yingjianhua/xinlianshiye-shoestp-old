package irille.sellerAction.omt;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.omt.inf.IOdrMeetingAction;
import irille.view.Page;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;

public class OdrMeetingAction extends SellerAction<OrderMeeting> implements IOdrMeetingAction {

    @Inject
    private IOdrMeetingManageService odrMeetingManageService;

    @Override
    public void loadstate() throws Exception {
        writerOrExport(odrMeetingManageService.loadstate());
    }
    @Setter
    @Getter
    private  String name;
    @Setter
    @Getter
    private Integer state;


    public void getMyOdrMeetingList() throws Exception {
        write(odrMeetingManageService.getMyOdrMeetingList(getStart(),getLimit(),name,state,getSupplier().getPkey())); ;
    }
    @Override
    public void getMyJoinOdrMeetingList() throws Exception {
        write(odrMeetingManageService.getMyJoinOdrMeetingList(getStart(),getLimit(),name,state,getSupplier().getPkey()));
    }

    @Override
    public void getMyOtherOdrMeetingList() throws Exception {
        write(odrMeetingManageService.getMyOtherOdrMeetingList(getStart(),getLimit(),name,state,getSupplier().getPkey()));
    }
    @Setter
    @Getter
    private  String pkeys;
    @Override
    public void batchdelete() throws Exception {
        odrMeetingManageService.batchdelete(pkeys);
        write();
    }
    @Setter
    @Getter
    private  Integer id;
    @Setter
    @Getter
    private  Integer fstate;//发起订购会修改状态 继续/暂停
    @Override
    public void Meettingupdstate() throws Exception {
        odrMeetingManageService.Meettingupdstate(id,fstate);
        write();
    }
}
