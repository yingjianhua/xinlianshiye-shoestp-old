package irille.shop.usr;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.idu.IduOther;
import irille.sellerAction.ISellerAction;
import irille.shop.usr.UsrAccess.T;

public class UsrAccessDAO {

  public static final Log LOG = new Log(UsrAccessDAO.class);

  public static void main(String[] args) {
    // InsInit.findController();
  }

  public static final List<UsrAccess> ACCESS;
  public static final Map<String, List<UsrAccess>> MODULE_ACCESS;
  public static final Map<String, Map<String, List<UsrAccess>>> CONTROLLER_ACCESS;

  static {
    ACCESS = BeanBase.list(UsrAccess.class, null, false);
    ACCESS.sort((a1, a2) -> a1.getSort().compareTo(a2.getSort()));
    CONTROLLER_ACCESS = mapByController();
    MODULE_ACCESS = mapByModule();
  }

  public static List<String> listModuleName() {
    return Query.SELECT(T.MODULE).FROM(UsrAccess.class).GROUP_BY(T.MODULE).queryList(String.class);
  }

  private static Map<String, Map<String, List<UsrAccess>>> mapByController() {
    Map<String, Map<String, List<UsrAccess>>> result =
        new HashMap<String, Map<String, List<UsrAccess>>>();
    for (UsrAccess line : ACCESS) {
      if (!result.containsKey(line.getModule())) {
        result.put(line.getModule(), new HashMap<String, List<UsrAccess>>());
      }
      if (!result.get(line.getModule()).containsKey(line.getController())) {
        result.get(line.getModule()).put(line.getController(), new ArrayList<UsrAccess>());
      }
      result.get(line.getModule()).get(line.getController()).add(line);
    }
    return result;
  }

  private static Map<String, List<UsrAccess>> mapByModule() {
    Map<String, List<UsrAccess>> result = new HashMap<String, List<UsrAccess>>();
    for (UsrAccess line : ACCESS) {
      if (!result.containsKey(line.getModule())) {
        result.put(line.getModule(), new ArrayList<UsrAccess>());
      }
      result.get(line.getModule()).add(line);
    }
    return result;
  }

  public static class InsInit extends IduOther<InsInit, UsrAccess> {

    private static Set<Class> Controllers;

    @Override
    public void before() {
      super.before();
      System.out.println("加载供应商功能权限-----------------");
      setControllers(loadController(new HashSet<Class>()));
    }

    @Override
    public void run() {
      super.run();
      String sql = "";
      for (Class clazz : getControllers()) {
        String className = clazz.getName();
        String act = "";
        // UsrSupplier
        String controller =
            className.substring(
                clazz.getName().lastIndexOf(".") + 2, clazz.getName().length() - "Action".length());
        // 供应商
        String controllerName = ((Controller) clazz.getAnnotation(Controller.class)).name();
        // usr
        String module = className.split("\\.")[2];
        // 用户管理
        String moduleName = ((Controller) clazz.getAnnotation(Controller.class)).module();

        System.out.println(controller);
        String method;
        String alias;
        int sort;
        for (Method m : clazz.getMethods()) {
          RequestMapping rm = m.getAnnotation(RequestMapping.class);
          if (rm != null) {
            alias = rm.alias();
            method = m.getName();
            sort = rm.sort();
            act = module + "_" + controller + "_" + method;
            System.out.println(act + " " + moduleName + "_" + controllerName + "_" + alias);

            UsrAccess access = UsrAccess.chk(UsrAccess.class, act);
            sql += ",'" + act + "'";
            if (access == null) {
              access = new UsrAccess().init();
              access.setModule(moduleName);
              access.setController(controllerName);
              access.setName(alias);
              access.setSort(sort);
              access.setPkey(act);
              access.ins();
            } else {
              access.setModule(moduleName);
              access.setController(controllerName);
              access.setName(alias);
              access.setSort(sort);
              access.upd();
            }
          }
        }
        if (sql.length() > 0) {
          Bean.executeUpdate(
              "delete from "
                  + UsrAccess.TB.getCodeSqlTb()
                  + " where "
                  + UsrAccess.T.PKEY.getFld().getCodeSqlField()
                  + " not in ("
                  + sql.substring(1)
                  + ")");
        }
      }
    }

    private static Set<Class> loadController(Set<Class> controllers) {
      File rootFile = new File(UsrAccessDAO.class.getResource("/").getFile());
      loadController(rootFile, rootFile.getPath() + File.separator, controllers);
      return controllers;
    }

    private static <T> void loadController(
        File rootFile, String parentDirectory, Set<Class> controllers) {
      if (rootFile.isDirectory()) {
        File[] files = rootFile.listFiles();
        for (File file : files) {
          loadController(file, parentDirectory, controllers);
        }
      } else {
        String className = null;
        try {
          if (rootFile.getPath().indexOf(".class") != -1) {
            // irille.sellerAction.usr.inf.IUsrSupplierAction
            className =
                rootFile
                    .getPath()
                    .replace(parentDirectory, "")
                    .replace(".class", "")
                    .replace(File.separator, ".");
            if (className.contains("irille.sellerAction")) {
              Class clazz = Class.forName(className);
              if (ISellerAction.class.isAssignableFrom(clazz)
                  && clazz.getAnnotation(Controller.class) != null) {
                controllers.add(clazz);
              }
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    public static Set<Class> getControllers() {
      return Controllers;
    }

    public static void setControllers(Set<Class> controllers) {
      Controllers = controllers;
    }
  }
}
