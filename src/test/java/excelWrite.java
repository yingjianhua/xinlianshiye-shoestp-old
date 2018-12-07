import irille.pub.util.excel.BaseExcel;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 18:42
 */
public class excelWrite {
    public static void main(String[] args) throws IOException {
        BaseExcel baseExcel = new BaseExcel("S:\\鞋贸港下单表格20181205最新修改.xlsx");
        Cell cell=baseExcel.addRow(baseExcel.getActiveSheets(), 9).createCell(2);
        baseExcel.setCellBorderStyle(cell);
        cell.setCellValue("你好");
        baseExcel.addPic(1, 9, 2, 10, "https://image.shoestp.com/public/upload/cnt/ad/1d8c6e6c0ad9060d75e275b33c80d373.jpg");
        baseExcel.saveTo("S:\\13132");
    }
}
