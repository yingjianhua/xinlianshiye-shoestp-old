package irille.pub.util.file;

import java.io.File;

import irille.pub.util.upload.ImageUpload;
import irille.shop.cnt.CntAd;
import irille.shop.cnt.CntMagazine;
import irille.shop.usr.UsrSupplier;
import irille.view.plt.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {

  public static void main(String[] args) throws JSONException {
//    String str =
//        "/public/upload/usr/supplier/8d636da1cad7333bc151027d512a6c2f.jpg,"
//            + "/public/upload/usr/supplier/a65e5c6ff166514aa1266bc62d920668.jpg,"
//            + "/public/upload/usr/supplier/5d633e0ac58b2698fc219a50803a1db8.jpg,"
//            + "/public/upload/usr/supplier/4421e0c46df5f73c9a4fdc55b8a929e8.jpg,"
//            + "/public/upload/usr/supplier/f2f3663ebed9812f2cf0d72991f4f883.jpg,"
//            + "/public/upload/usr/supplier/e29c9704e7b5b08ecec04fb78e73ef8a.jpg,"
//            + "/public/upload/usr/supplier/f4296e7e88c817999e0d327dc0db4448.jpg,"
//            + "/public/upload/usr/supplier/e97cd3469245a5ff163e0a1537619300.jpg,"
//            + "/public/upload/usr/supplier/22688175677a19688bff52b77f8ef19f.jpg,"
//            + "/public/upload/usr/supplier/27d307f56f1c6fc3951a4b6545db1639.jpg,"
//            + "/public/upload/usr/supplier/26424921d1d77aba21256cd3183072f2.jpg,"
//            + "/public/upload/usr/supplier/37d769ff4a7a54bbf990f126c99f235f.jpg,"
//            + "/public/upload/usr/supplier/3f138211683a50e50479d2bed8f1d58.jpg,"
//            + "/public/upload/usr/supplier/6aabe8f26075730ebd49e8f0d00e4486.jpg,"
//            + "/public/upload/usr/supplier/b27424deb040ff7cae0166b64d830e6.jpg,"
//            + "/public/upload/usr/supplier/723c2c745a9e420853e5e0f456f6aa76.jpg,"
//            + "/public/upload/usr/supplier/1aa7f4380722edaa748d011e31b8b9c.jpg,"
//            + "/public/upload/usr/supplier/c3e98aca681158f87d61bfb797b5c3e6.jpg,"
//            + "/public/upload/usr/supplier/f5589a666080ea5ceb066acd8490bf0e.jpg,"
//            + "/public/upload/usr/supplier/4c8c4eacba8ea3cc8dedcc2c38563917.jpg";
//    String[] strings = str.split(",");
//    JSONObject jsonObject = new JSONObject();
//    JSONArray jsonArray = new JSONArray();
//    Integer i = 1;
//    for (String string : strings) {
//      System.out.println("图片路径：" + string);
//      JSONObject object = new JSONObject();
//      object.put("id", i);
//      object.put("name", "风格" + i);
//      object.put("image", string);
//      jsonArray.put(object);
//      i++;
//    }
//    jsonObject.put("图片", jsonArray);
//    System.out.println(jsonObject);
        uploadImage2();
    //    test();
  }

  /** 批量上传html中的图片到oss,并替换文件中的内容 */
  //  public static void uploadImage() {
  //    String rootPath = "C:\\Users\\HelloBox\\Desktop\\20期\\新杂志\\women\\20"; // 上传之前本地图片路径
  //
  //    String filename = ".*.jpg";
  //    String regex = "[url]: \"(.*?)\",";
  //    new Finder()
  //        .find(rootPath, filename)
  //        .deal(
  //            fileName ->
  //                Consumer.replaceAll(
  //                    fileName,
  //                    regex,
  //                    match -> {
  //                      return "#";
  //                    }));
  //  }

  public static void test() {
    String rootPath = "C:\\Users\\HelloBox\\Desktop\\20期\\新杂志\\men\\20"; // 上传之前本地图片路径
    String filename = ".*\\.jpg";
    //        String regex = "[Ii]mg: \"(.*)\"";
    new Finder()
        .find(rootPath, filename)
        .deal(
            filepath -> {
              File file = new File(filepath);
              ImageView view = ImageUpload.upload(CntAd.class, "akfa.jpg", file);
              String regex = "(./" + filepath.substring(filepath.lastIndexOf("\\") + 1) + ")";
              Consumer.replaceAll(
                  rootPath + "\\index.html",
                  regex,
                  match -> {
                    return view.getUrl();
                  });
            });
  }

  /** 多图上传 */
  public static void uploadImage() {
    String rootPath = "C:\\Users\\admin\\Desktop\\店招";
    String imagePreix = rootPath + "";
    String filename = "^.*\\.png$";
    String regex = "[img|bgImg]: \"(.*?)\",";
    new Finder()
        .find(rootPath, filename)
        .deal(
            fileName ->
                Consumer.replaceAll(
                    fileName,
                    regex,
                    match -> {
                      if (match.startsWith("http://")) return match;
                      ImageView view =
                          ImageUpload.upload(
                              CntMagazine.class, "a.jpg", new File(imagePreix + match));
                      return view.getUrl();
                    }));
  }
  /** 多图上传 */
  public static void uploadImage2() {
    String rootPath =
        "C:\\Users\\admin\\Desktop\\工作\\文档\\鞋贸港3.1.1\\V3.1.1鞋贸港商家后台需求文档-黄显佩\\商家端优化-黄显佩\\商家默认系统店招切图";
    String imagePreix = rootPath + "";
    String filename = "所有店铺开店成功后默认的店招.png";
//    String filename = "^.*\\.png$";
    Integer i = 1;
    new Finder()
        .find(rootPath, filename)
        .deal(
            fileName -> {
              System.out.println("上传前" + fileName);
              ImageView view = ImageUpload.upload(UsrSupplier.class, "a.jpg", new File(fileName));
              System.out.println("上传后" + view.getUrl());
            });
  }

  /** 单张上传 */
  //	public static void main(String[] args) {
  //		String url = "C:/magazine/sell/nr/002/images/topics/women/3/1.jpg";
  //		ImageView view = ImageUpload.upload(CntMagazine.class, "a.jpg", new File(url));
  //				System.out.println( view.getUrl());
  //	}
}
