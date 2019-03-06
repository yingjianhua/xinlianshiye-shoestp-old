package irille.Entity.O2O.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * -买家类型
 * @author xinlian
 */
public enum O2O_BuyerType implements IEnumOpt{
	dealer(1,"经销商"),
	distributor(2,"分销商"),
	wholesaler(3,"批发商"),
	other(4,"其他");
	
	public static final String NAME = "买家类型";
    public static final O2O_BuyerType DEFAULT = other;
    private EnumLine _line;

    private O2O_BuyerType(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
