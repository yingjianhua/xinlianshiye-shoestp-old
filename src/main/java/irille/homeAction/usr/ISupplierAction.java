package irille.homeAction.usr;

import java.io.IOException;

import org.json.JSONException;

public interface ISupplierAction {

  /**
   * 获取供应商详情
   *
   * @throws IOException
   * @throws JSONException
   * @author Jianhua Ying
   */
  void getDetail() throws IOException, JSONException;
}
