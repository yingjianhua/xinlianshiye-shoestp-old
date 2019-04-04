package irille.pub.validate;

import irille.pub.bean.Bean;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.IEnumFld;

/**
 * @description: 正则校验
 * @author: lingjian
 * @create: 2019/3/19 10:35
 */
public class ValidRegex2 extends ValidRegex {
    public ValidRegex2(Bean bean) {
        super(bean);
    }
    public void validRegexMatched(String regex, String msg, IEnumFld... flds) {
        regMarch(true, regex, msg, flds);
    }
    private void regMarch(boolean isMarch, String reg, String msg, IEnumFld... flds) {
        if(fldsNotNull(flds))
            for(IEnumFld fld:flds) {
                if (isMarch){
                    if(!regMarch(reg, getString(fld)))
                        throw new WebMessageException(msg);
                }else{
                    if(regMarch(reg, getString(fld)))
                        throw new WebMessageException(msg);
                }
            }
    }

}
