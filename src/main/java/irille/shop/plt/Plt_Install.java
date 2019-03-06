package irille.shop.plt;

import irille.core.sys.SysMenuDAO;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanBase;
import irille.pub.bean.InstallBase;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltConfig.Variable;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Plt_Install extends InstallBase {

    public static final Plt_Install INST = new Plt_Install();

    @Override
    public void initMenu(SysMenuDAO m) {
        m.proc(PltErate.TB, 20, null);
        m.proc(PltPay.TB, 30, null);
        m.proc(PltCountry.TB, 40, null);
        m.proc(PltFreight.TB, 50, null);
        m.proc(PltConfig.TB, "基础设置", "plt", 60, null);
        m.proc(PltTrantslate.TB, 70, null);
    }

    @Override
    public void installAfter() {
        super.installAfter();
        SysUser lu = new SysUser();
        lu.setPkey(1);
        LoginUserMsg msg = new LoginUserMsg(lu, (byte) 0, null, null, null);
        Env.INST.initTran(msg, null);
        initPltErate();
        initPltCountry();
        initPltConfig();
    }

    private void initPltErate() {
        if (BeanBase.list(PltErate.class, null, false).size() > 0)
            return;
        PltErateDAO.InsInit ins = new PltErateDAO.InsInit();
        List<PltErate> list = new ArrayList<>();
        list.add(PltErateDAO.InsInit.build("", "USD", "$", true, true, BigDecimal.valueOf(1.0000), true, BigDecimal.valueOf(1.0000),1));
        list.add(PltErateDAO.InsInit.build("", "EUR", "€", false, false, BigDecimal.valueOf(0.8010), false, BigDecimal.valueOf(0.8010),1));
        list.add(PltErateDAO.InsInit.build("", "GBP", "￡", false, false, BigDecimal.valueOf(06330), false, BigDecimal.valueOf(0.6330),1));
        list.add(PltErateDAO.InsInit.build("", "CAD", "CA$", false, false, BigDecimal.valueOf(1.1260), false, BigDecimal.valueOf(1.1260),1));
        list.add(PltErateDAO.InsInit.build("", "AUD", "AU$", false, false, BigDecimal.valueOf(1.1650), false, BigDecimal.valueOf(1.1650),1));
        list.add(PltErateDAO.InsInit.build("", "CHF", "CHF", false, false, BigDecimal.valueOf(0.9620), false, BigDecimal.valueOf(0.9620),1));
        list.add(PltErateDAO.InsInit.build("", "HKD", "HK$", false, false, BigDecimal.valueOf(7.7520), false, BigDecimal.valueOf(7.7520),1));
        list.add(PltErateDAO.InsInit.build("", "JPY", "￥", false, false, BigDecimal.valueOf(117.4750), false, BigDecimal.valueOf(117.4750),1));
        list.add(PltErateDAO.InsInit.build("", "RUB", "p.", false, false, BigDecimal.valueOf(47.7350), false, BigDecimal.valueOf(47.7350),1));
        list.add(PltErateDAO.InsInit.build("", "BRL", "R$", false, false, BigDecimal.valueOf(2.5036), false, BigDecimal.valueOf(2.5036),1));
        list.add(PltErateDAO.InsInit.build("", "CLP", "$", false, false, BigDecimal.valueOf(599.1600), false, BigDecimal.valueOf(599.1600),1));
        list.add(PltErateDAO.InsInit.build("", "NOK", "kr.", false, false, BigDecimal.valueOf(6.8741), false, BigDecimal.valueOf(6.8741),1));
        list.add(PltErateDAO.InsInit.build("", "DKK", "kr.", false, false, BigDecimal.valueOf(5.9646), false, BigDecimal.valueOf(5.9646),1));
        list.add(PltErateDAO.InsInit.build("", "SEK", "kr.", false, false, BigDecimal.valueOf(7.4184), false, BigDecimal.valueOf(7.4184),1));
        list.add(PltErateDAO.InsInit.build("", "KRW", "₩", false, false, BigDecimal.valueOf(1099.4000), false, BigDecimal.valueOf(1099.4000),1));
        list.add(PltErateDAO.InsInit.build("", "ILS", "₪", false, false, BigDecimal.valueOf(3.8784), false, BigDecimal.valueOf(3.8784),1));
        list.add(PltErateDAO.InsInit.build("", "MXN", "$", false, false, BigDecimal.valueOf(13.7249), false, BigDecimal.valueOf(13.7249),1));
        ins.setList(list);
        ins.commit();
    }

    private void initPltCountry() {
        if (BeanBase.list(PltCountry.class, null, false).size() > 0)
            return;
        PltCountryDAO.InsInit ins = new PltCountryDAO.InsInit();
        List<PltCountry> list = new ArrayList<>();
        list.add(PltCountryDAO.InsInit.build("Afghanistan", "AF", "+93", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Albania", "AL", "+355", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Algeria", "DZ", "+213", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("American Samoa", "AS", "+1684", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Andorra", "AD", "+376", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Angola", "AO", "+244", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Anguilla", "AI", "+1264", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Antigua and Barbuda", "AG", "+1268", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Argentina", "AR", "+54", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Armenia", "AM", "+374", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Aruba", "AW", "+297", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Australia", "AU", "+61", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Austria", "AT", "+43", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Azerbaijan", "AZ", "+994", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bahamas", "BS", "+1242", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bahrain", "BH", "+973", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bangladesh", "BD", "+880", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Barbados", "BB", "+1246", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Belarus", "BY", "+375", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Belgium", "BE", "+32", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Belize", "BZ", "+501", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Benin", "BJ", "+229", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bermuda", "BM", "+1441", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bhutan", "BT", "+975", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bolivia", "BO", "+591", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bosnia and Herzegovina", "BA", "+387", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Botswana", "BW", "+267", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bouvet Island", "BV", "+0", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Brazil", "BR", "+55", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("British Indian Ocean Territory", "IO", "+246", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Brunei Darussalam", "BN", "+673", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Bulgaria", "BG", "+359", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Burkina Faso", "BF", "+226", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Burundi", "BI", "+257", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cambodia", "KH", "+855", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cameroon", "CM", "+237", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Canada", "CA", "+1", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cape Verde", "CV", "+238", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cayman Islands", "KY", "+1345", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Central African Republic", "CF", "+236", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Chad", "TD", "+235", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Chile", "CL", "+56", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("China", "CN", "+86", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Christmas Island", "CX", "+61", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cocos Islands", "CC", "+672", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Colombia", "CO", "+57", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Comoros", "KM", "+269", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Congo", "CG", "+242", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cook Islands", "CK", "+682", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Costa Rica", "CR", "+506", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cote D'ivoire", "KT", "+225", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cuba", "CU", "+53", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Cyprus", "CY", "+357", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Czech Republic", "CZ", "+420", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Democratic People's Republic of Korea", "KP", "+850", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Democratic Republic of the Congo", "CD", "+243", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Denmark", "DK", "+45", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Djibouti", "DJ", "+253", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Dominica", "DO", "+1767", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("East Timor", "TL", "+670", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Ecuador", "EC", "+593", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Egypt", "EG", "+20", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("El Salvador", "SV", "+503", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Equatorial Guinea", "GQ", "+240", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Eritrea", "ER", "+291", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Estonia", "EE", "+372", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Ethiopia", "ET", "+251", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Falkland Islands", "FK", "+500", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Faroe Islands", "FO", "+298", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Fiji", "FJ", "+679", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Finland", "FI", "+358", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("France", "FR", "+33", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("French Guiana", "GF", "+594", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("French Polynesia", "PF", "+689", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("French Southern Territories", "TF", "+0", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Gabon", "GA", "+241", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Gambia", "GM", "+220", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Georgia", "GE", "+995", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Germany", "DE", "+49", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Ghana", "GH", "+233", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Gibraltar", "GI", "+350", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Greece", "GR", "+30", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Greenland", "GL", "+299", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Grenada", "GD", "+1473", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Guadeloupe", "GP", "+0", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Guam", "GU", "+1671", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Guatemala", "GT", "+502", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Guinea", "GN", "+224", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Guinea-Bissau", "GW", "+245", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Guyana", "GY", "+592", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Haiti", "HT", "+509", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Heard Island and Mcdonald Islands", "HM", "+0", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Honduras", "HN", "+504", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Hong Kong, China", "HK", "+852", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Hungary", "HU", "+36", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Iceland", "IS", "+354", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("India", "IN", "+91", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Indonesia", "ID", "+62", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Iran", "IR", "+98", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Iraq", "IQ", "+964", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Ireland", "IE", "+353", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Israel", "IL", "+972", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Italy", "IT", "+39", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Jamaica", "JM", "+1876", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Japan", "JP", "+81", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Jordan", "JO", "+962", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Kazakhstan", "KZ", "+7", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Kenya", "KE", "+254", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Kiribati", "KI", "+686", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Korea", "KR", "+82", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Kuwait", "KW", "+965", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Kyrgyzstan", "KG", "+996", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Laos", "LA", "+856", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Latvia", "LV", "+371", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Lebanon", "LB", "+961", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Lesotho", "LS", "+266", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Liberia", "LR", "+231", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Liechtenstein", "LI", "+423", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Lithuania", "LT", "+370", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Luxembourg", "LU", "+352", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Macau, China", "MO", "+853", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Macedonia", "MK", "+389", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Madagascar", "MG", "+261", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Malawi", "MW", "+265", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Malaysia", "MY", "+60", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Maldives", "MV", "+960", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Mali", "ML", "+223", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Malta", "MT", "+356", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Marshall Islands", "MH", "+692", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Martinique", "MQ", "+596", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Mauritania", "MR", "+222", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Mauritius", "MU", "+230", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Mayotte", "YT", "+269", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Mexico", "MX", "+52", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Micronesia", "FM", "+691", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Moldova", "MD", "+373", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Monaco", "MC", "+377", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Mongolia", "MN", "+976", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Montserrat", "MS", "+1664", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Morocco", "MA", "+212", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Mozambique", "MZ", "+258", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Myanmar", "MM", "+95", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Namibia", "NA", "+264", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Nauru", "NR", "+674", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Nepal", "NP", "+977", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Netherlands", "NL", "+31", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("New Caledonia", "NC", "+687", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("New Zealand", "NZ", "+64", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Nicaragua", "NI", "+505", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Niger", "NE", "+227", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Nigeria", "NG", "+234", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Niue", "NU", "+683", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Norfolk Island", "NF", "+672", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Northern Mariana Islands", "MP", "+1670", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Norway", "NO", "+47", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Oman", "OM", "+968", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Pakistan", "PK", "+92", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Palau", "PW", "+680", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Palestine", "PS", "+970", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Panama", "PA", "+507", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Papua New Guinea", "PG", "+675", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Paraguay", "PY", "+595", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Peru", "PE", "+51", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Philippines", "PH", "+63", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Pitcairn Islands", "PN", "+64", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Poland", "PL", "+48", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Portugal", "PT", "+351", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Puerto Rico", "PR", "+1787", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Qatar", "QA", "+974", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Reunion", "RE", "+262", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Romania", "RO", "+40", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Russia", "RU", "+7", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Rwanda", "RW", "+250", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Saint Helena", "SH", "+247", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Saint Kitts and Nevis", "KN", "+1869", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Saint Lucia", "LC", "+1758", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Saint Pierre and Miquelon", "PM", "+508", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Saint Vincent and the Grenadines", "VC", "+784", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("San Marino", "SM", "+378", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Sao Tome and Principe", "ST", "+239", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Saudi Arabia", "SA", "+966", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Senegal", "SN", "+221", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Serbia", "RS", "+381", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Seychelles", "SC", "+248", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Sierra Leone", "SL", "+232", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Singapore", "SG", "+65", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Slovakia", "SK", "+421", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Slovenia", "SI", "+386", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Socialist Republic of Vietnam", "VN", "+84", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Solomon Islands", "SB", "+677", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Somalia", "SO", "+252", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("South Africa", "ZA", "+27", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("South Georgia and The South Sandwich Islands", "GS", "+0", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Spain", "ES", "+34", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Sri Lanka", "LK", "+94", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("State of Libya", "LY", "+218", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Sudan", "SD", "+249", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Suriname", "SR", "+597", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Svalbard and Jan Mayen", "SJ", "+0", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Swaziland", "SZ", "+268", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Sweden", "SE", "+46", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Switzerland", "CH", "+41", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Syrian Arab Republic", "SY", "+963", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("TaiWan, China", "TW", "+886", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Tajikistan", "TJ", "+992", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Tanzania", "TZ", "+255", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Thailand", "TH", "+66", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("The Republic of Croatia", "HR", "+385", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Togo", "TG", "+228", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Tokelau", "TK", "+690", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Tonga", "TO", "+676", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Trinidad and Tobago", "TT", "+1868", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Tunisia", "TN", "+216", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Turkey", "TR", "+90", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Turkmenistan", "TM", "+993", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Turks and Caicos Islands", "TC", "+1649", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Tuvalu", "TV", "+688", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Uganda", "UG", "+256", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Ukraine", "UA", "+380", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("United Arab Emirates", "AE", "+971", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("United Kingdom", "GB", "+44", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("United States", "US", "+1", null, null, true, false, true));
        list.add(PltCountryDAO.InsInit.build("United States Minor Outlying Islands", "UM", "+0", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Uruguay", "UY", "+598", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("US Virgin Islands", "VI", "+1340", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Uzbekistan", "UZ", "+998", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Vanuatu", "VU", "+678", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Vatican City State", "VA", "+379", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Venezuela", "VE", "+58", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Wallis and Futuna Islands", "WF", "+681", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Western Sahara", "EH", "+210", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Western Samoa", "WS", "+685", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Yemen", "YE", "+967", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Zambia", "ZM", "+260", null, null, true, false, false));
        list.add(PltCountryDAO.InsInit.build("Zimbabwe", "ZW", "+263", null, null, true, false, false));

        ins.setList(list);
        ins.commit();
    }

    private void initPltConfig() {
        if (BeanBase.list(PltConfig.class, null, false).size() > 0)
            return;
        PltConfig.setVariable(Language.zh_TW + "," + Language.en, Variable.Language);
        PltConfig.setVariable(Language.en.name(), Variable.LanguageDefault);
        PltConfig.setVariable(Language.zh_TW.name(), Variable.MangeLanguage);
    }

    public static void main(String[] args) throws IOException {
        File f = new File("D:/country.txt");
        PrintWriter pw = new PrintWriter(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a;
        try {
            while ((a = br.readLine()) != null && !a.equals("end")) {
                String aas = a.substring(a.indexOf(" ") + 1, a.lastIndexOf(" "));
                String name = aas.substring(0, aas.lastIndexOf(" "));
                String shortName = aas.substring(aas.lastIndexOf(" ") + 1);
                String zone = a.substring(a.lastIndexOf(" ") + 1);

                pw.println("list.add(PltCountryDAO.InsInit.build(\"" + name + "\", \"" + shortName + "\", \"" + zone + "\", null, null, true, false, false));");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.close();
            br.close();
        }
    }
}

