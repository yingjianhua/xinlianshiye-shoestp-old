package irille.Entity.SVS.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public enum SVSGradeType implements IEnumOpt {
	SILVER(1, "银"), GOLD(2, "金"), DIAMONDS(3, "钻石"), NotAvailable(0, "暂无等级"),;
	public static final String NAME = "SVS商家等级类型";
	public static final SVSGradeType DEFAULT = NotAvailable;
	private EnumLine _line;

	private SVSGradeType(int key, String name) {
	        _line = new EnumLine(this, key, name);
	    }

	public EnumLine getLine() {
		return _line;
	}

}
