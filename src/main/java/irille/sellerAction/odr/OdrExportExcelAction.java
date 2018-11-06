package irille.sellerAction.odr;

import irille.pub.tb.IEnumFld;
import irille.sellerAction.SellerAction;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.usr.UsrPurchase;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/7/12
 * Time: 11:36
 */
public class OdrExportExcelAction extends SellerAction<OdrOrder> {

    public void export() throws IOException, JSONException {
        String[] flds = ServletActionContext.getRequest().getParameterValues("fld");
        if (flds == null) {
            writeSuccess();
            return;
        }
        String startDate = ServletActionContext.getRequest().getParameter("startDate");
        String endDate = ServletActionContext.getRequest().getParameter("endDate");
        OdrOrderDAO.other other = new OdrOrderDAO.other();
        getExcel(other.getExcelData(getSupplier().getPkey(), flds, startDate, endDate));
    }

    public enum efld {
        orderid(OdrOrder.T.ORDER_NUM),
        email(UsrPurchase.T.EMAIL, OdrOrder.T.PURCHASE),
        odrtime(OdrOrder.T.TIME),
        freightprice(OdrOrder.T.FREIGHT_PRICE),
        safeprice(OdrOrder.T.INSURANCE_PRICE),
        countprice(OdrOrder.T.PRICE_TOTAL),
        odrtpye(OdrOrder.T.TYPE),
        paytype(OdrOrder.T.PAY_TYPE),
        name(OdrOrder.T.NAME),
        address(OdrOrder.T.ADDRESS);

        private IEnumFld fld;
        private IEnumFld outkey;
        private boolean pk;

        efld(IEnumFld fld) {
            this.pk = false;
            this.fld = fld;
        }

        efld(IEnumFld fld, IEnumFld outkey) {
            this.fld = fld;
            this.outkey = outkey;
            this.pk = true;
        }

        public IEnumFld getOutkey() {
            return outkey;
        }

        public boolean isPk() {
            return pk;
        }

        public IEnumFld getFld() {
            return fld;
        }

        public String getName() {
            return fld.getFld().getName();
        }
    }

    private void getExcel(ByteArrayOutputStream in) {
        ServletActionContext.getResponse().setHeader("Content-disposition", "filename=" + new Date() + ".xlsx");
        ServletActionContext.getResponse().setContentType("text/plain");
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(in.toByteArray());
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

