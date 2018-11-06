package irille.view.pdt;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.view.BaseView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/22
 * Time: 16:42
 */
public class PdtProductVueView implements BaseView {
    @SetBean(OriginalField = "pkey")
    private int id;
    private String name;
    @SetBean(NotSet = true)
    private List items;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PdtProductVueView setName(String name) {
        this.name = name;
        return this;
    }

    public List getItems() {
        return items;
    }

    public PdtProductVueView setItems(List items) {
        this.items = items;
        return this;
    }

    public PdtProductVueView setId(int id) {
        this.id = id;
        return this;
    }

}
