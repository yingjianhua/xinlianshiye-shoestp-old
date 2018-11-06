package irille.view.cnt;

import java.util.List;

/**
 * 移动端首页广告展示
 * @author yingjianhua
 *
 */
public class IndexAdView4PC {
	
	//首页中间的广告，位于轮播图下方,三张图片
	private List<AdView> middleAd;
	
	//产品分类广告
	private List<AdView> productCategoryAd;

	public List<AdView> getMiddleAd() {
		return middleAd;
	}

	public void setMiddleAd(List<AdView> middleAd) {
		this.middleAd = middleAd;
	}

	public List<AdView> getProductCategoryAd() {
		return productCategoryAd;
	}

	public void setProductCategoryAd(List<AdView> productCategoryAd) {
		this.productCategoryAd = productCategoryAd;
	}
	
}
