package irille.shop.pdt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtAttr.T;
import irille.shop.plt.PltConfigDAO;
import irille.view.pdt.PdtProductVueView;

public class PdtAttrDAO {
    public static final Log LOG = new Log(PdtAttrDAO.class);

    public static class Ins extends IduInsLines<Ins, PdtAttr, PdtAttrLine> {

        @Override
        public void before() {
            super.before();
            getB().stCreateBy(getUser());
            getB().setCreateTime(Env.getTranBeginTime());
            getB().setDeleted(OYn.NO.getLine().getKey());
            translateUtil.autoTranslate(getB());
        }

        @Override
        public void after() {
            super.after();
            for (PdtAttrLine line : getLines()) {
            	line.setDeleted(OYn.NO.getLine().getKey());
                line.stCreateBy(getUser());
                line.setCreateTime(Env.getTranBeginTime());
                translateUtil.autoTranslate(line);
            }
            insLine(getB(), getLines(), PdtAttrLine.T.MAIN.getFld());
        }

    }

    public static class PageSelect extends IduOther<PageSelect, PdtAttr> {

        /**
         * @Description: 获取商品所有规格属性
         * @author lijie@shoestp.cn
         * @date 2018/8/22 17:05
         */
        public List getAllAttr(FldLanguage.Language language) {
            List result = new ArrayList();
            //不做分类查询
            String sql=PdtAttr.T.DELETED.getFld().getCodeSqlField()+" = "+ OYn.NO.getLine().getKey();
            PdtAttr.list(PdtAttr.class, sql, false).forEach(l -> {
                PdtProductVueView attr = new PdtProductVueView();
                translateUtil.getAutoTranslate(l, language);
                attr.setId(l.getPkey());
                attr.setName(l.getName());
                List lineList = new ArrayList();
                PdtAttrLine.list(PdtAttrLine.class, PdtAttrLine.T.MAIN + "=" + l.getPkey() + " AND "+PdtAttrLine.T.DELETED+" = "+OYn.NO.getLine().getKey(), false).forEach(ll -> {
                    translateUtil.getAutoTranslate(ll, language);
                    PdtProductVueView line = new PdtProductVueView();
                    line.setId(ll.getPkey());
                    line.setName(ll.getName());
                    lineList.add(line);
                });
                attr.setItems(lineList);
                result.add(attr);
            });
            return result;
        }
    }

    public static class Upd extends IduUpdLines<Upd, PdtAttr, PdtAttrLine> {
        @Override
        public void before() {
            super.before();
            try {
				JSONObject json = new JSONObject(getB().getName());
				String value = json.getString(PltConfigDAO.manageLanguage().name());
				getB().setName(value);
				PdtAttr dbBean = loadThisBeanAndLock();
				dbBean.setDeleted(Sys.OYn.NO.getLine().getKey());
	            PropertyUtils.copyProperties(dbBean, translateUtil.autoTranslate(getB()), T.NAME, T.CATEGORY);
	            setB(dbBean);
			} catch (JSONException e) {
				e.printStackTrace();
			}
            
        }

        @Override
        public void valid() {
            super.valid();

        }

        @Override
        public void after() {
            super.after();
            for (PdtAttrLine line : getLines()) {
                if (line.getPkey() == null) {
                	line.setDeleted(Sys.OYn.NO.getLine().getKey());
                    line.stCreateBy(getUser());
                    line.setCreateTime(Env.getTranBeginTime());
                }else{
                	line.setDeleted(Sys.OYn.NO.getLine().getKey());
                }
                line = translateUtil.autoTranslateByManageLanguage(line, true);
            }
            PdtAttrLineDAO.updByMain(getLines(), getB().getPkey());
//            updLine(getB(),getLines(), PdtAttrLine.T.MAIN.getFld());
        }

    }

    public static class Del extends IduUpd<Upd, PdtAttr> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
			PdtAttr dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
            List<PdtAttrLine> list=getLines(PdtAttrLine.T.MAIN, getB().getPkey());
            for (PdtAttrLine  pal: list) {
            	pal.setDeleted(OYn.YES.getLine().getKey());
            	pal.upd();
			}
        }
    }

}
