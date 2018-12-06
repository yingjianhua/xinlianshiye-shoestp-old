import irille.pub.util.excel.BaseExcel;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 18:42
 */
public class excelWrite {
    public static void main(String[] args) {
        BaseExcel baseExcel = new BaseExcel("S:\\鞋贸港下单表格20181205最新修改.xlsx");
        baseExcel.addRow(baseExcel.getActiveSheets(), 9).createCell(1).setCellValue("你好");
        baseExcel.saveTo("S:\\13132");
    }
}
