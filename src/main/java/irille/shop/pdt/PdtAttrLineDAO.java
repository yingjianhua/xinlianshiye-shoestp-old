package irille.shop.pdt;

import java.util.List;

import irille.core.sys.Sys.OYn;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.shop.pdt.PdtAttrLine.T;

public class PdtAttrLineDAO {

	public static void updByMain(List<PdtAttrLine> list, Integer mainPkey) {
		String wheresql = T.MAIN + "=? and "+T.DELETED+"=?";
		List<PdtAttrLine> listOld = BeanBase.list(PdtAttrLine.class, wheresql, false, mainPkey, OYn.NO.getLine().getKey()); // 数据库旧数据
		boolean insFlag;
		for (PdtAttrLine formBean : (List<PdtAttrLine>) list) {
			formBean.setMain(mainPkey);
			insFlag = true; // 默认新增标志
			for (PdtAttrLine bean : listOld) {
				if (bean.equals(formBean)) {
					insFlag = false; // 修改标志
					PropertyUtils.copyWithoutCollection(bean, formBean);
					Idu.validFromOut(bean);
					bean.upd();
					break;
				}
			}
			if (insFlag) {
				formBean.setRowVersion((short) 0);
				Idu.validFromOut(formBean);
				formBean.ins();
			}
		}
		// 删除不存的数据
		for (PdtAttrLine bean : listOld) {
			if (list.contains(bean))
				continue;
			bean.stDeleted(true);
			bean.upd();
		}
	}
}
