package irille.view.pdt;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/15
 * Time: 17:52
 */
public class PdtCategoriesView {
    private String name;
    private long pkey;
    private List items;

    public String getName() {
        return name;
    }

    public PdtCategoriesView setName(String name) {
        this.name = name;
        return this;
    }

    public long getPkey() {
        return pkey;
    }

    public PdtCategoriesView setPkey(long pkey) {
        this.pkey = pkey;
        return this;
    }

    public List getItems() {
        return items;
    }

    public PdtCategoriesView setItems(List items) {
        this.items = items;
        return this;
    }
}
