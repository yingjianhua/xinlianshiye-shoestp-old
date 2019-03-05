package irille.Dao.SVS.impl;

import java.io.IOException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Dao.SVS.SVSInfoService;
import irille.view.SVS.SVSInfoView;

public class SVSInfoServiceImpl implements SVSInfoService {
	@Inject
	ObjectMapper om;
	
	/**
	 * 申请认证SVS
	 * @author GS
	 * @param research           研发能力信息
	 * @param productionCapacity 生产能力信息
	 * @param realFactory        工厂信息
	 * @param productQuality     产品质量信息
	 * @param tradeTeam          外贸团队信息
	 * @param exhibition         参加的展会信息
	 * @param partner            合作商信息
	 */
	@Override
	public void applicationForCertification(String research, String productionCapacity, String realFactory,
			String productQuality, String tradeTeam, String exhibition, String partner) {
		 try {
			 SVSInfoView.research	 re=om.readValue("", SVSInfoView.research.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
