package irille.view.pdt;

import irille.view.BaseView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/22
 * Time: 16:42
 */
public class PdtProductCatView implements BaseView {
    private int value;
    private String label;
    private List children;


    public int getValue() {
        return value;
    }

    public PdtProductCatView setValue(int value) {
        this.value = value;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public PdtProductCatView setLabel(String label) {
        this.label = label;
        return this;
    }

    public List getChildren() {
        return children;
    }

    public PdtProductCatView setChildren(List children) {
        this.children = children;
        return this;
    }
}
