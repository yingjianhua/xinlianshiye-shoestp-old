package irille.view.pdt;

import irille.homeAction.pdt.dto.PdtCommentView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/16
 * Time: 10:24
 */
public class PdtCommentViewPageView {


    private PdtYouMayLikeView pdtProduct;
    private List<PdtYouMayLikeView> likeViews;
    private List<PdtCommentView> pdtCommentViews;
    private List breadcrumbNav;
    private long total;
    private long productAvg;
    private boolean commentPermission = true;
    private String commentPermissionMessage;


    public String getCommentPermissionMessage() {
        return commentPermissionMessage;
    }

    public PdtCommentViewPageView setCommentPermissionMessage(String commentPermissionMessage) {
        this.commentPermissionMessage = commentPermissionMessage;
        return this;
    }

    public boolean isCommentPermission() {
        return commentPermission;
    }

    public PdtCommentViewPageView setCommentPermission(boolean commentPermission) {
        this.commentPermission = commentPermission;
        return this;
    }

    public PdtYouMayLikeView getPdtProduct() {
        return pdtProduct;
    }

    public PdtCommentViewPageView setPdtProduct(PdtYouMayLikeView pdtProduct) {
        this.pdtProduct = pdtProduct;
        return this;
    }

    public List<PdtYouMayLikeView> getLikeViews() {
        return likeViews;
    }

    public PdtCommentViewPageView setLikeViews(List<PdtYouMayLikeView> likeViews) {
        this.likeViews = likeViews;
        return this;
    }

    public List<PdtCommentView> getPdtCommentViews() {
        return pdtCommentViews;
    }

    public PdtCommentViewPageView setPdtCommentViews(List<PdtCommentView> pdtCommentViews) {
        this.pdtCommentViews = pdtCommentViews;
        return this;
    }

    public List getBreadcrumbNav() {
        return breadcrumbNav;
    }

    public PdtCommentViewPageView setBreadcrumbNav(List breadcrumbNav) {
        this.breadcrumbNav = breadcrumbNav;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PdtCommentViewPageView setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getProductAvg() {
        return productAvg;
    }

    public PdtCommentViewPageView setProductAvg(long productAvg) {
        this.productAvg = productAvg;
        return this;
    }

    @Override
    public String toString() {
        return "PdtCommentViewPageView{" +
                "pdtProduct=" + pdtProduct +
                ", likeViews=" + likeViews +
                ", pdtCommentViews=" + pdtCommentViews +
                ", breadcrumbNav=" + breadcrumbNav +
                ", total=" + total +
                ", productAvg=" + productAvg +
                ", commentPermission=" + commentPermission +
                '}';
    }
}
