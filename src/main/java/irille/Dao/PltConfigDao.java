package irille.Dao;

import irille.pub.PropertyUtils;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduUpd;
import irille.shop.plt.PltConfig;

import java.util.HashMap;
import java.util.Map;
import irille.platform.plt.View.LanguageView;
import irille.pub.tb.FldLanguage;

import java.util.ArrayList;
import java.util.List;

import static irille.shop.plt.PltConfig.Variable.LanguageDefault;

public class PltConfigDao {
    public Map<Integer, Map<String, String>> getLanguageList() {
        SQL sql = new SQL();
        sql.SELECT(PltConfig.T.PKEY);
        sql.SELECT(PltConfig.T.VARIABLE);
        sql.SELECT(PltConfig.T.VALUE);
        sql.FROM(PltConfig.class);
        PltConfig pltConfig = Query.sql(sql).query(PltConfig.class);
        Map<String, String> map = new HashMap<>();
        Map<Integer, Map<String, String>> maps = new HashMap<>();
        if (pltConfig.getValue() != null) {
            map.put(pltConfig.getVariable(), pltConfig.getValue());
        }
        maps.put(pltConfig.getPkey(), map);
        return maps;
    }

    public List<LanguageView> getLanguages() {
        List<LanguageView> list = new ArrayList<>();
        for (FldLanguage.Language value : FldLanguage.Language.values()) {
            LanguageView view = new LanguageView();
            view.setDisplayName(value.displayName());
            view.setShortName(value.name());
            list.add(view);
        }
        return list;
    }

    public LanguageView getCurrentLanguage() {
        SQL sql = new SQL();
        sql.SELECT(PltConfig.class);
        sql.FROM(PltConfig.class);
        sql.WHERE(PltConfig.T.VARIABLE, "=?", LanguageDefault.toString());
        PltConfig pltConfig = Query.sql(sql).query(PltConfig.class);
        LanguageView view = new LanguageView();
        if (pltConfig.getValue() != null) {
            view.setShortName(pltConfig.getValue());
            view.setDisplayName(FldLanguage.Language.valueOf(pltConfig.getValue()).displayName());
        }
        return view;
    }
    public static class updlang extends IduUpd<updlang, PltConfig> {
        @Override
        public void before() {
            PltConfig dbbean =PltConfig.chkUniqueVariable(true, getB().getVariable());
            PropertyUtils.copyProperties(dbbean,getB(), PltConfig.T.VALUE);
            setB(dbbean);

        }
    }
}
