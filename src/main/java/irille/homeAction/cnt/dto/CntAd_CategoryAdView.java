package irille.homeAction.cnt.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/7/25
 * Time: 20:03
 */
public class CntAd_CategoryAdView {
     private Map main;
    private List items;

    public Map getMain() {
        return main;
    }

    public void setMain(Map main) {
        this.main = main;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "{" +
                "\"main\":" + main +
                ", \"items\":" + items +
                '}';
    }
}
