package irille.Entity.O2O.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * -主要销售市场
 *
 * @author xinlian
 */
public enum O2O_Marketing implements IEnumOpt {
  north_ameriva(1, "北美洲"),
  south_ameriva(2, "南美洲"),
  east_asia(3, "东亚"),
  central_asia(4, "中亚"),
  south_asia(5, "南亚"),
  north_asia(6, "北亚"),
  south_africa(7, "南非"),
  eastem_europe(8, "东欧"),
  westem_europe(9, "西欧"),
  northem_europe(10, "北欧"),
  central_europe(11, "中欧"),
  austealia(12, "澳洲"),
  other(13, "其他");

  public static final String NAME = "销售市场";
  public static final O2O_Marketing DEFAULT = other;
  private EnumLine _line;

  private O2O_Marketing(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
