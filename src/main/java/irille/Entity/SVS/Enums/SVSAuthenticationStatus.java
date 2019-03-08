package irille.Entity.SVS.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public enum SVSAuthenticationStatus implements IEnumOpt {
	SUCCESS(1, "认证成功"), FAIL(2, "认证失败"), ToBeAudited(0, "待审核"),NoApplication(-1,"未申请"),;
	public static final String NAME = "认证结果类型";
	public static final SVSAuthenticationStatus DEFAULT = NoApplication;
	private EnumLine _line;

	private SVSAuthenticationStatus(int key, String name) {
	        _line = new EnumLine(this, key, name);
	    }

	public EnumLine getLine() {
		return _line;
	}

}
