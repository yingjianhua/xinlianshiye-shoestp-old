package irille.Entity.O2O.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * -展会活动国家
 * @author xinlian
 */
public enum O2O_ExhibitionCountry implements IEnumOpt{
	hungary(1,"匈牙利"),
	italy(2,"意大利"),
	france(3,"法国"),
	russia(4,"俄罗斯"),
	romania(5,"罗马尼亚"),
	poland(6,"波兰"),
	other(7,"其他");
	
	public static final String NAME = "买家类型";
    public static final O2O_ExhibitionCountry DEFAULT = other;
    private EnumLine _line;

    private O2O_ExhibitionCountry(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
