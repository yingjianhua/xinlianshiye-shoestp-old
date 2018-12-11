package irille.sellerAction.eo;

import com.google.inject.Inject;
import irille.Dao.EO.EasyOdrDao;
import irille.Entity.EO.EasyOdr;
import irille.Service.Eo.EasyOdrService;
import irille.homeAction.HomeAction;
import irille.sellerAction.SellerAction;
import irille.view.EO.easyodrView;
import irille.view.EO.eolineView;
import irille.view.Page;
import irille.view.odr.OrderSearchView;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EasyOdrAction extends SellerAction<EasyOdr> {

    private OrderSearchView search;
    @Inject
    private EasyOdrDao easyOdrDao;
    public  void   newOrderlist() throws IOException {
        Page<easyodrView> page=easyOdrDao.sellerlist(getStart(),getLimit(),search,getSupplier().getPkey());
        write(page);
    }
    private Integer eastOdrId;
    private String  eastOdrIds;
    private String  path;
    /**
    *@Description:   导出单个订单表格
    *@date 2018/12/10 16:59
    *@anthor wilson zhang
    */
    public  void  oneexport() throws Exception{
        responseheader(easyOdrDao.oneexport(eastOdrId,path));
    }
    /**
     *@Description:   导出多个订单表格
     *@date 2018/12/10 16:59
     *@anthor wilson zhang
     */
    public void manyexports(){
        try {
            responseheader(easyOdrDao.manyexport(eastOdrIds,path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //导出当前供应商的全部订单表格
    public void manyexport(){
        String list=easyOdrDao.allOdr(getSupplier().getPkey());
        responseheader(easyOdrDao.manyexport(list,path));
    }

    private  void responseheader(ByteArrayOutputStream byteArrayOutputStream ){
        ServletActionContext.getResponse().setHeader("Content-disposition", "filename=" + new Date() + ".zip");
        ServletActionContext.getResponse().setContentType("text/plain");
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream( byteArrayOutputStream.toByteArray());
            ServletActionContext.getResponse().setHeader("Content-Length", String.valueOf(inputStream.available()));
            OutputStream outputStream = new BufferedOutputStream(ServletActionContext.getResponse().getOutputStream());
            byte[] bytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(bytes, 0, 1024)) != -1) {
                outputStream.write(bytes, 0, bytesRead);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
