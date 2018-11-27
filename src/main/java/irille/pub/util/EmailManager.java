package irille.pub.util;

import irille.view.se.sendEmail;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class EmailManager {
    //缓存发送邮件的队列
    public static BlockingQueue<sendEmail> queue = new ArrayBlockingQueue<sendEmail>(2000);
    //发送邮件线程状态
    private static boolean isRunning = false;
    //超时时间间隔
    public static long TIMEOUT_INTERVAL = 3*60*1000;

    public static boolean sendMail(sendEmail mail){
        boolean rsp = false;
        try {
            //阻塞3秒
            rsp = queue.offer(mail, 3, TimeUnit.SECONDS);

            if(!rsp){
                throw new Exception("服务器发送邮件繁忙，请稍后再发");
            }


            if(!isRunning){
                startup();
                isRunning = true;
            }

        } catch (Exception e) {
            log.error("添加发送邮件队列出错", e);
        }
        return rsp;
    }
    public static void startup(){
        if(isRunning){
            return;
        }
        Thread th = new  Thread(){
            @Override
            public void run(){
                log.debug("邮件发送消息线程启动");

                long timestamp = System.currentTimeMillis();
                while(isRunning)
                {
                    long timeout = timestamp + TIMEOUT_INTERVAL;
                    if(System.currentTimeMillis()>timeout)
                    {
                        //空跑一段时间(3分钟)后线程退出
                        break;
                    }
                    try
                    {
                        if(queue.size()==0)
                        {
                            Thread.sleep(1000);
                            continue;
                        }
                        timestamp = System.currentTimeMillis();
                        sendEmail mail = queue.poll();
                        EmailUtils.sendMail(mail);

                        //发送一个邮件休息2秒，防止发送过快，导致主油箱被锁定
                        Thread.sleep(2000);

                    } catch (Exception e)
                    {
                        log.error("邮件推送线程出错",e);
                    }
                }
                isRunning = false;
                log.debug("邮件发送线程停止。");
            }
        };

        th.start();
    }
}
