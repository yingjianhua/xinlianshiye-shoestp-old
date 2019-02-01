package irille.Service.Manage.O2O.Imp;

import irille.Dao.O2O.O2OMapDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Map;
import irille.Entity.O2O.O2O_Product;
import irille.Service.Manage.O2O.IO2OMapServer;
import irille.core.sys.Sys;
import irille.homeAction.pdt.dto.ProductInfoView;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.util.sendHttpsUtils;
import irille.view.O2O.O2OMapView;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.List;
import java.util.stream.Collectors;

public class O2OMapServerImp implements IO2OMapServer {
    private static final String google_map_url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";
    private static final String key = "AIzaSyCPbc3yNYQgVc56qbUuAY_Yap-uDMkDkvc";
    @Inject
    private O2OMapDao o2OMapDao;

    @Inject
    private O2OProductDao o2OProductDao;

    public JSONObject load(String address){
        if(null == address){
            throw new WebMessageException(ReturnCode.failure,"请输入地址");
        }
        try{
            URIBuilder builder = new URIBuilder(google_map_url);
            String param = URLDecoder.decode(address,"UTF-8");
            builder.addParameter("input",param);
            builder.addParameter("inputtype","textquery");
            builder.addParameter("fields","formatted_address,name,rating,geometry");
            builder.addParameter("key",key);
            String result = sendHttpsUtils.get(builder.build().toString(),"127.0.0.1",1080);
            return new JSONObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductInfoView findByEarliestPdt_PkeyAnd(FldLanguage.Language language, Integer pdt) {
        O2O_Product o2oPdt = o2OProductDao.findEarliestActByPdt_Pkey(pdt);
        ProductInfoView view = null;
        if(null != o2oPdt){
            view = new ProductInfoView();
            O2O_Activity activity = o2oPdt.gtActivityId();
            O2O_Map map = activity.gtAddress();
            O2OMapView mapView = O2OMapView.toView(language,map);
            view.setMap(mapView);
            view.setMin_oq(o2oPdt.getMinOq());
            view.setPrice(o2oPdt.getPrice());
        }
        return view;
    }

    public List<O2OMapView> list(){
        return o2OMapDao.list().stream().map(map->{
            O2OMapView view = new O2OMapView();
            view.setId(map.getPkey());
            view.setLatitude(map.getLatitude());
            view.setLongitude(map.getLongitude());
            view.setName(map.getName());
            return view;
        }).collect(Collectors.toList());
    }

    public void ins(O2OMapView view){
        O2O_Map map = new O2O_Map();
        map.setName(view.getName());
        map.setLongitude(view.getLongitude());
        map.setLatitude(view.getLatitude());
        map.setIsDelete(Sys.OYn.NO.getLine().getKey());
        translateUtil.autoTranslate(map);
        map.ins();
    }

    public static void main(String[] args) throws Exception {
        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";
        URIBuilder builder = new URIBuilder(url);
        builder.addParameter("input","wenzhou Museum");
        builder.addParameter("inputtype","textquery");
        builder.addParameter("fields","formatted_address,name,rating,geometry");
        builder.addParameter("key","AIzaSyCPbc3yNYQgVc56qbUuAY_Yap-uDMkDkvc");
        String result = sendHttpsUtils.get(builder.build().toString(),"127.0.0.1",1080);
        JSONObject json = new JSONObject(result);
        System.out.println(json);
    }

    public void del(Integer id){
        if (null == id)
            throw new WebMessageException(ReturnCode.failure,"请选择地址");
        O2O_Map map = BeanBase.chk(O2O_Map.class,id);
        if(null == map)
            throw new WebMessageException(ReturnCode.failure,"地址不存在");
        map.setIsDelete(Sys.OYn.YES.getLine().getKey());
        map.upd();
    }
}
