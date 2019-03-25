package irille.homeAction.p;

/**
 * 个人中心页面汇总
 *
 * @author Jianhua Ying
 */
public interface IMyAction {

  /**
   * 首页
   *
   * @return
   */
  String home();

  /**
   * 消息中心
   *
   * @return
   */
  String messageCenter();

  /**
   * 联系人
   *
   * @return
   */
  String contacts();

  /**
   * 收藏
   *
   * @return
   */
  String favorites();

  /**
   * 账户设置
   *
   * @return
   */
  String accountSettings();
}
