package irille.pub.scheduled;

import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import irille.Service.Manage.O2O.Imp.O2OActicityServerImp;
import irille.Service.Manage.Pdt.Imp.PdtProductManageServiceImp;
import irille.pub.dynamicScore.SVSNewestPdtAction;
import irille.pub.exception.WebMessageException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/** 秒为单位 */
public class ScheduledTask implements ServletContextListener {
  private static final int minute = 60 * 10;
  private static final int hour = 60 * 10 * 60;

  private LinkedBlockingDeque<Task> _tasks = new LinkedBlockingDeque<Task>();

  private Scheduler scheduler = null;

  public void initScheduledTask() {
    //		addTask(QuartzTest.class,1,"测试");
    addTask(O2OActicityServerImp.class, 5 * minute, "O2O活动状态变更");
    addTask(SVSNewestPdtAction.updSVSFraction.class, "0 0/15 * * * ?", "SVS动态分");
    addTask(SVSNewestPdtAction.updSVSGrade.class, "0 0 0 20 * ?", "SVS等级");
    addTask(PdtProductManageServiceImp.class, "0 1 * * * ? *", "产品上架状态变更");
  }

  public void addTask(Class clazz, Object time, String name) {
    Task t = new Task();
    t.setClazz(clazz);
    if (time instanceof Integer) {
      t.setTime(Integer.valueOf(String.valueOf(time)));
    } else if (time instanceof String) {
      if (CronExpression.isValidExpression(String.valueOf(time))) {
        t.setCron(String.valueOf(time));
      } else {
        throw new WebMessageException("非法Cron表达式");
      }
    } else {
      throw new WebMessageException("不支持的时间类型");
    }

    t.setName(name);
    _tasks.add(t);
  }

  // 启动计划任务
  public void start() {
    for (Task t : _tasks) {
      JobDetail job =
          JobBuilder.newJob(t.getClazz())
              .withIdentity(
                  t.getClazz().getSimpleName() + "_Job",
                  t.getClazz().getName().substring(0, t.getClazz().getName().lastIndexOf(".")))
              .build();

      Trigger trigger = null;
      if (t.getTime() != null) {
        trigger =
            TriggerBuilder.newTrigger()
                .withIdentity(
                    t.getClazz().getSimpleName() + "_Trigger",
                    t.getClazz().getName().substring(0, t.getClazz().getName().lastIndexOf(".")))
                .startNow()
                .withSchedule(
                    SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(t.getTime())
                        .repeatForever())
                .build();
      } else if (t.getCron() != null) {
        trigger =
            TriggerBuilder.newTrigger()
                .withIdentity(
                    t.getClazz().getSimpleName() + "_Trigger",
                    t.getClazz().getName().substring(0, t.getClazz().getName().lastIndexOf(".")))
                .withSchedule(
                    CronScheduleBuilder.cronSchedule(t.getCron()).inTimeZone(TimeZone.getDefault()))
                .build();
      }

      try {
        scheduler.scheduleJob(job, trigger);
      } catch (SchedulerException e) {
        log.info(t.getName() + "任务出错");
        continue;
      }
    }
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      initScheduledTask();
      scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
      System.out.println("<<<======启动计划任务======>>>");
      start();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    try {
      scheduler.shutdown();
      System.out.println("<<<======关闭计划任务======>>>");
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}
