package irille.shop.plt;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidForm.ErrMsgs;
import irille.shop.usr.UsrValid.errMsgs;

public class ValidPayandRate extends ValidForm {
	private static final Log LOG = new Log(ValidPayandRate.class);
	public enum ErrMsgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
        compareErr("【{0}】不能大于或者等于【{1}】！"),
        numErr("【{0}】不能小于0"),
        strErr("【{0}】不能有数字"),
        ;
        private String _msg;
        private ErrMsgs(String msg) {_msg = msg;}
        public String getMsg() {return _msg;}
    } //@formatter:on

	public ValidPayandRate(Bean bean) {
		super(bean);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 判断两个值大小
	 * @param min
	 * @param max
	 * @param paras
	 */
	public void  compareNum(BigDecimal min,BigDecimal max,Object... paras){
	for (Object object : paras) {
		if (min.compareTo(max) >= 0) 
			throw LOG.err(ErrMsgs.compareErr,paras);
	}	
	}
	/**
	 * 判断该值不小于0
	 * @param value
	 * @param paras
	 */
	public void  compareZero(BigDecimal value,Object... paras){
		for (Object object : paras) {
			if(value.compareTo(BigDecimal.ZERO)<0)
			throw LOG.err(ErrMsgs.numErr,paras);
		}	
	}
	
	public void  isNumber(String str,Object... paras){
		String reg=".*\\d+.";
		boolean flag=reg(reg,str);
		if (flag)
		throw LOG.err(ErrMsgs.strErr,paras);	
	}
	public void validMail(String mail , Object... param){
		String reg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		if (null!=mail&&!"".equals(mail)) {
		boolean flag=reg(reg,mail);
		if(flag==false)
		throw LOG.err(errMsgs.wrongErr,param);	
		}
		
	}
	private static boolean reg(String reg , String str){
		Pattern pattern = Pattern.compile(reg);
		Matcher m = pattern.matcher(str);
		return m.matches();
	}
}
