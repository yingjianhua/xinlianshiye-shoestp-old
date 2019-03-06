package irille.Entity.O2O.Enums;

import irille.core.sys.Sys.OSex;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public enum O2O_Sex implements IEnumOpt{

	men(1,"男"),
	women(2,"女");
	
	public static final String NAME="性别";
	public static final O2O_Sex DEFAULT = men; // 定义缺省值
	private EnumLine _line;
	private O2O_Sex(int key, String name) {_line=new EnumLine(this,key,name);	}
	public EnumLine getLine(){return _line;	}
}
