package irille.Dao.EO;

import irille.pub.LogMessage;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.excel.BaseExcel;
import irille.pub.util.excel.Entity.ToZipOutputStream;
import irille.shop.easy.EasyOdr;
import irille.shop.easy.EasyOdr.T;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrPurchase;
import irille.view.Easy.EasyodrView;
import irille.view.Easy.EolineView;
import irille.view.Page;
import irille.view.odr.OrderSearchView;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EasyOdrDao {
    private static final LogMessage LOG = new LogMessage(EasyOdrDao.class);
    @Inject
    private EasyOdrDao.Ins ins;

    @Inject
    private EasyOdrLineDao easyOdrLineDao;

    /**
     * @Description: 导出新订单详情表格
     * @date 2018/12/7 13:57
     * @anthor wilson zhang
     */
    public ByteArrayOutputStream oneexport(Integer eastOdrId, Integer supId) throws Exception {
        EasyodrView easyodrView = getload(eastOdrId);
        easyodrView.setList(easyOdrLineDao.getListOdrLine(eastOdrId, PltConfigDAO.supplierLanguage(supId)));
        BaseExcel baseExcel = new BaseExcel(this.getClass().getResource("/Bin/鞋贸港下单表格20181205最新修改.xlsx").getPath());
        Workbook wb = baseExcel.getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));
        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle1.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle1.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle1.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle1.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle1.setBorderTop(CellStyle.BORDER_THIN);
        Sheet sheet = wb.getSheetAt(0);
        sheet.getRow(4).getCell(2).setCellValue(easyodrView.getCompany());
        Cell cell = sheet.getRow(5).getCell(2);
        cell.setCellValue(easyodrView.getTime());
        cell.setCellStyle(cellStyle);
        Cell cell1 = sheet.getRow(5).getCell(4);
        cell1.setCellValue(easyodrView.getName());
        cell1.setCellStyle(cellStyle1);
        Cell cell2 = sheet.getRow(5).getCell(6);
        cell2.setCellValue(easyodrView.getPhone());
        cell2.setCellStyle(cellStyle1);
        Cell cell3 = sheet.getRow(6).getCell(2);
        cell3.setCellValue(easyodrView.getEmail());
        cell3.setCellStyle(cellStyle1);
        Cell cell4 = sheet.getRow(6).getCell(4);
        cell4.setCellValue(easyodrView.getAddress());
        cell4.setCellStyle(cellStyle1);
        int start = 9;
        int end = 10;
        List<EolineView> eolist = easyodrView.getList();
        for (int i = 0; i < eolist.size(); i++) {
            if (eolist.get(i).getImage() != null && eolist.get(i).getImage().length() > 0) {
                String[] strings = eolist.get(i).getImage().split(",");
                if (strings.length > 0) {
                    baseExcel.addPic(1, start, 2, end, "https://image.shoestp.com" + strings[0]);
                } else {
                    baseExcel.addPic(1, start, 2, end, "https://image.shoestp.com" + eolist.get(i).getImage());
                }
            }
            Cell cellname = sheet.getRow(start).getCell(2);
            cellname.setCellValue(eolist.get(i).getProductname());
            cellname.setCellStyle(cellStyle1);
            Cell cellcolor = sheet.getRow(start).getCell(3);
            cellcolor.setCellValue(eolist.get(i).getColor());
            cellcolor.setCellStyle(cellStyle1);
            Cell cellsize = sheet.getRow(start).getCell(4);
            cellsize.setCellValue(eolist.get(i).getSize());
            cellsize.setCellStyle(cellStyle1);
            Cell cellnum = sheet.getRow(start).getCell(5);
            cellnum.setCellValue(eolist.get(i).getNum());
            cellnum.setCellStyle(cellStyle1);
            Cell cellremak = sheet.getRow(start).getCell(6);
            if (eolist.get(i).getRemarks() != null) {
                cellremak.setCellValue(eolist.get(i).getRemarks());
            }
            cellremak.setCellStyle(cellStyle1);
            start += 1;
            end += 1;
        }
        Cell cellcount = baseExcel.addRow(baseExcel.getActiveSheets(), start).createCell(1);
        cellcount.setCellValue("合计：" + easyodrView.getCount());
        cellcount.setCellStyle(cellStyle1);
        return baseExcel.saveToOutputStream();
    }

    /**
     * @Description: 导出多张新订单详情表格
     * @date 2018/12/10 16:42
     * @anthor wilson zhang
     */
    public ByteArrayOutputStream manyexport(String eastOdrIds, Integer supId) {
        List list = null;
        try {
            list = new ArrayList();
            for (String s : eastOdrIds.split(",")) {
                ToZipOutputStream toZipOutputStream = new ToZipOutputStream();
                toZipOutputStream.setName("鞋贸港下单表格" + (new Date()).toString() + "_" + s + "最新修改");
                toZipOutputStream.setSuffix("xlsx");
                toZipOutputStream.setOutputStream(oneexport(Integer.valueOf(s), supId));
                list.add(toZipOutputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseExcel.toZipOutputStream(list);
    }

    /**
     * @Description: 根据新订单 id获取 新订单easyodrView DTO
     * @date 2018/12/7 15:00
     * @anthor wilson zhang
     */
    public EasyodrView getload(Integer eastOdrId) throws Exception {
        EasyodrView ev = new EasyodrView();
        SQL sql = new SQL() {{
            SELECT(T.ORDER_NUM, T.ADDRESS, T.COUNYPD, T.NAME, T.TIME, T.PHONE, T.PURCHASE);
            FROM(EasyOdr.class);
            WHERE(T.PKEY, "=?", eastOdrId);
        }};
        Map<String, Object> os = Query.sql(sql).queryMap();
        UsrPurchase up = BeanBase.load(UsrPurchase.class, (Integer) os.get(T.PURCHASE.getFld().getCodeSqlField()));
        ev.setOdrnum((String) os.get(T.ORDER_NUM.getFld().getCodeSqlField()));
        ev.setCompany(up.getCompany());
        ev.setTime((Date) os.get(T.TIME.getFld().getCodeSqlField()));
        ev.setName((String) os.get(T.NAME.getFld().getCodeSqlField()));
        ev.setPhone((String) os.get(T.PHONE.getFld().getCodeSqlField()));
        ev.setEmail(up.getEmail());
        JSONObject js = new JSONObject((String) os.get(T.ADDRESS.getFld().getCodeSqlField()));
        String address = "";
        address += BeanBase.load(PltCountry.class, (Integer) js.get("countryid")).getName(FldLanguage.Language.zh_CN) + ",";
        address += BeanBase.load(PltProvince.class, (Integer) js.get("regionid")).getName(FldLanguage.Language.zh_CN) + ",";
        address += (String) js.get("city") + "," + (String) js.get("address");
        ev.setAddress(address);
        ev.setCount((Integer) os.get(T.COUNYPD.getFld().getCodeSqlField()));
        return ev;
    }


    /**
     * @Description: 商家端显示订购会商品列表
     * @parameter :  1.开始查询条数 2.查询几条 3.产品名称 4.开始时间 5.商家id
     * @date 2018/12/5 15:59
     * @anthor wilson zhang
     */
    public Page sellerlist(Integer start, Integer limit, OrderSearchView osv, Integer supplierid) {
        if (null == start) {
            start = 0;
        }
        if (null == limit) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT(T.PKEY, T.TIME, T.ORDER_NUM, T.ADDRESS, T.COUNYPD, T.NAME, T.PHONE, T.PURCHASE)
                    .FROM(EasyOdr.class);
            if (null != osv) {
                System.out.println(osv.getBeginTime());
                if (null != osv.getBeginTime() && null != osv.getEndTime()) {
                    WHERE(T.TIME, ">?", osv.getBeginTime());
                    WHERE(T.TIME, "<?", osv.getEndTime());
                }
                if (null != osv.getNumber()) {
                    WHERE(T.ORDER_NUM, "like '%" + osv.getNumber() + "%'");
                }
            }
            if (null != supplierid) {
                WHERE(T.SUPPLIER, "=?", supplierid);
            }
        }};
        Integer count = Query.sql(sql).queryCount();
        List<EasyodrView> eslist = Query.sql(sql).queryMaps().stream().map(bean -> new EasyodrView() {{
            setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
            setTime((Date) bean.get(T.TIME.getFld().getCodeSqlField()));
            setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
            setPhone((String) bean.get(T.PHONE.getFld().getCodeSqlField()));
            UsrPurchase up = BeanBase.load(UsrPurchase.class, (Integer) bean.get(T.PURCHASE.getFld().getCodeSqlField()));
            setEmail(up.getEmail());
            setOdrnum((String) bean.get(T.ORDER_NUM.getFld().getCodeSqlField()));
            setAddress((String) bean.get(T.ADDRESS));
            setCount((Integer) bean.get(T.COUNYPD.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());

        return new Page(eslist, start, limit, count);
    }

    public String allOdr(Integer supplier, Date begin, Date end) {
        String list = "";
        SQL sql = new SQL() {{
            SELECT("GROUP_CONCAT(" + T.PKEY + ")");
            FROM(EasyOdr.class);
            if (supplier != null) {
                WHERE(T.SUPPLIER, "=?", supplier);
            }
            if (begin != null) {
                WHERE(T.TIME, ">=?", begin);
            }
            if (end != null) {
                WHERE(T.TIME, "<?", end);
            }
        }};
        list = Query.sql(sql).queryObject(String.class);
        return list;
    }


    public EasyOdr addNewOrder(EasyOdr easyOdr) {
        ins.setB(easyOdr).commit();
        return ins.getB();
    }

    public Integer buildOrderNum(EasyOdr order) {
        //设置订单号
        String timeStamp = Env.getSystemTime().getTime() + "";
        String pkey = String.valueOf(order.getPkey());
        String orderid = timeStamp.substring(0, timeStamp.length() - pkey.length()) + pkey;
        order.setOrderNum(orderid);
        order.upd();
        return order.getPkey();
    }

    public void updateCountry(Integer count) {
        ins.getB().setCounypd(count);
        ins.getB().upd();
    }

    public void removaCat(Integer purchaseid, Integer pdtSpecId) {
        SQL sql = new SQL() {{
            DELETE_FROM(UsrCart.class)
                    .WHERE(UsrCart.T.PURCHASE, "=?", purchaseid)
                    .WHERE(UsrCart.T.SPEC, "=?", pdtSpecId);
        }};
        Query.sql(sql).executeUpdate();
    }

    /**
     * @Description: 生成订单管理
     * @date 2018/12/5 13:44
     * @anthor wilson zhang
     */
    public static class Ins extends IduIns<Ins, EasyOdr> {
        @Override
        public void before() {
            getB().setTime(Env.getTranBeginTime());
            getB().setCounypd(0);
            super.before();
        }
    }


}
