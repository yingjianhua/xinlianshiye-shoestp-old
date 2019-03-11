package irille.Filter.svr;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import irille.pub.scheduled.ScheduledTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinlianshiye.shoestp.plat.service.pm.IPMTemplateService;
import com.xinlianshiye.shoestp.plat.service.pm.IVariableService;
import com.xinlianshiye.shoestp.plat.service.pm.imp.PMTemplateServiceImp;
import com.xinlianshiye.shoestp.plat.service.pm.imp.VariableServiceImp;

import irille.action.sys.SysMenuAction;
import irille.pub.ClassTools;
import irille.pub.bean.BeanBase;
import irille.pub.bean.PackageBase;
import irille.pub.bean.PackageBase.TbMsg;
import irille.pub.inf.IDb;
import irille.pub.svr.Env;
import irille.shop.lg.LgAccess;
import irille.shop.plt.Plt_ConfPackage;

public class ShoestpInitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(ShoestpInitServlet.class);

    private static final IDb db = Env.INST.getDB();

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("自动建表...");
        createTable(LgAccess.class);

        logger.info("初始化基类...");
        initBeanLoad();

        logger.info("初始化所有安装模块下的菜单...");
        SysMenuAction.initMenus();
        logger.info("设置数据库模式");
        //设置数据库Mode
        BeanBase.executeUpdate("SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY',''));");
        logger.info("初始化数据...");
        Plt_ConfPackage.INST.install();
        
        
        logger.info("初始化站内信模板...");
        IPMTemplateService templateService = new PMTemplateServiceImp();
        templateService.initTemp();
        IVariableService variableService = new VariableServiceImp();
        logger.info("站内信模板初始化完毕...");
//        logger.info("初始化计划任务...");
//        TaskUtil taskUtil = new TaskUtil();
//        taskUtil.addTask(new GetGooleAnalyticsTask(), 1L, TimeUnit.DAYS);

        ServletContext context = config.getServletContext();
        context.createListener(ScheduledTask.class);
//        context.addListener(ScheduledTask.class.getName());
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
