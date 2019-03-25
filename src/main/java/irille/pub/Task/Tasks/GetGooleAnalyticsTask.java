package irille.pub.Task.Tasks;

import javax.inject.Inject;

import irille.Service.Activity.IActivityService;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/5 Time: 11:30 */
public class GetGooleAnalyticsTask implements Runnable {

  @Inject private IActivityService activityService;

  @Override
  public void run() {
    activityService.generateData();
  }
}
