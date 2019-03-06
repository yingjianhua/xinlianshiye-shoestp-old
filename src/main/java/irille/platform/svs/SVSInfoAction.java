package irille.platform.svs;

import irille.Entity.SVS.SVSInfo;
import irille.action.ActionBase;
import lombok.Data;

@Data
public class SVSInfoAction extends ActionBase<SVSInfo> {

	// 修改对应认证信息
	void updAutInfo() {
	};

	// 获取对应用户SVS信息
	void getAutInfo() {
	};

	// 获取SVS认证信息列表
	void findAutInf() {
	}

	@Override
	public Class beanClazz() {
		// TODO Auto-generated method stub
		return SVSInfo.class;
	}
}
