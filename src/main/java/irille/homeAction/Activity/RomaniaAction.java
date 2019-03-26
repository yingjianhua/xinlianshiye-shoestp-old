package irille.homeAction.Activity;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.Dao.Old.ActivitySignIn.ActivityNewInqDao;
import irille.Entity.NewInquiry.NewInquiry;
import irille.Service.Activity.IActivityService;
import irille.homeAction.HomeAction;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import lombok.Getter;
import lombok.Setter;

/** 罗马尼亚活动页临时action */
public class RomaniaAction extends HomeAction {

  @Inject private IActivityService activityService;

  public String execute() {
    if (isMobile()) {
      setResult("../activity/Jsp/m/Romania/index/order-meeting-en.jsp");
      return HomeAction.TRENDS;
    }
    setResult("../activity/Jsp/Romania/Romaniaindex/order-meeting-en.jsp");
    return HomeAction.TRENDS;
  }

  public String classifyactivity() {
    setResult("../activity/Jsp/Romania/ProductList/index.jsp");
    return HomeAction.TRENDS;
  }

  @Getter @Setter private int supId;
  @Getter @Setter private String email;
  @Getter @Setter private String name;
  @Getter @Setter private String detail;
  @Inject private ActivityNewInqDao activityNewInqDao;

  public void inquiry() throws IOException {
    NewInquiry inq = new NewInquiry();
    if (!(Arrays.asList(281, 298, 283, 318, 279, 295, 16, 291, 282, 13, 317, 23, 78, 301, 165, 292)
        .contains(supId))) {
      //      writeErr(-1, "该供应商不是指定供应商");
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.wrong_supplier, curLanguage()));
    } else if (!(email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"))) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_Invalid_email, curLanguage()));
      //      writeErr(0, "邮箱格式不正确");
    } else {
      inq.setSupplierid(supId);
      inq.setName(name);
      inq.setEmail(email);
      inq.setDetail(detail);
      activityNewInqDao.setB(inq).commit();
      writeErr(1, null);
    }
  }

  public void test() {
    activityService.generateData();
  }
}
