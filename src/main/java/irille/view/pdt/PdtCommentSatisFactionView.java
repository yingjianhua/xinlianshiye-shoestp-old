package irille.view.pdt;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/11
 * Time: 11:36
 */
@JsonFilter(value = "PdtCommentSatisFactionView")
public class PdtCommentSatisFactionView {
    private int overall_rating;
    private int usefulness;
    private int build_quality;
    private int ease_of_use;
    private int reference_price;

    public int getUsefulness() {
        return usefulness;
    }

    public PdtCommentSatisFactionView setUsefulness(int usefulness) {
        this.usefulness = usefulness;
        return this;
    }

    public int getBuild_quality() {
        return build_quality;
    }

    public PdtCommentSatisFactionView setBuild_quality(int build_quality) {
        this.build_quality = build_quality;
        return this;
    }

    public int getEase_of_use() {
        return ease_of_use;
    }

    public PdtCommentSatisFactionView setEase_of_use(int ease_of_use) {
        this.ease_of_use = ease_of_use;
        return this;
    }

    public int getReference_price() {
        return reference_price;
    }

    public PdtCommentSatisFactionView setReference_price(int reference_price) {
        this.reference_price = reference_price;
        return this;
    }

    public int getOverall_rating() {
        return overall_rating;
    }

    public PdtCommentSatisFactionView setOverall_rating(int overall_rating) {
        this.overall_rating = overall_rating;
        return this;
    }

    public static void main(String[] args) throws JsonProcessingException {
        PdtCommentSatisFactionView pdtCommentSatisFactionView = new PdtCommentSatisFactionView();
        pdtCommentSatisFactionView.setUsefulness(1);
        pdtCommentSatisFactionView.setBuild_quality(1);
        pdtCommentSatisFactionView.setEase_of_use(1);
        pdtCommentSatisFactionView.setReference_price(1);
        pdtCommentSatisFactionView.setOverall_rating(1);
        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writeValueAsString(pdtCommentSatisFactionView));
    }
}
