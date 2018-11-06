package irille.temporary.action;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.usr.UsrSupplierDAO.GtSup;
import irille.temporary.entity.BigLoveList;
import irille.temporary.entity.DataInfo;
import irille.temporary.entity.DataLogoData;
import irille.temporary.util.getNameUtil;
import irille.temporary.util.sendHttpsUtils;


public class BigLoveListAction extends HomeAction<DataLogoData>{
	private static final LogMessage LOG = new LogMessage(BigLoveListAction.class);
    private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void getRegListInfo() throws JSONException, Exception{
	String [] countryArray={"温州","丽水","台州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","温州","丽水","丽水","台州"};
	//String [] nameArray={"乡下闲人","李天王","LXL","小桥流水","心无奈","善良的我","寂静","六月","丁志杰","傅铁柱","一叶知秋","默默","月华","人生如梦","时光","安之奈何","小白","熙梦","张益达","杰哥哥","张伟","小布","手飞","超哥","月桂先生","面筋哥"};
	String [] operation={"成功注册大爱创！","成功创业"};
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	for (int i = 0; i < 20; i++) {
	BigLoveList bgl=new BigLoveList();	
	bgl.setName(getNameUtil.getChineseName());	
	bgl.setFromName(countryArray[getNameUtil.getNum(0,countryArray.length-1)]);
	bgl.setRegTime(sdf.format(new Date()));
	bgl.setOperation(operation[getNameUtil.getNum(0, operation.length-1)]);
	bgl.ins();	
	}
	SQL sql = new SQL(){{
		SELECT(BigLoveList.class);
		FROM(BigLoveList.class);
		ORDER_BY(BigLoveList.T.REG_TIME, "desc");
		LIMIT(0, 20);
	}};
	List<BigLoveList> bigList=Query.sql(sql).queryList(BigLoveList.class);
	Collections.shuffle(bigList);
	JSONObject json = new JSONObject();
	JSONArray ja = new JSONArray();
	JSONObject lineJson = null;

	for (BigLoveList biglove : bigList.subList(0,bigList.size())) {
		lineJson = crtJsonByBean(biglove);
		ja.put(lineJson);
	} 
	json.put(STORE_ROOT, ja);
	writerOrExport(json);
	}
	
	/**
	* 获取大爱创头像等信息
	 * @throws IOException 
	 * @throws JSONException 
	*/
	public void getHeadPicInfo() throws IOException, JSONException{
	 String urlA="https://www.shoeslogo.com/atdApi/rankIntegralPage?token=a0c4cc3862ddaacdb6005df309725eee";
	 //String urlB="https://www.shoeslogo.com/atdApi/rankEndowPage?token=a0c4cc3862ddaacdb6005df309725eee";
	 String urlB="https://www.shoeslogo.com/atdApi/rankEndowPage?token=a0c4cc3862ddaacdb6005df309725eee";
	 String headPicA=sendHttpsUtils.httpsRequest(urlA,"GET","");
	 String headPicB=sendHttpsUtils.httpsRequest(urlB,"GET","");
	 JSONObject json=new JSONObject();
	 json.put("rankIntegral",headPicA);
	 json.put("rankEndow",headPicB);
	 ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
	 ServletActionContext.getResponse().getWriter().print(json);			
	}
	
	@Override
	public void list() throws Exception {
		// TODO Auto-generated method stub
		super.list();
	}
	
	 /* private static Date randomDate(String beginDate, String endDate) {  
       try {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            Date start = format.parse(beginDate);// 构造开始日期  
            Date end = format.parse(endDate);// 构造结束日期  
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
            if (start.getTime() >= end.getTime()) {  
                return null;  
            }  
            long date = random(start.getTime(), end.getTime());  
  
            return new Date(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }*/
}