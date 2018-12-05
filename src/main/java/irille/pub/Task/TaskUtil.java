package irille.pub.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/5
 * Time: 11:29
 */
public class TaskUtil {
    ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();


    public void addTask(Runnable runnable, Long delay, TimeUnit timeUnit) {
        service.schedule(runnable, delay, timeUnit);
    }
}
