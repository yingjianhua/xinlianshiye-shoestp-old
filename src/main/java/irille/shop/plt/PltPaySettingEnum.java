package irille.shop.plt;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/10/30
 * Time: 10:03
 */
public interface PltPaySettingEnum {

    enum Ooffline_Pay implements PltPaySettingEnum {
        ACCOUNT_NAME("account_name"),//结算开户人

        BANK_ACCOUNT("bank_account"),//银行账户
        //        DEPOS_IT_BANK("depos_it_bank"),//开户行
        DEPOS_IT_BANK_SUBBRANCH("depos_it_bank_subbranch"), //开户支行
        DEPOS_IT_BANK_SUBBRANCH_ASCRIPTION("depos_it_bank_subbranch_ascription") //开户支行归属地
        ;
        /**
         * 前端定义字段
         *
         * @author lijie@shoestp.cn
         * @Description:
         * @date 2018/8/6 9:47
         */
        private String fldName;


        Ooffline_Pay(String fldName) {
            this.fldName = fldName;
        }

        public String getFldName() {
            return fldName;
        }
    }

    enum TT_Pay implements PltPaySettingEnum {
        BENEFICIARYBANK("beneficiarybank"), //收款行
        SWIFTCODE("swiftcode"), //swift 号
        BRNEFICIARY("brneficiary"), //受益人 （公司名
        BANKADDRESS("bankaddress"), //银行开户地址
        BENEFICIARYACCOUNT("beneficiaryaccount"); //银行开户账户
        /**
         * 前端定义字段
         *
         * @author lijie@shoestp.cn
         * @Description:
         * @date 2018/8/6 9:42
         */
        private String fldName;

        TT_Pay(String fldName) {
            this.fldName = fldName;
        }

        public String getFldName() {
            return fldName;
        }
    }
}
