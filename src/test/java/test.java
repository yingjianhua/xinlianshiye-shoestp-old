import irille.platform.pdt.PdtColorAction;
import irille.shop.pdt.PdtColor;

/**
 * @Author: lingjian
 * @Date: Created in 2019/1/17 15:24
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        PdtColorAction a = new PdtColorAction();
        PdtColor d = new PdtColor();
        d.setName("sss");
        a.setBean(d);
        System.out.println(a.getBean());
    }
}
