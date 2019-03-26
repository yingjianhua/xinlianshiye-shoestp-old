package irille.platform.o2o;

import java.io.IOException;

import org.json.JSONException;

/**
 * O2O活动控制层
 *
 * @author Jianhua Ying
 */
public interface IO2OActivityAction {

  /**
   * 活动列表
   *
   * <p>搜索条件
   *
   * <ul>
   *   <li>开始时间
   *   <li>截止时间
   *   <li>活动名称
   *   <li>活动状态
   */
  void list() throws IOException;

  /** 取消活动 */
  void cancel() throws IOException, JSONException;

  /** 发布活动 */
  void deploy() throws IOException, JSONException;

  /** 查看活动报名 */
  void enrollList() throws IOException;

  /** 加載活動 */
  void load() throws IOException;

  void privetePdtlist() throws IOException;

  void upperLowerList() throws IOException;
}
