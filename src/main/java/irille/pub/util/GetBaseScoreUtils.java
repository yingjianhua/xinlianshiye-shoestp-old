package irille.pub.util;

import java.io.IOException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.view.SVS.SVSInfoView;

public class GetBaseScoreUtils {
	@Inject
	public static ObjectMapper om;

	/**
	 * 计算基础分
	 * 
	 * @param research
	 * @param productionCapacity
	 * @param realFactory
	 * @param productQuality
	 * @param tradeTeam
	 * @param exhibition
	 * @param partner
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static int getBaseScore(String research, String productionCapacity, String realFactory,
			String productQuality, String tradeTeam, String exhibition, String partner) throws Exception {
		int result = 0;
		SVSInfoView.research researchBean = om.readValue(research, SVSInfoView.research.class);
		if (researchBean.getCertificate() != null) {
			// 判断专利证书 1个证书累加1分，1个以上3之间累计3分，3个以上加6分
			String[] certificate = researchBean.getCertificate().split(",");
			if (certificate.length == 1)
				result += 1;
			if (certificate.length > 1 && certificate.length <= 3)
				result += 3;
			if (certificate.length > 3)
				result += 6;
		}
		if (researchBean.getIsTeam() != null) {
			// 有独立开发团队加4分
			if (researchBean.getIsTeam().intValue() == 1)
				result += 4;
		}
		if(researchBean.getNumOfShoes()!=null){
			if(researchBean.getNumOfShoes().intValue()<1000)
				result+=3;
			if(researchBean.getNumOfShoes().intValue()>1000&&researchBean.getNumOfShoes().intValue()<=1500)
			   result+=6;
			if(researchBean.getNumOfShoes().intValue()>1500)
			   result+=10;
			
		}
			

		return result;
	}
}
