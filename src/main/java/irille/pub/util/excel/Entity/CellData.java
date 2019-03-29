package irille.pub.util.excel.Entity;

import java.util.Calendar;
import java.util.Date;

import irille.pub.util.excel.Other.DateCellStyle;
import irille.pub.util.excel.Units.ExcelUnits;

public class CellData {
  private int col;
  private int row;
  private Object data;
  private AbsCellStyle cellStyle;

  public CellData(int col, int row, Object data) {
    this.col = col;
    this.row = row;

    this.data = data;
  }

  public CellData() {}

  public CellData(int col, int row) {
    this.col = col;
    this.row = row;
  }

  public CellData(String xy, Object value) {
    int[] i = ExcelUnits.StringToCellXy(xy);
    this.col = i[0];
    this.row = i[1];
    setData(value);
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    if (data instanceof Date || data instanceof Calendar) {
      this.setCellStyle(DateCellStyle.build());
    }
    this.data = data;
  }

  public AbsCellStyle getCellStyle() {
    return cellStyle;
  }

  public void setCellStyle(AbsCellStyle cellStyle) {
    this.cellStyle = cellStyle;
  }
}
