package irille.homeAction.svs;

import java.io.IOException;

import javax.inject.Inject;

import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.SVSInfo;
import irille.homeAction.HomeAction;
import irille.homeAction.svs.inf.ISVSInfoAction;
import lombok.Data;

@Data
public class SVSInfoAction extends HomeAction<SVSInfo> implements ISVSInfoAction {
  @Inject private SVSInfoService svsInfoService;
  private Integer supplierId;
  private Integer mainId;

  @Override
  public void getSvsRatingAndRos() throws IOException {
    write(svsInfoService.getSvsRatingAndRos(supplierId));
  }

  @Override
  public void getSvsRatingAndRosMain() throws IOException {
    write(svsInfoService.getSvsRatingAndRosMain(mainId));
  }
}
