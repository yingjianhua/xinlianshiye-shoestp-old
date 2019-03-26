import irille.pub.util.excel.BaseExcel;
import irille.pub.util.excel.Entity.ToZipOutputStream;
import org.apache.poi.ss.usermodel.Cell;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 18:42
 */
public class excelWrite {
    public static void main(String[] args) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(Paths.get("s:/1234.zip").toFile());
        ) {
            List list = new ArrayList();
            for (int i = 0; i < 5; i++) {
                BaseExcel baseExcel = new BaseExcel("S:\\鞋贸港下单表格20181205最新修改.xlsx");
//新增新的一行
                Cell cell = baseExcel.addRow(baseExcel.getActiveSheets(), 9).createCell(2);
//                添加网络图片
                baseExcel.addPic(1, 9, 2, 10, "https://image.shoestp.com/public/upload/cnt/ad/1d8c6e6c0ad9060d75e275b33c80d373.jpg");
                cell.setCellValue("你好");
                cell = baseExcel.addRow(baseExcel.getActiveSheets(), 10).createCell(2);
                baseExcel.setRowHeight(10, (short) 50);
//                添加网络图片
                baseExcel.addPic(1, 10, 2, 11, "https://image.shoestp.com/public/upload/cnt/ad/1d8c6e6c0ad9060d75e275b33c80d373.jpg");
                cell.setCellValue("你好");
//设置边框
                baseExcel.setCellBorderStyle(1, 9, 7, 11);
                list.add(ToZipOutputStream.from("订单号" + i, baseExcel.getSuffix(), baseExcel.saveToOutputStream()));
            }
//输出zip流
            OutputStream outputStream = BaseExcel.toZipOutputStream(list);
            fileOutputStream.write(((ByteArrayOutputStream) outputStream).toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
