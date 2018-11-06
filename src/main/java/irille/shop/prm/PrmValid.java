package irille.shop.prm;

import java.util.Date;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.validate.ValidForm;

public class PrmValid extends ValidForm{
	
	private static final Log LOG = new Log(PrmValid.class);

	public PrmValid(Bean bean) {
		super(bean);
	}
	
	public enum errMsgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		later("【{0}】不能晚于【{1}】"),
		early("【{0}】不能早于当前时间"),
        ;
        private String _msg;
        private errMsgs(String msg) {_msg = msg;}
        public String getMsg() {return _msg;}
    } //@formatter:on
	
	public void validTime(Date startTime,Date endTime,Object... param){
		if(startTime.getTime() <= System.currentTimeMillis()){
			throw LOG.err(errMsgs.early,param);
		}
		if(startTime.getTime() > endTime.getTime()){
			throw LOG.err(errMsgs.later,param);
		}
	}
}
