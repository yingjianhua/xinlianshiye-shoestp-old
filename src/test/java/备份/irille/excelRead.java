package 备份.irille;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import irille.core.prv.PrvRoleAct;
import irille.core.sys.Sys;
import irille.core.sys.SysUser;
import irille.pub.Exp;
import irille.pub.svr.DbPool;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.FldLanguage;
import irille.pub.util.excel.BaseExcel;
import irille.pub.util.upload.ImageUpload;
import irille.shop.pdt.*;
import irille.view.pdt.PdtProductSaveView;
import irille.view.pdt.PdtProductSpecSaveView;
import org.json.JSONException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/10/4
 * Time: 11:11
 */
public class excelRead {
    public static void main(String[] args) throws JSONException {


        StartInitServlet.initBeanLoad();
        PrvRoleAct.TB.getCode();
        SysUser lu = new SysUser();
        lu.setPkey(1);
        LoginUserMsg msg = new LoginUserMsg(lu, (byte) 0, null, null, null);
        Env.INST.initTran(msg, null);
        BaseExcel baseExcel = new BaseExcel("C:\\Users\\HelloBox\\Desktop\\shoestp重构\\数据\\信息表 罗马尼亚_2018-10-5.xlsx");
        String suffix = baseExcel.getPic(0).getMimeType();
        suffix = suffix.substring(suffix.lastIndexOf("/") + 1);
        String Path = "C:\\Users\\HelloBox\\Desktop\\shoestp重构\\数据\\罗马尼亚800";


        for (int i = 1; i < 211; i++) {
            PdtProductSaveView pdtProductSaveView = new PdtProductSaveView();
            pdtProductSaveView.setId(-1);
            if (isBlank(baseExcel.readValue(1, i))) {
                Map map = Maps.newHashMap();
                map.put("en", String.valueOf(baseExcel.readValue(1, i)).replace("\n", "").replace("\r\n", ""));
                pdtProductSaveView.setPdtName(map);
            }
            if (isBlank(baseExcel.readValue(2, i))) {
                //商品分类
                pdtProductSaveView.setProductCat(getspfl(String.valueOf(baseExcel.readValue(2, i))));
            }
            if (isBlank(baseExcel.readValue(3, i))) {
                //店铺分类
//                String color = String.valueOf(baseExcel.readValue(3, i));
//                    list.add(String.valueOf(getdpfl()));
                if (getdpfl(String.valueOf(baseExcel.readValue(3, i))) == -1) {
                    System.out.println(baseExcel.readValue(3, i));
                }
                pdtProductSaveView.setSupplierCat(getdpfl(String.valueOf(baseExcel.readValue(3, i))));

            }
            if (isBlank(baseExcel.readValue(4, i))) {
                //货号
                pdtProductSaveView.setNumber_left("TOP");
                pdtProductSaveView.setNumber_right(String.valueOf(baseExcel.readValue(4, i)));
            }
            if (isBlank(baseExcel.readValue(5, i))) {
                //货号
                pdtProductSaveView.setSku(String.valueOf(baseExcel.readValue(5, i)));
            }
            if (isBlank(baseExcel.readValue(6, i))) {
                //最小起订量
                pdtProductSaveView.setMin_oq(((Number) baseExcel.readValue(6, i)).intValue());
            }
            if (isBlank(baseExcel.readValue(7, i))) {
                //最大起订量
                pdtProductSaveView.setMax_oq(((Number) baseExcel.readValue(7, i)).intValue());
            }
            if (isBlank(baseExcel.readValue(8, i))) {
                //库存
//                pdtProductSaveView.setStock(((Number) baseExcel.readValue(8, i)).intValue());
            }
            if (isBlank(baseExcel.readValue(9, i))) {
                //警告库存
                pdtProductSaveView.setWarnStock(((Number) baseExcel.readValue(9, i)).intValue());
            }


            if (isBlank(baseExcel.readValue(11, i))) {
                //商城价
                pdtProductSaveView.setPrice(((Number) baseExcel.readValue(11, i)).doubleValue());
            }
            if (isBlank(baseExcel.readValue(12, i))) {
                //市场价
                pdtProductSaveView.setMktPrice(((Number) baseExcel.readValue(12, i)).doubleValue());
            }
            if (isBlank(baseExcel.readValue(13, i))) {
                //进货价
                pdtProductSaveView.setPurPrice(((Number) baseExcel.readValue(13, i)).doubleValue());
            }

            if (isBlank(baseExcel.readValue(14, i))) {
                //规格颜色
                String color = String.valueOf(baseExcel.readValue(14, i));
                List list = new ArrayList();
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getColor(s) == -1) {
                        System.out.println(s);
                        continue;
                    }
                    list.add(String.valueOf(getColor(s)));
                }
                pdtProductSaveView.setSpecColor(Sets.newHashSet(list));
            }
            List<Integer> list = new ArrayList();
            if (isBlank(baseExcel.readValue(10, i))) {
                //码数
                //TODO
                String color = String.valueOf(baseExcel.readValue(10, i));
                List size = new ArrayList();
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getsize(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    size.add(String.valueOf(getsize(s)));
                }
                pdtProductSaveView.setSpecSize(Sets.newHashSet(size));
            }
            //商品属性
            if (isBlank(baseExcel.readValue(15, i))) {
                //鞋底材质
                String color = String.valueOf(baseExcel.readValue(15, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    list.add(getXdCz(s));
                }
            }
            if (isBlank(baseExcel.readValue(16, i))) {
                //鞋面材质
                String color = String.valueOf(baseExcel.readValue(16, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    list.add(getxmcl(s));
                }
            }
            if (isBlank(baseExcel.readValue(17, i))) {
                //内里材质
                String color = String.valueOf(baseExcel.readValue(17, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    list.add(getnlcl(s));
                }
            }
            if (isBlank(baseExcel.readValue(18, i))) {
                //鞋垫材质
                String color = String.valueOf(baseExcel.readValue(18, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getxdcl(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getxdcl(s));
                }
            }
            if (isBlank(baseExcel.readValue(19, i))) {
                //鞋头材质
                String color = String.valueOf(baseExcel.readValue(19, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getxtcl(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getxtcl(s));
                }
            }
            if (isBlank(baseExcel.readValue(20, i))) {
//                //跟底
//                pdtProductSaveView.setPurPrice(((Number) baseExcel.readValue(20, i)).doubleValue());
                String color = String.valueOf(baseExcel.readValue(20, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getGd(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getGd(s));
                }
            }
            if (isBlank(baseExcel.readValue(21, i))) {
//                //功能
//                pdtProductSaveView.setPurPrice(((Number) baseExcel.readValue(21, i)).doubleValue());
                String color = String.valueOf(baseExcel.readValue(21, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getgn(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getgn(s));
                }
            }
            if (isBlank(baseExcel.readValue(22, i))) {
//                //流行元素
                String color = String.valueOf(baseExcel.readValue(22, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getlxys(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getlxys(s));
                }
            }
            if (isBlank(baseExcel.readValue(23, i))) {
//                //风格类型
                String color = String.valueOf(baseExcel.readValue(23, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getfglx(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getfglx(s));
                }
            }
            if (isBlank(baseExcel.readValue(24, i))) {
//                //适合春秋
                String color = String.valueOf(baseExcel.readValue(24, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getcq(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getcq(s));
                }
            }
            if (isBlank(baseExcel.readValue(25, i))) {
//                //高跟鞋
                String color = String.valueOf(baseExcel.readValue(25, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getgg(s) == -1) {
                        System.out.println(s);
                        continue;

                    }
                    list.add(getgg(s));
                }
            }
            if (isBlank(baseExcel.readValue(26, i))) {
//                //闭合方式
//                pdtProductSaveView.setPurPrice(((Number) baseExcel.readValue(26, i)).doubleValue());
                String color = String.valueOf(baseExcel.readValue(26, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getbhfs(s) == -1 && s.length() > 0) {
                        System.out.println(s);
                        continue;
                    }
                    list.add(getbhfs(s));
                }
            }
            if (isBlank(baseExcel.readValue(27, i))) {
//                //图案
                String color = String.valueOf(baseExcel.readValue(27, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (getta(s) == -1 && s.length() > 0) {
                        System.out.println(s);
                        continue;
                    }
                    list.add(getta(s));
                }
            }
            if (isBlank(baseExcel.readValue(28, i))) {
//                //筒高
                String color = String.valueOf(baseExcel.readValue(28, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (gettg(s) == -1 && s.length() > 0) {
                        System.out.println(s);
                        continue;
                    }
                    list.add(gettg(s));
                }
            }
            if (isBlank(baseExcel.readValue(29, i))) {
                String color = String.valueOf(baseExcel.readValue(29, i));
                for (String s : color.replace("。", ",").replace("+", ",").replace("、", ",").replace("，", ",").split(",")) {
                    if (gettg(s) == -1 && s.length() > 0) {
                        System.out.println(s);
                        continue;
                    }
                    list.add(gettg(s));
                }
//            开口深度
            }
            Map<String, String> ImageList = Maps.newHashMap();
            System.out.printf("当前Index:%d \r\n", i);
            for (int i1 = 1; i1 < 6; i1++) {
                String FileName = (i + 1) + "-" + i1 + ".jpg";
                if (Paths.get(Path + "\\" + FileName).toFile().exists()) {
                    ImageList.put(FileName, ImageUpload.upload(PdtProduct.class, FileName, Paths.get(Path + "\\" + FileName).toFile()).getUrl());
                } else {
                    System.out.println(Path + "\\" + FileName + "不存在");
                }

            }

            pdtProductSaveView.setAttr(list);
            pdtProductSaveView.setPdtPics(ImageList);
            List spec = new ArrayList();
            for (Object size : pdtProductSaveView.getSpecSize().toArray()) {
                for (Object color : pdtProductSaveView.getSpecColor().toArray()) {
                    PdtProductSpecSaveView specSaveView = new PdtProductSpecSaveView();
                    specSaveView.setId(-1);
                    JsonObject jsonObject = new JsonObject();
                    for (FldLanguage.Language value : FldLanguage.Language.values()) {
                        PdtColor langColor = PdtColor.get(PdtColor.class, getColor(String.valueOf(baseExcel.readValue(14, i))));
                        PdtSize langSize = PdtSize.load(PdtSize.class, (Integer.valueOf(String.valueOf(size))));
                        jsonObject.addProperty(value.name(), langColor.getName(value) + "  " + langSize.getName(value));
                    }
                    specSaveView.setName(jsonObject.toString());
                    specSaveView.setColor(Integer.parseInt(String.valueOf(color)));
                    specSaveView.setSize(Integer.parseInt(String.valueOf(size)));
                    specSaveView.setSku(pdtProductSaveView.getSku());
                    specSaveView.setPrice(pdtProductSaveView.getPrice());
//                    specSaveView.setStock(pdtProductSaveView.getStock());
                    specSaveView.setWeight(0);
                    spec.add(specSaveView);
                }
            }
            pdtProductSaveView.setSpec(spec);
            Map<String, String> description = Maps.newHashMap();
            description.put("en", " ");
            pdtProductSaveView.setDescription(description);
            save(pdtProductSaveView);
        }


    }

    private static int getdpfl(String valueOf) {
        String name = valueOf.trim().replace(" ", "");
        if (name.equalsIgnoreCase("童鞋")) return 8;
        if (name.equalsIgnoreCase("女鞋")) return 6;
        if (name.equalsIgnoreCase("男装鞋")) return 4;
        if (name.equalsIgnoreCase("南装鞋")) return 4;
        if (name.equalsIgnoreCase("女装鞋")) return 6;
        if (name.equalsIgnoreCase("童鞋")) return 6;
        return -1;
    }

    private static int getspfl(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("男鞋系列/男装鞋/男休闲鞋")) return 374;
        if (name.equalsIgnoreCase("男装鞋系列/男装鞋/男士皮鞋")) return 527;
        if (name.equalsIgnoreCase("男装鞋系列/男装鞋/休闲鞋")) return 374;
        if (name.equalsIgnoreCase("男装鞋系列/男装鞋/运动鞋")) return 377;

        if (name.equalsIgnoreCase("女鞋系列/女鞋系列/休闲鞋")) return 492;
        if (name.equalsIgnoreCase("女鞋系列/女装鞋/女休闲鞋")) return 492;

        if (name.equalsIgnoreCase("女鞋系列/女装鞋/女运动鞋")) return 381;
        if (name.equalsIgnoreCase("女鞋系列/女装鞋/运动鞋")) return 381;

        if (name.equalsIgnoreCase("童鞋系列/男孩系列/男孩休闲鞋")) return 516;
        if (name.equalsIgnoreCase("童鞋系列/女孩系列/女孩休闲鞋")) return 391;
        if (name.equalsIgnoreCase("童鞋系列/女孩系列/女孩运动鞋")) return 391;
        return -1;
    }


    public static void save(PdtProductSaveView pdtProductSaveView) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PdtProduct pdtProduct = new PdtProduct();
            pdtProduct.setPkey(pdtProductSaveView.getId());
            pdtProduct.setName(objectMapper.writeValueAsString(pdtProductSaveView.getPdtName()));
            //审核通过
            pdtProduct.setIsVerify(Sys.OYn.YES.getLine().getKey());
            //产品分类
            pdtProduct.setCategory(pdtProductSaveView.getProductCat());
            //店铺分类
            pdtProduct.setCategoryDiy(pdtProductSaveView.getSupplierCat());
            pdtProduct.setCode(pdtProductSaveView.getNumber_left() + "-" + pdtProductSaveView.getNumber_right());
            pdtProduct.setSupplier(262);
            pdtProduct.setSku(pdtProductSaveView.getSku());
            pdtProduct.setCurPrice(BigDecimal.valueOf(pdtProductSaveView.getPrice()));
            pdtProduct.setPurPrice(BigDecimal.valueOf(pdtProductSaveView.getPurPrice()));
            pdtProduct.setMktPrice(BigDecimal.valueOf(pdtProductSaveView.getMktPrice()));
            pdtProduct.setWsPrice(BigDecimal.valueOf(pdtProductSaveView.getPrice()));
            pdtProduct.setMinOq(pdtProductSaveView.getMin_oq());
            pdtProduct.setMaxOq(pdtProductSaveView.getMax_oq());
            pdtProduct.setSales(0);
            pdtProduct.setWarnStock(pdtProductSaveView.getWarnStock());
            pdtProduct.setNormAttr(String.valueOf(pdtProductSaveView.getAttr().stream().map(o -> {
                if (o == null)
                    return "-1";
                return String.valueOf(o);
            }).collect(Collectors.joining(","))));
            pdtProduct.setColorAttr(String.valueOf(pdtProductSaveView.getSpecColor().stream().map(o -> {
                return String.valueOf(o);
            }).collect(Collectors.joining(","))));
            pdtProduct.setSizeAttr(String.valueOf(pdtProductSaveView.getSpecSize().stream().map(o -> {
                return String.valueOf(o);
            }).collect(Collectors.joining(","))));
            pdtProduct.setState(pdtProductSaveView.isState() ? Sys.OYn.YES.getLine().getKey() : Sys.OYn.NO.getLine().getKey());
            pdtProduct.setSoldInTime(pdtProductSaveView.isSoldInStatus() ? Sys.OYn.YES.getLine().getKey() : Sys.OYn.NO.getLine().getKey());
            if (pdtProductSaveView.isSoldInStatus()) {
                if (pdtProductSaveView.getSoldInTime() == null) {
                    return;
                }
                pdtProduct.setSoldTimeB(pdtProductSaveView.getSoldInTime().get(0));
                pdtProduct.setSoldTimeE(pdtProductSaveView.getSoldInTime().get(1));
            }
            pdtProduct.setProductType((byte) 0);
            pdtProduct.setIsNew((byte) 1);
            pdtProduct.setIsIndex((byte) 1);
            pdtProduct.setState((byte) 1);
            pdtProduct.setIsHot((byte) 1);
            pdtProduct.setFavoriteCount(0);
            pdtProduct.setIsFreeShipping(pdtProductSaveView.isFreeShipping() ? Sys.OYn.YES.getLine().getKey() : Sys.OYn.NO.getLine().getKey());
            pdtProduct.setWidth(BigDecimal.valueOf(pdtProductSaveView.getWidth()));
            pdtProduct.setHeight(BigDecimal.valueOf(pdtProductSaveView.getHeight()));
            pdtProduct.setLength(BigDecimal.valueOf(pdtProductSaveView.getLength()));
            pdtProduct.setBriefDescription(pdtProductSaveView.getBriefDescription());
            pdtProduct.setDescription(objectMapper.writeValueAsString(pdtProductSaveView.getDescription()));
            List<PdtSpec> list = new ArrayList<>();
            //            pdtProduct.setStock(pdtProductSaveView.getStock());
            pdtProduct.setPicture(String.join(",", pdtProductSaveView.getPdtPics().values()));
            pdtProduct.setWeight(BigDecimal.valueOf(pdtProductSaveView.getWeight()));
            int countStock = 0;
            boolean isAddStock = true;
            for (PdtProductSpecSaveView specSaveView : pdtProductSaveView.getSpec()) {
                PdtSpec spec = new PdtSpec();
                spec.setPkey(specSaveView.getId());
                if (pdtProduct.getPkey() > 0)
                    spec.setProduct(pdtProduct.getPkey());
                spec.setColor(specSaveView.getColor());
                spec.setSize(specSaveView.getSize());
                spec.setKeyName(specSaveView.getName());
                spec.setSku(specSaveView.getSku() != null && specSaveView.getSku().length() > 0 ? specSaveView.getSku() : null);
                //规格库存未0的情况下..就当未填写 设置特殊值
                spec.setPrice(specSaveView.getPrice() != null ? BigDecimal.valueOf(specSaveView.getPrice()) : BigDecimal.valueOf(-1));
                spec.setWeight(BigDecimal.valueOf(specSaveView.getWeight() != null ? specSaveView.getWeight() : -1));
                if (specSaveView.getPic() != null)
                    spec.setPics(String.join(",", specSaveView.getPic().values()));
                spec.setMarkup(BigDecimal.valueOf(0));
                spec.setDeleted(Sys.OYn.NO.getLine().getKey());
                if (specSaveView.getStock() != null && specSaveView.getStock() > 0) {
                    spec.setStoreCount(specSaveView.getStock());
                    if (isAddStock) {
                        countStock += specSaveView.getStock();
                    }
                } else {
                    spec.setStoreCount(-1);
                    isAddStock = false;
                }
                list.add(spec);
            }

            if (isAddStock) {
                pdtProduct.setStock(countStock);
            } else {
//                pdtProduct.setStock(pdtProductSaveView.getStock());
            }
            if (pdtProduct.getPkey() < 0) {
                PdtProductDAO.ExcelLoad publishDao = new PdtProductDAO.ExcelLoad();
                publishDao.setB(pdtProduct);
                publishDao.setLines(list);
                publishDao.commit();
                DbPool.getInstance().getConn().commit();
            } else {
                PdtProductDAO.Upd2 upd2 = new PdtProductDAO.Upd2();
                upd2.setB(pdtProduct);
                upd2.setLines(list);
                upd2.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exp e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getsize(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("35")) return 556;
        if (name.equalsIgnoreCase("36")) return 557;
        if (name.equalsIgnoreCase("37")) return 558;
        if (name.equalsIgnoreCase("38")) return 559;
        if (name.equalsIgnoreCase("39")) return 560;
        if (name.equalsIgnoreCase("40")) return 561;
        if (name.equalsIgnoreCase("41")) return 562;
        if (name.equalsIgnoreCase("42")) return 563;
        if (name.equalsIgnoreCase("43")) return 564;
        if (name.equalsIgnoreCase("44")) return 565;
        if (name.equalsIgnoreCase("45")) return 566;
        if (name.equalsIgnoreCase("27")) return 548;
        if (name.equalsIgnoreCase("28")) return 549;
        if (name.equalsIgnoreCase("29")) return 550;
        if (name.equalsIgnoreCase("30")) return 551;
        if (name.equalsIgnoreCase("31")) return 552;
        if (name.equalsIgnoreCase("32")) return 553;
        if (name.equalsIgnoreCase("33")) return 554;
        if (name.equalsIgnoreCase("34")) return 555;
        if (name.equalsIgnoreCase("21")) return 542;
        if (name.equalsIgnoreCase("22")) return 543;
        if (name.equalsIgnoreCase("23")) return 544;
        if (name.equalsIgnoreCase("24")) return 545;
        if (name.equalsIgnoreCase("25")) return 546;
        if (name.equalsIgnoreCase("26")) return 547;
        if (name.equalsIgnoreCase("46")) return 568;
        return -1;
    }

    private static List<String> getFile(String Path, int i) {
        List result = new ArrayList();
        for (String s : Paths.get(Path).toFile().list()) {
            if (Integer.parseInt(s.substring(0, s.indexOf(".")).split("-")[0]) == i) {
                result.add(s);
            }
        }
        return result;
    }

    private static int gettg(String s) {
        String name = s.trim().replace(" ", "");

        return -1;
    }

    private static int getta(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("纯色")) return 156;
        if (name.equalsIgnoreCase("純色")) return 156;
        if (name.equalsIgnoreCase("拼色")) return 158;

        return -1;
    }

    private static int getbhfs(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("系带")) return 141;
        if (name.equalsIgnoreCase("繫帶")) return 141;
        if (name.equalsIgnoreCase("拉链")) return 140;
        if (name.equalsIgnoreCase("拉鏈")) return 140;
        if (name.equalsIgnoreCase("套脚")) return 143;
        if (name.equalsIgnoreCase("套腳")) return 143;
        if (name.equalsIgnoreCase("搭扣")) return 144;
        if (name.equalsIgnoreCase("魔术贴")) return 142;
        if (name.equalsIgnoreCase("魔術貼")) return 142;
        if (name.equalsIgnoreCase("鬆緊帶")) return 145;
        return -1;
    }

    private static int getgg(String s) {
        String name = s.trim().replace(" ", "");
        return -1;
    }

    private static int getcq(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("春夏")) return 542;
        if (name.equalsIgnoreCase("秋冬")) return 265;
        if (name.equalsIgnoreCase("秋冬季")) return 265;
        if (name.equalsIgnoreCase("春秋")) return 645;
        return -1;
    }

    private static int getfglx(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("休闲")) return 574;
        if (name.equalsIgnoreCase("休閒")) return 574;
        if (name.equalsIgnoreCase("运动")) return 564;
        if (name.equalsIgnoreCase("商務")) return 562;
        if (name.equalsIgnoreCase("商务")) return 562;
        if (name.equalsIgnoreCase("韩版")) return 682;
        return -1;
    }

    private static int getlxys(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("素面")) return 631;
        if (name.equalsIgnoreCase("饰扣")) return 351;
        if (name.equalsIgnoreCase("图腾")) return 165;
        if (name.equalsIgnoreCase("皮草装饰")) return 161;
        if (name.equalsIgnoreCase("皮草裝飾")) return 161;
        if (name.equalsIgnoreCase("鉚釘")) return 166;
        if (name.equalsIgnoreCase("柳钉")) return 166;
        return -1;
    }

    private static int getgn(String s) {
        String name = s.trim().replace(" ", "");
        if (name.equalsIgnoreCase("增高")) return 182;
        if (name.equalsIgnoreCase("耐磨")) return 180;
        if (name.equalsIgnoreCase("透气")) return 176;
        if (name.equalsIgnoreCase("轻质")) return 177;
        if (name.equalsIgnoreCase("輕質")) return 177;
        if (name.equalsIgnoreCase("保暖")) return 179;
        return -1;

    }

    static boolean isBlank(Object o) {
        return o != null && String.valueOf(o).length() > 0;
    }

    public static int getColor(String colorName) {
        String name = colorName.trim().replace(" ", "");
        if (name.equalsIgnoreCase("白色")) return 241;
        if (name.equalsIgnoreCase("灰色")) return 274;
        if (name.equalsIgnoreCase("米色")) return 281;
        if (name.equalsIgnoreCase("黑色")) return 240;
        if (name.equalsIgnoreCase("红色")) return 572;
        if (name.equalsIgnoreCase("杏色")) return 277;
        if (name.equalsIgnoreCase("蓝绿")) return 617;
        if (name.equalsIgnoreCase("七彩")) return 610;
        if (name.equalsIgnoreCase("七彩色")) return 610;
//        if (name.equalsIgnoreCase("彩色")) return 610;
        if (name.equalsIgnoreCase("粉色")) return 612;
        if (name.equalsIgnoreCase("浅灰色")) return 599;
        if (name.equalsIgnoreCase("浅灰")) return 599;
        if (name.equalsIgnoreCase("浅黄色")) return 618;
        if (name.equalsIgnoreCase("深灰色")) return 336;
        if (name.equalsIgnoreCase("蓝色")) return 219;
        if (name.equalsIgnoreCase("迷彩色")) return 611;
        if (name.equalsIgnoreCase("彩色")) return 611;
        if (name.equalsIgnoreCase("迷彩")) return 611;
        if (name.equalsIgnoreCase("白粉色")) return 612;
        if (name.equalsIgnoreCase("灰白色")) return 628;
        if (name.equalsIgnoreCase("银色")) return 585;
        if (name.equalsIgnoreCase("绿色")) return 236;
        if (name.equalsIgnoreCase("深紫色")) return 269;
        if (name.equalsIgnoreCase("淡黄色")) return 618;
        if (name.equalsIgnoreCase("浅紫色")) return 595;
        if (name.equalsIgnoreCase("浅粉色")) return 619;
        if (name.equalsIgnoreCase("黄色")) return 607;
        if (name.equalsIgnoreCase("浅蓝色")) return 593;
        if (name.equalsIgnoreCase("浅蓝")) return 593;
        if (name.equalsIgnoreCase("深蓝色")) return 594;
        if (name.equalsIgnoreCase("深蓝")) return 594;
        if (name.equalsIgnoreCase("浅棕色")) return 598;
        if (name.equalsIgnoreCase("紫色")) return 239;
        if (name.equalsIgnoreCase("海军蓝")) return 613;
        if (name.equalsIgnoreCase("淡粉色")) return 427;
        if (name.equalsIgnoreCase("花色")) return 586;
        if (name.equalsIgnoreCase("驼色")) return 283;
        if (name.equalsIgnoreCase("淡蓝色")) return 620;
        if (name.equalsIgnoreCase("淡蓝")) return 620;
        if (name.equalsIgnoreCase("天蓝色")) return 621;
        if (name.equalsIgnoreCase("棕色")) return 255;
        if (name.equalsIgnoreCase("红黑")) return 622;
        if (name.equalsIgnoreCase("淡灰色")) return 623;
        if (name.equalsIgnoreCase("蓝白")) return 460;
        if (name.equalsIgnoreCase("褐色")) return 624;
        if (name.equalsIgnoreCase("蓝白色")) return 460;
        if (name.equalsIgnoreCase("深褐色")) return 615;
        if (name.equalsIgnoreCase("金色")) return 520;
        if (name.equalsIgnoreCase("藏青色")) return 614;
        if (name.equalsIgnoreCase("浅卡其色")) return 602;
        if (name.equalsIgnoreCase("卡其")) return 602;
        if (name.equalsIgnoreCase("酒红色")) return 588;
        if (name.equalsIgnoreCase("墨绿色")) return 616;
        if (name.equalsIgnoreCase("红蓝")) return 625;
        if (name.equalsIgnoreCase("橘黄色")) return 626;
        if (name.equalsIgnoreCase("灰粉")) return 627;
        return -1;
    }

    public static int getXdCz(String name) {
        String string = name.trim().replace(" ", "");
        if (string.equalsIgnoreCase("橡胶")) return 321;
        if (string.equalsIgnoreCase("橡胶发泡")) return 109;
        if (string.equalsIgnoreCase("复合底")) return 333;
        if (string.equalsIgnoreCase("EVA")) return 103;
        if (string.equalsIgnoreCase("TPR")) return 107;
        return -1;
    }

    public static int getxmcl(String name) {
        String string = name.trim().replace(" ", "");
        if (string.equalsIgnoreCase("仿牛绒")) return 623;
        if (string.equalsIgnoreCase("牛反绒")) return 623;
        if (string.equalsIgnoreCase("帆布")) return 293;
        if (string.equalsIgnoreCase("PU")) return 66;
        if (string.equalsIgnoreCase("pu")) return 66;
        if (string.equalsIgnoreCase("绒布")) return 275;
        if (string.equalsIgnoreCase("网布")) return 543;
        if (string.equalsIgnoreCase("亮片布")) return 69;
        if (string.equalsIgnoreCase("布")) return 660;
        if (string.equalsIgnoreCase("格利特")) return 657;
        if (string.equalsIgnoreCase("牛仔布")) return 412;
        if (string.equalsIgnoreCase("头层牛皮")) return 71;
        if (string.equalsIgnoreCase("纺织布")) return 710;
        if (string.equalsIgnoreCase("网纱")) return 626;
        if (string.equalsIgnoreCase("棉")) return 714;
        if (string.equalsIgnoreCase("网面")) return 725;
        if (string.equalsIgnoreCase("绒面")) return 721;
        return -1;
    }

    public static int getnlcl(String name) {
        String string = name.trim().replace(" ", "");
        if (string.equalsIgnoreCase("网布")) return 287;
        if (string.equalsIgnoreCase("布")) return 621;
        if (string.equalsIgnoreCase("网纱")) return 661;
        if (string.equalsIgnoreCase("无")) return 0;
        if (string.equalsIgnoreCase("帆布")) return 319;
        if (string.equalsIgnoreCase("PU")) return 256;
        if (string.equalsIgnoreCase("纺织")) return 279;
        if (string.equalsIgnoreCase("无内里")) return 0;
        if (string.equalsIgnoreCase("棉")) return 666;
        if (string.equalsIgnoreCase("超纤")) return 733;
        if (string.equalsIgnoreCase("头层牛皮")) return 739;
        if (string.equalsIgnoreCase("绒布")) return 327;
        if (string.equalsIgnoreCase("纺织布")) return 745;
        return -1;
    }

    public static int getxdcl(String name) {
        String string = name.trim().replace(" ", "");
        if (string.equalsIgnoreCase("布")) return 627;
        if (string.equalsIgnoreCase("Pu")) return 124;
        if (string.equalsIgnoreCase("帆布")) return 130;
        return -1;
    }

    public static int getxtcl(String name) {
        String string = name.trim().replace(" ", "");
        if (string.equalsIgnoreCase("圆头")) return 136;
        if (string.equalsIgnoreCase("扁头")) return 139;
        if (string.equalsIgnoreCase("鱼嘴")) return 348;
        return -1;
    }

    public static int getGd(String name) {
        String string = name.trim().replace(" ", "");
        if (string.equalsIgnoreCase("平底")) return 151;
        if (string.equalsIgnoreCase("厚底")) return 153;
        return -1;
    }
}
