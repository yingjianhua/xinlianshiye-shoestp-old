package irille.shop.usr;

import irille.platform.usr.View.UsrMainView;
import irille.pub.LogMessage;
import irille.pub.bean.Bean;
import irille.pub.bean.sql.SQL;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.view.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsrMainDAO {
    public static final LogMessage LOG = new LogMessage(UsrMain.class);


    /**
     * @Description: 获取供应商注册信息-卖家
     * @author: lingjian
     * @Date: 2019/3/7 10:15
     */
    public static Page getRegistList(Integer start, Integer limit,String company, String email){
        SQL sql=new SQL(){{
            SELECT(UsrMain.class).FROM(UsrMain.class).WHERE(UsrMain.T.IDENTITY,"=1");
            if(company !=null) {
                WHERE(UsrMain.T.COMPANY, "like '%" + company + "%'");
            }
            if(email !=null){
                WHERE(UsrMain.T.EMAIL,"like '%"+email+"%'");
            }
        }};
        Integer count= irille.pub.bean.Query.sql(sql).queryCount();
        List<UsrMainView> list= irille.pub.bean.Query.sql(sql.LIMIT(start,limit)).queryMaps().stream().map(bean->new UsrMainView(){{
            setPkey((Integer)bean.get(UsrMain.T.PKEY.getFld().getCodeSqlField()));
            setEmail((String) bean.get(UsrMain.T.EMAIL.getFld().getCodeSqlField()));
            setNickName((String) bean.get(UsrMain.T.NICKNAME.getFld().getCodeSqlField()));
            setCompany((String) bean.get(UsrMain.T.COMPANY.getFld().getCodeSqlField()));
            setCountry(Bean.load(PltCountry.class,(Integer)bean.get(UsrMain.T.COUNTRY.getFld().getCodeSqlField())).getName());
            setProvince(Bean.load(PltProvince.class,(Integer)bean.get(UsrMain.T.PROVINCE.getFld().getCodeSqlField())).getName());
            setAddress((String) bean.get(UsrMain.T.ADDRESS.getFld().getCodeSqlField()));
            setContacts((String) bean.get(UsrMain.T.CONTACTS.getFld().getCodeSqlField()));
            setTelphone((String) bean.get(UsrMain.T.TELPHONE.getFld().getCodeSqlField()));
            setRegTime((Date) bean.get(UsrMain.T.REG_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return  new Page(list,start,limit,count);
    }

    /**
     * 根据id获取供应商注册信息
     * @author: lingjian
     * @Date: 2019/3/7 13:41
     * @param pkey
     * @return
     */
    public static List<UsrMainView> getRegistById(String pkey){
        SQL sql=new SQL(){{
            SELECT(UsrMain.class).FROM(UsrMain.class).WHERE(UsrMain.T.PKEY,"=?",pkey);
        }};
        List<UsrMainView> list= irille.pub.bean.Query.sql(sql).queryMaps().stream().map(bean->new UsrMainView(){{
            setPkey((Integer)bean.get(UsrMain.T.PKEY.getFld().getCodeSqlField()));
            setEmail((String) bean.get(UsrMain.T.EMAIL.getFld().getCodeSqlField()));
            setNickName((String) bean.get(UsrMain.T.NICKNAME.getFld().getCodeSqlField()));
            setCompany((String) bean.get(UsrMain.T.COMPANY.getFld().getCodeSqlField()));
            setCountry(Bean.load(PltCountry.class,(Integer)bean.get(UsrMain.T.COUNTRY.getFld().getCodeSqlField())).getName());
            setProvince(Bean.load(PltProvince.class,(Integer)bean.get(UsrMain.T.PROVINCE.getFld().getCodeSqlField())).getName());
            setAddress((String) bean.get(UsrMain.T.ADDRESS.getFld().getCodeSqlField()));
            setContacts((String) bean.get(UsrMain.T.CONTACTS.getFld().getCodeSqlField()));
            setTelphone((String) bean.get(UsrMain.T.TELPHONE.getFld().getCodeSqlField()));
            setRegTime((Date) bean.get(UsrMain.T.REG_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return list;
    }
}
