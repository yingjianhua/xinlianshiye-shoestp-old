package irille.homeAction.usr.dto;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/7/20
 * Time: 9:13
 */
public class Page_supplierProductView {
    private long pkey;
    private String picture;
    private String rewrite;

    public String getRewrite() {
        return rewrite;
    }

    public void setRewrite(String rewrite) {
        this.rewrite = rewrite;
    }

    public long getPkey() {
        return pkey;
    }

    public void setPkey(long pkey) {
        this.pkey = pkey;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        if (picture != null) {
            String[] strings = picture.split(",");
            this.picture = strings.length > 0 ? strings[0] : picture;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"pkey\":" + pkey +
                ", \"picture\":\"" + picture + '\"' +
                '}';
    }
}
