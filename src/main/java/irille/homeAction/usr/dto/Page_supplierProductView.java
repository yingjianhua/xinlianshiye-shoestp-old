package irille.homeAction.usr.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/7/20
 * Time: 9:13
 */
@Data
public class Page_supplierProductView {
    private long pkey;
    private String picture;
    private String rewrite;



    public void setPicture(String picture) {
        if (picture != null) {
            String[] strings = picture.split(",");
            this.picture = strings.length > 0 ? strings[0] : picture;
        }
    }
}
