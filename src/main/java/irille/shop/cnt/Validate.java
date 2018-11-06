package irille.shop.cnt;

import irille.pub.Log;
import irille.pub.PubInfs;
import irille.pub.bean.Bean;
import irille.pub.validate.ValidForm;



public class Validate extends ValidForm{

    private static final Log LOG = new Log(ValidForm.class);

    public Validate(Bean bean) {
        super(bean);
        // TODO Auto-generated constructor stub
    }

    /**
     * 验证正整数
     * @param number
     * @param paras
     */
    public static void validNumber(Integer[] number, Object... paras){
        for(int i = 0;i<number.length;i++){
            if(number[i]<0)
                throw LOG.err(ErrMsgs.NumberErr,paras[i]);
        }
    }

    /**
     * 验证记录已存在
     * @param obj
     * @param paras
     */
    public static void validUniqueErr(Object[] obj,Object... paras){
        for (int i = 0;i<obj.length;i++) {
            if(obj[i] !=null)
                throw LOG.err(ErrMsgs.UniqueErr,paras[i]);
        }
    }
    
    /**
     * 验证数值不能为NULL
     * @param obj
     * @param paras
     */
    public static void validNotNull(Object[] obj,Object... paras){
    	for (int i = 0;i<obj.length;i++) {
            if(obj[i] ==null)
                throw LOG.err(ErrMsgs.UniqueErr,paras[i]);
        }
    }






    public enum ErrMsgs implements PubInfs.IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
        langError("【{0}】不符合要求，不可操作！{1}"),
        NotEmpty("【{0}】字段不能为空"),
        Msg("【{0}】字段不符合要求,{1}"),
        NumberErr("【{0}】必须为正整数"),
        UniqueErr("记录【{0}】已存在，不可操作！"),
        ;

        private String _msg;
        private ErrMsgs(String msg) {
            _msg = msg;
        }
        public String getMsg() {
            return _msg;
        }
    } //@formatter:on

}
