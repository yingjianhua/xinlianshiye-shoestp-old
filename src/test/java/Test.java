import irille.core.prv.PrvRoleAct;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;

public class Test {
  public static void main(String[] args) {
    StartInitServlet.initBeanLoad();
    PrvRoleAct.TB.getCode();
    PdtProduct pdtColor = new PdtProduct();
    String db =
        "{\"de\": \"Rot\", \"en\": \"Red\", \"es\": \"Rojo\", \"fr\": \"Rouge\", \"hu\": \"piros\", \"ja\": \"レッド\", \"pt\": \"Vermelho\", \"ro\": \"roșu\", \"ru\": \"красный\", \"zh_CN\": \"红色\", \"zh_TW\": \"紅色\"}";
    String save =
        "{\"de\": \"Rot\", \"en\": \"\", \"es\": \"Rojo\", \"fr\": \"Rouge\", \"hu\": \"piros\", \"ja\": \"レッド\", \"pt\": \"Vermelho\", \"ro\": \"roșu\", \"ru\": \"красный\", \"zh_CN\": \"红色\", \"zh_TW\": \"紅色\"}";
    pdtColor.setName(save);
    System.out.println(
        translateUtil
            .newAutoTranslate(
                pdtColor, translateUtil.buildFilter(db, save, FldLanguage.Language.zh_CN))
            .getName());
  }
}
