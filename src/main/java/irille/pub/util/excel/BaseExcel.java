package irille.pub.util.excel;

import irille.pub.util.excel.Entity.AbsRichTextString;
import irille.pub.util.excel.Entity.CellData;
import irille.pub.util.excel.Entity.ExcelSettings;
import irille.pub.util.excel.Other.DateCellStyle;
import irille.pub.util.excel.Units.ExcelUnits;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class BaseExcel {
    private Workbook workbook;
    private Sheet activeSheet;
    private String filePath;
    private String suffix = "xlsx";
    private int activeSheetId;

    public BaseExcel() {
        workbook = new XSSFWorkbook();
        activeSheet = workbook.createSheet("Sheet1");
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public BaseExcel(String filePath) {
        try {
            this.filePath = filePath;
            FileInputStream fis = new FileInputStream(filePath);
            if (ExcelUnits.getSuffix(filePath).equalsIgnoreCase(suffix)) {
                this.workbook = new XSSFWorkbook(fis);
            } else {
                this.workbook = new HSSFWorkbook(fis);
            }
            if (workbook.getNumberOfSheets() > 0) {
                activeSheetId = 0;
                activeSheet = workbook.getSheetAt(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("初始化失败");
            System.err.println(filePath);
        }
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }


    /***
     * 设置版本，默认为07版本Excel格式（兼容性较好）
     * @param excelVersion
     */
    public void setExcelVersion(ExcelSettings excelVersion) {
        if (ExcelSettings.VERSION_03 == excelVersion) {
            workbook = new HSSFWorkbook();
            suffix = ".xls";
        } else {
            workbook = new XSSFWorkbook();
        }
    }

    /***
     * 获取激活列列名
     * @return
     */
    public String getActiveSheetName() {
        return activeSheet.getSheetName();
    }


    /***
     *
     * @param filePath  读取路径
     * @param sheetName 设置默认sheet名称 没有按照这个名称生成
     */
    public BaseExcel(String filePath, String sheetName) {
        try {
            this.filePath = filePath;
            FileInputStream fis = new FileInputStream(filePath);
            this.workbook = new HSSFWorkbook(fis);
            if (!(workbook.getNumberOfSheets() > 0)) {
                activeSheet = workbook.createSheet(sheetName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("初始化失败");
            System.err.println(filePath);
        }
    }

    /***
     *创建Shett
     * @param name
     */
    public void createSheet(String name) {
        Sheet sheet = workbook.getSheet(name);
        if (sheet != null)
            return;
        workbook.createSheet(name);
        selectSheet(name);
    }

    /***
     * 激活指定列
     * @param name
     */
    public void selectSheet(String name) {
        activeSheetId = workbook.getSheetIndex(name);
        selectSheet(activeSheetId);
    }

    /***
     * 获取激活列名
     * @return
     */
    public Sheet getActiveSheets() {
        return workbook.getSheetAt(getActiveSheet());
    }

    /***
     * 获取激活的Sheet
     * @return
     */
    public int getActiveSheet() {
        return activeSheetId;
    }


    /***
     * 根据ID激活指定列
     * @param id
     */
    public void selectSheet(int id) {
        workbook.setActiveSheet(id);
        activeSheet = workbook.getSheetAt(id);
    }


    private Row getRow(int row) {
        Row result = null;
        result = activeSheet.getRow(row);
        return result != null ? result : activeSheet.createRow(row);
    }

    private Cell getCell(Row row, int cell) {
        Cell result = null;
        result = row.getCell(cell);
        return result != null ? result : row.createCell(cell);
    }

    /***
     * 读取指定列
     * @param x
     * @param y
     * @return
     */
    public Object readValue(int x, int y) {
        Cell cell = getCell(getRow(y), x);
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_BLANK:
                return "";
        }
        return null;
    }

    /***
     * 读取指定列
     * @param xy
     * @return
     */
    public Object readValue(String xy) {
        int[] ints = ExcelUnits.StringToCellXy(xy);
        return readValue(ints[0], ints[1]);
    }

    /***
     * 读取指定序号的图片
     * @param i
     * @return
     */
    public PictureData getPic(int i) {
        if (workbook.getAllPictures() != null) {
            return workbook.getAllPictures().get(i);
        }
        return null;
    }

    private void witerCellData(CellData cellData) {
        Row row = getRow(cellData.getRow());
        Cell cell = getCell(row, cellData.getCol());
        Object data = cellData.getData();
        do {
            if (data instanceof Double) {
                cell.setCellValue((Double) data);
                break;
            }
            if (data instanceof Date) {
                cell.setCellValue((Date) data);
                break;
            }
            if (data instanceof Calendar) {
                cell.setCellValue((Calendar) data);
                break;
            }
            if (data instanceof RichTextString) {
                cell.setCellValue((RichTextString) data);
                break;
            }
            if (data instanceof AbsRichTextString) {
                AbsRichTextString richTextString = (AbsRichTextString) data;
                cell.setCellValue(richTextString.get(workbook));
            }
            if (data == null) {
                cell.setCellValue("");
                break;
            }
            cell.setCellValue(String.valueOf(data));
        } while (false);
        if (cellData.getCellStyle() != null) {
            cell.setCellStyle(cellData.getCellStyle().get(workbook));
        }
    }

    /***
     * 写入数据
     * @param rowData
     */
    public void witerCellData(List<CellData> rowData) {
        if (activeSheet == null) {
            activeSheet = workbook.getSheetAt(activeSheetId);
        }
        for (CellData rowDatum : rowData) {
            witerCellData(rowDatum);
        }
    }

    /***
     * 写入数据
     * @param rowData
     */
    public void witerCellData(CellData... rowData) {
        if (activeSheet == null) {
            activeSheet = workbook.getSheetAt(activeSheetId);
        }
        for (CellData rowDatum : rowData) {
            witerCellData(rowDatum);
        }
    }

    /***
     * 从第几行,第几列开始写入数据
     * @param begincol
     * @param row
     * @param rowData
     */
    public void witerColData(int begincol, int row, Object... rowData) {
        if (activeSheet == null) {
            activeSheet = workbook.getSheetAt(activeSheetId);
        }
        if (rowData.length < 1) {
            System.err.println("无写入数据");
            return;
        }
        int t;
        for (t = 0; t < rowData.length; t++) {
            Object o = rowData[t];
            CellData cellData = new CellData((t + begincol), row, o);
            if (o instanceof Date || o instanceof java.sql.Date || o instanceof Timestamp) {
                cellData.setCellStyle(DateCellStyle.build("YYYY-MM-dd"));
            }
            witerCellData(cellData);
        }
    }


    public Map<String, PictureData> getPic() {
        Map<String, PictureData> sheetIndexPicMap = new HashMap ();
        if (workbook instanceof XSSFWorkbook) {
            for (POIXMLDocumentPart dr : ((XSSFSheet) activeSheet).getRelations()) {
                if (dr instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) dr;
                    List<XSSFShape> shapes = drawing.getShapes();
                    for (XSSFShape shape : shapes) {
                        XSSFPicture pic = (XSSFPicture) shape;
                        XSSFClientAnchor anchor = pic.getPreferredSize();
                        CTMarker ctMarker = anchor.getFrom();
                        String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
                        sheetIndexPicMap.put(picIndex, pic.getPictureData());
                    }
                }
            }
        } else {
            List<HSSFPictureData> pictures = ((HSSFWorkbook) workbook).getAllPictures();
            if (pictures.size() != 0) {
                for (HSSFShape shape : ((HSSFSheet) activeSheet).getDrawingPatriarch().getChildren()) {
                    HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                    if (shape instanceof HSSFPicture) {
                        HSSFPicture pic = (HSSFPicture) shape;
                        int pictureIndex = pic.getPictureIndex() - 1;
                        HSSFPictureData picData = pictures.get(pictureIndex);
                        String picIndex = String.valueOf(anchor.getRow1()) + "_"
                                + String.valueOf(anchor.getCol1());
                        sheetIndexPicMap.put(picIndex, picData);
                    }
                }
                return sheetIndexPicMap;
            } else {
                return null;
            }
        }

        return sheetIndexPicMap;
    }

    public void saveTo(String filePath) {
        saveTo(new File(filePath + suffix));
    }

    public void saveTo(File file) {
        try {
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("====》文件写入失败");
            System.err.println(file);
        }
    }

    public void saveTo(String filePath, String fileName) {
        saveTo(new File(filePath + fileName + suffix));
    }

    public ByteArrayOutputStream saveToOutputStream() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }

    /***
     * 保存
     */
    public void save() throws Exception {
        if (filePath != null) {
            saveTo(new File(filePath));
            return;
        }
        throw new Exception("未指定保存文件路径及名称，请使用saveTO方法保存");
    }
}
