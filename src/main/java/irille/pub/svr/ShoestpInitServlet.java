package irille.pub.svr;

import irille.action.sys.SysMenuAction;
import irille.pub.ClassTools;
import irille.pub.Task.TaskUtil;
import irille.pub.Task.Tasks.GetGooleAnalyticsTask;
import irille.pub.bean.PackageBase;
import irille.pub.bean.PackageBase.TbMsg;
import irille.pub.inf.IDb;
import irille.shop.lg.LgAccess;
import irille.shop.plt.Plt_ConfPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.concurrent.TimeUnit;

public class ShoestpInitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(ShoestpInitServlet.class);

    private static final IDb db = Env.INST.getDB();

    public void init() throws ServletException {
        logger.info("自动建表...");
        createTable(LgAccess.class);

        logger.info("初始化基类...");
        initBeanLoad();

        logger.info("初始化所有安装模块下的菜单...");
        SysMenuAction.initMenus();

        logger.info("初始化数据...");
        Plt_ConfPackage.INST.install();
        logger.info("初始化计划任务...");
        TaskUtil taskUtil = new TaskUtil();
        taskUtil.addTask(new GetGooleAnalyticsTask(), 1L, TimeUnit.DAYS);

    }

    public void initBeanLoad() {
        for (Class<?> packClass : Plt_ConfPackage.INST.getPacks().keySet()) {
            PackageBase pack = (PackageBase) ClassTools.getStaticProerty(packClass, "INST");
            for (TbMsg tb : pack.getTbMsgs()) {
                System.out.println("类初始加载：" + tb.getTb().getCode());
            }
        }
    }

    public void createTable(Class<?> beanClass) {
        try {
            db.db(beanClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
