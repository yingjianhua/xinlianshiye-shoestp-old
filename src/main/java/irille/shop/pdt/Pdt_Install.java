package irille.shop.pdt;

import irille.core.sys.SysMenuDAO;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanBase;
import irille.pub.bean.InstallBase;
import irille.pub.svr.DbPool;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.usr.UsrProductCategory;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

public class Pdt_Install extends InstallBase {

    public static final Pdt_Install INST = new Pdt_Install();

    @Override
    public void initMenu(SysMenuDAO m) {
        m.proc(PdtAttrCat.TB, 10, null);
        m.proc(PdtProduct.TB, 20, null);
        m.proc(PdtCat.TB, 30, null);
        m.proc(UsrProductCategory.TB, 40, null, "pdt");
        m.proc(PdtAttr.TB, 50, null);
        m.proc(PdtColor.TB, 60, null);
        m.proc(PdtSize.TB, 70, null);

    }

    public void installAfter() {
        super.installAfter();
        SysUser lu = new SysUser();
        lu.setPkey(1);
        LoginUserMsg msg = new LoginUserMsg(lu, (byte) 0, null, null, null);
        Env.INST.initTran(msg, null);
        initPdtAttr();
//		initPdtCat();
//		initPdtSize();
//        initPdtColor();
        try {
            DbPool.getInstance().getConn().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Pdt_Install pdt_install = new Pdt_Install();
        pdt_install.installAfter();

    }

    private void initPdtColor() {
        if (BeanBase.list(PdtColor.class, null, false).size() > 0)
            return;
        PdtColorDAO.InsInit act = new PdtColorDAO.InsInit();
        List<PdtColor> list = new ArrayList<>();
        List<String> l = Arrays.asList("红色", "蓝色", "緑色", "黃色", "橙色", "紫色", "黑色", "白色", "棕色", "酒紅色", "桃紅色", "深緑色", "深紫色", "鮮紅色", "灰色", "杏色", "深藍色", "米色", "驼色", "金色", "银色", "军绿色", "古铜色", "深粉", "深灰", "粉色", "深棕", "淺棕", "深灰", "卡其色", "黑粗紋", "黑細紋", "紅棕", "黃褐色", "槍色", "淺藍", "咖啡色", "紅棕色", "灰褐色", "淡紫色", "亮黑色", "玫瑰金", "紫紅色", "淡粉色", "红白色", "黄金", "蓝白色", "黑红", "裸色", "银粉", "黄金白银", "黑白", "黄蓝", "玫瑰金", "枚红色", "黑金", "藏蓝");
        for (String o : l) {
            list.add(translateUtil.autoTranslate(PdtColorDAO.InsInit.build(o)));
        }
        act.setList(list);
        act.commit();
    }

    private void initPdtSize() {
        if (BeanBase.list(PdtSize.class, null, false).size() > 0)
            return;
        PdtSizeDAO.InsInit act = new PdtSizeDAO.InsInit();
        act.setStream(Stream.of("定制28EU--35EU", "定制25EU--30EU", "定制31EU--36EU", "定制20EU--24EU", "定制19EU--24EU", "定制35EU--40EU", "定制40EU--48EU", "定制30EU--35EU", "定制37EU--43EU", "定制39EU--46EU", "定制36EU--41EU", "定制40EU--46EU", "定制40EU--45EU", "定制(38EU--44EU)", "定制(38EU-46EU)", "定制(38EU--43EU)", "定制(38EU--45EU)", "定制(39EU--46EU)", "定制(35EU--46EU)", "定制(28EU-35EU)", "定制(36EU-40EU)", "定制（21EU-25EU）", "定制（25EU-29EU）", "定制(26EU-36EU)", "定制(21EU-31EU)", "定制(35EU-43EU)", "定制(35EU-42EU)", "定制(34EU-40EU)", "定制(34EU-39EU)", "定制(35EU-39EU)", "定制(44EU-47EU)", "定制(24EU-37EU)", "定制(37EU-44EU)", "定制(36EU-42EU)", "定制(18EU-23EU)", "定制(34EU-47EU)", "定制(39EU-44EU)", "定制(41EU-46EU)", "定制（35EU--41EU）", "定制（38-47）", "定制（40-44）", "40-45", "38-46", "36-44", "35-41", "定制（35EU-40EU)", "定制（36EU--44EU）", "定制（39EU--44EU）", "20-27", "5-10#,11-4#(美码）", "40", "41", "42", "43", "44", "45", "34-44", "34-44", "34-40", "34-44", "34-44", "34-44", "29-35", "39-45", "25-34", "29-35", "30-35", "39-45", "婴儿（美/欧）0.5-1码/16", "婴儿（美/欧）1.5-2码/17", "婴儿（美/欧）2.5-3码/18", "小童（美/欧）3.5-4码/19", "小童（美/欧）4.5-5码/20", "小童（美/欧）5.5/21", "小童（美/欧）6-6.5码/22", "小童（美/欧）7-7.5码/23", "小童（美/欧）8码/24", "小童（美/欧）8.5-9码/25", "小童（美/欧）9.5码/26", "小童（美/欧）10码/27", "中童（美/欧）10.5码/27", "中童（美/欧）11码/28", "中童（美/欧）11.5码/29", "中童（美/欧）12-12.5码/30", "中童（美/欧）13-13.5码/31", "中童（美/欧）1码/32", "中童（美/欧）1.5-2码/33", "中童（美/欧）2.5-3码/34", "大童（美/欧）3.5码/35", "大童（美/欧）4-4.5码/36", "大童（美/欧）5-5.5码/37", "大童（美/欧）6-6.5码/38", "大童（美/欧）7码/39", "女（美/欧）5/35", "女（美/欧）5.5/35.5", "女（美/欧）6/36", "女（美/欧）6.5/37", "女（美/欧）7/37.5", "女（美/欧）7.5/38", "女（美/欧）8/38.5", "女（美/欧）8.5/39", "女（美/欧）9/40", "女（美/欧）9.5/41", "女（美/欧）10/42", "男（美/欧）5/37.5", "男（美/欧）5.5/38", "男（美/欧）6/38.5", "男（美/欧）6.5/39", "男（美/欧）7/40", "男（美/欧）7.5/40.5", "男（美/欧）8/41", "男（美/欧）8.5/42", "男（美/欧）9/42.5", "男（美/欧）9.5/43", "男（美/欧）10/44", "男（美/欧）10.5/44.5", "男（美/欧）11/45", "男（美/欧）11.5/45.5", "男（美/欧）12/46", "男（美/欧）13/47.5", "38-44"));
        act.commit();
    }

    private void initPdtCat() {
        if (BeanBase.list(PdtCat.class, null, false).size() > 0)
            return;
        PdtCatDAO.InsMap act = new PdtCatDAO.InsMap();
        Map<String, Map<String, List<String>>> category = new LinkedHashMap<String, Map<String, List<String>>>();
        category.put("Men Collections", new LinkedHashMap<String, List<String>>());
        category.put("Womens Collections", new LinkedHashMap<String, List<String>>());
        category.put("Kids Collections", new LinkedHashMap<String, List<String>>());
        category.get("Men Collections").put("Mens shoes", Arrays.asList("Dress shoes", "Mens Leather Shoes", "Casual Shoes", "Loafers&amp;Slip-ons", "Sports Shoes"));
        category.get("Men Collections").put("Mens boots", Arrays.asList("Safety Shoes", "Dress  boots"));
        category.get("Men Collections").put("Mens sandals", Arrays.asList());
        category.get("Womens Collections").put("Womens shoes", Arrays.asList("Sports shoes", "Casual shoes", "Loafers", "Heels", "Flats", "Dress shoes", "Wedges"));
        category.get("Womens Collections").put("Womens boots", Arrays.asList("Ankle Boots", "Mid calf boots", "Knee high boots", "Casual boots"));
        category.get("Womens Collections").put("Womens sandals", Arrays.asList("Flat sandals", "Wedge sandals", "Heel sandals", "Slippers"));
        category.get("Kids Collections").put("Boys Collections", Arrays.asList("Boys boots", "Boys sports shoes", "Boys casual shoes", "Boys slippers", "Boys sandals"));
        category.get("Kids Collections").put("Girls Collections", Arrays.asList(
                "Girls boots", "Girls sports shoes", "Girls casual shoes", "Girls sandals", "Girls slippers", "Girls dress shoes"));
        category.get("Kids Collections").put("Babies Collections", Arrays.asList());
        category.get("Kids Collections").put("Waterproof boots&amp;Rain boots", Arrays.asList());

        act.setCategoryMap(category);
        act.commit();
    }

    private void initPdtAttr() {
        if (BeanBase.list(PdtAttrCat.class, null, false).size() > 0)
            return;
        PdtAttrCatDAO.InsMap act = new PdtAttrCatDAO.InsMap();

        Map<String, Map<String, List<String>>> categoryMap = new LinkedHashMap<String, Map<String, List<String>>>();
        Map<String, List<String>> attrMap = new LinkedHashMap<>();
        categoryMap.put("通用鞋子", attrMap);

        attrMap.put("风格类型", Arrays.asList("青春潮流", "商务", "休闲", "运动", "民族风",
                "韩版", "英伦", "欧美", "复古"));
        attrMap.put("适合季节", Arrays.asList("春秋", "夏季", "冬季", "四季", "秋冬季", "秋季"));
        attrMap.put("内里材料", Arrays.asList("半粒面", "檫色皮", "樹膏皮", "磨砂皮", "壓花皮",
                "漆皮", "粒面皮", "搖粒絨", "PU", "毛里", "仿羊羔毛", "纺织品", "MESH",
                "Fabric", "Fur", "布", "网布", "帆布", "短毛绒", "绒布", "绒毛", "佳積布",
                "頭層豬皮", "金絲絨", "二層豬皮", "超纖布", "天鵝絨", "超纖", "頭層豬皮", "漆皮", "鹿皮"));
        attrMap.put("鞋底材料", Arrays.asList("EVA", "EVA發泡膠", "MD", "PVC",
                "TPR(牛筋）", "TPU", "橡膠發泡", "煙膠", "PU", "橡胶", "组合底", "聚氨酯"));
        attrMap.put("鞋面材料", Arrays.asList("PU", "二層牛皮（除牛反絨）", "二層豬皮", "亮片布",
                "太空革", "頭層牛皮（除牛反絨）", "頭層豬皮", "孔雀皮", "絨", "牛津布", "絨布", "毛",
                "紡織", "超纖", "帆布", "網", "韓國絨", "飛織", "PE", "反絨", "豬八革", "瘋馬皮",
                "銀絲布", "超纖布", "牛絨", "超織", "牛仔布", "磨砂皮", "漆皮"));
        attrMap.put("鞋垫材料", Arrays.asList("PU", "二層豬皮", "人造短毛絨", "人造長毛絨",
                "頭層牛皮", "頭層豬皮", "布", "純羊毛", "織布", "佳績布", "仿羊羔毛", "天鵝絨"));
        attrMap.put("高跟鞋", Arrays.asList("低跟（1--3cm）", "平跟（小於等於1cm）",
                "中跟（3--5cm）", "高跟（5cm及以上）"));
        attrMap.put("鞋头", Arrays.asList("圓頭", "方頭", "尖頭", "扁頭", "魚嘴"));
        attrMap.put("闭合方式", Arrays.asList("拉鏈", "繫帶", "魔術貼", "套腳", "搭扣", "鬆緊帶"));
        attrMap.put("制作工艺",
                Arrays.asList("固特異", "縫製鞋", "膠粘鞋", "硫化鞋", "注壓鞋", "冷粘"));
        attrMap.put("根底", Arrays.asList("平底", "內增高", "厚底", "坡跟", "高跟", "摇摇底"));
        attrMap.put("图案", Arrays.asList("純色", "格子", "拼色", "手繪"));
        attrMap.put("流行元素", Arrays.asList("奢華馬毛", "皮草裝飾", "素面", "編制", "馬銜扣",
                "圖騰", "鉚釘", "抽條", "飾釦", "流蘇", "鬆緊拼接", "透明跟", "電秀", "金屬跟"));
        attrMap.put("功能",
                Arrays.asList("矯正", "透氣", "輕質", "防水", "保暖", "耐磨", "減震", "增高"));
        attrMap.put("凉鞋-后帮", Arrays.asList("后空", "前後絆帶", "后絆帶", "腳腕絆帶", "包跟"));
        attrMap.put("凉鞋-侧帮", Arrays.asList("中空", "側空", "滿幫"));
        attrMap.put("开口深度", Arrays.asList("淺口", "中口", "深口"));
        attrMap.put("筒高", Arrays.asList("低筒", "中筒", "高筒"));
        attrMap.put("生产周期", Arrays.asList("70天", "30天", "约50天", "两个月", "一个月",
                "45天", "7天", "15天", "25天"));
        act.setCategoryMap(categoryMap);
        act.commit();
    }
}
