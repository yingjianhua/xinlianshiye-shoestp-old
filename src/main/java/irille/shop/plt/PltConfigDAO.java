package irille.shop.plt;

import java.util.ArrayList;
import java.util.List;

import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltConfig.Variable;
import irille.shop.usr.UsrSupplier;
import irille.view.plt.LanguageView;

public class PltConfigDAO {
	
	public static List<LanguageView> listLanguageView() {
		String[] enabled = PltConfig.getVariable(Variable.Language).split(",");
		List<LanguageView> views = new ArrayList<>();
		for(Language L:Language.values()) {
			LanguageView view = new LanguageView();
			view.setShortName(L.name());
			view.setDisplayName(L.displayName());
			boolean f = false;
			for(String s:enabled) {
				if(s.equals(L.name())) {
					f = true;
					break;
				}
			}
			view.setIsEnabled(f);
			views.add(view);
		}
		return views;
	}
	
	public static Language manageLanguage() {
		return Language.valueOf(PltConfig.getVariable(Variable.MangeLanguage));
	}
	
	public static Language supplierLanguage(Integer supplier) {
		return Language.valueOf(PltConfig.getVariable(Variable.MangeLanguage));
	}
	public static Language supplierLanguage(UsrSupplier supplier) {
		return Language.valueOf(PltConfig.getVariable(Variable.MangeLanguage));
	}
	
	public static class InsInit extends IduOther<InsInit, PltConfig> {
		@Override
		public void run() {
			super.run();
		}
	}
	
	public static class Ins extends IduIns<Ins, PltConfig> {
		@Override
		public void run() {
		}
	}
	
	public static class Upd extends IduUpd<Upd, PltConfig> {
		@Override
		public void before() {
		}
		@Override
		public void run() {
			try {
				PltConfig.setVariable(getB().getValue(), PltConfig.Variable.valueOf(getB().getVariable()));
			} catch(IllegalArgumentException e) {
			}
		}
	}
	
	public static class Del extends IduDel<Del, PltConfig> {
		@Override
		public void run() {
		}
	}

}
