package irille.pub.scheduled;

import irille.Service.Manage.O2O.Imp.O2OActicityServerImp;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class ScheduledTask implements ServletContextListener{
	
	private LinkedBlockingDeque<Task> _tasks = new LinkedBlockingDeque<Task>();
	
	private Scheduler scheduler = null;
	
	public void initScheduledTask() {
//		addTask(QuartzTest.class,1,"测试");
		addTask(O2OActicityServerImp.class,1600,"O2O活动状态变更");
	}
	
	public void addTask(Class clazz,int time,String name) {
		Task t = new Task();
		t.setClazz(clazz);
		t.setTime(time);
		t.setName(name);
		_tasks.add(t);
	}
	
	//启动计划任务
	public void start() {
        for(Task t:_tasks) {
        	JobDetail job = JobBuilder.newJob(t.getClazz())
        			.withIdentity(t.getClazz().getSimpleName() + "_Job", t.getClazz().getName().substring(0,t.getClazz().getName().lastIndexOf(".")))
    	            .build();
        	
        	
        	Trigger trigger = TriggerBuilder.newTrigger()
    	            .withIdentity(t.getClazz().getSimpleName() + "_Trigger", t.getClazz().getName().substring(0,t.getClazz().getName().lastIndexOf(".")))
    	            .startNow()
    	            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
    	                .withIntervalInSeconds(t.getTime())
    	                .repeatForever())
    	            .build();
        	
        	 try {
				scheduler.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				log.info(t.getName()+"任务出错");
				continue;
			}
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
