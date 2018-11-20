package irille.sellerAction.omt;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.Service.Manage.OdrMeeting.IOdrMeetingProductManageService;
import irille.Service.Manage.OdrMeeting.IOdrMeettingExhibitionService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.omt.inf.IOdrMeetingAction;
import irille.view.Page;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;

public class OdrMeetingAction extends SellerAction<OrderMeeting> implements IOdrMeetingAction {

    @Inject
    private IOdrMeetingManageService odrMeetingManageService;
    @Inject
    private IOdrMeetingProductManageService odrMeetingProductManageService;

    @Inject
    private IOdrMeettingExhibitionService odrMeettingExhibitionService;
    @Override
    public void loadsupstate() throws Exception {
        writerOrExport(odrMeetingManageService.loadsupstate());
    }
    @Override
    public void loadstate() throws Exception {
        writerOrExport(odrMeetingManageService.loadstate());
    }

    @Override
    public void exhibitionlist() throws Exception {
        write(odrMeettingExhibitionService.listExhibition());
    }

    @Setter
    @Getter
    private  String name;
    @Setter
    @Getter
    private Integer state;

    @Setter
    @Getter
    private Integer supstate;

    public void getMyOdrMeetingList() throws Exception {
        write(odrMeetingManageService.getMyOdrMeetingList(getStart(),getLimit(),name,supstate,state,getSupplier().getPkey())); ;
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
    @Override  //發佈訂購會 刪除訂購會
    public void batchdelete() throws Exception {
        odrMeetingManageService.batchdelete(pkeys);
        write();
    }
    @Override   //參加訂購會 刪除訂購會
    public void joindelete() throws Exception {
        odrMeetingManageService.joindelete(pkeys);
        write();
    }

    @Override  //參加訂購會 插入訂購會
    public void joininsert() throws Exception {
        odrMeetingManageService.insertjoinOdr(id,getSupplier().getPkey());
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
    public void getorderInformation() throws IOException {
        write(odrMeetingManageService.getorderInformation(id));
    }
    public void getOrderGoodsList() throws IOException {
        write(odrMeetingProductManageService.getOrderGoodsList(getStart(),getLimit(),id));
    }


}
