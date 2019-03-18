package irille.pub.util.file;

import java.io.File;

import irille.pub.util.upload.ImageUpload;
import irille.shop.cnt.CntAd;
import irille.view.plt.ImageView;

public class Test {

  public static void main(String[] args) {
    test();
  }

  /** 批量上传html中的图片到oss,并替换文件中的内容 */
  public static void uploadImage() {
    String rootPath = "C:\\Users\\HelloBox\\Desktop\\20期\\新杂志\\women\\20"; // 上传之前本地图片路径
    String filename = ".*.jpg";
    String regex = "[url]: \"(.*?)\",";
    new Finder()
        .find(rootPath, filename)
        .deal(
            fileName ->
                Consumer.replaceAll(
                    fileName,
                    regex,
                    match -> {
                      return "#";
                    }));
  }

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
  //	public static void uploadImage() {
  //		String rootPath = "C:/Users/yjh/Documents/Tencent Files/244588659/FileRecv/do2";
  //		String imagePreix = rootPath+"";
  //		String filename = "index.html";
  //		String regex = "[img|bgImg]: \"(.*?)\",";
  //		new Finder().find(rootPath, filename).deal(fileName->Consumer.replaceAll(fileName, regex,
  // match->{
  //				if(match.startsWith("http://"))
  //					return match;
  //				ImageView view = ImageUpload.upload(CntMagazine.class, "a.jpg", new File(imagePreix+match));
  //				return view.getUrl();
  //			})
  //		);
  //	}

  /** 单张上传 */
  //	public static void main(String[] args) {
  //		String url = "C:/magazine/sell/nr/002/images/topics/women/3/1.jpg";
  //		ImageView view = ImageUpload.upload(CntMagazine.class, "a.jpg", new File(url));
  //				System.out.println( view.getUrl());
  //	}

}
