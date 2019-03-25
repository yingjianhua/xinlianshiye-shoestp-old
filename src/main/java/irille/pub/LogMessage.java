package irille.pub;

import irille.pub.PubInfs.IMsg;

public class LogMessage extends Log {

  public LogMessage(Class clazz) {
    super(clazz);
  }

  public Exp errTran(IMsg msg, Object... paras) {
    return super.err(msg.name(), msg.name() + test(paras));
  }

  public Exp errTran(String code, String message, Object... paras) {
    String name = code + test(paras);
    return super.err(code, name);
  }

  public String test(Object[] obj) {
    String str = "";
    for (int i = 0; i < obj.length; i++) {
      str += "##" + obj[i].toString();
    }
    return str;
  }
}
